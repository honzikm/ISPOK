/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.pres.bb;

import ispok.dto.LevelDto;
import ispok.dto.TournamentStructureDto;
import ispok.helper.FacesUtil;
import ispok.service.TournamentStructureService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@Component
@SessionScoped
public class TournamentStructureEdit {

    @Autowired
    private TournamentStructureService tournamentStructureService;

    private List<LevelDto> levels;

//    private List<TournamentStructureDto> tournamentStructures;
    private Long tournamentStructureId;
    private TournamentStructureDto tournamentStructure;

    /**
     * Get the value of tournamentStructure
     *
     * @return the value of tournamentStructure
     */
    public TournamentStructureDto getTournamentStructure() {
        return tournamentStructure;
    }

    /**
     * Get the value of tournamentStructureId
     *
     * @return the value of tournamentStructureId
     */
    public Long getTournamentStructureId() {
        return tournamentStructureId;
    }

    /**
     * Set the value of tournamentStructureId
     *
     * @param tournamentStructureId new value of tournamentStructureId
     */
    public void setTournamentStructureId(Long tournamentStructureId) {
        this.tournamentStructureId = tournamentStructureId;
        levels = tournamentStructureService.getLevelsByTournamentStructureId(tournamentStructureId);
    }

    @PostConstruct
    private void init() {
        tournamentStructure = new TournamentStructureDto();
        levels = new ArrayList<>(20);
//        LevelDto level = new LevelDto();
//        level.setBigBlind(0);
//        level.setSmallBlind(0);
//        level.setNumber(1);
//        level.setAnte(0);
//        level.setDuration(0);
//        level.setBreakDuration(0);
//        levels = new ArrayList<>(20);
//        levels.add(level);
    }

    public List<LevelDto> getLevels() {
        return levels;
    }

    public List<TournamentStructureDto> getTournamentStructures() {
        List<TournamentStructureDto> tournamentStructures = tournamentStructureService.getAll();
        if (tournamentStructures != null && !tournamentStructures.isEmpty()) {
            setTournamentStructureId(tournamentStructures.get(0).getId());
        } else {
            levels.clear();
        }
        return tournamentStructures;
    }

    public void addLevel(int levelNumber) {
        LevelDto ld = new LevelDto();
        ld.setNumber(levelNumber + 1);
        levels.add(levelNumber, ld);
        for (int i = levelNumber + 1; i < levels.size(); i++) {
            levels.get(i).setNumber(i + 1);
        }
    }

    public void removeLevel(int levelNumber) {
        levels.remove(levelNumber - 1);
        if (levels.isEmpty()) {
            LevelDto level = new LevelDto();
            level.setNumber(1);
            levels.add(level);
        } else {
            for (int i = levelNumber - 1; i < levels.size(); i++) {
                levels.get(i).setNumber(i + 1);
            }
        }
    }

    public void clear() {
        tournamentStructure.setName(null);
        levels.clear();
        LevelDto level = new LevelDto();
        level.setNumber(1);
        levels.add(level);
    }

    public void test() {
        levels = tournamentStructureService.getLevelsByTournamentStructureId(tournamentStructure.getId());
    }

    public String newStructure() {
        LevelDto level = new LevelDto();
        level.setBigBlind(0);
        level.setSmallBlind(0);
        level.setNumber(1);
        level.setAnte(0);
        level.setDuration(0);
        level.setBreakDuration(0);
        levels = new ArrayList<>(20);
        levels.add(level);
        return "/admin/management/tournaments/newTournamentStructure.xhtml";
    }

    public String add() {
        tournamentStructureService.save(tournamentStructure, levels);
        if (tournamentStructure.getId() == null) {
            FacesUtil.addWarnMessage("fail", "fail");
            return null;
        }
        return null;
    }

    public String cancel() {
        levels = tournamentStructureService.getLevelsByTournamentStructureId(tournamentStructureId);
        return "/admin/management/tournaments/tournamentStructures.xhtml";
    }
}
