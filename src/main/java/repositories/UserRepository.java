package repositories;

import models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UserRepository implements Repository<User> {

    private final String fileName = "users.txt";

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public void delete(List<User> users) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));

        Iterator itr = users.iterator();

        while (itr.hasNext()) {
            User user = (User) itr.next();
            bufferedWriter.write(user.getUsername() + "," + user.getPassword() + "\n");
        }

        bufferedWriter.close();
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
    public List<User> filterBy(List<User> users, Predicate<User> predicate) throws IOException {
        return users.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    @Override
    public User findBy(List<User> users, Predicate<User> predicate) throws IOException {
        return users.stream()
                .filter(predicate)
                .findFirst().orElse(null);
    }

    @Override
    public boolean isExists(List<User> users, Predicate<User> predicate) throws IOException {
        return users.stream()
                .anyMatch(predicate);
    }
}
