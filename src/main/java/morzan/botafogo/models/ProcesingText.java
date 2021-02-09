package morzan.botafogo.models;

import com.basistech.rosette.api.MorphologicalFeature;
import com.basistech.rosette.api.RosetteAPI;
import com.basistech.rosette.api.RosetteAPIException;
import com.basistech.rosette.apimodel.EntitiesResponse;
import com.basistech.rosette.apimodel.EntityMention;
import com.basistech.rosette.apimodel.MorphologyResponse;
import com.basistech.rosette.apimodel.Response;
import com.basistech.rosette.apimodel.SentimentResponse;
import com.basistech.rosette.apimodel.jackson.ApiModelMixinModule;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import morzan.botafogo.beans.Lexiword;
import morzan.botafogo.beans.NumbResults;
import morzan.botafogo.beans.NumberValues;
import morzan.botafogo.beans.PosWord;
import morzan.botafogo.beans.ProcesedWord;
import morzan.botafogo.snowball.MethodsStemmer;

/**
 *
 * @author EQ
 */
public class ProcesingText {

    private static final String KEY_PROP_NAME = "c21fd4214e81308af3f89a9b9a84e041";
    private static final String URL_PROP_NAME = "rosette.api.altUrl";

    ReadingLexicon readingLexicon = ReadingLexicon.getInstance();
    TranslatingGlosbe translatingGlosbe = TranslatingGlosbe.getInstance();
    List<Lexiword> lexiwords = new ArrayList<>();

    public NumbResults master(String text) throws IOException, RosetteAPIException {
        List<PosWord> pos = this.posText(text);
        List<String> conjunctions = this.removingConjuctions(pos);
        double confidence = this.getSentiments(text);
        List<String> lemmas = this.onlyLemmas(text, conjunctions);
        Map<String, List<String>> engLemmas = this.getEngSynsForSentence(lemmas);
        if (lexiwords == null || lexiwords.isEmpty()) {
            lexiwords = this.callingLexicon();
        }
        List<String> stems = this.stemmingLemma(lemmas);
        List<ProcesedWord> procesedWords = this.joiningThreeThings(lemmas, engLemmas, stems);

        double punctuation = 0;
        float posPunct = 0;
        float negPunct = 0;
        float neuPunct = 0;
        
        int posCount = 0;
        int negCount = 0;
        int neuCount = 0;
        int c = 0;

        for (ProcesedWord compilado : procesedWords) {
            NumberValues results = getValueFromLexicon(compilado.getStem(), compilado.getLemma(), lexiwords, compilado.getEngSyns());
            punctuation += results.getPunctuation();
            posPunct += results.getPosPunct();
            negPunct += results.getNegPunct();
            neuPunct += results.getNeutralPunct();
            posCount += results.getPosCount();
            negCount += results.getNegCount();
            neuCount += results.getNeuCount();
            c++;
        }
        
        System.out.println("*************************************************");
        System.out.println("Valor de negPunct = " + negPunct);
        System.out.println("Valor de neuPunct = " + neuPunct);
        System.out.println("Valor de posPunct = " + posPunct);
        System.out.println("Valor de negCount = " + negCount);
        System.out.println("Valor de neuCount = " + neuCount);
        System.out.println("Valor de posCount = " + posCount);
        System.out.println("Valor de puntuación final = " + punctuation);
        double moodEstimate = this.estimateMood(punctuation, c);
        System.out.println("valor de herramienta: " + moodEstimate);
        System.out.println("valor de Rosette API: " + confidence);
        double piePos = (posPunct*360/punctuation);
        double pieNeg = (negPunct*360/punctuation);
        double pieNeu = (neuPunct*360/punctuation);
        System.out.println("% de emociones positivas: " + piePos);
        System.out.println("% de emociones negativas: " + pieNeg);
        System.out.println("% de emociones neutras: " + pieNeu);
        
        NumbResults finale = new NumbResults(piePos, pieNeg, pieNeu);
        finale.setEstimate(moodEstimate);
        return finale;
    }

    public List<PosWord> posText(String text) throws IOException, RosetteAPIException {
        // creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing, and coreference resolution 
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse");
        props.setProperty("tokenize.language", "es");
        props.setProperty("pos.model", "edu/stanford/nlp/models/pos-tagger/spanish/spanish-distsim.tagger");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        List<PosWord> poss = new ArrayList<>();
    // read some text from the file..
        //File inputFile = new File("src/test/resources/sample-content.txt");
        //String text = Files.toString(inputFile, Charset.forName("UTF-8"));

        // create an empty Annotation just with the given text
        Annotation document = new Annotation(text);

        // run all Annotators on this text
        pipeline.annotate(document);

        // these are all the sentences in this document
        // a CoreMap is essentially a Map that uses class objects as keys and has values with custom types
        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);

        for (CoreMap sentence : sentences) {
            // traversing the words in the current sentence
            // a CoreLabel is a CoreMap with additional token-specific methods
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                // this is the text of the token
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                // this is the POS tag of the token
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                // this is the NER label of the token
                String ne = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);

                System.out.println("word: " + word + " pos: " + pos + " ne:" + ne);

                /*if (cleanPoints(word)==false){
                 words.add(word);
                 }*/
                if (pos.charAt(0) != 'f') {
                    poss.add(new PosWord(word, pos));
                    //System.out.println("agregado: " + word + " - " + pos);
                }
            }

            // this is the parse tree of the current sentence
      /*Tree tree = sentence.get(TreeCoreAnnotations.TreeAnnotation.class);
             System.out.println("parse tree:\n" + tree);
      
             // this is the Stanford dependency graph of the current sentence
             SemanticGraph dependencies = sentence.get(CollapsedCCProcessedDependenciesAnnotation.class);
             System.out.println("dependency graph:\n" + dependencies);
             }
      
             */
        }

//        getSynAntForTokens(poss);
        return poss;
    }

    public double getSentiments(String text) throws RosetteAPIException, IOException {
        RosetteAPI rosetteAPI = new RosetteAPI.Builder().apiKey(KEY_PROP_NAME).build();
        SentimentResponse response = rosetteAPI.getSentiment(text);
        //se tiene un arreglo de lemmas con lemmas.getLemmas();
        System.out.println(responseToJson(response));
        return response.getDocument().getConfidence();
    }

    public List<String> getLemmas(String text) throws IOException, RosetteAPIException {
        RosetteAPI rosetteAPI = new RosetteAPI.Builder().apiKey(KEY_PROP_NAME).build();
        MorphologyResponse lemmas = rosetteAPI.getMorphology(MorphologicalFeature.LEMMAS, text);
        System.out.println(responseToJson(lemmas));
        return lemmas.getLemmas();
    }

    public List<String> getEntities(String text) throws IOException, RosetteAPIException {
        RosetteAPI rosetteAPI = new RosetteAPI.Builder().apiKey(KEY_PROP_NAME).build();
        EntitiesResponse entities = rosetteAPI.getEntities(text);
        System.out.println(responseToJson(entities));
        List<String> entity = new ArrayList<String>();
        for (EntityMention mention : entities.getEntities()) {
            entity.add(mention.getMention());
        }
        return entity;
    }

    public List<String> onlyLemmas(String text, List<String> conjunctions) throws RosetteAPIException, IOException {
        List<String> entities = this.getEntities(text);
        List<String> lemmas = this.getLemmas(text);
        Iterator<String> iterator = lemmas.iterator();
        while (iterator.hasNext()) {
            String lemma = iterator.next();
            //System.out.println(lemma);
            if (lemma.equals(".") || lemma.equals(",") || lemma.equals(":") || lemma.equals(";") || lemma.equals("!")) {
                iterator.remove();
            } else {
                if (!conjunctions.isEmpty() || conjunctions!=null) {
                    for (String c : conjunctions) {
                        if (lemma.equalsIgnoreCase(c)) {
                            iterator.remove();
                        }
                    }
                }

            }

//            while (entities.size()>0){
//                for(String entity: entities){
//                    if(lemma.equalsIgnoreCase(entity)){
//                        iterator.remove();
//                    }
//                }
//            }
        }
        for (String lemma : lemmas) {
            System.out.println(lemma);
        }
        return lemmas;
    }

    protected List<String> removingConjuctions(List<PosWord> pos){
        List<String> conjunctions = new ArrayList<>();
        for (PosWord p: pos){
            if(p.getWordtype().equalsIgnoreCase("cs")){
                conjunctions.add(p.getWord());
            }
        }
        return conjunctions;
    }
    
    protected Map<String, List<String>> getEngSynsForSentence(List<String> spa_lemmas) {
        Map<String, List<String>> eSynSentence = new HashMap<>();
        List<String> listSyns = new ArrayList<>();

        for (String lemma : spa_lemmas) {
            listSyns = translatingGlosbe.getTranslatingSynonyms(lemma);
            eSynSentence.put(lemma, listSyns);
        }
        return eSynSentence;
    }

    public List<Lexiword> callingLexicon() throws RosetteAPIException, IOException {
        List<Lexiword> lexiwords = readingLexicon.readLexicon();
        if (lexiwords != null) {
            System.out.println("NO es nulo");
//            for (Lexiword lex: lexiwords){
//                System.out.println(lex.getWord() + "\t " + lex.getMean() + "\t " + lex.getStem());
//            }
        } else {
            System.out.println("SI es nulo");
        }
        return lexiwords;
    }

    protected List<String> stemmingLemma(List<String> lemmas) {
        MethodsStemmer stemmer = new MethodsStemmer();
        List<String> stemsFromLemmas = new ArrayList<>();
        for (String lemma : lemmas) {
            //System.out.println(stemmer.getStem(lemma));
            stemsFromLemmas.add(stemmer.getStem(lemma));
        }
        return stemsFromLemmas;
    }

    public List<ProcesedWord> joiningThreeThings(List<String> lemmas, Map<String, List<String>> engLemmas, List<String> stems) {
        List<ProcesedWord> procesedWords = new ArrayList<>();
        for (int i = 0; i < lemmas.size(); i++) {
            String spa_lemmas = lemmas.get(i);
            ProcesedWord composedWord = new ProcesedWord();
            composedWord.setLemma(spa_lemmas);
            composedWord.setEngSyns(engLemmas.get(spa_lemmas));
            composedWord.setStem(stems.get(i));
            procesedWords.add(composedWord);
        }
        System.out.println("Se creó con éxito");
        return procesedWords;
    }

    public NumberValues getValueFromLexicon(String stem, String lemma, List<Lexiword> lexiwords, List<String> engLemmas) {
        double punctuation = 0.0d;
        boolean encontrado = false;
        float aux = 0f;
        NumberValues results = new NumberValues();
        float posPunct = 0;
        float negPunct = 0;
        float neuPunct = 0;
        int posCount = 0;
        int negCount = 0;
        int neuCount = 0;

        for (Lexiword lexword : lexiwords) {
            if (lexword.getWord().equalsIgnoreCase(lemma)) {
                System.out.println(lexword.getMean() + " " + lexword.getWord() + " " + lemma);
                if (lexword.getMean() < 3) {
                    negPunct += lexword.getMean();
                    negCount++;
                    System.out.println("Valor de negPunct = " + negPunct);
                } else if (lexword.getMean() >= 3 && lexword.getMean() < 6) {
                    neuPunct += lexword.getMean();
                    neuCount++;
                    System.out.println("Valor de negPunct = " + neuPunct);
                } else if (lexword.getMean() >= 6) {
                    posPunct += lexword.getMean();
                    posCount++;
                    System.out.println("Valor de negPunct = " + posPunct);
                }
                punctuation += lexword.getMean();
                System.out.println(punctuation + " " + stem);
                encontrado = true;
                break;
            } else {
                if (lexword.getStem().equalsIgnoreCase(stem)) {
                System.out.println(lexword.getMean() + " " + lexword.getWord() + " " + lexword.getStem());
                if (lexword.getMean() < 3) {
                    negPunct += lexword.getMean();
                    negCount++;
                    System.out.println("Valor de negPunct = " + negPunct);
                } else if (lexword.getMean() >= 3 && lexword.getMean() < 6) {
                    neuPunct += lexword.getMean();
                    neuCount++;
                    System.out.println("Valor de negPunct = " + neuPunct);
                } else if (lexword.getMean() >= 6) {
                    posPunct += lexword.getMean();
                    posCount++;
                    System.out.println("Valor de negPunct = " + posPunct);
                }
                punctuation += lexword.getMean();
                System.out.println(punctuation + " " + stem);
                encontrado = true;
            }
            }
        }

        if (encontrado == false && punctuation == 0.0d) {
            for (Lexiword lexword : lexiwords) {
                for (String engLemma : engLemmas) {
//                    System.out.println(eWord + "\t" + engLemma);
                    if (lexword.geteWord().equalsIgnoreCase(engLemma)) {
                        System.out.println(lexword.geteWord());
                        System.out.println(engLemma);
                        System.out.println(lexword.getMean());
                        if (lexword.getMean() < 3) {
                            negPunct += lexword.getMean();
                            negCount++;
                            System.out.println("Valor de negPunct en aux = " + negPunct);
                        } else if (lexword.getMean() >= 3 && lexword.getMean() < 6) {
                            neuPunct += lexword.getMean();
                            neuCount++;
                            System.out.println("Valor de negPunct en aux = " + neuPunct);
                        } else if (lexword.getMean() >= 6) {
                            posPunct += lexword.getMean();
                            posCount++;
                            System.out.println("Valor de negPunct en aux = " + posPunct);
                        }
                        aux += lexword.getMean();
                        System.out.println("valor de aux: " + aux);
                        encontrado = true;

                    }

                }

            }
            if ((posCount + negCount + neuCount) != 0) {
                double prom = aux / (posCount + negCount + neuCount);
                System.out.println("valor de cont = " + (posCount + negCount + neuCount));
                System.out.println("Valor de prom = " + prom);
                punctuation += prom;//prom;
                System.out.println("Valor de punctuation = " + punctuation);
                posCount = negCount = neuCount = 0;
                if(punctuation < 3){
                    negCount = 1;
                }else if (punctuation >= 3 && punctuation < 6){
                    neuCount = 1;
                }else{
                    posCount = 1;
                }
            } else {
                punctuation += 0.0d;
            }
        }

//            int c = 0;
//            int aux = 0;
//            while (encontrado== false){
//                String eWord = lexword.geteWord();
//                for (String engLemma: engLemmas){
////                    System.out.println(eWord + "\t" + engLemma);        
//                    if (eWord.equalsIgnoreCase(engLemma)){
//                        System.out.println(eWord);
//                        System.out.println(engLemma);
//                        aux += lexword.getMean();
//                        System.out.println("PuntuaciÃ³n recogida = " + aux);
//                        encontrado=true;
//                        break;
//                    }
//                    encontrado=true;
//                }
//            }
//            if (encontrado == false && punctuation==0.0d){
//                for (String engLemma: engLemmas){
////                    System.out.println(eWord + "\t" + engLemma);
////                    while(encontrado == true){        
//                    if (lexword.geteWord().equalsIgnoreCase(engLemma)){
//                        System.out.println(lexword.geteWord());
//                        System.out.println(engLemma);
//                        System.out.println(lexword.getMean());
//                        aux += lexword.getMean();
//                        System.out.println(aux);
//                        encontrado = true;
//                        
//                    }
//                    if (encontrado == true){
//                        punctuation += aux;
//                        
//                    }
////                    }
//                }
//                //punctuation += aux;
//                
//            }
        System.out.println(punctuation + " " + stem);
        results.setPunctuation(punctuation);
        results.setPosPunct(posPunct);
        results.setNegPunct(negPunct);
        results.setNeutralPunct(neuPunct);
        results.setNegCount(negCount);
        results.setNeuCount(neuCount);
        results.setPosCount(posCount);
        System.out.println("Valor de negPunct = " + negPunct);
        System.out.println("Valor de neuPunct = " + neuPunct);
        System.out.println("Valor de posPunct = " + posPunct);
        System.out.println("Valor de negCount = " + negCount);
        System.out.println("Valor de neuCount = " + neuCount);
        System.out.println("Valor de posCount = " + posCount);
        return results;
    }

    public double estimateMood(double punctuation, int quantWords) {
        System.out.println("cant de palabras: " + quantWords);
        return punctuation/quantWords;
    }
    
    public String determineMood(){
        if(true){
            return "positivo";
        }else{
            return "negativo";
        }
    }
    
    protected int countNo(List<String> lemmas) {
        int cont = 0;
        for (String lemma : lemmas) {
            if (lemma.equalsIgnoreCase("no")) {
                cont++;
            }
        }
        return cont;
    }

    protected static String responseToJson(Response response) throws JsonProcessingException {
        ObjectMapper mapper = ApiModelMixinModule.setupObjectMapper(new ObjectMapper());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsString(response);
    }

    protected String getAltUrlFromSystemProperty() {
        String altUrlStr = System.getProperty(URL_PROP_NAME);
        if (altUrlStr == null || altUrlStr.trim().length() < 1) {
            altUrlStr = "https://api.rosette.com/rest/v1";
        }
        return altUrlStr.trim();
    }

    /*protected boolean cleanPoints(String token){
     String pattern = "[.]";
     Pattern r = Pattern.compile(pattern);
     Matcher m = r.matcher(token);
     if (m.find()){
     return false;
     }else{
     return true;
     }
     }*/
//    public void getSynAntForTokens(List<PosWord> poss) {
//        for (int i = 0; i < poss.size(); i++) {
//            PosWord token = poss.get(i);
//            poss.remove(i);
//            //List<BhtText> synonyms = new CallingBigHugeTheasurus().makeSearch(token.getWord(), "syn");
//            //List<BhtText> antonyms = new CallingBigHugeTheasurus().makeSearch(token.getWord(), "ant"); 
//
//            //token.setSynonyms(synonyms);
//            //token.setAntonyms(antonyms);
//            poss.add(token);
//        }
//
//    }
}
