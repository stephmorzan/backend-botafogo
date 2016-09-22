/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morzan.botafogo.models;

import com.basistech.rosette.api.RosetteAPI;
import com.basistech.rosette.api.RosetteAPIException;
import com.basistech.rosette.apimodel.Response;
import com.basistech.rosette.apimodel.SentimentResponse;
import com.basistech.rosette.apimodel.jackson.ApiModelMixinModule;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

/**
 *
 * @author EQ
 */
public class ProcesingText {
    
    private static final String KEY_PROP_NAME = "c21fd4214e81308af3f89a9b9a84e041";
    private static final String URL_PROP_NAME = "rosette.api.altUrl";
    
    public void procesarTexto(String text) throws IOException, RosetteAPIException{
       // creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing, and coreference resolution 
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse");
    props.setProperty("tokenize.language", "es");
    props.setProperty("pos.model", "edu/stanford/nlp/models/pos-tagger/spanish/spanish-distsim.tagger");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    
    List<String> lemmas = new LinkedList<String>();

    
    // read some text from the file..
    File inputFile = new File("src/test/resources/sample-content.txt");
    //String text = Files.toString(inputFile, Charset.forName("UTF-8"));
      

    // create an empty Annotation just with the given text
    Annotation document = new Annotation(text);

    // run all Annotators on this text
    pipeline.annotate(document);

    // these are all the sentences in this document
    // a CoreMap is essentially a Map that uses class objects as keys and has values with custom types
    List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);

    for(CoreMap sentence: sentences) {
      // traversing the words in the current sentence
      // a CoreLabel is a CoreMap with additional token-specific methods
      for (CoreLabel token: sentence.get(CoreAnnotations.TokensAnnotation.class)) {
        // this is the text of the token
        String word = token.get(CoreAnnotations.TextAnnotation.class);
        // this is the POS tag of the token
        String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
        // this is the NER label of the token
        String ne = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
        
        lemmas.add(token.get(LemmaAnnotation.class));
        
        System.out.println("word: " + word + " pos: " + pos + " ne:" + ne);
      }

      for(String lemma: lemmas){
          System.out.println("lemma: " + lemma);
      }
      
      // this is the parse tree of the current sentence
      /*Tree tree = sentence.get(TreeCoreAnnotations.TreeAnnotation.class);
      System.out.println("parse tree:\n" + tree);
      
      // this is the Stanford dependency graph of the current sentence
      SemanticGraph dependencies = sentence.get(CollapsedCCProcessedDependenciesAnnotation.class);
      System.out.println("dependency graph:\n" + dependencies);
    }
      
      // This is the coreference link graph
    // Each chain stores a set of mentions that link to each other,
    // along with a method for getting the most representative mention
    // Both sentence and token offsets start at 1!
    Map<Integer, CorefChain> graph = 
        document.get(CorefChainAnnotation.class);
      
      */

    }
    
        
    
    }
    
    public void probandoRosette(String text) throws RosetteAPIException, IOException{
        RosetteAPI rosetteAPI = new RosetteAPI.Builder().apiKey(KEY_PROP_NAME).build();
        SentimentResponse response = rosetteAPI.getSentiment(text);
        System.out.println(responseToJson(response));
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
}
