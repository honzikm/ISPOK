package ispok.dao;

import ispok.bo.AbstractBusinessObject;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.stereotype.Component;

@Component("genericDao")
public class GenericHibernateJpaDao implements GenericDao {

    Logger logger = LogManager.getLogger();

    @Autowired
    protected EntityManagerFactory entityManagerfactory;

    /**
     * Get entity manager for the current transaction
     *
     * @return
     */
    protected EntityManager getEntityManager() {
        return EntityManagerFactoryUtils.getTransactionalEntityManager(entityManagerfactory); //entity manager with @Transactional support
    }

    /**
     * Vrati vsechny objekty dane tridy
     *
     * @return vsechny objekty tridy, jez je injektovana jako clazz, serazene
     * dle id sestupne
     */
    @SuppressWarnings("unchecked")
    @Override
    public <ENTITY> List<ENTITY> getAll(Class<ENTITY> clazz) {
        return getEntityManager().createQuery("SELECT e FROM " + clazz.getSimpleName() + " e").getResultList();
    }

    /**
     * Vrati vsechny objekty serazene sestupne dle dane property
     *
     * @param property
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public <ENTITY> List<ENTITY> getAllOrderedDesc(String property, Class<ENTITY> clazz) {
        throw new IllegalStateException("Not implemented yet");
    }

    /**
     * Vrati vsechny objekty serazene vzestupne dle dane property
     *
     * @param <ENTITY>
     * @param property
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public <ENTITY> List<ENTITY> getAllOrderedAsc(String property, Class<ENTITY> clazz) {
        throw new IllegalStateException("Not implemented yet");
    }

    /**
     * Vrati objekty dane tridy, jejichz property se rovna objektu predanemu v
     * parametru, serazene dle id sestupne
     *
     * @param <ENTITY>
     * @param property property, kterou porovnavame
     * @param value hodnota, se kterou porovnavame
     * @param clazz
     * @return vsechny vyhovujici zaznamy
     */
    @SuppressWarnings("unchecked")
    @Override
    public <ENTITY> List<ENTITY> getByProperty(String property, Object value, Class<ENTITY> clazz) {
        String queryString = "SELECT e FROM " + clazz.getSimpleName() + " e WHERE e." + property + " = :value";
        return getEntityManager().createQuery(queryString).setParameter("value", value).getResultList();
    }

    /**
     * Smaze objekt dle daneho ID
     *
     * @param <ENTITY>
     * @param id id objektu je smazani
     * @param clazz
     */
    @Override
    public <ENTITY extends AbstractBusinessObject> void removeById(long id, Class<ENTITY> clazz) {
        ENTITY e = getEntityManager().find(clazz, id);
        if (e != null) {
            getEntityManager().remove(e);
        }
    }

    /**
     * smaze danou entitu
     *
     * @param <ENTITY>
     * @param o entita ke smazani
     */
    @Override
    public <ENTITY extends AbstractBusinessObject> void remove(ENTITY o) {
        getEntityManager().remove(o);
    }

    /**
     * Vrati objekt (pomoci get) dane tridy dle ID
     *
     * @param id id objektu k vraceni
     * @return objekt identifikovany id, @null pokud neexistuje
     */
    @SuppressWarnings("unchecked")
    @Override
    public <ENTITY> ENTITY getById(Long id, Class<ENTITY> clazz) {
        return getEntityManager().find(clazz, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <ENTITY> ENTITY loadById(long id, Class<ENTITY> clazz) {
        ENTITY e = null;
        try {
//            e = (ENTITY) ((Session) getEntityManager().getDelegate()).load(clazz, id);
            e = (ENTITY) ((Session) getEntityManager().getDelegate()).get(clazz, id);
        } catch (EntityNotFoundException enfe) {
        }
        return e;
    }

    @Override
    public <ENTITY extends AbstractBusinessObject> ENTITY saveOrUpdate(ENTITY o) {
        logger.entry();
        if (o.getId() == null) {
            getEntityManager().persist(o);
        } else {
            getEntityManager().merge(o);
        }
        logger.exit();
        return o;
    }

    @Override
    public <ENTITY> ENTITY
            getByPropertyUnique(String property, Object value, Class<ENTITY> clazz
            ) {
        ENTITY e;
        Object o = null;
        if (value == null) {
            try {
                o = getEntityManager().createQuery("FROM " + clazz.getSimpleName() + " WHERE " + property + " IS NULL").getSingleResult();
            } catch (Exception ex) {
                return null;
            }
//            e = clazz.cast(getEntityManager().createQuery("FROM " + clazz.getSimpleName() + " WHERE " + property + " IS NULL").getSingleResult());
        } else {
            try {
                o = getEntityManager().createQuery("FROM " + clazz.getSimpleName() + " WHERE " + property + " = :value").setParameter("value", value).getSingleResult();
            } catch (Exception ex) {
                return null;
            }
//            e = clazz.cast(getEntityManager().createQuery("FROM " + clazz.getSimpleName() + " WHERE " + property + " = :value").setParameter("value", value).getSingleResult());
        }
        e = clazz.cast(o);
        return e;
    }
//    @Override
//    public <ENTITY> ENTITY getByPropertyUnique(String property, Object value, Class<ENTITY> clazz) {
//        Session session = getEntityManager().unwrap(Session.class);
//
////        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
////        CriteriaQuery cq;
////        cq = cb.createQuery(clazz);
////
////        criteria.
//
//        ENTITY e;
//        if (value == null) {
//            e = clazz.cast(getEntityManager().createQuery("FROM " + clazz.getSimpleName() + " WHERE " + property + " IS NULL").getSingleResult());
//        } else {
//            e = clazz.cast(getEntityManager().createQuery("FROM " + clazz.getSimpleName() + " WHERE " + property + " = :value").setParameter("value", value).getSingleResult());
//        }
//        return e;
//    }

    @Override
    public <ENTITY> List<ENTITY> getPage(int from, int maxResults, Class<ENTITY> clazz
    ) {
        throw new IllegalStateException("Not implemented yet");
    }

    @Override
    @SuppressWarnings("unchecked")
    public <ENTITY> List<ENTITY> getPage(int first, int rows, String sortBy, boolean ascending, Class<ENTITY> clazz
    ) {

        Session session = getEntityManager().unwrap(Session.class);
        Criteria criteria = session.createCriteria(clazz);

        if (ascending) {
            criteria.addOrder(Order.asc(sortBy));
        } else {
            criteria.addOrder(Order.desc(sortBy));
        }

        criteria.setFirstResult(first);
        criteria.setMaxResults(rows);

        return criteria.list();

//        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
//        CriteriaQuery<ENTITY> cq = cb.createQuery(clazz);
//
//        Root<ENTITY> root = cq.from(clazz);
//
//        String queryString = "FROM " + clazz.getSimpleName() + " order by " + sortBy + " " + sortOrder + " offset " + first + " limit " + rows;
//
//        return getEntityManager().createQuery(queryString).getResultList();
    }
    //    @Override
    //    public <ENTITY> List<ENTITY> getPage(int first, int rows, String sortBy, boolean ascending, Class<ENTITY> clazz) {
    //        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
    ////        Class
    //        CriteriaQuery<ENTITY> cq = cb.createQuery(clazz);
    //        Root<ENTITY> from = cq.from(clazz);
    //
    //        CriteriaQuery<ENTITY> select = cq.select(from);
    //
    ////        getEntityManager().createQuery(cq)
    //
    //        if (ascending) {
    //            cq.orderBy(cb.asc(from.get(sortBy)));
    //        } else {
    //            cq.orderBy(cb.desc(from.get(sortBy)));
    //        }
    //
    //        TypedQuery<ENTITY> typedQuery;
    //        typedQuery = getEntityManager().createQuery(select);
    //        typedQuery.setFirstResult(first);
    //        typedQuery.setMaxResults(rows);
    //
    //        return typedQuery.getResultList();
    //
    //
    ////        CriteriaQuery<clazz>
    ////         getEntityManager().createQuery(null)
    //    }

    public void merge(Object o) {
        throw new IllegalStateException("Not implemented yet");
    }

    @Override
    public <ENTITY> Long getCount(Class<ENTITY> clazz) {
        Session session = getEntityManager().unwrap(Session.class);
        Number number = (Number) session.createCriteria(clazz).setProjection(Projections.rowCount()).uniqueResult();
        return number.longValue();
    }

    @Override
    public <ENTITY> Long getCount(Map<String, Object> filters, Class<ENTITY> clazz) {
        Session session = getEntityManager().unwrap(Session.class);
        Criteria criteria = session.createCriteria(clazz);
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
        return number.longValue();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <ENTITY> List<ENTITY> getPage(int first, int rows, String sortBy, boolean ascending, Map<String, Object> filters, Class<ENTITY> clazz) {
        Session session = getEntityManager().unwrap(Session.class);

//        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
//
//        CriteriaQuery<ENTITY> cq = cb.createQuery(clazz);
//
//        Root<ENTITY> root = cq.from(clazz);
//
//        cq.select(root);
//
//        cb.like(ENTITY., null)
        Criteria criteria = session.createCriteria(clazz);

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

        return criteria.list();
    }
}
