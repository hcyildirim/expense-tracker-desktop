package repositories;

import models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UserRepository implements Repository<User, String> {

    private final String fileName = "users.txt";

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
    public User getById(String username) throws IOException {
        Predicate<User> usernamePredicate = d -> d.getUsername().equalsIgnoreCase(username);

        return all().stream()
                .filter(usernamePredicate)
                .findFirst().orElse(null);
    }

    @Override
    public void create(User user) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true));

        bufferedWriter.write(user.getUsername() + "," + user.getPassword() + "\n");

        bufferedWriter.close();
    }

    @Override
    public void update(User user) throws IOException {
        List<User> users = all();

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
        Iterator itr = users.iterator();

        while (itr.hasNext()) {
            User u = (User) itr.next();

            if (u.getUsername().equalsIgnoreCase(user.getUsername())) {
                bufferedWriter.write(user.getUsername() + "," + user.getPassword() + "\n");
            } else {
                bufferedWriter.write(u.getUsername() + "," + u.getPassword() + "\n");
            }
        }

        bufferedWriter.close();
    }

    @Override
    public void delete(User user) throws IOException {
        List<User> users = all();

        Predicate<User> usernamePredicate = d -> d.getUsername().equalsIgnoreCase(user.getUsername());
        users = users.stream()
                .filter(usernamePredicate.negate()) // select users except this username
                .collect(Collectors.toList());

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
        Iterator itr = users.iterator();

        while (itr.hasNext()) {
            User u = (User) itr.next();
            bufferedWriter.write(u.getUsername() + "," + u.getPassword() + "\n");
        }

        bufferedWriter.close();
    }

    public boolean isExists(Predicate<User> predicate) throws IOException {
        return all().stream()
                .anyMatch(predicate);
    }
}
