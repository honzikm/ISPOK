/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.dto;

import ispok.bo.TournamentStructure;
import java.util.List;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
public class TournamentStructureDto extends AbstractDto {

    private String name;
    private List<Long> levelIds;



    /**
     * Get the value of levelId
     *
     * @return the value of levelId
     */
    public List<Long> getLevelIds() {
        return levelIds;
    }

    /**
     * Set the value of levelId
     *
     * @param levelId new value of levelId
     */
    public void setLevelIds(List<Long> levelId) {
        this.levelIds = levelId;
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
