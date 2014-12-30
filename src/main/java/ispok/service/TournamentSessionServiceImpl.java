/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.service;

import ispok.bo.Tournament;
import ispok.bo.TournamentSession;
import ispok.bo.Visitor;
import ispok.dto.TournamentSessionDto;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@Component
public class TournamentSessionServiceImpl extends AbstractDataAccessService implements TournamentSessionService {

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
}
