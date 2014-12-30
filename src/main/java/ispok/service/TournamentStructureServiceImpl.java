/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.service;

import ispok.bo.Betset;
import ispok.bo.Level;
import ispok.bo.TournamentStructure;
import ispok.bo.TournamentStructureLevel;
import ispok.dao.BetsetDao;
import ispok.dao.LevelDao;
import ispok.dto.LevelDto;
import ispok.dto.TournamentStructureDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@Component
public class TournamentStructureServiceImpl extends AbstractDataAccessService implements TournamentStructureService {

    @Autowired
    BetsetDao betsetDao;
    @Autowired
    LevelDao LevelDao;

    @Override
    public TournamentStructureDto getById(Long id) {
        TournamentStructure ts = genericDao.loadById(id, TournamentStructure.class);
        List<TournamentStructureLevel> tsls = ts.getLevels();
        List<Long> levelIds = new ArrayList<>(tsls.size());
        for (TournamentStructureLevel nl : tsls) {
            levelIds.add(nl.getLevel().getId());
        }
        TournamentStructureDto tsd = new TournamentStructureDto();
        tsd.setId(ts.getId());
        tsd.setName(ts.getName());
        tsd.setLevelIds(levelIds);
        return tsd;
    }

    @Override
    public List<LevelDto> getLevelsByTournamentStructureId(Long id) {
        TournamentStructure ts = genericDao.getById(id, TournamentStructure.class);
        List<LevelDto> levelDtos = new ArrayList<>(ts.getLevels().size());
        for (TournamentStructureLevel nl : ts.getLevels()) {
            LevelDto levelDto = new LevelDto();
            levelDto.setId(nl.getId());
            levelDto.setNumber(nl.getNumber());
            levelDto.setDuration(nl.getLevel().getDuration());
            levelDto.setBreakDuration(nl.getLevel().getBreakDuration());
            levelDto.setAnte(nl.getLevel().getBetset().getAnte());
            levelDto.setBigBlind(nl.getLevel().getBetset().getBigBlind());
            levelDto.setSmallBlind(nl.getLevel().getBetset().getSmallBlind());
            levelDtos.add(levelDto);
        }
        return levelDtos;
    }

    @Override
    public LevelDto getLevelById(Long id) {
        TournamentStructureLevel tsl = genericDao.loadById(id, TournamentStructureLevel.class);
        LevelDto levelDto = new LevelDto();
        levelDto.setId(tsl.getId());
        levelDto.setNumber(tsl.getNumber());
        levelDto.setAnte(tsl.getLevel().getBetset().getAnte());
        levelDto.setBigBlind(tsl.getLevel().getBetset().getBigBlind());
        levelDto.setSmallBlind(tsl.getLevel().getBetset().getSmallBlind());
        levelDto.setDuration(tsl.getLevel().getDuration());
        levelDto.setBreakDuration(tsl.getLevel().getBreakDuration());
        return levelDto;
    }

    @Override
    public void saveLevel(LevelDto ld) {

        Betset bs = betsetDao.get(ld.getBigBlind(), ld.getSmallBlind(), ld.getAnte());
        if (bs == null) {
            bs = new Betset(ld.getSmallBlind(), ld.getSmallBlind(), ld.getAnte());
            genericDao.saveOrUpdate(bs);
        }

        Level l = LevelDao.get(ld.getDuration(), ld.getBreakDuration(), bs.getId());

        if (l == null) {
            l = new Level(ld.getDuration(), ld.getBreakDuration(), bs);
        }

        TournamentStructureLevel nl = null;
        List<TournamentStructureLevel> nls = genericDao.getAll(TournamentStructureLevel.class);
        for (TournamentStructureLevel numLev : nls) {
            if (numLev.getNumber() == ld.getNumber() && numLev.getLevel() == l) {
                nl = numLev;
                break;
            }
        }
        if (nl == null) {
            nl = new TournamentStructureLevel(l, ld.getNumber());
            ld.setId(genericDao.saveOrUpdate(nl).getId());

        }
    }

    private TournamentStructureLevel saveNumberedLevel(LevelDto ld) {
        Betset bs = betsetDao.get(ld.getBigBlind(), ld.getSmallBlind(), ld.getAnte());
        if (bs == null) {
            bs = new Betset(ld.getBigBlind(), ld.getSmallBlind(), ld.getAnte());
            genericDao.saveOrUpdate(bs);
        }

        Level l = LevelDao.get(ld.getDuration(), ld.getBreakDuration(), bs.getId());
        if (l == null) {
            l = new Level(ld.getDuration(), ld.getBreakDuration(), bs);
            genericDao.saveOrUpdate(l);
        }

        TournamentStructureLevel nl = null;
        List<TournamentStructureLevel> nls = genericDao.getAll(TournamentStructureLevel.class);
        for (TournamentStructureLevel numLev : nls) {
            if (numLev.getNumber() == ld.getNumber() && numLev.getLevel() == l) {
                nl = numLev;
                break;
            }
        }
        if (nl == null) {
            nl = new TournamentStructureLevel(l, ld.getNumber());
            nl.setTournamentStructure(null);
            ld.setId(genericDao.saveOrUpdate(nl).getId());

        }
        return nl;
    }

    @Override
    public void save(TournamentStructureDto tournamentStructureDto, List<LevelDto> levelDtos) {

        List<TournamentStructure> tss = genericDao.getByProperty("name", tournamentStructureDto.getName(), TournamentStructure.class);
        if (!tss.isEmpty()) {
            tournamentStructureDto.setId(null);
            return;
        }
        TournamentStructure ts = new TournamentStructure();
//        List<NumberedLevel> nls = new ArrayList<>();
        ts.setName(tournamentStructureDto.getName());
        genericDao.saveOrUpdate(ts);

        for (LevelDto ld : levelDtos) {
//            nls.add(saveNumberedLevel(ld));

            Betset bs = betsetDao.get(ld.getBigBlind(), ld.getSmallBlind(), ld.getAnte());
            if (bs == null) {
                bs = new Betset(ld.getBigBlind(), ld.getSmallBlind(), ld.getAnte());
                genericDao.saveOrUpdate(bs);
            }

            Level l = LevelDao.get(ld.getDuration(), ld.getBreakDuration(), bs.getId());
            if (l == null) {
                l = new Level(ld.getDuration(), ld.getBreakDuration(), bs);
                genericDao.saveOrUpdate(l);
            }

            TournamentStructureLevel nl = null;
            List<TournamentStructureLevel> nls = genericDao.getAll(TournamentStructureLevel.class);
            for (TournamentStructureLevel numLev : nls) {
                if (numLev.getNumber() == ld.getNumber() && numLev.getLevel() == l && numLev.getTournamentStructure() == ts) {
                    nl = numLev;
                    break;
                }
            }
            if (nl == null) {
                nl = new TournamentStructureLevel(l, ld.getNumber());
                nl.setTournamentStructure(ts);
                ld.setId(genericDao.saveOrUpdate(nl).getId());

            }
        }
    }

    @Override
    public List<TournamentStructureDto> getAll() {

        List<TournamentStructure> tournamentStructures = genericDao.getAll(TournamentStructure.class);
        List<TournamentStructureDto> tournamentStructureDtos = new ArrayList<>(tournamentStructures.size());

        for (TournamentStructure ts : tournamentStructures) {
            TournamentStructureDto tsd = new TournamentStructureDto();
            tsd.setId(ts.getId());
            tsd.setName(ts.getName());
            List<Long> longs = new ArrayList<>(ts.getLevels().size());
            for (TournamentStructureLevel tsl : ts.getLevels()) {
                longs.add(tsl.getId());
            }
            tsd.setLevelIds(longs);
            tournamentStructureDtos.add(tsd);
        }
        return tournamentStructureDtos;
    }
}
