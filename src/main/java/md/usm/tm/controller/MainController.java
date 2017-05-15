package md.usm.tm.controller;

import md.usm.tm.model.Comment;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;

/**
 * Created by pcovaliov on 11/2/2016.
 */

@Controller
@RequestMapping("main")
public class MainController {

    @Autowired
    private TweetServiceImpl tweetService;

    @Autowired
    @Qualifier("userServiceImpl")
    private UserServiceImpl userService;

    private void init(Model model) {
        model.addAttribute("task",new Task());
        model.addAttribute("tweet", new Task());
        model.addAttribute("followMe", userService.getUsersWhoFollowMe());
        model.addAttribute("myTweets", userService.getAllUsersTweets(userService.getIdByName(getPrincipal())));
        model.addAttribute("allTweets", userService.allTweetsToShow(0, 25));
        model.addAttribute("IFollow", userService.getUsersIFollow());
        model.addAttribute("followingOffers", userService.getFollowersOffers(5));
        model.addAttribute("currentUser", userService.getUserByName(getPrincipal()));
        model.addAttribute("avatar", User.list);
        model.addAttribute("maxPage", userService.allTweetsToShow().size());
        model.addAttribute("currentPage", 0);
    }

    @RequestMapping
    public String allTweets(Model model) {
        init(model);
        return "tweet";
    }

    @RequestMapping("/page={page}")
    public String allTweetsPagination(Model model, @PathVariable int page) {
        int size = 25;
        model.addAttribute("currentPage", page);
        model.addAttribute("tweet", new Task());
        model.addAttribute("allTweets", userService.allTweetsToShow(page * size, size));
        model.addAttribute("IFollow", userService.getUsersIFollow());
        model.addAttribute("followingOffers", userService.getFollowersOffers(5));
        model.addAttribute("currentUser", userService.getUserByName(getPrincipal()));
        model.addAttribute("maxPage", userService.allTweetsToShow().size());
        model.addAttribute("avatar", User.list);
        model.addAttribute("uploaded", new String("file:/" + System.getProperty("catalina.home") + File.separator + "webapps" + File.separator + "images" + File.separator));
        return "tweet";
    }

    @RequestMapping("/{id}")
    public String showCurrentTweet(Model model, @PathVariable int id) {
        model.addAttribute("tweet", tweetService.getTweetById(id));
        model.addAttribute("comment", new Comment());
        model.addAttribute("comments", tweetService.getComments(id));
        return "redirect:/tweet";
    }

    @RequestMapping(value = "/addtweet", method = RequestMethod.POST)
    public String addTweet(@RequestParam String text, @RequestParam String image) {
        Task task = new Task(userService.getUserByName(getPrincipal()), text);
        if (image != null && !image.isEmpty()) {
            task.setImage(image);
        }
        tweetService.addTweet(task);
        return "redirect:/main";
    }

    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    @RequestMapping(value = "/deleteTweet/{id}", method = RequestMethod.GET)
    public String deleteTweet(@PathVariable int id) {
        tweetService.deleteTweet(tweetService.getTweetById(id));
        return "redirect:/user/profile/";
    }



}