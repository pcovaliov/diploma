package md.iic.netty.service;

import md.iic.netty.dao.UserDaoImpl;
import md.iic.netty.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml")
@Service
@Transactional
public class UserServiceImplTest {

    private static final String username = "Superman";
    private static final String password = "123456";
    private static final String first_name = "Toster";
    private static final String email = "ssoulessa@gmail.com";
    private User user;

    @Autowired
    private UserDaoImpl userDao;

    @Before
    public void setUser() {
        user = new User();
        user.setUsername(username);
        user.setFirst_name(first_name);
        user.setPassword(password);
        user.setEmail(email);
    }


    @Test
    public void testAddUser() throws Exception {
        userDao.addUser(user);
        Assert.assertNotNull(userDao.findByUsername(username));
    }

    @Test
    public void testUpdateUser() throws Exception {
        userDao.addUser(user);
        user.setFirst_name("Vovan");
        userDao.updateUser(user);

        Assert.assertEquals(user, userDao.findByUsername(username));
    }

    @Test
    public void testDeleteUser() throws Exception {
        userDao.addUser(user);
        userDao.deleteUser(userDao.findByUsername(username));

        Assert.assertNull(userDao.findByUsername(username));
    }

    @Test
    public void testGetUserById() throws Exception {
        userDao.addUser(user);

        Assert.assertEquals(user, userDao.getUserById(user.getId()));
    }

    @Test
    public void testGetUserByName() throws Exception {
        userDao.addUser(user);

        Assert.assertEquals(user, userDao.findByUsername(username));
    }

    @Test
    public void testGetIdByName() throws Exception {
        userDao.addUser(user);

        Assert.assertEquals(user.getId(), userDao.getIdByName(username));
    }

    @Test
    @Ignore
    public void testGetAllUsers() throws Exception {

    }

    @Test
    @Ignore
    public void testGetUsersIFollow() throws Exception {

    }

    @Test
    @Ignore
    public void testGetFollowersOffers() throws Exception {

    }

    @Test
    @Ignore
    public void testRemoveUserFromUsersIFollow() throws Exception {

    }

    @Test
    @Ignore
    public void testAddUserToFollower() throws Exception {

    }

    @Test
    @Ignore
    public void testGetAllUsersTweets() throws Exception {

    }

    @Test
    @Ignore
    public void testGetAllTweetsOfUsersIFollow() throws Exception {

    }

    @Test
    @Ignore
    public void testAllTweetsToShow() throws Exception {

    }

    @Test
    @Ignore
    public void testAllTweetsToShow1() throws Exception {

    }

    @Test
    @Ignore
    public void testSort() throws Exception {

    }

    @Test
    @Ignore
    public void testListOfEmails() throws Exception {

    }

    @Test
    @Ignore
    public void testListOfUsernames() throws Exception {

    }

    @Test
    @Ignore
    public void testListOfPasswords() throws Exception {

    }

    @Test
    @Ignore
    public void testGetUsersWhoFollowMe() throws Exception {

    }

    @Test
    @Ignore
    public void testGetUsersWhoFollowMe1() throws Exception {

    }

    @Test
    @Ignore
    public void testGetPrincipal() throws Exception {

    }

    @Test
    @Ignore
    public void testAutologin() throws Exception {

    }
}
