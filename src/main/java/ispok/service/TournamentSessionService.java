/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.service;

import ispok.dto.TournamentSessionDto;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
public interface TournamentSessionService {

    @Transactional
    public void save(TournamentSessionDto tournamentSessionDto);

    @Transactional
    public List<TournamentSessionDto> getByVisitorId(Long id);
}
