package md.usm.tm.controller;

import md.usm.tm.model.Period;
import md.usm.tm.model.Project;
import md.usm.tm.model.Task;
import md.usm.tm.model.User;
import md.usm.tm.service.TaskServiceImpl;
import md.usm.tm.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pcovaliov on 11/2/2016.
 */

@Controller
@RequestMapping("main")
public class MainController extends BaseController {

    @Autowired
    private TaskServiceImpl taskService;

    @Autowired
    @Qualifier("userServiceImpl")
    private UserServiceImpl userService;

    private void init(Model model) {
        List<Task> todoTasks = new ArrayList<Task>();
        User currentUser = userService.getUserByName(getPrincipal());

        for (int i = 0; i < 20; i++) {
            todoTasks.add(new Task("Task01", currentUser, new Project(), new Period()));
            todoTasks.add(new Task("Task01", currentUser, new Project(), new Period()));
            todoTasks.add(new Task("Task01", currentUser, new Project(), new Period()));
        }

        model.addAttribute("todo", todoTasks);
        model.addAttribute("task", new Task());

        model.addAttribute("currentPage", 0);
    }

    @RequestMapping
    public String allTasks(Model model) {
        init(model);
        return "main";
    }

    @RequestMapping("/page={page}")
    public String allTasksPagination(Model model, @PathVariable int page) {
        int size = 25;
        model.addAttribute("currentPage", page);
        return "main";
    }


    @RequestMapping(value = "/addtask", method = RequestMethod.POST)
    public String addTask(@RequestParam String text, @RequestParam String image) {
        Task task = new Task(text, userService.getUserByName(getPrincipal()), new Project(), new Period());
        if (image != null && !image.isEmpty()) {
            task.setImage(image);
        }
        taskService.addTask(task);
        return "redirect:/main";
    }

    @RequestMapping(value = "/deleteTask/{id}", method = RequestMethod.GET)
    public String deleteTask(@PathVariable int id) {
        taskService.deleteTask(taskService.getTaskById(id));
        return "redirect:/main";
    }

}