/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.dto;

import ispok.bo.CashgameSession;
import java.util.Date;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
public class CashgameSessionDto extends AbstractDto {

    private Long cashgameId;
    private Long visitorId;
    private Date start;
    private Date stop;

    public CashgameSessionDto(CashgameSession cs) {
        this.id = cs.getId();
        this.cashgameId = cs.getCashgame().getId();
        this.visitorId = cs.getVisitor().getId();
        if (cs.getStart() != null) {
            this.start = (Date) cs.getStart().clone();
        }
        if (cs.getStop() != null) {
            this.stop = (Date) cs.getStop().clone();
        }
    }

    public CashgameSessionDto() {
    }

    /**
     * Get the value of cashgameId
     *
     * @return the value of cashgameId
     */
    public Long getCashgameId() {
        return cashgameId;
    }

    /**
     * Set the value of cashgameId
     *
     * @param cashgameId new value of cashgameId
     */
    public void setCashgameId(Long cashgameId) {
        this.cashgameId = cashgameId;
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

}
