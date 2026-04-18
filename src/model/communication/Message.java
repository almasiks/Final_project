package model.communication;

import model.users.Employee;
import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    private Employee sender;
    private Employee receiver;
    private String content;
    private Date timestamp;
    private boolean isRead;


    public Message(Employee sender, Employee receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.timestamp = new Date();
        this.isRead = false;
    }


    public Employee getSender() { return sender; }
    public String getContent() { return content; }
    public boolean isRead() { return isRead; }


    public void markAsRead() {
        this.isRead = true;
    }

    @Override
    public String toString() {
        return "[" + timestamp + "] From: " + sender.getFirstName() + " - " + content;
    }
}