package md.usm.tm.dao;

import md.usm.tm.model.Task;
import md.usm.tm.model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by pcovaliov on 11/2/2016.
 */

@Repository
@SuppressWarnings(value = "unchecked")
public class UserDaoImpl {

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    public User addUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
        logger.info("User was added");
        return user;
    }

    public void updateUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
        logger.info("User was updated");
    }

    public User deleteUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.createNativeQuery("delete from followers where (followed_id=:user_id) or (follower_id=:user_id)")
                .setParameter("user_id", user.getId());
        session.delete(user);
        logger.info("User was deleted");
        return user;
    }

    public User getUserById(int id) {
        return (User) sessionFactory.getCurrentSession()
                .createNativeQuery(
                        "SELECT * FROM users WHERE id=:id", User.class)
                .setParameter("id", id)
                .uniqueResult();
    }

    public List<User> getAllUsers() {
        return sessionFactory.getCurrentSession()
                .createNativeQuery("SELECT * FROM users", User.class)
                .getResultList();
    }

    public List<Task> getAllUsersTasks(int user_id) {
        return sessionFactory.getCurrentSession().createNativeQuery(
                "SELECT * FROM task WHERE user_id=:user_id ORDER BY postDateTime DESC", Task.class)
                .setParameter("user_id", user_id)
                .getResultList();
    }


    public int getIdByName(String username) {
        User user = findByUsername(username);
        return user.getId();
    }

    public User findByUsername(String username) {
        return (User) sessionFactory.getCurrentSession()
                .createNativeQuery(
                        "SELECT * FROM users " +
                                "WHERE username=:username", User.class)
                .setParameter("username", username)
                .uniqueResult();
    }

    public List<String> getAllEmails() {
        return sessionFactory.getCurrentSession()
                .createNativeQuery("select email from users")
                .list();
    }

    public List<String> getAllUsernames() {
        return sessionFactory.getCurrentSession()
                .createNativeQuery("select username from users")
                .list();
    }

}
