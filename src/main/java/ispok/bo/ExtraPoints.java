/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.bo;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@Entity
public class ExtraPoints extends AbstractBusinessObject {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int count;

    /**
     * Get the value of value
     *
     * @return the value of value
     */
    public int getValue() {
        return count;
    }

    /**
     * Set the value of value
     *
     * @param value new value of value
     */
    public void setValue(int value) {
        this.count = value;
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
