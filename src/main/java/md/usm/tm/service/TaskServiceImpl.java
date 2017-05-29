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
    private TaskDaoImpl tweetDao;

    public Task addTask(Task task) {
        return tweetDao.addTask(task);
    }

    public void updateTweet(Task task) {
        tweetDao.updateTask(task);
    }

    public Task deleteTask(Task task) {
        if (task.getUser().getUsername().equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
            return tweetDao.deleteTask(task);
        }
        return null;
    }

    public Task getTaskById(int id) {
        return tweetDao.getTaskById(id);
    }

    public List<Task> getTaskByUserId(int id) {
        return tweetDao.getTaskByUserId(id);
    }


}
