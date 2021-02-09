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
public class ThesaurusText {
    
    private String typeWord;
    private String synAnt;
    private String newWord;

    public ThesaurusText(String typeWord, String synAnt, String newWord) {
        this.typeWord = typeWord;
        this.synAnt = synAnt;
        this.newWord = newWord;
    }

    public ThesaurusText() {
    }

    public String getTypeWord() {
        return typeWord;
    }

    public void setTypeWord(String typeWord) {
        this.typeWord = typeWord;
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
