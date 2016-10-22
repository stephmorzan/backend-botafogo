/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morzan.botafogo.beans;

import java.util.List;

/**
 *
 * @author EQ
 */
public class PosWord {
    
    private String word;
    private String wordtype;
    private List<BhtText> synonyms;
    private List<BhtText> antonyms;

    public PosWord() {
    }

    public PosWord(String word, String wordtype) {
        this.word = word;
        this.wordtype = wordtype;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWordtype() {
        return wordtype;
    }

    public void setWordtype(String wordtype) {
        this.wordtype = wordtype;
    }

    public List<BhtText> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(List<BhtText> synonyms) {
        this.synonyms = synonyms;
    }

    public List<BhtText> getAntonyms() {
        return antonyms;
    }

    public void setAntonyms(List<BhtText> antonyms) {
        this.antonyms = antonyms;
    }
    
    
}
