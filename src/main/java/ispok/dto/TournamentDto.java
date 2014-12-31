/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.dto;

import java.util.Date;

public class TournamentDto extends AbstractDto {

    private String name;
    private String info;
    private Date start;
    private Date finish;
    private Date lateReg;
    private float buyin;
    private float addon;
    private int bonusPoints;
    private Long seriesId = (long) 0;
    private Long placeId = (long) 0;
    private Long tournamentStructureId = (long) 0;
    private int levelNumber;
    private int levelTime;
    private Long payoutStructureId = (long) 0;

    public TournamentDto() {
    }

    public TournamentDto(TournamentDto tdto) {
        this.id = tdto.getId();
        this.name = tdto.getName();
        this.info = tdto.getInfo();
        this.start = tdto.getStart();
        this.lateReg = tdto.getLateReg();
        this.finish = tdto.getFinish();
        this.buyin = tdto.getBuyin();
        this.addon = tdto.getAddon();
        this.bonusPoints = tdto.getBonusPoints();
        this.seriesId = tdto.getSeriesId();
        this.placeId = tdto.getPlaceId();
        this.tournamentStructureId = tdto.getTournamentStructureId();
        this.levelNumber = tdto.getLevelNumber();
        this.levelTime = tdto.getLevelTime();
        this.payoutStructureId = tdto.getPayoutStructureId();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * Get the value of payoutStructureId
     *
     * @return the value of payoutStructureId
     */
    public Long getPayoutStructureId() {
        return payoutStructureId;
    }

    /**
     * Set the value of payoutStructureId
     *
     * @param payoutStructureId new value of payoutStructureId
     */
    public void setPayoutStructureId(Long payoutStructureId) {
        this.payoutStructureId = payoutStructureId;
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
     * Get the value of start
     *
     * @return the value of start
     */
    public Date getStart() {
        return start;
    }

    /**
     * Set the value of start
     *
     * @param start new value of start
     */
    public void setStart(Date start) {
        this.start = start;
    }

    /**
     * Get the value of finish
     *
     * @return the value of finish
     */
    public Date getFinish() {
        return finish;
    }

    /**
     * Set the value of finish
     *
     * @param finish new value of finish
     */
    public void setFinish(Date finish) {
        this.finish = finish;
    }

    /**
     * Get the value of lateReg
     *
     * @return the value of lateReg
     */
    public Date getLateReg() {
        return lateReg;
    }

    /**
     * Set the value of lateReg
     *
     * @param lateReg new value of lateReg
     */
    public void setLateReg(Date lateReg) {
        this.lateReg = lateReg;
    }

    /**
     * Get the value of buyin
     *
     * @return the value of buyin
     */
    public float getBuyin() {
        return buyin;
    }

    /**
     * Set the value of buyin
     *
     * @param buyin new value of buyin
     */
    public void setBuyin(float buyin) {
        this.buyin = buyin;
    }

    /**
     * Get the value of addon
     *
     * @return the value of addon
     */
    public float getAddon() {
        return addon;
    }

    /**
     * Set the value of addon
     *
     * @param addon new value of addon
     */
    public void setAddon(float addon) {
        this.addon = addon;
    }

    /**
     * Get the value of bonusPoints
     *
     * @return the value of bonusPoints
     */
    public int getBonusPoints() {
        return bonusPoints;
    }

    /**
     * Set the value of bonusPoints
     *
     * @param bonusPoints new value of bonusPoints
     */
    public void setBonusPoints(int bonusPoints) {
        this.bonusPoints = bonusPoints;
    }

    /**
     * Get the value of seriesId
     *
     * @return the value of seriesId
     */
    public Long getSeriesId() {
        return seriesId;
    }

    /**
     * Set the value of seriesId
     *
     * @param seriesId new value of seriesId
     */
    public void setSeriesId(Long seriesId) {
        this.seriesId = seriesId;
    }

    /**
     * Get the value of placeId
     *
     * @return the value of placeId
     */
    public Long getPlaceId() {
        return placeId;
    }

    /**
     * Set the value of placeId
     *
     * @param placeId new value of placeId
     */
    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    /**
     * Get the value of tournamentStructureId
     *
     * @return the value of tournamentStructureId
     */
    public Long getTournamentStructureId() {
        return tournamentStructureId;
    }

    /**
     * Set the value of tournamentStructureId
     *
     * @param tournamentStructureId new value of tournamentStructureId
     */
    public void setTournamentStructureId(Long tournamentStructureId) {
        this.tournamentStructureId = tournamentStructureId;
    }

    /**
     * Get the value of levelNumber
     *
     * @return the value of levelNumber
     */
    public int getLevelNumber() {
        return levelNumber;
    }

    /**
     * Set the value of levelNumber
     *
     * @param levelNumber new value of levelNumber
     */
    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    /**
     * Get the value of levelTime
     *
     * @return the value of levelTime
     */
    public int getLevelTime() {
        return levelTime;
    }

    /**
     * Set the value of levelTime
     *
     * @param levelTime new value of levelTime
     */
    public void setLevelTime(int levelTime) {
        this.levelTime = levelTime;
    }

}
