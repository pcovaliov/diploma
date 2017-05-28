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
public class ProjectDaoImpl {

    private static final Logger logger = RootLogger.getLogger(ProjectDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;
}
