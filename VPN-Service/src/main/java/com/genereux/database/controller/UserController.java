package com.genereux.database.controller;


import com.genereux.database.entity.User;
import com.genereux.database.service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.stereotype.Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService theUserService){
        userService = theUserService;
    }

    // add mapping for "/list"
    @GetMapping("/list")
    public String listUsers(Model model){

        List<User> theUsers = userService.findAll();

        // add to the spring model
        model.addAttribute("users", theUsers);
        return "Users/list-users";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){

        // create model attribute to bind form data
        User theUser = new User();

        theModel.addAttribute("user", theUser);

        return "Users/user-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("userId") int theId,  Model theModel){
        // get the user from the service
        User theUser = userService.findById(theId);

        // set user as a model attribute to pre-populate the form
        theModel.addAttribute("user", theUser);

        // send over to our form
        return "Users/user-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("userId") int theId){
        // get the user from the service
        userService.deleteById(theId);

        // send over to our form
        return "redirect:/users/list";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("user") User theUser){

        // save the user
        userService.save(theUser);

        // use a redirect to prevent duplicate submissions
        return "redirect:/users/list";
    }

}
