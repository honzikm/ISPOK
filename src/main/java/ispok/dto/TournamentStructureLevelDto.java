/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ispok.dto;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
public class TournamentStructureLevelDto {
    
    
        private Long levelId;

    /**
     * Get the value of levelId
     *
     * @return the value of levelId
     */
    public Long getLevelId() {
        return levelId;
    }

    /**
     * Set the value of levelId
     *
     * @param levelId new value of levelId
     */
    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    private Long tournamentStructureId;

    /**
     * Get the value of tournamentStructureId
     *
     * @return the value of tournamentStructureId
     */
    public Long getTournamentStructureId() {
        return tournamentStructureId;
    }

    /**
     * Set the value of tournamentStructureId
     *
     * @param tournamentStructureId new value of tournamentStructureId
     */
    public void setTournamentStructureId(Long tournamentStructureId) {
        this.tournamentStructureId = tournamentStructureId;
    }

        private int levelNumber;

    /**
     * Get the value of levelNumber
     *
     * @return the value of levelNumber
     */
    public int getLevelNumber() {
        return levelNumber;
    }

    /**
     * Set the value of levelNumber
     *
     * @param levelNumber new value of levelNumber
     */
    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

}
