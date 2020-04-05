package repositories;

import models.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements Repository<User> {

    private final String fileName = "users.txt";

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public List<User> all() throws IOException {
        List<User> users = new ArrayList<User>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        String row;

        while ((row = bufferedReader.readLine()) != null) {
            String[] data = row.split(",");

            User user = new User();
            user.setUsername(data[0]);
            user.setPassword(data[1]);

            users.add(user);
        }

        bufferedReader.close();

        return users;
    }

    @Override
    public User getById(Long id) {
        return null;
    }
}
