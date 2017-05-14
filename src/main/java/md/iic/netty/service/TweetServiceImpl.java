package md.iic.netty.service;

import md.iic.netty.dao.TweetDaoImpl;
import md.iic.netty.model.Comment;
import md.iic.netty.model.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by mbezaliuc on 11/2/2016.
 */

@Service
@Transactional
public class TweetServiceImpl {

    @Autowired
    private TweetDaoImpl tweetDao;

    public Tweet addTweet(Tweet tweet) {
        return tweetDao.addTweet(tweet);
    }

    public void updateTweet(Tweet tweet) {
        tweetDao.updateTweet(tweet);
    }

    public Tweet deleteTweet(Tweet tweet) {
        if (tweet.getUser().getUsername().equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
            return tweetDao.deleteTweet(tweet);
        }
        return null;
    }

    public Tweet getTweetById(int id) {
        return tweetDao.getTweetById(id);
    }

    public List<Comment> getComments(int id) {
        return tweetDao.getComments(id);
    }

}
