package com.dev.cinema.security;

import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import com.dev.cinema.service.UserService;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userService.findByEmail(email);
        org.springframework.security.core.userdetails.User.UserBuilder userBuilder =
                org.springframework.security.core.userdetails.User.withUsername(email);
        if (optionalUser.isPresent()) {
            userBuilder.password(new BCryptPasswordEncoder()
                    .encode(optionalUser.get().getPassword()));
            userBuilder.roles(optionalUser.get().getRoles().stream()
                    .map(Role::getRoleName)
                    .map(Enum::name)
                    .toArray(String[]::new));
        } else {
            throw new UsernameNotFoundException("User with email " + email + " was not found");
        }
        return userBuilder.build();
    }
}
