package main.models;

public enum UserType {
    ADMIN, USER;

    public static UserType getFromString(String s) {
        if (s.equals("admin")) {
            return ADMIN;
        } else if (s.equals("user")) {
            return USER;
        } else {
            return null;
        }
    }

}
