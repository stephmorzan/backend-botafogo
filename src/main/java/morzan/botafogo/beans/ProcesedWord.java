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
public class ProcesedWord {
    
    private String lemma;
    private String stem;
    private List<String> engSyns;

    public ProcesedWord() {
    }

    public ProcesedWord(String lemma, String stem, List<String> engSyns) {
        this.lemma = lemma;
        this.stem = stem;
        this.engSyns = engSyns;
    }

    public String getLemma() {
        return lemma;
    }

    public void setLemma(String lemma) {
        this.lemma = lemma;
    }

    public String getStem() {
        return stem;
    }

    public void setStem(String stem) {
        this.stem = stem;
    }

    public List<String> getEngSyns() {
        return engSyns;
    }

    public void setEngSyns(List<String> engSyns) {
        this.engSyns = engSyns;
    }
    
    
    
}
