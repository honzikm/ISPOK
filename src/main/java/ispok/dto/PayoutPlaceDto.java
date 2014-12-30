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
public class PayoutPlaceDto extends AbstractDto {

    private int place;
    private float percent;
    private float money;

    public PayoutPlaceDto() {
        place = 0;
        percent = 0;
        money = 0;
    }

    /**
     * Get the value of money
     *
     * @return the value of money
     */
    public float getMoney() {
        return money;
    }

    /**
     * Set the value of money
     *
     * @param money new value of money
     */
    public void setMoney(float money) {
        this.money = money;
    }

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
