/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.service;

import ispok.dto.CashgameDto;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
public interface CashgameService {

    @Transactional(readOnly = true)
    public List<CashgameDto> getPage(int first, int pageSize, String sortField, boolean ascending, Map<String, Object> filters);

    @Transactional(readOnly = true)
    public Long getCount(Map<String, Object> filters);

    @Transactional
    public void save(CashgameDto cashgameDto);

    @Transactional
    public void delete(Long id);

    @Transactional
    public List<CashgameDto> getActiveByOfficeId(Long id);

}
