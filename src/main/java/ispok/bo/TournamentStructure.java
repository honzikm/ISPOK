/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.bo;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@Entity
public class TournamentStructure extends AbstractBusinessObject {

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "tournamentStructure")
    private List<TournamentStructureLevel> numberedLevels;

    public List<TournamentStructureLevel> getLevels() {
        return numberedLevels;
    }

    public void setLevels(List<TournamentStructureLevel> levels) {
        this.numberedLevels = levels;
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
