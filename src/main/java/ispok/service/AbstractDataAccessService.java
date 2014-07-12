/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.service;

import ispok.dao.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author mickapa1
 */
public abstract class AbstractDataAccessService {
    @Autowired
    protected GenericDao genericDao;
    public void setGenericDao(GenericDao genericDao) {
        this.genericDao = genericDao;
    }

    public GenericDao getGenericDao() {
        return genericDao;
    }    
}
