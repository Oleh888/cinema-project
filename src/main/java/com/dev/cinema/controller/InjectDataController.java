package com.dev.cinema.controller;

import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import com.dev.cinema.service.RoleService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inject")
public class InjectDataController {
    private final RoleService roleService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final ShoppingCartService shoppingCartService;

    @Autowired
    public InjectDataController(RoleService roleService, UserService userService,
                                PasswordEncoder passwordEncoder,
                                ShoppingCartService shoppingCartService) {
        this.roleService = roleService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.shoppingCartService = shoppingCartService;
    }

    @PostConstruct
    public void init() {
        injectRoles();
        injectUsers();
    }

    private void injectRoles() {
        roleService.add(new Role(Role.RoleName.ADMIN));
        roleService.add(new Role(Role.RoleName.USER));
    }

    private void injectUsers() {
        User admin = new User();
        admin.setEmail("admin@ukr.net");
        admin.setPassword(passwordEncoder.encode("123456"));
        admin.setRoles(Set.of(roleService.getRoleByName("ADMIN")));
        User user = new User();
        user.setEmail("user@ukr.net");
        user.setPassword(passwordEncoder.encode("123456"));
        user.setRoles(Set.of(roleService.getRoleByName("USER")));
        userService.add(admin);
        userService.add(user);
        shoppingCartService.registerNewShoppingCart(user);
    }
}
