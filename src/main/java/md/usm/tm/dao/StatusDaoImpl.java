package md.usm.tm.dao;

import md.usm.tm.model.Status;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.RootLogger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by pcovaliov on 5/28/2017.
 */
@Repository
public class StatusDaoImpl extends AbstractGenericDao<Status, Integer> {

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

    @Transactional
    public List<Status> getAll() {
        return sessionFactory.getCurrentSession()
                .createNativeQuery("SELECT * FROM status", Status.class)
                .getResultList();
    }

    @Transactional
    public Status getStatusByDescription(String description){
        Status status = sessionFactory.getCurrentSession()
                .createNativeQuery("SELECT * FROM status s where s.status='" + description + "'", Status.class)
                .getSingleResult();
        return status;
    }
}
