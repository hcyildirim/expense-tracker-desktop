package models;

public class AuthUser {
    private static AuthUser instance;

    private String username;

    private AuthUser(String username) {
        this.username = username;
    }

    public static AuthUser getInstance(String userName) {
        if (instance == null) {
            instance = new AuthUser(userName);
        }
        return instance;
    }

    public String getUsername() {
        return username;
    }

    public void cleanUserSession() {
        username = null;
    }
}