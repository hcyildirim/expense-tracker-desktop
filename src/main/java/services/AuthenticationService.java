package services;

import models.User;
import models.UserSession;
import repositories.UserRepository;

import java.io.IOException;
import java.util.function.Predicate;

public class AuthenticationService {
    private UserRepository userRepository = new UserRepository();

    public boolean logIn(String username, String password) {
        boolean loggedIn = false;

        try {
            Predicate<User> usernamePredicate = d -> d.getUsername().equalsIgnoreCase(username);
            Predicate<User> passwordPredicate = d -> d.getPassword().equals(password);

            if (loggedIn = userRepository.isExists(usernamePredicate.and(passwordPredicate)))
            {
                User user = userRepository.getByUsername(username);
                UserSession.getInstance().setUser(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return loggedIn;
    }

    public void logOut() {
        UserSession.getInstance().removeUser();
    }
}
