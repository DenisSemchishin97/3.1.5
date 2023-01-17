package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;
import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/api/")

public class AdminRestController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleRepository;

    public AdminRestController (UserService userService,PasswordEncoder passwordEncoder,RoleService roleRepository){
        this.userService=userService;
        this.passwordEncoder=passwordEncoder;
        this.roleRepository=roleRepository;
    }

    @GetMapping("users")
    public ResponseEntity<List<User>> showAllUsers() {
        List<User> users = userService.getAllUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping("roles/all")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleRepository.getAllRoles();

        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
    @GetMapping("roles/{id}")
    public ResponseEntity<Role> getRole(@PathVariable Long id) {
        Role role = roleRepository.getRoleById(id);

        return new ResponseEntity<>(role, HttpStatus.OK);
    }
    @GetMapping("users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.findUserById(id);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

@GetMapping("/rest/user")
    public ResponseEntity<User> getUser(Principal principal) {
        return new ResponseEntity<>(userService.findByUserEmail(principal.getName()), HttpStatus.OK);
    }
    @PostMapping("users")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        userService.saveUser(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }


    @PutMapping("users")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        userService.update(user.getId(), user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        userService.deleteByid(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}




