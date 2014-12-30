/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.dto;

import java.util.Date;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
public class TournamentSessionDto extends AbstractDto {

    private Long tournamentId;
    private Long visitorId;
    private Date sitout;
    private int place;
    private float money;

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

    /**
     * Get the value of sitout
     *
     * @return the value of sitout
     */
    public Date getSitout() {
        return sitout;
    }

    /**
     * Set the value of sitout
     *
     * @param sitout new value of sitout
     */
    public void setSitout(Date sitout) {
        this.sitout = sitout;
    }

    /**
     * Get the value of visitorId
     *
     * @return the value of visitorId
     */
    public Long getVisitorId() {
        return visitorId;
    }

    /**
     * Set the value of visitorId
     *
     * @param visitorId new value of visitorId
     */
    public void setVisitorId(Long visitorId) {
        this.visitorId = visitorId;
    }

    /**
     * Get the value of tournamentId
     *
     * @return the value of tournamentId
     */
    public Long getTournamentId() {
        return tournamentId;
    }

    /**
     * Set the value of tournamentId
     *
     * @param tournamentId new value of tournamentId
     */
    public void setTournamentId(Long tournamentId) {
        this.tournamentId = tournamentId;
    }

}
