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
    private String eStem;

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

    public String geteWord() {
        return eWord;
    }

    public void seteWord(String eWord) {
        this.eWord = eWord;
    }

    public String geteStem() {
        return eStem;
    }

    public void seteStem(String eStem) {
        this.eStem = eStem;
    }
    
    
    
}
