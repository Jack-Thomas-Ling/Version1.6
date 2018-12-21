package controller;


import io.swagger.annotations.ApiOperation;
import model.Topic;
import model.User;
import model.UserDetailed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.ForumService;
import service.UserService;

import java.util.List;

@Controller
@RequestMapping("/test")

public class IndexController {

    @Autowired
    private UserService userService;
    @Autowired
    private ForumService forumService;

    @RequestMapping(value = "/index1",method = RequestMethod.GET)
    @ApiOperation(value = "index1",httpMethod = "GET")

    public String getToIndex(){
        return "test";
    }

    @RequestMapping(value = "/showinfo")
    public String showInfo(Model model){
        List<User> list=userService.getAllUsers();
        model.addAttribute("users",list);

        return "showInfo";
    }

    @RequestMapping(value = "/showtopic")
    public String showTopic(Model model){
        List<Topic> list=forumService.getAllTopics();
        model.addAttribute("topics",list);

    return "showTopic" ;

    }

    @RequestMapping(value = "/showdetail/{id}",method = RequestMethod.GET)
    public String showDetail(@PathVariable("id") int id ,Model model){

        UserDetailed userDetailed=userService.getDetailById(id);
        model.addAttribute("userDetail",userDetailed);
        return "showUserDetailedInfo";
    }


}
