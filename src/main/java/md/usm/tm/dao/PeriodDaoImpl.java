package md.usm.tm.dao;

import md.usm.tm.model.Period;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.RootLogger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by pcovaliov on 5/28/2017.
 */
@Repository
public class PeriodDaoImpl extends AbstractGenericDao<Period, Integer> {

    private static final Logger logger = RootLogger.getLogger(PeriodDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    protected PeriodDaoImpl() {
        super(Period.class);
    }

    public Period addPeriod(Period period) {
        getCurrentSession().persist(period);
        logger.info("Period was added");
        return period;
    }

    public List<Period> getAllUsersPeriods(int userId) {
        return getCurrentSession().createNativeQuery(
                "SELECT * FROM period WHERE user_id=:user_id", Period.class)
                .setParameter("user_id", userId)
                .getResultList();
    }

    public void deletePeriodById(int id) {
        getCurrentSession().delete(getById(id));
    }

    @Override
    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
