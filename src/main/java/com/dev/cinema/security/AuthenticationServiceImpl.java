package com.dev.cinema.security;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.model.User;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import com.dev.cinema.util.HashUtil;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final HashUtil hashUtil;

    public AuthenticationServiceImpl(ShoppingCartService shoppingCartService,
                                     UserService userService, HashUtil hashUtil) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.hashUtil = hashUtil;
    }

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User userFromDB = userService.findByEmail(email).orElseThrow(() ->
                new AuthenticationException("Incorrect email or password!"));
        if (isValid(userFromDB, password)) {
            return userFromDB;
        }
        throw new AuthenticationException("Incorrect email or password!");
    }

    @Override
    public User register(String name, String email, String password) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setSalt(hashUtil.getSalt());
        user.setPassword(hashUtil.hashPassword(user.getPassword(), user.getSalt()));
        userService.add(user);
        shoppingCartService.registerNewShoppingCart(user);
        return user;
    }

    private boolean isValid(User user, String password) {
        return hashUtil.hashPassword(password, user.getSalt()).equals(user.getPassword());
    }
}
