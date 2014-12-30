/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.bo;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@Entity
public class Betset extends AbstractBusinessObject {

    @Column(nullable = false, name = "big_blind")
    private float bigBlind;

    @Column(nullable = false, name = "small_blind")
    private float smallBlind;

    @Column(nullable = false)
    private float ante;

    public Betset() {
    }

    public Betset(float bigBlind, float smallBlind, float ante) {
        this.bigBlind = bigBlind;
        this.smallBlind = smallBlind;
        this.ante = ante;
    }

    /**
     * Get the value of ante
     *
     * @return the value of ante
     */
    public float getAnte() {
        return ante;
    }

    /**
     * Set the value of ante
     *
     * @param ante new value of ante
     */
    public void setAnte(float ante) {
        this.ante = ante;
    }

    /**
     * Get the value of smallBlind
     *
     * @return the value of smallBlind
     */
    public float getSmallBlind() {
        return smallBlind;
    }

    /**
     * Set the value of smallBlind
     *
     * @param smallBlind new value of smallBlind
     */
    public void setSmallBlind(float smallBlind) {
        this.smallBlind = smallBlind;
    }

    /**
     * Get the value of bigBlind
     *
     * @return the value of bigBlind
     */
    public float getBigBlind() {
        return bigBlind;
    }

    /**
     * Set the value of bigBlind
     *
     * @param bigBlind new value of bigBlind
     */
    public void setBigBlind(float bigBlind) {
        this.bigBlind = bigBlind;
    }
}
