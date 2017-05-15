package md.usm.tm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pcovaliov on 11/2/2016.
 */

@Entity
@Table(name = "task")
public class Task implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "text")
    private String text;

    @Column(name = "postDateTime")
    private LocalDateTime postDateTime = LocalDateTime.now().plusHours(2);

    @Column(name="likes")
    private int likes = 0;

    @Column(name = "image")
    private String image;

    @JsonIgnore
    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OrderBy(value = "postDateTime ASC")
    private List<Comment> taskComments = new ArrayList<>();

    public Task() {
    }

    public Task(User user, String text) {
        this.user = user;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getPostDateTime() {
        return postDateTime;
    }

    public void setPostDateTime(LocalDateTime postDateTime) {
        this.postDateTime = postDateTime;
    }

    public List<Comment> gettaskComments() {
        return taskComments;
    }

    public void settaskComments(List<Comment> taskComments) {
        this.taskComments = taskComments;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (id != task.id) return false;
        if (user != null ? !user.equals(task.user) : task.user != null) return false;
        if (text != null ? !text.equals(task.text) : task.text != null) return false;
        return postDateTime != null ? postDateTime.equals(task.postDateTime) : task.postDateTime == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (postDateTime != null ? postDateTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", user=" + user +
                ", text='" + text + '\'' +
                ", postDateTime=" + postDateTime +
                '}';
    }
}
