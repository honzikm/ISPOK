/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.dao;

import ispok.bo.Cashgame;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@Component("cashgameDao")
public class CashgameHibernateJpaDao implements CashgameDao {

    @Autowired
    private EntityManagerFactory entityManagerfactory;

    private EntityManager getEntityManager() {
        return EntityManagerFactoryUtils.getTransactionalEntityManager(entityManagerfactory); //entity manager with @Transactional support
    }

    @Override
    public List<Cashgame> getPage(int first, int rows, String sortBy, boolean ascending, Map<String, Object> filters) {

        Session session = getEntityManager().unwrap(Session.class);

        Criteria criteria = session.createCriteria(Cashgame.class);

        for (Map.Entry<String, Object> filter : filters.entrySet()) {

            if ("id".equals(filter.getKey())) {
                criteria.add(Restrictions.eq("id", Long.parseLong(filter.getValue().toString())));
            } else {
                criteria.add(Restrictions.ilike(filter.getKey(), (String) filter.getValue(), MatchMode.START));
            }
        }

        if (ascending) {
            criteria.addOrder(Order.asc(sortBy));
        } else {
            criteria.addOrder(Order.desc(sortBy));
        }

        criteria.setFirstResult(first);
        criteria.setMaxResults(rows);

//        criteria.setResultTransformer(Transformers.aliasToBean(Cashgame.class));
        List<Cashgame> list = criteria.list();

        return list;
    }

    @Override
    public Long getCount(Map<String, Object> filters) {

        Session session = getEntityManager().unwrap(Session.class);
        Criteria criteria = session.createCriteria(Cashgame.class);
        criteria.setProjection(Projections.property("id"));

        for (Map.Entry<String, Object> filter : filters.entrySet()) {

            if ("id".equals(filter.getKey())) {
                criteria.add(Restrictions.eq("id", Long.parseLong(filter.getValue().toString())));
            } else {
                criteria.add(Restrictions.ilike(filter.getKey(), (String) filter.getValue(), MatchMode.START));
            }
        }

        criteria.setProjection(Projections.rowCount());
        Number number = (Number) criteria.uniqueResult();

        return number.longValue();
    }

}
