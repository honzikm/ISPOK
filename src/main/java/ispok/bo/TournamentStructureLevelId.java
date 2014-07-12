/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.bo;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class TournamentStructureLevelId implements Serializable {

    @ManyToOne
    private Level level;

    @ManyToOne
    private TournamentStructure tournamentStructure;

    public TournamentStructureLevelId() {
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public TournamentStructure getTournamentStructure() {
        return tournamentStructure;
    }

    public void setTournamentStructure(TournamentStructure tournamentStructure) {
        this.tournamentStructure = tournamentStructure;
    }

}
