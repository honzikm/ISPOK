/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.dao;

import ispok.bo.Country;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.stereotype.Component;

@Component("countryDao")
public class CountryHibernateJpaDao implements CountryDao {

    @Autowired
    protected EntityManagerFactory entityManagerfactory;

    protected EntityManager getEntityManager() {
        return EntityManagerFactoryUtils.getTransactionalEntityManager(entityManagerfactory); //entity manager with @Transactional support
    }

    @Override
    public List<Country> getAll() {

        Session session = getEntityManager().unwrap(Session.class);

//        Criteria criteria = session.createCriteria(Country.class, "country");
//
//        ProjectionList projectionList = Projections.projectionList();
//        projectionList
//                .add(Projections.property("id"), "id")
//                .add(Projections.property(language), "eng");
//
//        criteria.setProjection(projectionList);
//        criteria.setResultTransformer(Transformers.aliasToBean(Country.class));
//
//        criteria.addOrder(Order.asc(language));
//
//        List<Country> list = criteria.list();
        Criteria criteria = session.createCriteria(Country.class, "country");

        List<Country> list = criteria.list();

        return list;

//        EntityManager em = getEntityManager();
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Country> cq = cb.createQuery(Country.class);
////
//        Root<Country> root = cq.from(Country.class);
//
//        cq.select(cb.construct(Country.class, root.get("id"), root.get(language)));
//        cq.orderBy(cb.asc(root.get(language)));
////
//        return em.createQuery(cq).getResultList();
////        session.createCriteria(null)
////        Criteria cr = session.createCriteria(Country.class);
////        String queryString = "SELECT id, alpha2, alpha3, :lang FROM country";
////        return getEntityManager().createQuery(queryString).setParameter("lang", language).getResultList();
////        return new ArrayList<>();
    }

}
