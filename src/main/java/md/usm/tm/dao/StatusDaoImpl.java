package md.usm.tm.dao;

import md.usm.tm.model.Status;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.RootLogger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * Created by pcovaliov on 5/28/2017.
 */
@Repository
public class StatusDaoImpl extends AbstractGenericDao<Status,Integer>{

    private static final Logger logger = RootLogger.getLogger(StatusDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    protected StatusDaoImpl() {
        super(Status.class);
    }


    @Override
    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
