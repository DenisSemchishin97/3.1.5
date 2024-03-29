package ru.kata.spring.boot_security.demo.util;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;
@Component
public class Creat {
    private RoleService roleRepository;
    private UserService userRepository;

    Creat(UserService userRepository,RoleService roleRepository){
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;

    }
    @PostConstruct
    public void run(){
        Role role = new Role("Admin",1);
        Role role1 = new Role("user",2);
        roleRepository.saveRole(role);
        roleRepository.saveRole(role1);
        Set<Role>SetRoles=new HashSet<>();
        SetRoles.add(role);
        SetRoles.add(role1);
        Set<Role>users=new HashSet<>();
        users.add(role1);
        User user = new User("Denis","user", "100",25L,"denis@mail.ru",SetRoles);
        User user1 = new User("noname","user1","100",33L,"noname@mail.com",users);
        userRepository.saveUser(user);
        userRepository.saveUser(user1);
    }

}


