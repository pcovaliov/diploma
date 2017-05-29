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

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private StatusEnum status;

    @Column(name = "description")
    private String description;

    public Status(StatusEnum status, String description) {
        this.status = status;
        this.description = description;
    }
    public Status(StatusEnum status) {
        this.status = status;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
