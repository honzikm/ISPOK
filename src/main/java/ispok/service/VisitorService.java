/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.service;

import ispok.dto.VisitDto;
import ispok.dto.VisitorDto;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
public interface VisitorService {

    /**
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<VisitorDto> getAll();

    /**
     *
     * @param name
     * @return
     */
    @Transactional(readOnly = true)
    public VisitorDto getVisitorByNickname(String name);

    /**
     *
     * @param email
     * @return
     */
    @Transactional
    public VisitorDto getVisitorByEmail(String email);

    /**
     *
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public VisitorDto getVisitorById(Long id);

    /**
     *
     * @param visitorDto
     */
    @Transactional
    public void addVisitor(VisitorDto visitorDto);

    /**
     *
     * @param visitorDto
     */
    @Transactional
    public void updateVisitor(VisitorDto visitorDto);

    /**
     *
     * @param id
     */
    @Transactional
    public void deleteVisitor(Long id);

    /**
     *
     * @param first
     * @param rows
     * @param sortBy
     * @param ascending
     * @return
     */
    @Transactional
    public List<VisitorDto> getPage(int first, int rows, String sortBy, boolean ascending);

    @Transactional(readOnly = true)
    public Long getVisitorCount();

    /**
     *
     * @param filters
     * @return
     */
    @Transactional(readOnly = true)
    public Long getVisitorCount(Map<String, Object> filters);

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

    /**
     *
     * @param emaString
     * @return
     */
    @Transactional(readOnly = true)
    public boolean emailAvailable(String emaString);

    @Transactional
    public boolean visit(Long id, Date date);

    @Transactional(readOnly = true)
    public List<VisitDto> getVisitsByVisitorId(Long id);

}
