package models;

public class AuthUser {
    private static AuthUser instance;

    private String userName;

    private AuthUser(String userName) {
        this.userName = userName;
    }

    public static AuthUser getInstance(String userName) {
        if (instance == null) {
            instance = new AuthUser(userName);
        }
        return instance;
    }

    public String getUserName() {
        return userName;
    }

    public void cleanUserSession() {
        userName = null;
    }
}