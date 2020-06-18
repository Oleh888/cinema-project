package com.dev.cinema.controller;

import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import com.dev.cinema.service.RoleService;
import com.dev.cinema.service.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inject")
public class InjectDataController {
    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public InjectDataController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void injectRolesToDb() {
        roleService.add(Role.of("USER"));
        roleService.add(Role.of("ADMIN"));
    }

    @PostMapping
    public String injectAdminToDB() {
        User admin = new User();
        admin.setEmail("admin@ukr.net");
        admin.setPassword("123456");
        Role adminRole = roleService.getRoleByName("ADMIN");
        admin.setRoles(Set.of(adminRole));
        userService.add(admin);
        return "Admin successfully added to DB email - admin@ukr.net, password - 123456";
    }
}
