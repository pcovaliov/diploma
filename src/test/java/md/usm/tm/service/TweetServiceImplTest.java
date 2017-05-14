package md.usm.tm.service;

import md.usm.tm.dao.TweetDaoImpl;
import md.usm.tm.model.Task;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by mbezaliuc on 11/21/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml")
@Service
@Transactional
public class TweetServiceImplTest {

    private static final String text = "JUNIT TWEET";
    private static final String textTwo = "JUNIT TWEET TWO";
    private static final String textThree = "JUNIT TWEET THREE";
    private Task tweet = new Task();

    @Autowired
    private TweetDaoImpl tweetDao;

    @Before
    public void setTweet() {
        tweet.setText(text);
    }
//
//    @Test
//    public void testAddTweet() throws Exception {
//        tweetDao.addTweet(tweet);
//
//        Assert.assertNotNull(tweetDao.getTweetById(tweet.getId()));
//    }
//
//    @Test
//    public void testUpdateTweet() throws Exception {
//        tweetDao.addTweet(tweet);
//        tweet.setText("TWEET JUNIT");
//        tweetDao.updateTweet(tweet);
//
//        Assert.assertEquals(tweet, tweetDao.getTweetById(tweet.getId()));
//    }

    @Test
    public void testDeleteTweetJunit() throws Exception {
        tweetDao.addTweet(tweet);

//        tweetDao.deleteTweet(tweet);

        System.out.println(tweetDao.getTweetById(tweet.getId()));
//        Assert.assertNull();
    }

    @Test
    public void testGetTweetById() throws Exception {
        tweetDao.addTweet(tweet);

        Assert.assertEquals(tweet, tweetDao.getTweetById(tweet.getId()));

//        Assert.assertNull(tweetDao.getTweetById(99999));
    }

    @Test
    public void testAllTweets() throws Exception {


    }

    @Test
    public void testGetComments() throws Exception {

    }

    @Test
    public void testTweetLikesById() throws Exception {

    }

    @Test
    public void testAddLike() throws Exception {

    }

}