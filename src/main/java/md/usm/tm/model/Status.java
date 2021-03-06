package md.usm.tm.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by pcovaliov on 5/15/2017.
 */

@Entity
@Table(name = "status")
public class Status implements Serializable {

    public Status() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Status)) return false;

        Status status1 = (Status) o;

        if (getId() != status1.getId()) return false;
        if (getDescription() != null ? !getDescription().equals(status1.getDescription()) : status1.getDescription() != null)
            return false;
        return getStatus() != null ? getStatus().equals(status1.getStatus()) : status1.getStatus() == null;

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }


}
