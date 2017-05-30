package md.usm.tm.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by pcovaliov on 5/15/2017.
 */

@Entity
@Table(name = "status")
public class Status implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;


    public Status(String status, String description) {
        this.status = status;
        this.description = description;
    }
    public Status(String status) {
        this.status = status;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
