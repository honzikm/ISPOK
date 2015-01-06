/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.dao;

import ispok.bo.TournamentSession;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
@Component("tournamentSessionDao")
public class TournamentSessionDaoImpl implements TournamentSessionDao {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    private EntityManager getEntityManager() {
        return EntityManagerFactoryUtils.getTransactionalEntityManager(entityManagerFactory); //entity manager with @Transactional support
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<TournamentSession> getByVisitorId(Long id) {
        Session session = getEntityManager().unwrap(Session.class);
        Criteria criteria = session.createCriteria(TournamentSession.class);

        criteria.add(Restrictions.eq("visitor.id", id));

        return criteria.list();
    }
}
