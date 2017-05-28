package md.usm.tm.controller;

import md.usm.tm.model.Period;
import md.usm.tm.model.Project;
import md.usm.tm.model.Task;
import md.usm.tm.model.User;
import md.usm.tm.service.TaskServiceImpl;
import md.usm.tm.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@RestController
public class RestService extends BaseController{

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private TaskServiceImpl tweetService;

    @RequestMapping(value = "/allusers/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllTweets() {
        List<User> users = userService.getAllUsers();

        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("id") int id) {
        System.out.println("Fetching User with id " + id);
        User user = userService.getUserById(id);
        if (user == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }


    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile file, HttpSession session) {
        String rootPath = System.getProperty("catalina.home") + File.separator + "webapps";

        String name = null;

        if (!file.isEmpty()) {
            try {
                name = file.getOriginalFilename();
                System.out.println(name);
                byte[] bytes = file.getBytes();
                File dir = new File(rootPath + File.separator + "images");
                System.out.println(dir);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File uploadedFile = new File(dir.getAbsolutePath() + File.separator + name);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
                stream.write(bytes);
                stream.flush();
                stream.close();

                return name;

            } catch (Exception e) {
            }
        } else {
            return null;
        }
        return null;
    }

    @RequestMapping(value = "/addtask", method = RequestMethod.POST)
    public String addTweet(@RequestParam String text, @RequestParam String image) {
        Task tweet = new Task(text, userService.getUserByName(getPrincipal()), new Project(), new Period());
        if (image != null && !image.isEmpty()) {
            tweet.setImage(image);
        }
        tweetService.addTask(tweet);
        return "redirect:/main";
    }

}