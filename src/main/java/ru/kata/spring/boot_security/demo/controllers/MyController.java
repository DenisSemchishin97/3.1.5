package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;
@Controller
@RequestMapping("/user")
public class MyController {


        private UserService userService;
        MyController(UserService userService){
            this.userService=userService;
        }
        @GetMapping()
        public String showUserInformation(Principal principal, Model model){
            User user=userService.findByUserName(principal.getName());
            model.addAttribute("user",userService.findUserById(user.getId()));
            return "/user";
        }
}
