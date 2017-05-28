package md.usm.tm.dao;

import md.usm.tm.model.Period;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.RootLogger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by pcovaliov on 5/28/2017.
 */
@Repository
public class PeriodDaoImpl {

    private static final Logger logger = RootLogger.getLogger(PeriodDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    public Period addPeriod(Period period){
        Session session = sessionFactory.getCurrentSession();
        session.persist(period);

        logger.info("Period was added");
        return period;
    };
}
