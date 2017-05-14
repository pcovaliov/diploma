package md.usm.tm.service;

import md.usm.tm.dao.TweetDaoImpl;
import md.usm.tm.model.Comment;
import md.usm.tm.model.Task;
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

    public Task addTweet(Task task) {
        return tweetDao.addTweet(task);
    }

    public void updateTweet(Task task) {
        tweetDao.updateTweet(task);
    }

    public Task deleteTweet(Task task) {
        if (task.getUser().getUsername().equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
            return tweetDao.deleteTweet(task);
        }
        return null;
    }

    public Task getTweetById(int id) {
        return tweetDao.getTweetById(id);
    }

    public List<Comment> getComments(int id) {
        return tweetDao.getComments(id);
    }

}
