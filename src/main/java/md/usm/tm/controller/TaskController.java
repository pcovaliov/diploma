package md.usm.tm.controller;

import md.usm.tm.model.Task;
import md.usm.tm.model.User;
import md.usm.tm.service.*;
import md.usm.tm.validator.TaskValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private StatusServiceImpl statusService;

    @Autowired
    private TaskValidator taskValidator;

    private void init(Model model) {
        User currentUser = userService.getUserByName(getPrincipal());
//        List<Project> projectList = projectService.getAllUsersProjects(currentUser.getId());
//        Map<String, Project> projectMap = new HashMap<>();
//        for (Project project : projectList) {
//            projectMap.put( ((Integer)project.getId()).toString(), project);
//        }

        List<String> statusList = new ArrayList<>();

//        for (StatusEnum status : StatusEnum.values()){
//            statusList.add(status.toString());
//        }
        model.addAttribute("projectList", projectService.getAllUsersProjects(currentUser.getId()));
        model.addAttribute("periodList", periodService.getAllUsersPeriods(currentUser.getId()));
        model.addAttribute("statusList", statusService.getAll());
        model.addAttribute("taskList", taskService.getAllTasks());
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
    public String saveTask(@ModelAttribute("task") Task task, BindingResult bindingResult, Model model,
                           @RequestParam("file") MultipartFile file, HttpSession session) {
        taskValidator.validate(task, bindingResult);
        if (bindingResult.hasErrors()){
            init(model);
            return "tasks";
        }

//        String rootPath = System.getProperty("catalina.home") + File.separator + "webapps";
//        String rootPath = "C://Projects";
        String rootPath = "C://Users//aborisco//IdeaProjects//diploma//src//main//webapp//WEB-INF//resources//uploadFiles";
        String imagePath = "/resources/uploadFiles/";
        String name = null;

        if (!file.isEmpty()) {
            try {
                name = file.getOriginalFilename();
                byte[] bytes = file.getBytes();
                File dir = new File(rootPath + File.separator);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File uploadedFile = new File(dir.getAbsolutePath() + File.separator + name);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
                stream.write(bytes);
                stream.flush();
                stream.close();

            } catch (Exception e) {
                e.getMessage();
                return "redirect:/main";
            }
        }

        User user = userService.getUserByName(getPrincipal());
        task.setUser(user);
        task.setAttachment(imagePath + name);
        if (task.getId() != 0) {
            taskService.update(task);
        } else {
            taskService.save(task);

            List<Task> tasks = taskService.getAllTasks();
            task = tasks.get(tasks.size()-1);
            System.out.println(task);
            task.setShortName(task.getProject().getShortName() + "" + task.getId());
            taskService.update(task);
        }

        return "redirect:/main";
    }

}
