/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.service;

import ispok.dto.TournamentDto;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
public interface TournamentService {

//    @Transactional(readOnly = true)
//    public List<TournamentDto> getAll();
    @Transactional(readOnly = true)
    public TournamentDto getById(Long id);

    @Transactional(readOnly = true)
    public List<TournamentDto> getPage(int first, int pageSize, String sortField, boolean ascending, Map<String, Object> filters);

    @Transactional(readOnly = true)
    public Long getCount(Map<String, Object> filters);

    @Transactional
    public void save(TournamentDto tdto);

    @Transactional
    public List<TournamentDto> getAll();

    @Transactional
    public void delete(Long id);

    @Transactional(readOnly = true)
    public List<TournamentDto> getUpcoming(Date date);
}
