package md.usm.tm.controller;

import md.usm.tm.model.Period;
import md.usm.tm.model.User;
import md.usm.tm.service.PeriodServiceImpl;
import md.usm.tm.service.UserServiceImpl;
import md.usm.tm.validator.PeriodValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by pcovaliov on 5/29/2017.
 */
@Controller
public class PeriodFormController extends BaseController {

    @Autowired
    private PeriodServiceImpl periodService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private PeriodValidator periodValidator;

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
    public String saveProject(Model model, @ModelAttribute("period") Period period, BindingResult bindingResult) {
        periodValidator.validate(period, bindingResult);
        if (bindingResult.hasErrors()){
            return "periodform";
        }

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
