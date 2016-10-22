/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morzan.botafogo.beans;

/**
 *
 * @author EQ
 */
public class BhtText {
    
    private String wordtype;
    private String synAnt;
    private String newWord;

    public BhtText(String wordtype, String synAnt, String newWord) {
        this.wordtype = wordtype;
        this.synAnt = synAnt;
        this.newWord = newWord;
    }

    public BhtText() {
    }

    public String getWordtype() {
        return wordtype;
    }

    public void setWordtype(String wordtype) {
        this.wordtype = wordtype;
    }

    public String getSynAnt() {
        return synAnt;
    }

    public void setSynAnt(String synAnt) {
        this.synAnt = synAnt;
    }

    public String getNewWord() {
        return newWord;
    }

    public void setNewWord(String newWord) {
        this.newWord = newWord;
    }
    
    
}
