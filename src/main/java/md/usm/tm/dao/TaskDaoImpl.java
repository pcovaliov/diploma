package md.usm.tm.dao;

import md.usm.tm.model.Task;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.RootLogger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by pcovaliov on 11/2/2016.
 */

@Repository
@SuppressWarnings("unchecked")
public class TaskDaoImpl {

    private static final Logger logger = RootLogger.getLogger(TaskDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    public Task addTask(Task task) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(task);

        logger.info("Task was added");

        return task;
    }

    public Task updateTask(Task task) {
        Session session = sessionFactory.getCurrentSession();
        session.update(task);
        logger.info("Task was updated");

        return task;
    }

    public Task deleteTask(Task task) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(task);
        logger.info("Task was deleted");
        return task;
    }

    public Task getTaskById(int id) {
        return (Task) sessionFactory.getCurrentSession()
                .createNativeQuery("SELECT * FROM task WHERE id=:id", Task.class)
                .setParameter("id", id)
                .getResultList().get(0);
    }


}