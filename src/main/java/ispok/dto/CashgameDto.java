/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.dto;

import com.sun.webkit.dom.CSSPrimitiveValueImpl;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
public class CashgameDto extends AbstractDto {

    private String name;
    private String info;
    private Long officeId;
    private float bigBlind;
    private float smallBlind;
    private float minBuyin;
    private float maxBuyin;
    private float rake;
    private float maxRake;
    private int points;
    private float ante;
    private int chillTime;
    private boolean enabled;

    public CashgameDto() {

    }

    public CashgameDto(CashgameDto cd) {
        this.id = cd.id;
        this.name = cd.getName();
        this.info = cd.getInfo();
        this.officeId = cd.getOfficeId();
        this.bigBlind = cd.getBigBlind();
        this.smallBlind = cd.getSmallBlind();
        this.ante = cd.getAnte();
        this.minBuyin = cd.getMinBuyin();
        this.maxBuyin = cd.getMaxBuyin();
        this.rake = cd.getRake();
        this.maxRake = cd.getMaxRake();
        this.points = cd.getPoints();
        this.chillTime = cd.getChillTime();
        this.enabled = cd.isEnabled();
    }

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

    /**
     * Get the value of chillTime
     *
     * @return the value of chillTime
     */
    public int getChillTime() {
        return chillTime;
    }

    /**
     * Set the value of chillTime
     *
     * @param chillTime new value of chillTime
     */
    public void setChillTime(int chillTime) {
        this.chillTime = chillTime;
    }

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    /**
     * Get the value of info
     *
     * @return the value of info
     */
    public String getInfo() {
        return info;
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
