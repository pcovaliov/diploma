package md.usm.tm.controller;

import md.usm.tm.model.Project;
import md.usm.tm.model.User;
import md.usm.tm.service.ProjectServiceImpl;
import md.usm.tm.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by pcovaliov on 5/29/2017.
 */
@Controller
public class ProjectFormController extends BaseController {

    @Autowired
    private ProjectServiceImpl projectService;

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "user/project/editProject/{id}", method = RequestMethod.GET)
    public String editProject(Model model, @PathVariable int id) {
        model.addAttribute("project", projectService.getById(id));
        return "projectform";
    }

    @RequestMapping(value = "user/project/createProject", method = RequestMethod.GET)
    public String createProject(Model model) {
        model.addAttribute("project", new Project());
        return "projectform";
    }

    @RequestMapping(value = "/saveProject", method = RequestMethod.POST)
    public String saveProject(Model model, @ModelAttribute("project") Project project) {
        User user = userService.getUserByName(getPrincipal());
        project.setUser(user);
        if (project.getId() != 0) {
            projectService.update(project);
        } else {
            projectService.save(project);
        }
        return "redirect:/user/profile/";
    }

}
