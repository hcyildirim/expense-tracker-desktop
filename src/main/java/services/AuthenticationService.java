package services;

import models.AuthUser;
import models.User;
import repositories.UserRepository;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

public class AuthenticationService {
    private UserRepository userRepository = new UserRepository();

    public boolean logIn(String username, String password) {
        boolean loggedIn = false;

        try {
            List<User> users = userRepository.all();

            Predicate<User> usernamePredicate = d -> d.getUsername().equalsIgnoreCase(username);
            Predicate<User> passwordPredicate = d -> d.getPassword().equals(password);

            if (loggedIn = userRepository.isExists(users, usernamePredicate.and(passwordPredicate)))
            {
                AuthUser.getInstance(username);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return loggedIn;
    }

    public void logOut(String username) {
        AuthUser session = AuthUser.getInstance(username);
        session.cleanUserSession();
    }
}
