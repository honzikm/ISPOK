/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.bo;

import ispok.dto.CashgameSessionDto;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@Entity
public class CashgameSession extends AbstractBusinessObject {

    @ManyToOne
    private Cashgame cashgame;
    @ManyToOne
    private Visitor visitor;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date start;
    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date stop;


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
     * Get the value of stop
     *
     * @return the value of stop
     */
    public Date getStop() {
        return stop;
    }

    /**
     * Set the value of stop
     *
     * @param stop new value of stop
     */
    public void setStop(Date stop) {
        this.stop = stop;
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
     * Get the value of cashgame
     *
     * @return the value of cashgame
     */
    public Cashgame getCashgame() {
        return cashgame;
    }

    /**
     * Set the value of cashgame
     *
     * @param cashgame new value of cashgame
     */
    public void setCashgame(Cashgame cashgame) {
        this.cashgame = cashgame;
    }

}
