/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.dto;

import ispok.bo.Cashgame;
import ispok.bo.Office;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
public class OfficeDto extends AbstractDto {

    private String name;
    private String info;
    private List<Long> cashgameIds;

    /**
     * Get the value of cashgameIds
     *
     * @return the value of cashgameIds
     */
    public List<Long> getCashgameIds() {
        return cashgameIds;
    }

    /**
     * Set the value of cashgameIds
     *
     * @param cashgameIds new value of cashgameIds
     */
    public void setCashgameIds(List<Long> cashgameIds) {
        this.cashgameIds = cashgameIds;
    }

    public OfficeDto() {
    }

    public OfficeDto(OfficeDto officeDto) {
        this.id = officeDto.id;
        this.name = officeDto.name;
    }

    public OfficeDto(String name) {
        this.name = name;
    }

    public OfficeDto(Office office) {
        this.id = office.getId();
        this.name = office.getName();
        this.info = office.getInfo();
        this.cashgameIds = new ArrayList<Long>(office.getCashgames().size());
        for (Cashgame c : office.getCashgames()) {
            cashgameIds.add(c.getId());
        }
    }

    /**
     * Get the value of info
     *
     * @return the value of info
     */
    public String getInfo() {
        return info;
    }

    /**
     * Set the value of info
     *
     * @param info new value of info
     */
    public void setInfo(String info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
