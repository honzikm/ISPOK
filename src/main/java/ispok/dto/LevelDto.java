/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.dto;

public class LevelDto extends AbstractDto {

    private int duration;
    private int breakDuration;
    private Long betsetId;

    public LevelDto() {
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

    /**
     * Get the value of betsetId
     *
     * @return the value of betsetId
     */
    public Long getBetsetId() {
        return betsetId;
    }

    /**
     * Set the value of betsetId
     *
     * @param betsetId new value of betsetId
     */
    public void setBetsetId(Long betsetId) {
        this.betsetId = betsetId;
    }

}
