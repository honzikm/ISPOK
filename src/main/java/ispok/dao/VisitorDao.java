/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.dao;

import ispok.bo.Visit;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
public interface VisitorDao {

    /**
     *
     * @param <ENTITY>
     * @param first
     * @param rows
     * @param sortBy
     * @param ascending
     * @param filters
     * @param clazz
     * @return
     */
    public <ENTITY> List<ENTITY> getPage(int first, int rows, String sortBy, boolean ascending, Map<String, Object> filters, Class<ENTITY> clazz);

    /**
     *
     * @param <ENTITY>
     * @param clazz
     * @return
     */
    public <ENTITY> Long getCount(Class<ENTITY> clazz);

    /**
     *
     * @param <ENTITY>
     * @param filters
     * @param clazz
     * @return
     */
    public <ENTITY> Long getCount(Map<String, Object> filters, Class<ENTITY> clazz);

    /**
     *
     * @param id
     * @return
     */
    public List<Visit> getVisitsByVisitorId(Long id);

}
