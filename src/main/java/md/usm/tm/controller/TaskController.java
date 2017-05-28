package md.usm.tm.controller;

import md.usm.tm.model.Project;
import md.usm.tm.model.Task;
import md.usm.tm.model.User;
import md.usm.tm.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pcovaliov on 5/17/2017.
 */

@Controller
@RequestMapping("task")
public class TaskController extends BaseController{

    @Autowired
    UserServiceImpl userService;

    private void init(Model model) {
        System.out.println("Started INIT");
        List<Project> todoTasks = new ArrayList<Project>();
        //User currentUser = userService.getUserByName(getPrincipal());

        todoTasks.add(new Project("Some Project 1","Some Project 1"));
        todoTasks.add(new Project("Some Project 1","Some Project 2"));
        todoTasks.add(new Project("Some Project 1","Some Project 3"));

        model.addAttribute("projectList",todoTasks);

        System.out.println("END INIT");
    }

    @RequestMapping
    public String allTasks(Model model) {
        init(model);
        return "tasks";
    }

}
