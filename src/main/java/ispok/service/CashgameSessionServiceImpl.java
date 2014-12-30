/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.service;

import ispok.bo.Cashgame;
import ispok.bo.CashgameSession;
import ispok.bo.Visitor;
import ispok.dao.CashgameSessionDao;
import ispok.dto.CashgameSessionDto;
import ispok.dto.VisitorDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@Component
public class CashgameSessionServiceImpl extends AbstractDataAccessService implements CashgameSessionService {

    @Autowired
    CashgameSessionDao CashgameSessionDao;

    @Override
    public List<VisitorDto> getActiveVisitors(Long sessionId) {
        List<CashgameSession> cashgameSession = CashgameSessionDao.getActiveByCashgameId(sessionId);
        List<VisitorDto> visitorDtos = new ArrayList<>(20);
        for (CashgameSession cs : cashgameSession) {
            if (cs.getStop() == null) {
                VisitorDto visitorDto = new VisitorDto(cs.getVisitor());
                visitorDtos.add(visitorDto);
            }
        }
        return visitorDtos;
    }

    @Override
    public void save(CashgameSessionDto cashgameSessionDto) {
        CashgameSession cashgameSession;
        try {
            cashgameSession = genericDao.loadById(cashgameSessionDto.getId(), CashgameSession.class);
        } catch (Exception e) {
            cashgameSession = new CashgameSession();
        }
        if (cashgameSession == null) {
            cashgameSession = new CashgameSession();
        }
        Cashgame cashgame = genericDao.getById(cashgameSessionDto.getCashgameId(), Cashgame.class);
        Visitor visitor = genericDao.getById(cashgameSessionDto.getVisitorId(), Visitor.class);
        cashgameSession.setCashgame(cashgame);
        cashgameSession.setVisitor(visitor);
        cashgameSession.setStart(cashgameSessionDto.getStart());
        cashgameSession.setStop(cashgameSessionDto.getStop());
        cashgameSessionDto.setId(genericDao.saveOrUpdate(cashgameSession).getId());
    }

    @Override
    public List<CashgameSessionDto> getActiveSessionsByCashgameId(Long cashgameId) {
        List<CashgameSession> cashgameSession = CashgameSessionDao.getActiveByCashgameId(cashgameId);
        List<CashgameSessionDto> cashgameSessionDtos = new ArrayList<>(cashgameSession.size());
        for (CashgameSession cs : cashgameSession) {
            cashgameSessionDtos.add(new CashgameSessionDto(cs));
        }
        return cashgameSessionDtos;
    }
}
