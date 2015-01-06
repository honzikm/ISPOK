/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.dao;

import ispok.bo.Tournament;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
@Component
public class TournamentHibernateJpaDao implements TournamentDao {

    private final Logger logger = LogManager.getLogger();

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    private EntityManager getEntityManager() {
        return EntityManagerFactoryUtils.getTransactionalEntityManager(entityManagerFactory); //entity manager with @Transactional support
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Tournament> getUpcoming(Date date) {
        Session session = ((Session) getEntityManager().getDelegate());
        Criteria criteria = session.createCriteria(Tournament.class);
        criteria.add(Restrictions.gt("start", date));
        return criteria.list();
    }

    @Override
    public List<Tournament> getPage(int first, int rows, String sortBy, boolean ascending, Map<String, Object> filters) {

//        Session session = getEntityManager().unwrap(Session.class);
//        Criteria criteria = session.createCriteria(Tournament.class);
//        CriteriaBuilder cb = entityManagerfactory.getCriteriaBuilder();
//        CriteriaQuery<Tournament> cq = cb.createQuery(Tournament.class);
//        Root<Tournament> tou = cq.from(Tournament.class);
//        cb.para
//        criteria.add(cb.equal(tou, tou))
//        String queryString = "SELECT e FROM " + Tournament.class.getSimpleName();
//        if (!filters.isEmpty()) {
//            queryString += " WHERE ";
//            for (Map.Entry<String, Object> entry : filters.entrySet()) {
//                if ("id".equals(entry.getKey())) {
//                    queryString += "e." + entry.getKey() + " = " + entry.getValue();
//                } else {
//                    queryString += "e." + entry.getKey() + " LIKE '" + entry.getValue() + "'";
//                }
//            }
//        }
//                " e WHERE e." + property + " = :value";
//        return getEntityManager().createQuery(queryString).setParameter("value", value).getResultList();
//        return null;
//        Session session = getEntityManager().unwrap(Session.class);
//        Criteria criteria = session.createCriteria(Tournament.class);
//
//        for (Map.Entry<String, Object> filter : filters.entrySet()) {
//            if ("id".equals(filter.getKey())) {
//                criteria.add(Restrictions.eq("id", Long.parseLong(filter.getValue().toString())));
//            } else {
//                criteria.add(Restrictions.ilike(filter.getKey(), (String) filter.getValue(), MatchMode.START));
//            }
//        }
//
//        if (ascending) {
//            criteria.addOrder(Order.asc(sortBy));
//        } else {
//            criteria.addOrder(Order.desc(sortBy));
//        }
//
//        criteria.setFirstResult(first);
//        criteria.setMaxResults(rows);
//
//        criteria.setResultTransformer(Transformers.aliasToBean(clazz));
//        List<ENTITY> list = criteria.list();
//
//        return list;
        EntityManager em = getEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tournament> cq = cb.createQuery(Tournament.class);
        Root<Tournament> t = cq.from(Tournament.class);

        cq.select(t);

        TypedQuery<Tournament> tq = em.createQuery(cq);

        List<Tournament> tournaments = tq.getResultList();
        return tournaments;

    }

    @Override
    public Long getCount(Map<String, Object> filters) {

        EntityManager em = getEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Tournament> t = cq.from(Tournament.class);

        cq.select(cb.count(t));

        TypedQuery<Long> tq = em.createQuery(cq);
        return tq.getSingleResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Tournament> getTournamentsByTournamentStructureId(Long id) {
        Session session = getEntityManager().unwrap(Session.class);
        Criteria criteria = session.createCriteria(Tournament.class);
        criteria.add(Restrictions.eq("tournamentStructure.id", id));
        return criteria.list();
    }
}
