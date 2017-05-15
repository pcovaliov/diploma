package md.usm.tm.model;

import javax.persistence.*;

/**
 * Created by pcovaliov on 5/15/2017.
 */

@Entity
@Table(name = "status")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task_id;

    @Column(name = "status")
    private String status;


}
