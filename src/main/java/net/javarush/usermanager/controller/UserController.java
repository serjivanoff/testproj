package net.javarush.usermanager.controller;
import net.javarush.usermanager.model.User;
import net.javarush.usermanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private UserService userService;

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "users", method = RequestMethod.GET)
    public String listUsers(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("listUsers", this.userService.listUsers());
        return "users";
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user) {
        if (user.getId() == 0) this.userService.addUser(user);
        else
            this.userService.updateUser(user);
        return "redirect:/users";
    }

    @RequestMapping("/remove/{id}")
    public String removeUser(@PathVariable("id") int id) {
        this.userService.removeUser(id);
        return "redirect:/users";
    }

    @RequestMapping("/edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", this.userService.getUserById(id));
        model.addAttribute("listUsers", this.userService.listUsers());
        return "users";
    }

    @RequestMapping("userdata/{id}")
    public String userData(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", this.userService.getUserById(id));
        return "userdata";
    }
    @RequestMapping("search/")
    public String userSearch(@RequestParam("q")String name, Model model){
        model.addAttribute("userSearch",this.userService.getUsersByName(name));
        return "search";
    }
    @RequestMapping("nextpage")
    public String nextPage(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("listUsers", this.userService.nextPage());
        return "users";
    }
    @RequestMapping("prevpage")
    public String prevPage(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("listUsers", this.userService.prevPage());
        return "users";
    }

}