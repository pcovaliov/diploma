package md.iic.netty.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mbezaliuc on 11/2/2016.
 */

@Entity
@Table(name = "users")
public class User implements Serializable {

    public static List<String> list = new ArrayList<String>() {{
        add("01.png");
        add("02.png");
        add("03.png");
        add("04.png");
        add("05.png");
        add("06.png");
        add("07.png");
        add("08.png");
        add("09.png");
        add("010.png");
        add("011.png");
        add("012.png");
        add("049.png");
        add("014.png");
        add("015.png");
        add("016.png");
        add("017.png");
        add("018.png");
        add("024.png");
        add("025.png");
        add("026.png");
        add("027.png");
        add("028.png");
        add("047.png");
        add("031.png");
        add("032.png");
        add("033.png");
        add("034.png");
        add("035.png");
        add("036.png");
        add("037.png");
        add("038.png");
        add("039.png");
        add("040.png");
        add("041.png");
        add("042.png");
        add("043.png");
        add("044.png");
        add("045.png");
        add("048.png");
        add("050.png");
        add("051.png");
    }};

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Transient
    private String confirmPassword;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "avatar")
    private String avatar = "/resources/pics/useravatar.png";

    @Column(name = "description")
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OrderBy(value = "postDateTime DESC")
    private List<Tweet> allTweets = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 30)
    @JoinTable(name = "followers", joinColumns = @JoinColumn(name = "follower_id"),
            inverseJoinColumns = @JoinColumn(name = "followed_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"followed_id", "follower_id"}))
    private List<User> usersIFollow = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "usersIFollow", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 30)
    private List<User> myFollowers = new ArrayList<>();

    public User() {
    }

    public User(String username, String password, String first_name, String last_name, String email) {
        this.username = username;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Tweet> getAllTweets() {
        return allTweets;
    }

    public void setAllTweets(List<Tweet> allTweets) {
        this.allTweets = allTweets;
    }

    public List<User> getUsersIFollow() {
        return usersIFollow;
    }

    public void setUsersIFollow(List<User> usersIFollow) {
        this.usersIFollow = usersIFollow;
    }

    public List<User> getMyFollowers() {
        return myFollowers;
    }

    public void setMyFollowers(List<User> myFollowers) {
        this.myFollowers = myFollowers;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (first_name != null ? !first_name.equals(user.first_name) : user.first_name != null) return false;
        if (last_name != null ? !last_name.equals(user.last_name) : user.last_name != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        return allTweets != null ? allTweets.equals(user.allTweets) : user.allTweets == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (first_name != null ? first_name.hashCode() : 0);
        result = 31 * result + (last_name != null ? last_name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (allTweets != null ? allTweets.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return username;
    }
}