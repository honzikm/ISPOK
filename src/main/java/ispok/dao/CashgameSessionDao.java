/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.dao;

import ispok.bo.CashgameSession;
import java.util.List;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
public interface CashgameSessionDao {

    public List<CashgameSession> getActive(Long id);

    public List<CashgameSession> getActiveByCashgameId(Long cashgameId);

    public List<CashgameSession> getByVisitorId(Long id);

}
