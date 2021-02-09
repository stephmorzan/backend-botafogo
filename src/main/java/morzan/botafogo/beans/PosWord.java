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

    
}
