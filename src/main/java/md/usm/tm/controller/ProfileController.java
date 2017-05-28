package md.usm.tm.controller;

import md.usm.tm.model.Task;
import md.usm.tm.model.User;
import md.usm.tm.service.TweetServiceImpl;
import md.usm.tm.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    private TweetServiceImpl tweetService;

    private void init(Model model) {
        model.addAttribute("currentUser", userService.getUserByName(getPrincipal()));
        model.addAttribute("avatar", User.list);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showCurUserProfile() {
        return "redirect:/user/profile/" + getPrincipal();
    }



    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String editProfile(@ModelAttribute("user") User user) {
        User loggedUser = userService.getUserByName(getPrincipal());
        user.setUsersIFollow(new ArrayList<User>());
        user.setUsersIFollow(loggedUser.getUsersIFollow());
        userService.updateUser(user);
        return "redirect:/user/profile/settings";
    }


    @RequestMapping(value = "/edittweet/{id}", method = RequestMethod.POST)
    public String editTweet(@PathVariable int id, @RequestParam String text) {
//        init(model);
//        User user = userService.getUserByName(getPrincipal());
//        model.addAttribute("usersTweets", userService.getAllUsersTweets(user.getId()));
        Task tweet = tweetService.getTweetById(id);
        tweet.setText(text);
        tweetService.updateTweet(tweet);
        return "redirect:/user/profile/";
    }

}
