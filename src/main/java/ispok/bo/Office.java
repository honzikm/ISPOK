/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.bo;

import ispok.dto.OfficeDto;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@Entity
public class Office extends AbstractBusinessObject {

    @Column(nullable = false, unique = true, length = 255)
    private String name;
    @Column(nullable = true, unique = false, length = 255)
    private String info;
    @OneToMany(mappedBy = "office")
    private List<Cashgame> cashgames;

    /**
     * Get the value of cashgames
     *
     * @return the value of cashgames
     */
    public List<Cashgame> getCashgames() {
        return cashgames;
    }

    /**
     * Set the value of cashgames
     *
     * @param cashgames new value of cashgames
     */
    public void setCashgames(List<Cashgame> cashgames) {
        this.cashgames = cashgames;
    }

    public Office() {
    }

    public Office(String name) {
        this.name = name;
    }

    public Office(OfficeDto officeDto) {
        this.id = officeDto.getId();
        this.name = officeDto.getName();
        this.info = officeDto.getInfo();
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
