package services;

import models.User;
import repositories.UserRepository;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

public class UserService {

    private UserRepository userRepository = new UserRepository();

    public boolean authenticate(String username, String password) {
        boolean isAuthenticated = false;

        try {
            List<User> users = userRepository.all();

            Predicate<User> usernamePredicate = d -> d.getUsername().equalsIgnoreCase(username);
            Predicate<User> passwordPredicate = d -> d.getPassword().equals(password);

            isAuthenticated = userRepository.isExists(users, usernamePredicate.and(passwordPredicate));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return isAuthenticated;
    }

    public void delete(String username) {
        try {
            List<User> users = userRepository.all();

            Predicate<User> usernamePredicate = d -> d.getUsername().equalsIgnoreCase(username);
            users = userRepository.filterBy(users, usernamePredicate.negate());

            userRepository.delete(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
