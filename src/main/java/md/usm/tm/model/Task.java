package md.usm.tm.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;

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
    private OffsetDateTime postDateTime = OffsetDateTime.now();

    @Column(name = "attachment")
    private String attachment;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "period_id")
    private Period period;

    @Column(name = "short_name")
    private String shortName;

    public Task(User user, Status status, String name, String text, OffsetDateTime postDateTime, String attachment, Project project, Period period, String shortName) {
        this.user = user;
        this.status = status;
        this.name = name;
        this.text = text;
        this.postDateTime = postDateTime;
        this.attachment = attachment;
        this.project = project;
        this.period = period;
        this.shortName = shortName;
    }

    public Task() {
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
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

    public OffsetDateTime getPostDateTime() {
        return postDateTime;
    }

    public void setPostDateTime(OffsetDateTime postDateTime) {
        this.postDateTime = postDateTime;
    }


    public String getImage() {
        return attachment;
    }

    public void setImage(String image) {
        this.attachment = image;
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
        if (getAttachment() != null ? !getAttachment().equals(task.getAttachment()) : task.getAttachment() != null)
            return false;
        if (getProject() != null ? !getProject().equals(task.getProject()) : task.getProject() != null) return false;
        if (getPeriod() != null ? !getPeriod().equals(task.getPeriod()) : task.getPeriod() != null) return false;
        return getShortName() != null ? getShortName().equals(task.getShortName()) : task.getShortName() == null;

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getText() != null ? getText().hashCode() : 0);
        result = 31 * result + (getPostDateTime() != null ? getPostDateTime().hashCode() : 0);
        result = 31 * result + (getAttachment() != null ? getAttachment().hashCode() : 0);
        result = 31 * result + (getProject() != null ? getProject().hashCode() : 0);
        result = 31 * result + (getPeriod() != null ? getPeriod().hashCode() : 0);
        result = 31 * result + (getShortName() != null ? getShortName().hashCode() : 0);
        return result;
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
                ", shortName='" + shortName + '\'' +
                '}';
    }

}
