/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@Entity
public class Cashgame extends AbstractBusinessObject {

    @Column(nullable = false)
    private String name;
    @Column(nullable = true, length = 255, unique = false)
    private String info;
    @ManyToOne()
    private Office office;
    @Column(nullable = false)
    private float bigBlind;
    @Column(nullable = false)
    private float smallBlind;
    @Column(nullable = false)
    private float ante;
    @Column(nullable = false)
    private float minBuyin;
    @Column(nullable = false)
    private float maxBuyin;
    @Column(nullable = false)
    private float rake;
    @Column(nullable = false)
    private float maxRake;
    @Column(nullable = false)
    private int points;
    @Column(nullable = false)
    private int chillTime;
    @Column(nullable = false)
    private boolean enabled;

    /**
     * Get the value of enabled
     *
     * @return the value of enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Set the value of enabled
     *
     * @param enabled new value of enabled
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getChillTime() {
        return chillTime;
    }

    public void setChillTime(int chillTime) {
        this.chillTime = chillTime;
    }

    /**
     * Get the value of info
     *
     * @return the value of info
     */
    public String getInfo() {
        return info;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office Office) {
        this.office = Office;
    }

    /**
     * Set the value of info
     *
     * @param info new value of info
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * Get the value of minBuyin
     *
     * @return the value of minBuyin
     */
    public float getMinBuyin() {
        return minBuyin;
    }

    /**
     * Set the value of minBuyin
     *
     * @param minBuyin new value of minBuyin
     */
    public void setMinBuyin(float minBuyin) {
        this.minBuyin = minBuyin;
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
    public float getMaxRake() {
        return maxRake;
    }

    /**
     * Set the value of maxRake
     *
     * @param maxRake new value of maxRake
     */
    public void setMaxRake(float maxRake) {
        this.maxRake = maxRake;
    }

    /**
     * Get the value of rake
     *
     * @return the value of rake
     */
    public float getRake() {
        return rake;
    }

    /**
     * Set the value of rake
     *
     * @param rake new value of rake
     */
    public void setRake(float rake) {
        this.rake = rake;
    }

    /**
     * Get the value of maxBuyin
     *
     * @return the value of maxBuyin
     */
    public float getMaxBuyin() {
        return maxBuyin;
    }

    /**
     * Set the value of maxBuyin
     *
     * @param maxBuyin new value of maxBuyin
     */
    public void setMaxBuyin(float maxBuyin) {
        this.maxBuyin = maxBuyin;
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
