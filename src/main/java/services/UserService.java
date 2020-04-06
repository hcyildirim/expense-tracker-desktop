package services;

import models.User;
import repositories.UserRepository;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

public class UserService {

    private UserRepository userRepository = new UserRepository();

    public void create(String username, String password) {
        try {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);

            userRepository.create(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    public boolean isUsernameExists(String username) {
        boolean isExists = false;

        try {
            List<User> users = userRepository.all();

            Predicate<User> usernamePredicate = d -> d.getUsername().equalsIgnoreCase(username);

            isExists = userRepository.isExists(users, usernamePredicate);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return isExists;
    }
}
