/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.dao;

import ispok.bo.CashgameSession;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
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
@Component("cashgameSessionDao")
public class CashgameSessionHibernateJpaDao implements CashgameSessionDao {

    @Autowired
    protected EntityManagerFactory entityManagerfactory;

    protected EntityManager getEntityManager() {
        return EntityManagerFactoryUtils.getTransactionalEntityManager(entityManagerfactory); //entity manager with @Transactional support
    }

    @Override
    public List<CashgameSession> getActive(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CashgameSession> getActiveByCashgameId(Long cashgameId) {
        String jpql = "SELECT cs FROM CashgameSession cs WHERE cs.cashgame.id = :id AND cs.stop IS NULL";
        Query query = getEntityManager().createQuery(jpql);
        query.setParameter("id", cashgameId);
        @SuppressWarnings("unchecked")
        List<CashgameSession> cashgameSessions = query.getResultList();
        return cashgameSessions;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<CashgameSession> getByVisitorId(Long id) {
        Session session = getEntityManager().unwrap(Session.class);

        Criteria criteria = session.createCriteria(CashgameSession.class);

        criteria.add(Restrictions.eq("visitor.id", id));

        return criteria.list();
    }
}
