package morzan.botafogo.models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import morzan.botafogo.beans.Lexiword;
import morzan.botafogo.snowball.MethodsStemmer;

/**
 *
 * @author EQ
 */
public class ReadingLexicon {
    
    private final String lexRoot = "C:\\Documentos\\backend-botafogo\\src\\test\\resources\\Redondo(2007).csv";
    private MethodsStemmer stemmer = new MethodsStemmer();
    private CallingBigHugeTheasurus thesaurus = new CallingBigHugeTheasurus();

    private static ReadingLexicon singleton = null;
    
    public static ReadingLexicon getInstance() {
        if (singleton == null){
            singleton = new ReadingLexicon();
        }
        return singleton;
    }

    private ReadingLexicon() {
    }
    
    public List<Lexiword> readLexicon(){
        List<Lexiword> lexiwords = new ArrayList<>();
        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(lexRoot))) {

            br.readLine();
            
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] words = line.split(cvsSplitBy);
                //System.out.println("Words [word= " + words[2] + " , name=" + words[3] + "]");
                String eWord = words[1];
                String word = words[2];
                
                String stem = words[30];
                //System.out.println(stemmer.getStem(words[31]));
                String eStem = words[31];
                System.out.println(stemmer.getEStem(eWord));
                double mean = Double.parseDouble(words[3]);
//                System.out.println(words[29]);
                Lexiword lex = new Lexiword(word, mean);
                lex.setStem(stem);
                lex.seteWord(eWord);
                lex.seteStem(eStem);
                
                lexiwords.add(lex);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return lexiwords;
    }

    
    
    
}
