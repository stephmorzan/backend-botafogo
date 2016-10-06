/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morzan.botafogo.models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import morzan.botafogo.beans.Lexiword;

/**
 *
 * @author EQ
 */
public class ReadingLexicon {
    
    private final String lexRoot = "C:\\Documentos\\backend-botafogo\\src\\test\\resources\\Redondo(2007).csv";
    
    public List<Lexiword> readLexicon(){
        List<Lexiword> lexiwords = new ArrayList<>();
        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(lexRoot))) {

            br.readLine();
            
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] words = line.split(cvsSplitBy);
                System.out.println("Words [word= " + words[2] + " , name=" + words[3] + "]");
                String word = words[2];
                double mean = Double.parseDouble(words[3]);
                Lexiword lex = new Lexiword(word, mean);
                lexiwords.add(lex);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return lexiwords;
    }

    
    
    
}
