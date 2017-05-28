package md.usm.tm.dao;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.RootLogger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by pcovaliov on 5/28/2017.
 */
@Repository
public class StatusDaoImpl {

    private static final Logger logger = RootLogger.getLogger(StatusDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;



}
