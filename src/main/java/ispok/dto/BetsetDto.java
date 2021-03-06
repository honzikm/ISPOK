/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.dto;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
public class BetsetDto extends AbstractDto {

    private float bigBlind;
    private float smallBlind;
    private float ante;

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

}
