/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.service;

import ispok.bo.Office;
import ispok.bo.PayoutStructure;
import ispok.bo.Series;
import ispok.bo.Tournament;
import ispok.bo.TournamentStructure;
import ispok.dao.TournamentDao;
import ispok.dto.TournamentDto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jan
 */
@Component
public class TournamentServiceImpl extends AbstractDataAccessService implements TournamentService {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private TournamentDao tournamentDao;

//    @Override
//    public List<TournamentDto> getAllTournaments() {
//        GenericDao.ge
//    }
    @Override
    public TournamentDto getById(Long id) {
        Tournament tournament = genericDao.getById(id, Tournament.class);
        return getTournamentDto(tournament);
    }

    @Override
    public List<TournamentDto> getPage(int first, int pageSize, String sortField, boolean ascending, Map<String, Object> filters) {
        List<Tournament> tournaments = tournamentDao.getPage(first, pageSize, sortField, ascending, filters);
        List<TournamentDto> tournamentDtos = new ArrayList<>(tournaments.size());
        for (Tournament t : tournaments) {
            tournamentDtos.add(getTournamentDto(t));
        }
        return tournamentDtos;
    }

    @Override
    public List<TournamentDto> getUpcoming(Date date) {
        List<Tournament> tournaments = tournamentDao.getUpcoming(date);
        List<TournamentDto> tournamentDtos = new ArrayList<>(tournaments.size());
        for (Tournament t : tournaments) {
            tournamentDtos.add(getTournamentDto(t));
        }
        return tournamentDtos;
    }

    @Override
    public void save(TournamentDto tdto) {

        Tournament t = new Tournament();
        TournamentStructure ts = genericDao.getById(tdto.getTournamentStructureId(), TournamentStructure.class);
        Office o = genericDao.getById(tdto.getPlaceId(), Office.class);
        PayoutStructure ps = genericDao.getById(tdto.getPayoutStructureId(), PayoutStructure.class);
        Series s = genericDao.getById(tdto.getSeriesId(), Series.class);
//        try {
//            s = genericDao.loadById(tdto.getSeriesId(), Series.class);
//        } catch (Exception e) {
//        }

        t.setId(tdto.getId());
        t.setName(tdto.getName());
        t.setAddon(tdto.getAddon());
        t.setBonusPoints(tdto.getBonusPoints());
        t.setBuyin(tdto.getBuyin());
        t.setFinish(tdto.getFinish());
        t.setLateReg(tdto.getLateReg());
        t.setLevelNumber(tdto.getLevelNumber());
        t.setLevelTime(tdto.getLevelTime());
        t.setStart(tdto.getStart());
        t.setSeries(s);
        t.setPayoutStructure(ps);
        t.setPlace(o);
        t.setTournamentStructure(ts);

        genericDao.saveOrUpdate(t);
    }

    @Override
    public Long getCount(Map<String, Object> filters) {
        return tournamentDao.getCount(filters);
    }

    private TournamentDto getTournamentDto(Tournament tournament) {
        TournamentDto tournamentDto = new TournamentDto();
        tournamentDto.setId(tournament.getId());
        tournamentDto.setName(tournament.getName());
        tournamentDto.setStart(tournament.getStart());
        tournamentDto.setLateReg(tournament.getLateReg());
        tournamentDto.setFinish(tournament.getFinish());
        tournamentDto.setBuyin(tournament.getBuyin());
        tournamentDto.setAddon(tournament.getAddon());
        tournamentDto.setBonusPoints(tournament.getBonusPoints());
        tournamentDto.setLevelNumber(tournament.getLevelTime());
        tournamentDto.setLevelTime(tournament.getLevelTime());
        tournamentDto.setTournamentStructureId(tournament.getTournamentStructure().getId());
        if (tournament.getSeries() != null) {
            tournamentDto.setSeriesId(tournament.getSeries().getId());
        } else {
            tournamentDto.setSeriesId((long) 0);
        }
        tournamentDto.setPlaceId(tournament.getPlace().getId());
        tournamentDto.setPayoutStructureId(tournament.getPayoutStructure().getId());
        return tournamentDto;
    }

//    @Override
//    public List<TournamentDto> getAll() {
//        genericDao.getAllOrderedAsc("", null)
//    }
    @Override
    public List<TournamentDto> getAll() {
        List<Tournament> touraments = genericDao.getAll(Tournament.class);
        List<TournamentDto> tournamentDtos = new ArrayList<>(touraments.size());
        for (Tournament t : touraments) {
            tournamentDtos.add(getTournamentDto(t));
        }
        return tournamentDtos;
    }

    @Override
    public void delete(Long id) {
        genericDao.removeById(id, Tournament.class);
    }

}
