/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.dao;

import ispok.bo.Betset;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@Component("betsetDao")
public class BetsetHibernateJpaDao implements BetsetDao {

    @Autowired
    private EntityManagerFactory entityManagerfactory;

    private EntityManager getEntityManager() {
        return EntityManagerFactoryUtils.getTransactionalEntityManager(entityManagerfactory); //entity manager with @Transactional support
    }

    @Override
    @SuppressWarnings("unchecked")
    public Betset get(float bigBlind, float smallBlind, float ante) {
        String jpql = "SELECT bs FROM Betset bs WHERE bs.bigBlind = :bigBlind AND bs.smallBlind = :smallBlind AND bs.ante = :ante";
        Query query = getEntityManager().createQuery(jpql);
        query.setParameter("bigBlind", bigBlind);
        query.setParameter("smallBlind", smallBlind);
        query.setParameter("ante", ante);

        List<Betset> bs = query.getResultList();
        if (bs.isEmpty()) {
            return null;
        }
        return bs.get(0);
    }
}
