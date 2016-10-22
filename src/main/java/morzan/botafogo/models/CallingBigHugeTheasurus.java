/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morzan.botafogo.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import morzan.botafogo.beans.BhtText;

/**
 *
 * @author EQ
 */
public class CallingBigHugeTheasurus {
    
    //api key de respaldo
    //9a694e70253c6866f64200d0b0cb7f9f
    private final String KEY_API = "9a694e70253c6866f64200d0b0cb7f9f";
    private final String BHT_VERSION = "/2/";
    private final String BHT_URL = "http://words.bighugelabs.com/api";
    private final String FORMAT = "/txt"; //"/txt";


    public CallingBigHugeTheasurus() {
          
    }
    
    public static void main(String[] args) {
        CallingBigHugeTheasurus prueba = new CallingBigHugeTheasurus();
        prueba.makeSearch("worry", "syn");
    }
    
    public List<BhtText> makeSearch(String word, String kind){
        List<BhtText> thesaWords = new ArrayList<>();
        try {
            URL url = new URL (BHT_URL+BHT_VERSION+KEY_API+"/"+word+FORMAT);
            
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String inputLine;
            String splitter = "\\|";
            
            while((inputLine=br.readLine())!=null){
//                System.out.println(inputLine);
                String[] words =  inputLine.split(splitter);
                String wordtype = words[0];
                //System.out.println(wordtype);
                String synant = words[1];
                //System.out.println(synant);
                String meaning = words[2];
                //System.out.println(meaning);
                BhtText thesa = new BhtText(wordtype,synant,meaning);
                //System.out.println(thesa.getWordtype() + "\t" + thesa.getSynAnt() + "\t" + thesa.getNewWord());
                if(thesa.getSynAnt().equalsIgnoreCase(kind)){
                    thesaWords.add(thesa);
                }
                
            }
                        
        } catch (MalformedURLException ex) {
            Logger.getLogger(CallingBigHugeTheasurus.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(CallingBigHugeTheasurus.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return thesaWords;
    }
    
    
}
