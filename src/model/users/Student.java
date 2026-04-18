package model.users;


import model.academic.Course;
import model.academic.Mark;
import java.util.*;

public class Student extends User {
    private double gpa;
    private String faculty;
    private int yearOfStudy;
    private int currentCredits = 0;

    private List<Course> enrolledCourses = new ArrayList<>();
    private Map<Course, Mark> marks = new HashMap<>();
    private Map<Course, Integer> failAttempts = new HashMap<>();

    public Student() {}



    public int getCurrentCredits() { return currentCredits; }

    public int getFailCount(Course course) {
        return failAttempts.getOrDefault(course, 0);
    }


    public void viewTranscript() {

    }


    public void rateTeacher(Teacher teacher, int score) {

    }

    @Override
    public String toString() {
        return super.toString() + " [GPA: " + gpa + "]";
    }

    public void addCourse(Course course) {
        enrolledCourses.add(course);
    }
}