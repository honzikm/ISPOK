/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.bo;

import javax.persistence.Entity;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@Entity
public class PayoutPlace extends AbstractBusinessObject {

    private int place;
    private float percent;

    /**
     * Get the value of percent
     *
     * @return the value of percent
     */
    public float getPercent() {
        return percent;
    }

    /**
     * Set the value of percent
     *
     * @param percent new value of percent
     */
    public void setPercent(float percent) {
        this.percent = percent;
    }

    /**
     * Get the value of place
     *
     * @return the value of place
     */
    public int getPlace() {
        return place;
    }

    /**
     * Set the value of place
     *
     * @param place new value of place
     */
    public void setPlace(int place) {
        this.place = place;
    }

}
