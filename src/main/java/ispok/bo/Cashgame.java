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
public class Cashgame extends AbstractBusinessObject {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int bigBlind;
    @Column(nullable = false)
    private int smallBlind;
    @Column(nullable = false)
    private int ante;
    @Column(nullable = false)
    private int minBuyin;
    @Column(nullable = false)
    private int maxBuyin;
    @Column(nullable = false)
    private int rake;
    @Column(nullable = false)
    private int maxRake;
    @Column(nullable = false)
    private int points;

    /**
     * Get the value of minBuyin
     *
     * @return the value of minBuyin
     */
    public int getMinBuyin() {
        return minBuyin;
    }

    /**
     * Set the value of minBuyin
     *
     * @param minBuyin new value of minBuyin
     */
    public void setMinBuyin(int minBuyin) {
        this.minBuyin = minBuyin;
    }

    /**
     * Get the value of ante
     *
     * @return the value of ante
     */
    public int getAnte() {
        return ante;
    }

    /**
     * Set the value of ante
     *
     * @param ante new value of ante
     */
    public void setAnte(int ante) {
        this.ante = ante;
    }

    /**
     * Get the value of points
     *
     * @return the value of points
     */
    public int getPoints() {
        return points;
    }

    /**
     * Set the value of points
     *
     * @param points new value of points
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Get the value of maxRake
     *
     * @return the value of maxRake
     */
    public int getMaxRake() {
        return maxRake;
    }

    /**
     * Set the value of maxRake
     *
     * @param maxRake new value of maxRake
     */
    public void setMaxRake(int maxRake) {
        this.maxRake = maxRake;
    }

    /**
     * Get the value of rake
     *
     * @return the value of rake
     */
    public int getRake() {
        return rake;
    }

    /**
     * Set the value of rake
     *
     * @param rake new value of rake
     */
    public void setRake(int rake) {
        this.rake = rake;
    }

    /**
     * Get the value of maxBuyin
     *
     * @return the value of maxBuyin
     */
    public int getMaxBuyin() {
        return maxBuyin;
    }

    /**
     * Set the value of maxBuyin
     *
     * @param maxBuyin new value of maxBuyin
     */
    public void setMaxBuyin(int maxBuyin) {
        this.maxBuyin = maxBuyin;
    }

    /**
     * Get the value of smallBlind
     *
     * @return the value of smallBlind
     */
    public int getSmallBlind() {
        return smallBlind;
    }

    /**
     * Set the value of smallBlind
     *
     * @param smallBlind new value of smallBlind
     */
    public void setSmallBlind(int smallBlind) {
        this.smallBlind = smallBlind;
    }

    /**
     * Get the value of bigBlind
     *
     * @return the value of bigBlind
     */
    public int getBigBlind() {
        return bigBlind;
    }

    /**
     * Set the value of bigBlind
     *
     * @param bigBlind new value of bigBlind
     */
    public void setBigBlind(int bigBlind) {
        this.bigBlind = bigBlind;
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

}
