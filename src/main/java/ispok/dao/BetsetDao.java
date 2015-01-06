/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.dao;

import ispok.bo.Betset;
import java.util.List;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
public interface BetsetDao {

    public List<Betset> getAll();

    public Betset get(float bigBlind, float smallBlin, float ante);

}
