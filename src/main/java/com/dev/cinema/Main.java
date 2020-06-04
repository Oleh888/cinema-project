package com.dev.cinema;

import com.dev.cinema.config.AppConfig;
import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.security.AuthenticationService;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) throws AuthenticationException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        MovieService movieService = context.getBean(MovieService.class);
        Movie movie = new Movie();
        movie.setTitle("it");
        movie.setDescription("second part");
        movie = movieService.add(movie);
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(200);
        CinemaHallService cinemaHallService = context.getBean(CinemaHallService.class);
        cinemaHall = cinemaHallService.add(cinemaHall);
        MovieSession movieSession = new MovieSession();
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setMovie(movie);
        movieSession.setShowTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 30)));
        MovieSessionService movieSessionService = context.getBean(MovieSessionService.class);
        movieSessionService.add(movieSession);
        AuthenticationService authenticationService = context.getBean(AuthenticationService.class);
        authenticationService.register("Alex", "alex@ukr.net", "1");
        User user = authenticationService.login("alex@ukr.net", "1");
        List<MovieSession> availableSession =
                movieSessionService.findAvailableSessions(movie.getId(), LocalDate.now());
        MovieSession selectMovieSession = availableSession.get(0);
        ShoppingCartService shoppingCartService = context.getBean(ShoppingCartService.class);
        shoppingCartService.addSession(selectMovieSession, user);
        ShoppingCart userBucket = shoppingCartService.getByUser(user);
        User user2 = authenticationService
                .register("Bob", "bob@ukr.net", "1");
        OrderService orderService = context.getBean(OrderService.class);
        System.out.println(orderService.completeOrder(userBucket.getTickets(), user2));
        System.out.println(orderService.getOrderHistory(user2).get(0).getTickets());
    }
}
