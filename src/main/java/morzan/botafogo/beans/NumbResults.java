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
public class NumbResults {
    
    private double posPunct;
    private double negPunct;
    private double neuPunct;
    private double estimate;
    private String category;

    public NumbResults() {
    }

    public NumbResults(double posPunct, double negPunct, double neuPunct) {
        this.posPunct = posPunct;
        this.negPunct = negPunct;
        this.neuPunct = neuPunct;
    }

    public double getPosPunct() {
        return posPunct;
    }

    public void setPosPunct(double posPunct) {
        this.posPunct = posPunct;
    }

    public double getNegPunct() {
        return negPunct;
    }

    public void setNegPunct(double negPunct) {
        this.negPunct = negPunct;
    }

    public double getNeuPunct() {
        return neuPunct;
    }

    public void setNeuPunct(double neuPunct) {
        this.neuPunct = neuPunct;
    }

    public double getEstimate() {
        return estimate;
    }

    public void setEstimate(double estimate) {
        this.estimate = estimate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    
}
