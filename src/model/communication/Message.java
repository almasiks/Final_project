package model.communication;


import model.users.Employee;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Message implements Serializable {
    private Employee sender;
    private Employee receiver;
    private String text;
    private LocalDateTime sentAt;
    private boolean isRead;


    public Message(LocalDateTime sentAt, boolean isRead) {
        this.sentAt = LocalDateTime.now();
        this.isRead = false;
    }

    public Message(Employee receiver, String text) {
        this.sender = null;
        this.receiver = receiver;
        this.text = text;

    }

    public Employee getSender() { return sender; }
    public String getText() { return text; }
    public boolean isRead() { return isRead; }


    public void markAsRead() {
        this.isRead = true;
    }

    @Override
    public String toString() {
        return "Message{" +
                "from=" + sender.getLastName() +
                ", to=" + receiver.getLastName() +
                ", text='" + text + '\'' +
                ", sentAt=" + sentAt +
                ", read=" + isRead +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return isRead == message.isRead &&
                Objects.equals(sender, message.sender) &&
                Objects.equals(receiver, message.receiver)&&
                Objects.equals(text, message.text)&&
                Objects.equals(sentAt, message.sentAt);
    }
    @Override
    public int hashCode() {
        return Objects.hash(sender, receiver, text, sentAt, isRead);
    }
}