package model.users;

import patterns.DataStorage;
import java.util.List;

public class Admin extends Employee {

    public Admin(String id, String firstName, String lastName,
                 String email, String password) {
        super(id, firstName, lastName, email, password, "Administration");
    }

    public void addUser(User user) {
        DataStorage.getInstance().addUser(user);
        System.out.println("User added: " + user);
    }

    public void removeUser(String userId) {
        DataStorage.getInstance().removeUser(userId);
        System.out.println("User removed: " + userId);
    }

    public void updateUser(User user) {
        System.out.println("User updated: " + user);
    }

    public void viewAllUsers() {
        List<User> users = DataStorage.getInstance().getAllUsers();
        users.forEach(System.out::println);
    }

    public void viewLogs() {
        List<String> logs = DataStorage.getInstance().getLogs();
        logs.forEach(System.out::println);
    }
}