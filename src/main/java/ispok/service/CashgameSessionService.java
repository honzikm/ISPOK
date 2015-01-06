/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.service;

import ispok.dto.CashgameSessionDto;
import ispok.dto.VisitorDto;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@Transactional
public interface CashgameSessionService {

    @Transactional
    public List<VisitorDto> getActiveVisitors(Long sessionId);

    @Transactional
    public List<CashgameSessionDto> getActiveSessionsByCashgameId(Long cashgameId);

    @Transactional
    public void save(CashgameSessionDto cashgameSessionDto);

    @Transactional(readOnly = true)
    public List<CashgameSessionDto> getByVisitorId(Long id);
}
