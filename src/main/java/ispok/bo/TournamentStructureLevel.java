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

@Entity(name = "tournamentstructure_level")
public class TournamentStructureLevel {

    @EmbeddedId
    private TournamentStructureLevelId id = new TournamentStructureLevelId();

    @Column
    private int levelNumber;

    public TournamentStructureLevelId getId() {
        return id;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

}
