package model.communication;

import enums.NewsTopic;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class News implements Comparable<News>, Serializable {
    private String title;
    private String content;
    private NewsTopic topic;
    private LocalDate date;
    private List<String> comments;

    public News() {
        this.date = LocalDate.now();
        this.comments = new ArrayList<>();
    }

    public News(String title, String content, NewsTopic topic) {
        this();
        this.title = title;
        this.content = content;
        this.topic = topic;
    }

    public String getTitle() {
        return title;
    }

    public NewsTopic getTopic() {
        return topic;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<String> getComments() {
        return comments;
    }

    public void addComment(String comment) {
        if (comment != null && !comment.isBlank()) {
            comments.add(comment);
        }
    }

    public boolean isPinned() {
        return topic == NewsTopic.RESEARCH;
    }

    @Override
    public int compareTo(News other) {
        if (this.isPinned() && !other.isPinned()) return -1;
        if (!this.isPinned() && other.isPinned()) return 1;
        return other.date.compareTo(this.date);
    }

    @Override
    public String toString() {
        return (isPinned() ? "[PINNED] " : "") +
                "News{" +
                "title='" + title + '\'' +
                ", topic=" + topic +
                ", date=" + date +
                ", comments=" + comments.size() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof News news)) return false;
        return Objects.equals(title, news.title) &&
                Objects.equals(date, news.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, date);
    }
}