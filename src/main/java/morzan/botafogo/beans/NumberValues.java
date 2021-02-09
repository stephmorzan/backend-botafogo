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
public class NumberValues {
    
    private double punctuation;
    private float posPunct;
    private float negPunct;
    private float neutralPunct;
    private int posCount;
    private int negCount;
    private int neuCount;

    public NumberValues() {
    }

    public NumberValues(double punctuation, float posPunct, float negPunct, float neutralPunct, int posCount, int negCount, int neuCount) {
        this.punctuation = punctuation;
        this.posPunct = posPunct;
        this.negPunct = negPunct;
        this.neutralPunct = neutralPunct;
        this.posCount = posCount;
        this.negCount = negCount;
        this.neuCount = neuCount;
    }

    

    public double getPunctuation() {
        return punctuation;
    }

    public void setPunctuation(double punctuation) {
        this.punctuation = punctuation;
    }

    public float getPosPunct() {
        return posPunct;
    }

    public void setPosPunct(float posPunct) {
        this.posPunct = posPunct;
    }

    public float getNegPunct() {
        return negPunct;
    }

    public void setNegPunct(float negPunct) {
        this.negPunct = negPunct;
    }

    public float getNeutralPunct() {
        return neutralPunct;
    }

    public void setNeutralPunct(float neutralPunct) {
        this.neutralPunct = neutralPunct;
    }

    public int getPosCount() {
        return posCount;
    }

    public void setPosCount(int posCount) {
        this.posCount = posCount;
    }

    public int getNegCount() {
        return negCount;
    }

    public void setNegCount(int negCount) {
        this.negCount = negCount;
    }

    public int getNeuCount() {
        return neuCount;
    }

    public void setNeuCount(int neuCount) {
        this.neuCount = neuCount;
    }
    
    
    
}
