package model.users;

import enums.ManagerType;
import model.academic.Course;
import model.communication.News;
import patterns.DataStorage;

import java.util.Comparator;
import java.util.List;

public class Manager extends Employee {
    private ManagerType type;

    public Manager() {}

    public Manager(String id, String firstName, String lastName,
                   String email, String password, String department,
                   ManagerType type) {
        super(id, firstName, lastName, email, password, department);
        this.type = type;
    }

    public void assignTeacherCourse(Teacher t, Course c) {
        t.manageCourse(c);
        System.out.println("Course " + c.getCourseName()
                + " assigned to " + t.getFirstName());
    }

    public void approveStudentRegistration(Student student, Course course) {
        boolean success = course.registerStudent(student);
        if (success) {
            student.addCourse(course);
            System.out.println("Registration approved: "
                    + student.getFirstName() + " -> " + course.getCourseName());
        } else {
            System.out.println("Registration failed for "
                    + student.getFirstName());
        }
    }

    public void addCourseForRegistration(Course course) {
        DataStorage.getInstance().addCourse(course);
        System.out.println("Course added for registration: "
                + course.getCourseName());
    }

    public void createReport() {
        List<User> users = DataStorage.getInstance().getAllUsers();
        System.out.println("ACADEMIC PERFORMANCE REPORT");
        for (User u : users) {
            if (u instanceof Student s) {
                System.out.println(s.getFirstName() + " " + s.getLastName()
                        + " | GPA: " + s.getGpa()
                        + " | Credits: " + s.getCurrentCredits());
            }
        }
    }

    public void viewStudentsByGpa() {
        List<User> users = DataStorage.getInstance().getAllUsers();
        System.out.println("STUDENTS BY GPA");
        users.stream()
                .filter(u -> u instanceof Student)
                .map(u -> (Student) u)
                .sorted(Comparator.comparingDouble(Student::getGpa).reversed())
                .forEach(s -> System.out.println(
                        s.getFirstName() + " " + s.getLastName()
                                + " | GPA: " + s.getGpa()));
    }

    public void viewStudentsAlphabetically() {
        List<User> users = DataStorage.getInstance().getAllUsers();
        System.out.println("STUDENTS ALPHABETICALLY");
        users.stream()
                .filter(u -> u instanceof Student)
                .map(u -> (Student) u)
                .sorted(Comparator.comparing(User::getLastName))
                .forEach(s -> System.out.println(
                        s.getLastName() + " " + s.getFirstName()));
    }

    public void viewTeachers() {
        List<User> users = DataStorage.getInstance().getAllUsers();
        System.out.println("TEACHERS");
        users.stream()
                .filter(u -> u instanceof Teacher)
                .map(u -> (Teacher) u)
                .sorted(Comparator.comparing(User::getLastName))
                .forEach(t -> System.out.println(
                        t.getLastName() + " " + t.getFirstName()
                                + " | Position: " + t.getPosition()
                                + " | Rating: " + t.getAverageRating()));
    }

    public void addNews(News news) {
        DataStorage.getInstance().addNews(news);
        System.out.println("News added: " + news.getTitle());
    }

    public void removeNews(News news) {
        DataStorage.getInstance().removeNews(news);
        System.out.println("News removed: " + news.getTitle());
    }

    public void viewAllNews() {
        List<News> newsList = DataStorage.getInstance().getNewsList();
        System.out.println("NEWS");
        newsList.stream()
                .sorted()
                .forEach(System.out::println);
    }

    public ManagerType getType() {
        return type;
    }

    public void setType(ManagerType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return super.toString() + " [Manager, type: " + type + "]";
    }
}