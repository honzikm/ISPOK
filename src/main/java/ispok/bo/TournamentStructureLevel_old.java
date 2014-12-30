/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.bo;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

//@Entity(name = "tournamentstructure_level")
public class TournamentStructureLevel_old extends AbstractBusinessObject {

//    @EmbeddedId
//    private TournamentStructureLevelId id = new TournamentStructureLevelId();
//    @ManyToOne
    private Level level;

//    @ManyToOne
    private TournamentStructure tournamentStructure;

    /**
     * Get the value of tournamentStructure
     *
     * @return the value of tournamentStructure
     */
    public TournamentStructure getTournamentStructure() {
        return tournamentStructure;
    }

    /**
     * Set the value of tournamentStructure
     *
     * @param tournamentStructure new value of tournamentStructure
     */
    public void setTournamentStructure(TournamentStructure tournamentStructure) {
        this.tournamentStructure = tournamentStructure;
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

    @Column
    private int levelNumber;

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

}
