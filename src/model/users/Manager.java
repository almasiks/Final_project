package model.users;

import enums.ManagerType;
import model.academic.Course;
import model.communication.News;
import model.academic.Report;
import patterns.DataStorage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Manager extends Employee {
    private ManagerType managerType;


    public Manager(String id, String firstName, String lastName,
                   String email, String password, String department,
                   ManagerType type) {
        super(id, firstName, lastName, email, password, department);
        this.managerType = type;
    }

    public void assignTeacher(Teacher t, Course c) {
        t.manageCourse(c);
        c.addTeacher(t, null);
        System.out.println("Course " + c.getCourseName()
                + " assigned to " + t.getFirstName());
    }

    public void approveRegistration(Student student, Course course) {
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
    public void manageNews(News news) {
        List<News> newsList = DataStorage.getInstance().getNewsList();
        if(newsList.contains(news)) {
            newsList.remove(news);
            newsList.add(news);
            System.out.println("News " + news.getTitle() + " has been updated to the list");
        }else{
            DataStorage.getInstance().addNews(news);
            System.out.println("News " + news.getTitle() + " has been published to the list");
        }

    }

    public void addCourseForRegistration(Course course) {
        DataStorage.getInstance().addCourse(course);
        System.out.println("Course added for registration: "
                + course.getCourseName());
    }

    public Report createReport() {
        List<User> users = DataStorage.getInstance().getAllUsers();
        System.out.println("ACADEMIC PERFORMANCE REPORT");
        for (User u : users) {
            if (u instanceof Student s) {
                System.out.println(s.getFirstName() + " " + s.getLastName()
                        + " | GPA: " + s.getGpa()
                        + " | Credits: " + s.getCurrentCredits());
            }
        }
        return null;
    }
    public void viewStudentsSorted() {
        List<User> users = DataStorage.getInstance().getAllUsers();
        System.out.println("--- Students Sorted by GPA (High to Low) ---");
        users.stream()
                .filter(u -> u instanceof Student)
                .map(u -> (Student) u)
                .sorted((s1, s2) -> Double.compare(s2.getGpa(), s1.getGpa())) // Сортировка по убыванию GPA
                .forEach(s -> System.out.println(s.getLastName() + " " + s.getFirstName() + " | GPA: " + s.getGpa()));


    }

    public void Requests(){
        System.out.println("Current Requests:");

        // 1. Смотрим логи/жалобы (из твоего Teacher.sendComplaint)
        List<String> logs = DataStorage.getInstance().getLogs();
        if (logs.isEmpty()) {
            System.out.println("No pending complaints or logs.");
        } else {
            logs.stream()
                    .filter(log -> log.contains("[COMPLAINT]"))
                    .forEach(System.out::println);
        }

    }
    public ManagerType getType() {
        return managerType;
    }

    public void setType(ManagerType type) {
        this.managerType = type;
    }

    @Override
    public String toString() {
        return "Manager{" + getFirstName() + getLastName() + "managerType=" + managerType + '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Manager)) return false;
        Manager manager = (Manager) o;
        return managerType == manager.managerType;

    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), managerType);
    }
}