/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.dto;

import ispok.bo.Office;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
public class OfficeDto extends AbstractDto {

    private String name;

    public OfficeDto() {
    }

    public OfficeDto(String name) {
        this.name = name;
    }

    public OfficeDto(Office office) {
        this.id = office.getId();
        this.name = office.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
