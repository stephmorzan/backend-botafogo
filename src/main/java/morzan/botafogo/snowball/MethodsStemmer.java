/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morzan.botafogo.snowball;

/**
 *
 * @author EQ
 */
public class MethodsStemmer {
    
    public String getStem(String word){
        SpanishStemmer stemmer = new SpanishStemmer();
        stemmer.setCurrent(word);
        if(stemmer.stem()){
            return stemmer.getCurrent();
        }else{
            return "Algo está fallando!!!";
        }
        
    }
    
    public String getEStem(String word){
        EnglishStemmer stemmer = new EnglishStemmer();
        stemmer.setCurrent(word);
        if(stemmer.stem()){
            return stemmer.getCurrent();
        }else{
            return "Algo está fallando!!!";
        }
        
    }
    
}
