/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.dao;

import ispok.bo.Visitor;
import java.util.List;
import java.util.Map;
import javassist.convert.Transformer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.stereotype.Component;

@Component("visitorDao")
public class VisitorHibernateJpaDao implements VisitorDao {

    private Logger logger = LogManager.getLogger();

    @Autowired
    private EntityManagerFactory entityManagerfactory;

    private EntityManager getEntityManager() {
        return EntityManagerFactoryUtils.getTransactionalEntityManager(entityManagerfactory); //entity manager with @Transactional support
    }

    @Override
    public <ENTITY> List<ENTITY> getPage(int first, int rows, String sortBy, boolean ascending, Map<String, Object> filters, Class<ENTITY> clazz) {

        logger.entry();

        Session session = getEntityManager().unwrap(Session.class);

        Criteria criteria = session.createCriteria(clazz, "visitor");

        for (Map.Entry<String, Object> filter : filters.entrySet()) {
            logger.debug("Filter: {}={}", filter.getKey(), filter.getValue());

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

        ProjectionList projectionList = Projections.projectionList();
        projectionList
                .add(Projections.property("id"), "id")
                .add(Projections.property("visitor.firstName"), "firstName")
                .add(Projections.property("visitor.lastName"), "lastName")
                .add(Projections.property("visitor.birthDate"), "birthDate")
                .add(Projections.property("visitor.nin"), "nin")
                .add(Projections.property("visitor.nickname"), "nickname")
                .add(Projections.property("visitor.telephone"), "telephone")
                .add(Projections.property("visitor.email"), "email")
                .add(Projections.property("visitor.sex"), "sex")
                .add(Projections.property("visitor.passwordHash"), "passwordHash")
                .add(Projections.property("visitor.saltHash"), "saltHash")
                .add(Projections.property("visitor.idNumber"), "idNumber")
                .add(Projections.property("visitor.bonusPoints"), "bonusPoints")
                .add(Projections.property("visitor.citizenship"), "citizenship")
                .add(Projections.property("visitor.domicile"), "domicile")
                .add(Projections.property("visitor.bonusPoints"), "bonusPoints");

        criteria.setProjection(projectionList);
        criteria.setResultTransformer(Transformers.aliasToBean(clazz));
        List<ENTITY> list = criteria.list();

        logger.exit();
        return list;
    }

    @Override
    public <ENTITY> Long getCount(Class<ENTITY> clazz) {
        Session session = getEntityManager().unwrap(Session.class);
        Number number = (Number) session.createCriteria(clazz).setProjection(Projections.rowCount()).uniqueResult();
        return number.longValue();
    }

    @Override
    public <ENTITY> Long getCount(Map<String, Object> filters, Class<ENTITY> clazz) {

        logger.entry();

        Session session = getEntityManager().unwrap(Session.class);
        Criteria criteria = session.createCriteria(clazz);
        criteria.setProjection(Projections.property("id"));

        for (Map.Entry<String, Object> filter : filters.entrySet()) {
            logger.debug("Filter: {}={}", filter.getKey(), filter.getValue());

            if ("id".equals(filter.getKey())) {
                criteria.add(Restrictions.eq("id", Long.parseLong(filter.getValue().toString())));
            } else {
                criteria.add(Restrictions.ilike(filter.getKey(), (String) filter.getValue(), MatchMode.START));
            }
        }

        criteria.setProjection(Projections.rowCount());
        Number number = (Number) criteria.uniqueResult();
        logger.debug("getCount(..): {}" + number);
        logger.exit();
        return number.longValue();
    }
}
