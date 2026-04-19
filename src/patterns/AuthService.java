package patterns;

import model.users.User;

import java.util.List;

public class AuthService {
    private static AuthService instance;
    private User currentUser;

    private AuthService() {
    }

    public static AuthService getInstance() {
        if (instance == null) instance = new AuthService();
        return instance;
    }

    public User login(String email, String password) {
        List<User> users = DataStorage.getInstance().getAllUsers();
        for (User u : users) {
            if (u.getEmail().equals(email) && u.checkPassword(password)) {
                this.currentUser = u;
                DataStorage.getInstance().addLog("LOGIN: " + u.getEmail());
                System.out.println("Welcome, " + u.getFirstName() + "!");
                return u;
            }
        }
        System.out.println("Invalid credentials.");
        return null;
    }

    public void logout() {
        if (currentUser != null) {
            DataStorage.getInstance().addLog("LOGOUT: " + currentUser.getEmail());
            System.out.println("Goodbye, " + currentUser.getFirstName() + "!");
            currentUser = null;
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }
}