package md.usm.tm.controller;

import md.usm.tm.model.User;
import md.usm.tm.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by pcovaliov on 11/2/2016.
 */

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserServiceImpl userService;

    @RequestMapping(value = "")
    public String listUsers(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("listUsers", userService.getAllUsers());
        model.addAttribute("myTweets", userService.getAllUsersTweets(userService.getIdByName(getPrincipal())));
        model.addAttribute("followMe", userService.getUsersWhoFollowMe());
        model.addAttribute("currentUser", userService.getUserByName(getPrincipal()));
        model.addAttribute("IFollow", userService.getUsersIFollow());
        return "users";
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String deleteUser() {
        User user = userService.getUserByName(getPrincipal());
        userService.deleteUser(user);
        return "redirect:/login";
    }

    @RequestMapping(value = "/addAvatar/{avatar}", method = RequestMethod.POST)
    public String addAvatar(@PathVariable String avatar) {
        avatar = "/resources/logos/" + avatar;
        User user = userService.getUserByName(getPrincipal());
        user.setAvatar(avatar);
        userService.updateUser(user);
        return "redirect:/user/profile";
    }

    public String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

}
