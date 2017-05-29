package md.usm.tm.controller;

import md.usm.tm.model.*;
import md.usm.tm.service.PeriodServiceImpl;
import md.usm.tm.service.ProjectServiceImpl;
import md.usm.tm.service.TaskServiceImpl;
import md.usm.tm.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pcovaliov on 5/17/2017.
 */

@Controller
public class TaskController extends BaseController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ProjectServiceImpl projectService;

    @Autowired
    private PeriodServiceImpl periodService;

    @Autowired
    private TaskServiceImpl taskService;

    private void init(Model model) {
        User currentUser = userService.getUserByName(getPrincipal());
//        List<Project> projectList = projectService.getAllUsersProjects(currentUser.getId());
//        Map<String, Project> projectMap = new HashMap<>();
//        for (Project project : projectList) {
//            projectMap.put( ((Integer)project.getId()).toString(), project);
//        }

        model.addAttribute("projectList", projectService.getAllUsersProjects(currentUser.getId()));
        model.addAttribute("periodList", periodService.getAllUsersPeriods(currentUser.getId()));

        model.addAttribute("taskName");
//        model.addAttribute("project_id");
//        model.addAttribute("period_id");
//        model.addAttribute("taskText");
//        model.addAttribute("task_id");
    }


    @RequestMapping(value = "task")
    public String allTasks(Model model) {
        init(model);
        model.addAttribute("task", new Task());
        return "tasks";
    }


    @RequestMapping(value = "/saveTask", method = RequestMethod.POST)
    public String saveTask(Model model) {
        Task task = new Task();
        User user = userService.getUserByName(getPrincipal());
        task.setUser(user);
        if (task.getId() != 0) {
            taskService.update(task);
        } else {
            task.setStatus(new Status(StatusEnum.NOT_STARTED));
            taskService.save(task);
        }
        return "main";
    }

}
