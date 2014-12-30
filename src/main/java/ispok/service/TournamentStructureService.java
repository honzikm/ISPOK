/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.service;

import ispok.dto.LevelDto;
import ispok.dto.TournamentStructureDto;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
public interface TournamentStructureService {

    @Transactional
    public TournamentStructureDto getById(Long id);

    @Transactional
    public List<LevelDto> getLevelsByTournamentStructureId(Long id);

    @Transactional
    public LevelDto getLevelById(Long id);

    @Transactional
    public void saveLevel(LevelDto ld);

    @Transactional
    public void save(TournamentStructureDto tournamentStructureDto, List<LevelDto> levelDtos);

    @Transactional
    public List<TournamentStructureDto> getAll();

}
