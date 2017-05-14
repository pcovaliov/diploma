package md.usm.tm.service;

import md.usm.tm.dao.UserDaoImpl;
import md.usm.tm.model.Task;
import md.usm.tm.model.User;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.RootLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by mbezaliuc on 11/2/2016.
 */

@Service("userServiceImpl")
@Transactional
public class UserServiceImpl {

    private static final Logger logger = RootLogger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDaoImpl userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    public User addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDao.addUser(user);
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    public User deleteUser(User user) {
        return userDao.deleteUser(user);
    }

    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    public User getUserByName(String name) {
        return userDao.findByUsername(name);
    }

    public int getIdByName(String username) {
        return userDao.getIdByName(username);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public List<User> getUsersIFollow(int follower_id) {
        return userDao.getUsersIFollow(follower_id);
    }

    public List<User> getUsersIFollow() {
        return userDao.getUsersIFollow();
    }

    public List getFollowersOffers(int limit) {
        List<User> followersOffers = getAllUsers();
        followersOffers.removeAll(getUsersIFollow());
        followersOffers.remove(getUserByName(SecurityContextHolder.getContext().getAuthentication().getName()));
        Collections.shuffle(followersOffers);
        if (limit > followersOffers.size()) limit = followersOffers.size();
        return followersOffers.subList(0, limit);
    }

    public void removeUserFromUsersIFollow(User me, String username) {
        for (User user : me.getUsersIFollow()) {
            if (user.getUsername().equals(username)) {
                me.getUsersIFollow().remove(user);
                userDao.updateUser(me);
                logger.info("User deleted from followers");
                return;
            }
        }
    }

    public void addUserToFollower(User me, User toFollow) {
        me.getUsersIFollow().add(toFollow);
        userDao.updateUser(me);
        logger.info("User was added to folowers");
    }

    public List<Task> getAllUsersTweets(int id) {
        return userDao.getAllUsersTweets(id);
    }

    public List<Task> getAllTweetsOfUsersIFollow() {
        List<Task> allTweetsOfUsersIFollow = new ArrayList<>();
        for (User u : getUsersIFollow()) {
            allTweetsOfUsersIFollow.addAll(u.getAllTweets());
        }
        return allTweetsOfUsersIFollow;
    }

    public List<Task> allTweetsToShow() {
        List<Task> alltweets = new ArrayList<>();
        alltweets.addAll(getAllTweetsOfUsersIFollow());
        alltweets.addAll(getAllUsersTweets(getIdByName(SecurityContextHolder.getContext().getAuthentication().getName())));
        Collections.sort(alltweets, (o1, o2) -> o2.getPostDateTime().compareTo(o1.getPostDateTime()));
        return alltweets;
    }

    public List<Task> allTweetsToShow(int start, int size) {
        List<Task> list = allTweetsToShow();
        if (start + size > list.size()) return list.subList(start, list.size());
        return list.subList(start, start + size);
    }

    public List<String> listOfEmails() {
        return userDao.listOfEmails();
    }

    public List<String> listOfUsernames() {
        return userDao.listOfUsernames();
    }

    public List<User> getUsersWhoFollowMe() {
        return userDao.getUsersWhoFollowMe();
    }

    public List<User> getUsersWhoFollowMe(int id) {
        return userDao.getUsersWhoFollowMe(id);
    }

    public void autologin(User user) {
        if (user.getPassword() != null)
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword(),
                            userDetailsService.loadUserByUsername(user.getUsername()).getAuthorities()));
    }

    public int isFollowed(int thisUserId, int otherUserId) {
        return userDao.isFollowed(thisUserId, otherUserId);
    }

}
