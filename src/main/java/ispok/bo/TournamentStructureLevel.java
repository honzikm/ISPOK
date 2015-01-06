/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.bo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@Entity
public class TournamentStructureLevel extends AbstractBusinessObject {

    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Level level;
    @Column(nullable = false)
    private int number;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private TournamentStructure tournamentStructure;

    public TournamentStructureLevel() {
    }

    public TournamentStructureLevel(Level level, int number) {
        this.level = level;
        this.number = number;
    }

    public TournamentStructure getTournamentStructure() {
        return tournamentStructure;
    }

    public void setTournamentStructure(TournamentStructure tournamentStructure) {
        this.tournamentStructure = tournamentStructure;
    }

    /**
     * Get the value of number
     *
     * @return the value of number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Set the value of number
     *
     * @param number new value of number
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Get the value of level
     *
     * @return the value of level
     */
    public Level getLevel() {
        return level;
    }

    /**
     * Set the value of level
     *
     * @param level new value of level
     */
    public void setLevel(Level level) {
        this.level = level;
    }

}
