package md.iic.netty.dao;

import md.iic.netty.model.Comment;
import md.iic.netty.model.Tweet;
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

    public Tweet addTweet(Tweet tweet) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(tweet);
        logger.info("Tweet was added");

        return tweet;
    }

    public void updateTweet(Tweet tweet) {
        Session session = sessionFactory.getCurrentSession();
        session.update(tweet);
        logger.info("Tweet was updated");
    }

    public Tweet deleteTweet(Tweet tweet) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(tweet);
        logger.info("Tweet was deleted");
        return tweet;
    }

    public Tweet getTweetById(int id) {
        return (Tweet) sessionFactory.getCurrentSession()
                .createNativeQuery("SELECT * FROM tweet WHERE id=:id", Tweet.class)
                .setParameter("id", id)
                .getResultList().get(0);
    }

    public List<Comment> getComments(int tweet_id) {
        return sessionFactory.getCurrentSession()
                .createNativeQuery("SELECT * FROM comments WHERE tweet_id=:tweet_id ORDER BY postdatetime ASC", Comment.class)
                .setParameter("tweet_id", tweet_id)
                .getResultList();
    }

}
