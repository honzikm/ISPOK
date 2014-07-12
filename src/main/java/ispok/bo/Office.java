/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.bo;

import ispok.dto.OfficeDto;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@Entity
public class Office extends AbstractBusinessObject {

    @Column(nullable = false, unique = true)
    private String name;
    
    

    public Office() {
    }

    public Office(String name) {
        this.name = name;
    }

    public Office(OfficeDto officeDto) {
        this.id = officeDto.getId();
        this.name = officeDto.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
 
    
}
