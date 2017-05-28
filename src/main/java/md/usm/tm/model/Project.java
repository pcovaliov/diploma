package md.usm.tm.model;

import javax.persistence.*;

/**
 * Created by pcovaliov on 5/15/2017.
 */

@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "short_name")
    private String shortName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Project() {
    }

    public Project(String projectName, String shortName) {
        this.projectName = projectName;
        this.shortName = shortName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
