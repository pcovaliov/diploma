package md.usm.tm.dao;

import md.usm.tm.model.Comment;
import md.usm.tm.model.Task;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.RootLogger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mbezaliuc on 11/2/2016.
 */

@Repository
@SuppressWarnings("unchecked")
public class TweetDaoImpl {

    private static final Logger logger = RootLogger.getLogger(TweetDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    public Task addTweet(Task task) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(task);
        logger.info("Tweet was added");

        return task;
    }

    public void updateTweet(Task task) {
        Session session = sessionFactory.getCurrentSession();
        session.update(task);
        logger.info("Tweet was updated");
    }

    public Task deleteTweet(Task task) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(task);
        logger.info("Tweet was deleted");
        return task;
    }

    public Task getTweetById(int id) {
        return (Task) sessionFactory.getCurrentSession()
                .createNativeQuery("SELECT * FROM tweet WHERE id=:id", Task.class)
                .setParameter("id", id)
                .getResultList().get(0);
    }

    public List<Comment> getComments(int task_id) {
        return sessionFactory.getCurrentSession()
                .createNativeQuery("SELECT * FROM comments WHERE tweet_id=:tweet_id ORDER BY postdatetime ASC", Comment.class)
                .setParameter("tweet_id", task_id)
                .getResultList();
    }

}
