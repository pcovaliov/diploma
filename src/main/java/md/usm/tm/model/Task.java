package md.usm.tm.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by pcovaliov on 11/2/2016.
 */

@Entity
@Table(name = "task")
public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
//    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "status_id")
    private Status status;

    @Column(name = "name")
    private String name;

    @Column(name = "text")
    private String text;

    @Column(name = "postDateTime")
    private LocalDateTime postDateTime = LocalDateTime.now();

    @Column(name = "attachment")
    private String attachment;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "period_id")
    private Period period;

    public Task(User user, Status status, String name, String text, LocalDateTime postDateTime, String attachment, Project project, Period period) {
        this.user = user;
        this.status = status;
        this.name = name;
        this.text = text;
        this.postDateTime = postDateTime;
        this.attachment = attachment;
        this.project = project;
        this.period = period;
    }

    public Task() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
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


    public String getImage() {
        return attachment;
    }

    public void setImage(String image) {
        this.attachment = image;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", user=" + user +
                ", status=" + status +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", postDateTime=" + postDateTime +
                ", attachment='" + attachment + '\'' +
                ", project=" + project +
                ", period=" + period +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;

        Task task = (Task) o;

        if (getId() != task.getId()) return false;
        if (getUser() != null ? !getUser().equals(task.getUser()) : task.getUser() != null) return false;
        if (getStatus() != null ? !getStatus().equals(task.getStatus()) : task.getStatus() != null) return false;
        if (getName() != null ? !getName().equals(task.getName()) : task.getName() != null) return false;
        if (getText() != null ? !getText().equals(task.getText()) : task.getText() != null) return false;
        if (getPostDateTime() != null ? !getPostDateTime().equals(task.getPostDateTime()) : task.getPostDateTime() != null)
            return false;
        if (attachment != null ? !attachment.equals(task.attachment) : task.attachment != null) return false;
        if (getProject() != null ? !getProject().equals(task.getProject()) : task.getProject() != null) return false;
        return getPeriod() != null ? getPeriod().equals(task.getPeriod()) : task.getPeriod() == null;

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getText() != null ? getText().hashCode() : 0);
        result = 31 * result + (getPostDateTime() != null ? getPostDateTime().hashCode() : 0);
        result = 31 * result + (attachment != null ? attachment.hashCode() : 0);
        result = 31 * result + (getProject() != null ? getProject().hashCode() : 0);
        result = 31 * result + (getPeriod() != null ? getPeriod().hashCode() : 0);
        return result;
    }
}
