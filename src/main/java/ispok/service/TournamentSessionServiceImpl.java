/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.service;

import ispok.bo.Tournament;
import ispok.bo.TournamentSession;
import ispok.bo.Visitor;
import ispok.dao.TournamentSessionDao;
import ispok.dto.TournamentSessionDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@Component
public class TournamentSessionServiceImpl extends AbstractDataAccessService implements TournamentSessionService {

    @Autowired
    TournamentSessionDao tournamentSessionDao;

    @Override
    public void save(TournamentSessionDto tournamentSessionDto) {
        TournamentSession tournamentSession = new TournamentSession();
        Tournament tournament = genericDao.getById(tournamentSessionDto.getTournamentId(), Tournament.class);
        Visitor visitor = genericDao.getById(tournamentSessionDto.getVisitorId(), Visitor.class);
        tournamentSession.setId(tournamentSessionDto.getId());
        tournamentSession.setTournament(tournament);
        tournamentSession.setVisitor(visitor);
        tournamentSession.setSitout(tournamentSessionDto.getSitout());
        tournamentSession.setMoney(tournamentSessionDto.getMoney());
        tournamentSession.setPlace(tournamentSessionDto.getPlace());
        tournamentSessionDto.setId(genericDao.saveOrUpdate(tournamentSession).getId());
    }

    private TournamentSessionDto getTournamentSessionDto(TournamentSession ts) {
        TournamentSessionDto tsd = new TournamentSessionDto();
        tsd.setId(ts.getId());
        tsd.setMoney(ts.getMoney());
        tsd.setPlace(ts.getPlace());
        tsd.setSitout(ts.getSitout());
        tsd.setTournamentId(ts.getTournament().getId());
        tsd.setVisitorId(ts.getVisitor().getId());
        return tsd;
    }

    private List<TournamentSessionDto> getTournamentSessionDtos(List<TournamentSession> tournamentSessions) {
        List<TournamentSessionDto> tournamentSessionDtos = new ArrayList<>(tournamentSessions.size());
        for (TournamentSession ts : tournamentSessions) {
            tournamentSessionDtos.add(getTournamentSessionDto(ts));
        }
        return tournamentSessionDtos;
    }

    @Override
    public List<TournamentSessionDto> getByVisitorId(Long id) {
        List<TournamentSession> tournamentSessions = tournamentSessionDao.getByVisitorId(id);
        return getTournamentSessionDtos(tournamentSessions);
    }
}
