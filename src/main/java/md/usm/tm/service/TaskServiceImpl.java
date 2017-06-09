package md.usm.tm.service;

import md.usm.tm.dao.TaskDaoImpl;
import md.usm.tm.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by pcovaliov on 11/2/2016.
 */

@Service
@Transactional
public class TaskServiceImpl {

    @Autowired
    private TaskDaoImpl taskDao;

    public Task addTask(Task task) {
        return taskDao.addTask(task);
    }

    public Task save(Task task) { return taskDao.persist(task); }

    public void update(Task task) {
        taskDao.update(task);
    }

    public Task deleteTask(Task task) {
        if (task.getUser().getUsername().equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
            return taskDao.deleteTask(task);
        }
        return null;
    }

    public Task getTaskById(int id) {
        return taskDao.getTaskById(id);
    }

    public List<Task> getTaskByUserId(int id) {
        return taskDao.getTaskByUserId(id);
    }

    public List<Task> getAllTasks(){
        return taskDao.getAllTasks();
    }

    public List<Task> getTasksByProject(int project_id){return taskDao.getTasksByProject(project_id);}
}
