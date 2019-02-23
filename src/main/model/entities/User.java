package main.model.entities;

public class User {
    private String login;
    private int passwordHash;
    private UserType userType;

    public User(String login, int passwordHash, UserType userType) {
        this.login = login;
        this.passwordHash = passwordHash;
        this.userType = userType;
    }

    public String getLogin() {
        return login;
    }

    public int getPasswordHash() {
        return passwordHash;
    }

    public UserType getUserType() {
        return userType;
    }
}
