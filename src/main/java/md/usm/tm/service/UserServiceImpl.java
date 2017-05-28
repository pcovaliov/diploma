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
 * Created by pcovaliov on 11/2/2016.
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


    public List<String> listOfEmails() {
        return userDao.getAllEmails();
    }

    public List<String> listOfUsernames() {
        return userDao.getAllUsernames();
    }

    public void autologin(User user) {
        if (user.getPassword() != null)
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword(),
                            userDetailsService.loadUserByUsername(user.getUsername()).getAuthorities()));
    }

}
