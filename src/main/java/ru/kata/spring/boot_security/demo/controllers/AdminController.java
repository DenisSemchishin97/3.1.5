package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.services.UserService;
import java.security.Principal;
@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public AdminController(UserService userService, PasswordEncoder passwordEncoder,
                           RoleRepository roleRepository) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }




    @GetMapping("/users")
    public String getAllUsers(Model model,Principal principal) {
        User user=userService.findByUserName(principal.getName());
        model.addAttribute("user",userService.findUserById(user.getId()));
        model.addAttribute("AllUsers", userService.getAllUsers());
        model.addAttribute("role",user.getRoles());
        model.addAttribute("roles");
        return "infoAboutUsers";
    }

    @PostMapping("/users/addUser")
    public String addUser(@ModelAttribute("user") User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        return "redirect:/admin/users";
    }
    @GetMapping(value="/new")
    public String addNewUser(Model model,Principal principal){
        User user=userService.findByUserName(principal.getName());
        model.addAttribute("user",userService.findUserById(user.getId()));
        model.addAttribute("role",user.getRoles());

        return "/addNewUser";
    }

    @GetMapping ("/users/update/{id}")
//    /admin/user/edit/admin/users/update/{id}
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.update(user.getId(), user);
        return "redirect:/admin/users";
    }



    @GetMapping("/delete/{id}")
    public String delete(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.delete(user.getId(),user);
        return "redirect:/admin/users";
    }

    @GetMapping("/getOne")
    @ResponseBody
    public User getOne(Long id) {
        return userService.findUserById(id);
    }
}