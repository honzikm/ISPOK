/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.dao;

import ispok.bo.Tournament;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
public interface TournamentDao {

    /**
     * 
     * @param first
     * @param rows
     * @param sortBy
     * @param ascending
     * @param filters
     * @return 
     */
    public List<Tournament> getPage(int first, int rows, String sortBy, boolean ascending, Map<String, Object> filters);

    /**
     * 
     * @param filters
     * @return 
     */
    public Long getCount(Map<String, Object> filters);

}
