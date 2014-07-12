/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.service;

import ispok.dto.VisitorDto;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
public interface VisitorService {

//        @Transactional(readOnly = true)
//    public VisitorDto getEmployeeByUsername(String username);
    @Transactional(readOnly = true)
    public List<VisitorDto> getAllVisitors();

    @Transactional(readOnly = true)
    public VisitorDto getVisitorById(Long id);

    @Transactional
    public void addVisitor(VisitorDto visitorDto);

    @Transactional
    public void updateVisitor(VisitorDto visitorDto);

    @Transactional
    public void deleteVisitor(Long id);

    @Transactional
    public List<VisitorDto> getPage(int first, int rows, String sortBy, boolean ascending);

    @Transactional(readOnly = true)
    public Long getVisitorCount();
    
    /**
     *
     * @param filters
     * @return
     */
    @Transactional (readOnly = true)
    public Long getVisitorCount(Map<String, Object> filters);

//    public <ENTITY> List<ENTITY> getPage(int first, int rows, String sortBy, boolean ascending, Class<ENTITY> clazz);
    /**
     *
     * @param first
     * @param pageSize
     * @param sortField
     * @param ascending
     * @param filters
     * @return
     */
    @Transactional(readOnly = true)
    public List<VisitorDto> getPage(int first, int pageSize, String sortField, boolean ascending, Map<String, Object> filters);
    
}
