/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.service;

import ispok.dto.SeriesDto;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@Transactional
public interface SeriesService {
    
    @Transactional(readOnly = true)
    public List<SeriesDto> getAll();

    @Transactional(readOnly = true)
    public List<SeriesDto> getPage(int first, int pageSize, String sortField, boolean ascending, Map<String, Object> filters);

    @Transactional(readOnly = true)
    public Long getCount(Map<String, Object> filters);

    @Transactional(readOnly = true)
    public SeriesDto getSeriesById(Long id);

    @Transactional(readOnly = true)
    public boolean nameExists(String name);

    @Transactional
    public void add(SeriesDto seriesDto);

    @Transactional
    public void remove(Long id);

    @Transactional(readOnly = true)
    public SeriesDto getByName(String string);

    @Transactional
    public void save(SeriesDto series);

}
