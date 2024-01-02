package Services;

import java.util.HashSet;

import java.util.Set;


public class Register {
    private Set<String> usernames;

    public Register() {
        usernames = new HashSet<>();
    }

    public boolean isUsernameAvailable(String username) {
        return !usernames.contains(username);
    }

    public boolean addUser(String username) {
        if (isUsernameAvailable(username)) {
            usernames.add(username);
            System.out.println("User " + username + " registered successfully!");
            return true;
        } else {
            System.out.println("Username " + username + " is already taken. Please choose another.");
            return false;
        }
    }
}
