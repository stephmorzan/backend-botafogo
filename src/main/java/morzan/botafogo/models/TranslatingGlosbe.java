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

/**
 *
 * @author EQ
 */
public class TranslatingGlosbe {
    private final String ROOT = "https://glosbe.com/gapi/translate?from=spa&dest=eng&format=json&phrase=";
    private final String PRETTY = "&pretty=true";
    
    private static TranslatingGlosbe singleton;
    
    public static TranslatingGlosbe getInstance(){
        if (singleton == null){
            singleton = new TranslatingGlosbe();
        }
        return singleton;
    }
    
    private TranslatingGlosbe() {
    }
    
//    public static void main(String[] args) {
//        TranslatingGlosbe globse = new TranslatingGlosbe();
//        globse.getTranslatingSynonyms("bien");
//    }
    
    public List<String> getTranslatingSynonyms(String spaWord){
        
        List<String> engSyn = new ArrayList<>();
        
        try {
            URL url = new URL (ROOT + spaWord + PRETTY);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String inputLine;
            List<String> lines = new ArrayList<>();
            
            
            while((inputLine=br.readLine())!=null){
//                System.out.println(inputLine);
                String line = inputLine.replaceAll("  ", "");
//                System.out.println(line);
//                System.out.println(line.length());
//                System.out.println(line.substring(1));
                lines.add(line);   
                
            }
            
            for (int pos=0; pos < lines.size(); pos++){
                String line = lines.get(pos);
                if(line.length() > 9 && line.contains("\"text\" : \"") && lines.get(pos-1).equals("\"phrase\" : {")){
                    line = line.replaceAll("(\")(,)", "");
                    line = line.replaceAll("(\"text\" : \")", "");
                    //System.out.println(line);
                    engSyn.add(line);
                }
            }
            
            return engSyn;
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(TranslatingGlosbe.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(TranslatingGlosbe.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        
        return engSyn;
    }
    
}
