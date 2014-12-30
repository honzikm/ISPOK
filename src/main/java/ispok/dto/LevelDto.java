/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.dto;

public class LevelDto extends AbstractDto {

    private int duration;
    private int breakDuration;
    private float bigBlind;
    private float smallBlind;
    private float ante;
    private int number;

    public LevelDto() {
        duration = 0;
        breakDuration = 0;
        bigBlind = 0;
        smallBlind = 0;
        ante = 0;
        number = 0;
    }

    /**
     * Get the value of number
     *
     * @return the value of number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Set the value of number
     *
     * @param number new value of number
     */
    public void setNumber(int number) {
        this.number = number;
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

    /**
     * Get the value of duration
     *
     * @return the value of duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Set the value of duration
     *
     * @param duration new value of duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Get the value of breakDuration
     *
     * @return the value of breakDuration
     */
    public int getBreakDuration() {
        return breakDuration;
    }

    /**
     * Set the value of breakDuration
     *
     * @param breakDuration new value of breakDuration
     */
    public void setBreakDuration(int breakDuration) {
        this.breakDuration = breakDuration;
    }

}
