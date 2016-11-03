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
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author EQ
 */
public class TranslatingGlosbe {
    private final String ROOT = "https://glosbe.com/gapi/translate?from=spa&dest=eng&format=json&phrase=";
    private final String PRETTY = "&pretty=true";
    
    public TranslatingGlosbe() {
    }
    
    public static void main(String[] args) {
        TranslatingGlosbe globse = new TranslatingGlosbe();
        globse.getTranslatingSynonyms("acabar");
    }
    
    public void getTranslatingSynonyms(String spaWord){
        try {
            URL url = new URL (ROOT + spaWord + PRETTY);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String inputLine;
            List<String> lines = new ArrayList<>();
            List posLines = new ArrayList();
            int cont = 0;
            while((inputLine=br.readLine())!=null){
                System.out.println(inputLine);
                lines.add(inputLine);
                if(inputLine.equals("    \"phrase\" : {")){
                    posLines.add(++cont);
                }
                cont++;
            }
            /*int firstPart = lines.size();
            for (int i=0; i<posLines.size(); i++){
                //System.out.println(posLines.get(i));
                String syn = lines.get((int) posLines.get(i)).substring(6);
                lines.add(syn);
            }
            for(int j=0; j<firstPart; j++){
                lines.remove(j);
            }
            for(String a : lines){
                System.out.println(a);
            }*/
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(TranslatingGlosbe.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(TranslatingGlosbe.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    
}
