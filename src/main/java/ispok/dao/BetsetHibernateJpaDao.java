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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@Component("betsetDao")
public class BetsetHibernateJpaDao implements BetsetDao {

    private static Logger log = LogManager.getLogger();

    @Autowired
    private EntityManagerFactory entityManagerfactory;

    private EntityManager getEntityManager() {
        return EntityManagerFactoryUtils.getTransactionalEntityManager(entityManagerfactory); //entity manager with @Transactional support
    }

    @Override
    @SuppressWarnings("unchecked")
    public Betset get(float bigBlind, float smallBlind, float ante) {

        Session session = getEntityManager().unwrap(Session.class);

        Criteria criteria = session.createCriteria(Betset.class);

//        Map m = new HashMap<String, Float>();
//        m.put("bigBlind", bigBlind);
//        m.put("smallBlind", smallBlind);
//        m.put("ante", ante);
//        criteria.add(Restrictions.allEq(m));
//        Property p1 = Property.forName("bigBlind");
//        Property p2 = Property.forName("smallBlind");
//        Property p3 = Property.forName("ante");
//        criteria.add(Restrictions.and(p2.eq(smallBlind)));
        criteria.add(Restrictions.eq("bigBlind", bigBlind));
        criteria.add(Restrictions.eq("smallBlind", smallBlind));
        criteria.add(Restrictions.eq("ante", ante));
        List<Betset> betsets = criteria.list();
        log.debug("found: " + betsets.size());

        if (betsets.isEmpty()) {
            return null;
        }

        return betsets.get(0);

//        String jpql = "SELECT bs FROM Betset bs WHERE bs.bigBlind = :bigBlind AND bs.smallBlind = :smallBlind AND bs.ante = :ante";
//        Query query = getEntityManager().createQuery(jpql);
//        query.setParameter("bigBlind", bigBlind);
//        query.setParameter("smallBlind", smallBlind);
//        query.setParameter("ante", ante);
//
//        List<Betset> bs = query.getResultList();
//        if (bs.isEmpty()) {
//            return null;
//        }
//        return bs.get(0);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Betset> getAll() {
        Session session = getEntityManager().unwrap(Session.class);
        return session.createCriteria(Betset.class).list();
    }
}
