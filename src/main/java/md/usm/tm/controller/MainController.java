package md.usm.tm.controller;

import md.usm.tm.model.Project;
import md.usm.tm.model.Task;
import md.usm.tm.model.User;
import md.usm.tm.service.ProjectServiceImpl;
import md.usm.tm.service.StatusServiceImpl;
import md.usm.tm.service.TaskServiceImpl;
import md.usm.tm.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pcovaliov on 11/2/2016.
 */

@Controller
//@RequestMapping("main")
public class MainController extends BaseController {

    @Autowired
    private TaskServiceImpl taskService;

    @Autowired
    @Qualifier("userServiceImpl")
    private UserServiceImpl userService;

    @Autowired
    private ProjectServiceImpl projectService;

    @Autowired
    private StatusServiceImpl statusService;

    //TODO: remove thismethod
    private List<Task> initTestTasks(){
        List<Task> retval = new ArrayList<>();

        return retval;
    }

    private void init(Model model) {

        User currentUser = userService.getUserByName(getPrincipal());

        List<Project> projectList = projectService.getAllUsersProjects(currentUser.getId());
        List<Task> todoTasks = taskService.getTaskByUserId(currentUser.getId());

        model.addAttribute("todo", todoTasks);
        model.addAttribute("projectList", projectList);
        model.addAttribute("task", new Task());
        model.addAttribute("projects", new Project());

        model.addAttribute("currentPage", 0);
    }

    @RequestMapping
    public String allTasks(Model model) {
        init(model);
        return "main";
    }

    @RequestMapping(value = "/sortTask", method = RequestMethod.POST)
    public String sortRedirect(Model model, @ModelAttribute("projects") Project project) {
        project = projectService.findByProjectName(project.getProjectName());

        return "redirect:/sortTask/" + project.getId();
    }

   /* @RequestMapping(value = "/sortTask{idis}")
    public String sortRedirect2(Model model, @PathVariable("idis")int idis) {

        System.out.println("herererere ============================================================");
        System.out.println(idis + "===========================================================");
        return "redirect:/sortTask/" + idis;
    }
*/
    @RequestMapping(value = "/sortTask/{projectID}")
    public String sorting(Model model, @PathVariable("projectID")int projectID ) {

        User currentUser = userService.getUserByName(getPrincipal());
        List<Project> projectList = projectService.getAllUsersProjects(currentUser.getId());
        List<Task> todoTasks = taskService.getTasksByProject(projectID);
        Project project = projectService.getById(projectID);

        model.addAttribute("todo", todoTasks);
        model.addAttribute("projectList", projectList);
        model.addAttribute("task", new Task());
        model.addAttribute("projects", project);

        model.addAttribute("currentPage", 0);

        model.addAttribute("currentProject", projectID);
        return "main";
    }

    @RequestMapping(value = "/startProgress/{startID}", method = RequestMethod.POST)
    public String startProgress(Model model, @ModelAttribute("currentProject") String currentProject,
                                @PathVariable("startID") Integer startID ) {
        System.out.println(currentProject + "-----------------------------==============================================");

        Task task = taskService.getTaskById(startID);
        task.setStatus(statusService.getStatusById(2));
        taskService.update(task);
        Project project = projectService.getById(task.getProject().getId());

        if (currentProject.equals("")){
            return "redirect:/main";
        } else {
            return "redirect:/sortTask/" + project.getId();
        }

    }

    //Добить просмотр таска в сплывающемся окне
    //В сплывающем окне добавить опции по редактированию и так далее

//    @RequestMapping("/page={page}")
//    public String allTasksPagination(Model model, @PathVariable int page) {
//        int size = 25;
//        model.addAttribute("currentPage", page);
//        return "main";
//    }


//    @RequestMapping(value = "/addtask", method = RequestMethod.POST)
//    public String addTask(@RequestParam String text, @RequestParam String image) {
//        Task task = new Task();
//        if (image != null && !image.isEmpty()) {
//            task.setImage(image);
//        }
//        taskService.addTask(task);
//        return "redirect:/main";
//    }

    @RequestMapping(value = "/deleteTask/{id}", method = RequestMethod.GET)
    public String deleteTask(@PathVariable int id) {
        taskService.deleteTask(taskService.getTaskById(id));
        return "redirect:/main";
    }

}