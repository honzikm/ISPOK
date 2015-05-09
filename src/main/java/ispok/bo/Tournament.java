/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.bo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Tournament extends AbstractBusinessObject {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date start;

    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date finish;

    @Column(nullable = false, name = "late_reg")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lateReg;

    @Column(nullable = false)
    private float buyin;

    @Column(nullable = false)
    private float addon;

    @Column(nullable = false, name = "bonus_points")
    private int bonusPoints;

    @ManyToOne
    private Series series;

    @ManyToOne
    private Office place;

    @ManyToOne
    private TournamentStructure tournamentStructure;

    @ManyToOne
    private PayoutStructure payoutStructure;

    private int levelNumber;
    private int levelTime;

    @Column(nullable = false)
    private int rebuys;
    @Column(nullable = false)
    private int addons;

    /**
     *
     */
    public Tournament() {
    }

    public PayoutStructure getPayoutStructure() {
        return payoutStructure;
    }

    public void setPayoutStructure(PayoutStructure payoutStructure) {
        this.payoutStructure = payoutStructure;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getFinish() {
        return finish;
    }

    public void setFinish(Date finish) {
        this.finish = finish;
    }

    public Date getLateReg() {
        return lateReg;
    }

    public void setLateReg(Date lateReg) {
        this.lateReg = lateReg;
    }

    public float getBuyin() {
        return buyin;
    }

    public void setBuyin(float buyin) {
        this.buyin = buyin;
    }

    public float getAddon() {
        return addon;
    }

    public void setAddon(float addon) {
        this.addon = addon;
    }

    public int getBonusPoints() {
        return bonusPoints;
    }

    public void setBonusPoints(int bonusPoints) {
        this.bonusPoints = bonusPoints;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    /**
     * Get the value of place
     *
     * @return the value of place
     */
    public Office getPlace() {
        return place;
    }

    /**
     * Set the value of place
     *
     * @param place new value of place
     */
    public void setPlace(Office place) {
        this.place = place;
    }

    /**
     * Get the value of tournamentStructure
     *
     * @return the value of tournamentStructure
     */
    public TournamentStructure getTournamentStructure() {
        return tournamentStructure;
    }

    /**
     * Set the value of tournamentStructure
     *
     * @param tournamentStructure new value of tournamentStructure
     */
    public void setTournamentStructure(TournamentStructure tournamentStructure) {
        this.tournamentStructure = tournamentStructure;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

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

    /**
     *
     * @return
     */
    public int getRebuys() {
        return rebuys;
    }

    /**
     *
     * @param rebuys
     */
    public void setRebuys(int rebuys) {
        this.rebuys = rebuys;
    }

    /**
     *
     * @return
     */
    public int getAddons() {
        return addons;
    }

    /**
     *
     * @param addons
     */
    public void setAddons(int addons) {
        this.addons = addons;
    }

}
