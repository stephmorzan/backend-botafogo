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
                String word = words[2];
                String stem = stemmer.getStem(word);
                double mean = Double.parseDouble(words[3]);
                Lexiword lex = new Lexiword(word, mean);
                lex.setStem(stem);
                lexiwords.add(lex);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return lexiwords;
    }

    
    
    
}
