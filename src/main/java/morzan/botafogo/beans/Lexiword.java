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
public class Lexiword {
    
    private String word;
    private double mean;
    private String stem;
    private String eWord;
    private List<BhtText> synonyms;
    private List<BhtText> antonyms;

    public Lexiword(String word, double mean) {
        this.word = word;
        this.mean = mean;
    }

    public Lexiword() {
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public double getMean() {
        return mean;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public String getStem() {
        return stem;
    }

    public void setStem(String stem) {
        this.stem = stem;
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

    public String geteWord() {
        return eWord;
    }

    public void seteWord(String eWord) {
        this.eWord = eWord;
    }
    
    
    
}
