package md.usm.tm.model;

import javax.persistence.*;

/**
 * Created by pcovaliov on 11/24/2016.
 */

@Entity
@Table(name = "likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Like() {
    }

    public Like(Task tweet, User user) {
        this.task = tweet;
        this.user = user;
    }

    public Task getTweet() {
        return task;
    }

    public void setTweet(Task tweet) {
        this.task = tweet;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Like like = (Like) o;

        if (task != null ? !task.equals(like.task) : like.task != null) return false;
        return user != null ? user.equals(like.user) : like.user == null;

    }

    @Override
    public int hashCode() {
        int result = task != null ? task.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Like{" +
                "tweet=" + task +
                ", user=" + user +
                '}';
    }
}
