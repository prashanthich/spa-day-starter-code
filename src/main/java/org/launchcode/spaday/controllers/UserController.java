package org.launchcode.spaday.controllers;

import org.launchcode.spaday.data.UserData;
import org.launchcode.spaday.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {

    @GetMapping
    public String displayAllUsers(Model model) {
        model.addAttribute("users", UserData.getAll());
        return "user/index";
    }

    @GetMapping("add")
    public String displayAddUserForm() {

        return "user/add";
    }

    @PostMapping("add")
    public String processAddUserForm(Model model, @ModelAttribute User newUser, String verify) {
        // add form submission handling code here
        model.addAttribute("userName", newUser.getUserName());
        model.addAttribute("email", newUser.getEmail());
        model.addAttribute("error", "password and verify password" +
                "should be match");
        if (newUser.getPassword().equals(verify)) {
            UserData.add(newUser);
            return "user/index";
        }
        return "user/add";
    }

}
