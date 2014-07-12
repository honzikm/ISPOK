/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.bo;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Series extends AbstractBusinessObject {

    @Column(nullable = false)
    private String name;

    public Series() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
