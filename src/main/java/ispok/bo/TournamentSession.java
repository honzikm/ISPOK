/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.bo;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@Entity
public class TournamentSession extends AbstractBusinessObject {

    @ManyToOne
    private Tournament tournament;
    @ManyToOne
    private Visitor visitor;
    @Temporal(value = TemporalType.TIMESTAMP)
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
     * Get the value of visitor
     *
     * @return the value of visitor
     */
    public Visitor getVisitor() {
        return visitor;
    }

    /**
     * Set the value of visitor
     *
     * @param visitor new value of visitor
     */
    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    /**
     * Get the value of tournament
     *
     * @return the value of tournament
     */
    public Tournament getTournament() {
        return tournament;
    }

    /**
     * Set the value of tournament
     *
     * @param tournament new value of tournament
     */
    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

}
