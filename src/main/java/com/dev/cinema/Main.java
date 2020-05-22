package com.dev.cinema;

import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.User;
import com.dev.cinema.security.AuthenticationService;
import com.dev.cinema.service.UserService;

public class Main {
    private static final Injector INJECTOR = Injector.getInstance("com.dev.cinema");

    public static void main(String[] args) {
        UserService userService =
                (UserService) INJECTOR.getInstance(UserService.class);
        AuthenticationService authenticationService =
                (AuthenticationService) INJECTOR.getInstance(AuthenticationService.class);
        User user = new User();
        authenticationService.register("user@ukr.net", "password");
        System.out.println(userService.findByEmail("user@ukr.net"));
    }
}
