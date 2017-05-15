package md.usm.tm.dao;

import md.usm.tm.model.Like;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.RootLogger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by pcovaliov on 11/24/2016.
 */

@Repository
public class LikeDaoImpl {

    private static final Logger logger = RootLogger.getLogger(TweetDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    public Like addLike(Like like) {
        Session session = sessionFactory.getCurrentSession();
        session.save(like);
        logger.info("Like was added");

        return like;
    }

    public boolean thisTweetIsLikedByUser(int task_id, int user_id) {
        Session session = sessionFactory.getCurrentSession();
        if(session.createNativeQuery("SELECT * FROM likes WHERE task_id=" + task_id +
                " AND user_id=" + user_id, Like.class)
                .getResultList().size() == 1) return true;
        return false;
    }
}
