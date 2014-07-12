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

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@Entity
public class Visit extends AbstractBusinessObject {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date visit;

    @ManyToOne
    private Visitor visitor;

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
     * Get the value of date
     *
     * @return the value of date
     */
    public Date getDate() {
        return visit;
    }

    /**
     * Set the value of date
     *
     * @param date new value of date
     */
    public void setDate(Date date) {
        this.visit = date;
    }

}
