/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.dao;

import ispok.bo.Betset;
import ispok.bo.Level;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@Component("levelDao")
public class LevelHibernateJpaDao implements LevelDao {

    @Autowired
    private EntityManagerFactory entityManagerfactory;

    private EntityManager getEntityManager() {
        return EntityManagerFactoryUtils.getTransactionalEntityManager(entityManagerfactory); //entity manager with @Transactional support
    }

    @Override
    @SuppressWarnings("unchecked")
    public Level get(int duration, int breakDuration, Long betsetId) {
        String jpql = "SELECT l FROM Level l WHERE l.duration = :duration AND l.breakDuration = :breakDuration AND l.betset.id = :betsetId";
        Query query = getEntityManager().createQuery(jpql);
        query.setParameter("duration", duration);
        query.setParameter("breakDuration", breakDuration);
        query.setParameter("betsetId", betsetId);

        List<Level> l = query.getResultList();
        if (l.isEmpty()) {
            return null;
        }
        return l.get(0);
    }

}
