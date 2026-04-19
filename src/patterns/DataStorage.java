package patterns;

import model.academic.Course;
import model.communication.News;
import model.communication.Request;
import model.research.Journal;
import model.users.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataStorage implements Serializable {
    private static DataStorage instance;

    private List<User> users = new ArrayList<>();
    private List<News> newsList = new ArrayList<>();
    private List<Journal> journals = new ArrayList<>();
    private List<String> logs = new ArrayList<>();

    private DataStorage() {
    }

    public static DataStorage getInstance() {
        if (instance == null) instance = new DataStorage();
        return instance;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(String id) {
        users.removeIf(u -> u.getId().equals(id));
    }

    public List<User> getAllUsers() {
        return users;
    }

    public void addNews(News news) {
        newsList.add(news);
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public void addJournal(Journal journal) {
        journals.add(journal);
    }

    public List<Journal> getJournals() {
        return journals;
    }

    public void addLog(String entry) {
        logs.add(entry);
    }

    public List<String> getLogs() {
        return logs;
    }

    private List<Course> courses = new ArrayList<>();

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void removeCourse(Course course) {
        courses.remove(course);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void removeNews(News news) {
        newsList.remove(news);
    }

    private List<Request> requests = new ArrayList<>();

    public void addRequest(Request request) { requests.add(request); }
    public List<Request> getRequests() { return requests; }
}