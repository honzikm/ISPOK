/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.bo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@Entity
public class Level extends AbstractBusinessObject {

    @Column(nullable = false)
    private int duration;
    @Column(nullable = false, name = "break_duration")
    private int breakDuration;
    @ManyToOne(optional = false, cascade = {CascadeType.ALL})
    private Betset betset;

    public Level() {
    }

    public Level(int duration, int breakDuration, Betset betset) {
        this.duration = duration;
        this.breakDuration = breakDuration;
        this.betset = betset;
    }

    /**
     * Get the value of betset
     *
     * @return the value of betset
     */
    public Betset getBetset() {
        return betset;
    }

    /**
     * Set the value of betset
     *
     * @param betset new value of betset
     */
    public void setBetset(Betset betset) {
        this.betset = betset;
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

}
