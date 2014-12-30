/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.dao;

import ispok.bo.Cashgame;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
public interface CashgameDao {

    public List<Cashgame> getPage(int first, int rows, String sortBy, boolean ascending, Map<String, Object> filters);

    public Long getCount(Map<String, Object> filters);
    
}
