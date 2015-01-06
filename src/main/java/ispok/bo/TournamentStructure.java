/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.bo;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@Entity
public class TournamentStructure extends AbstractBusinessObject {

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "tournamentStructure", cascade = CascadeType.REMOVE)
    private List<TournamentStructureLevel> tournamentStructure_level;

    public List<TournamentStructureLevel> getLevels() {
        return tournamentStructure_level;
    }

    public void setLevels(List<TournamentStructureLevel> levels) {
        this.tournamentStructure_level = levels;
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
