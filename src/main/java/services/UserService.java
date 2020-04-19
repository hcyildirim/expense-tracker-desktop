package services;

import models.User;
import repositories.UserRepository;

import java.io.IOException;
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

    public void delete(String username) {
        try {
            User user = userRepository.getById(username);

            userRepository.delete(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isUsernameExists(String username) {
        boolean isExists = false;

        try {
            Predicate<User> usernamePredicate = d -> d.getUsername().equalsIgnoreCase(username);

            isExists = userRepository.isExists(usernamePredicate);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return isExists;
    }
}
