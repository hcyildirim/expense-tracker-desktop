package models;

public class UserSession {
    private static UserSession instance;

    protected User user;

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    public void removeUser() {
        this.user = null;
    }
}