package md.usm.tm.controller;

import md.usm.tm.model.Project;
import md.usm.tm.model.Task;
import md.usm.tm.model.User;
import md.usm.tm.service.PeriodServiceImpl;
import md.usm.tm.service.ProjectServiceImpl;
import md.usm.tm.service.TaskServiceImpl;
import md.usm.tm.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("user/profile")
public class ProfileController extends BaseController{

    @Autowired
    @Qualifier("userServiceImpl")
    private UserServiceImpl userService;

    @Autowired
    private TaskServiceImpl taskService;

    @Autowired
    private ProjectServiceImpl projectService;

    @Autowired
    private PeriodServiceImpl periodService;

    private void init(Model model) {

    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showCurUserProfile() {
        return "redirect:/user/profile/" + getPrincipal();
    }
    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public String showCurUserSettings(Model model) {
        String username = getPrincipal();
        model.addAttribute("user", userService.getUserByName(username));
        model.addAttribute("currentUser", userService.getUserByName(username));
        model.addAttribute("avatar", User.list);
        return "usersettings";
    }

    @RequestMapping(value = "{username}", method = RequestMethod.GET)
    public String showProfile(Model model, @PathVariable String username) {

        User user = userService.getUserByName(getPrincipal());
        model.addAttribute("currentUser", userService.getUserByName(getPrincipal()));
        model.addAttribute("projects", projectService.getAllUsersProjects(user.getId()));
        model.addAttribute("periods", periodService.getAllUsersPeriods(user.getId()));
        model.addAttribute("user", user);
        model.addAttribute("avatar", User.list);
        model.addAttribute("tasks",taskService.getTaskByUserId(user.getId()));

        return "coolprofile";
    }


    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String editProfile(@ModelAttribute("user") User user) {
        User loggedUser = userService.getUserByName(getPrincipal());

        userService.updateUser(user);
        return "redirect:/user/profile/settings";
    }


    @RequestMapping(value = "/edittask/{id}", method = RequestMethod.GET)
    public String editTask(@PathVariable int id, @RequestParam String text) {
        Task tweet = taskService.getTaskById(id);
        tweet.setText(text);
        taskService.updateTweet(tweet);
        return "redirect:/user/profile/";
    }

    @RequestMapping(value = "editProject/{id}", method =  RequestMethod.POST)
    public String editProject(@PathVariable int id){
        return "redirect:/user/profile/";
    }

    @RequestMapping(value = "/deleteProject/{id}", method =  RequestMethod.GET)
    public String deleteProject(@PathVariable int id){
        Project project = projectService.getById(id);
        projectService.deleteProject(project);
        return "redirect:/user/profile/";
    }
}
