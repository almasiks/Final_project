package model.users;

import model.communication.Message;

import java.util.ArrayList;
import java.util.List;

public class Employee extends User {
    private String department;
    private List<Message> inbox = new ArrayList<>();

    public Employee() {}

    public Employee(String id, String firstName, String lastName,
                    String email, String password, String department) {
        super(id, firstName, lastName, email, password);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void sendMessage(Employee receiver, String content) {
        Message msg = new Message(this, receiver, content);
        receiver.receiveMessage(msg);
        System.out.println("Message sent to " + receiver.getFirstName());
    }

    public void receiveMessage(Message message) {
        inbox.add(message);
    }

    public void viewInbox() {
        if (inbox.isEmpty()) {
            System.out.println("No messages.");
            return;
        }
        for (Message m : inbox) {
            System.out.println(m);
            m.markAsRead();
        }
    }

    public List<Message> getInbox() {
        return inbox;
    }
}