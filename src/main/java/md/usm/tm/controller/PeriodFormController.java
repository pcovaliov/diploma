package md.usm.tm.controller;

import md.usm.tm.model.Period;
import md.usm.tm.model.Project;
import md.usm.tm.model.User;
import md.usm.tm.service.PeriodServiceImpl;
import md.usm.tm.service.ProjectServiceImpl;
import md.usm.tm.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;

/**
 * Created by pcovaliov on 5/29/2017.
 */
@Controller
public class PeriodFormController extends BaseController {

    @Autowired
    private PeriodServiceImpl periodService;

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "user/profile/editPeriod/{id}", method = RequestMethod.GET)
    public String editProject(Model model, @PathVariable int id) {
        model.addAttribute("period", periodService.getById(id));
        return "periodform";
    }

    @RequestMapping(value = "user/profile/createPeriod", method = RequestMethod.GET)
    public String createProject(Model model) {
        model.addAttribute("period", new Period());
        return "periodform";
    }

    @RequestMapping(value = "/savePeriod", method = RequestMethod.POST)
    public String saveProject(Model model, @ModelAttribute("period") Period period) {
        User user = userService.getUserByName(getPrincipal());
        period.setUser(user);
        if (period.getId() != 0) {
            periodService.update(period);
        } else {
            periodService.save(period);
        }
        return "redirect:/user/profile/";
    }

}
