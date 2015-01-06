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
public class VisitDto extends AbstractDto {

    private Date visit;
    private Long visitorId;

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
     * Get the value of visit
     *
     * @return the value of visit
     */
    public Date getVisit() {
        return visit;
    }

    /**
     * Set the value of visit
     *
     * @param visit new value of visit
     */
    public void setVisit(Date visit) {
        this.visit = visit;
    }

}
