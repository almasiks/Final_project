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

    public Student(String id, String firstName, String lastName,
                   String email, String password, String faculty, int yearOfStudy) {
        super(id, firstName, lastName, email, password);
        this.faculty = faculty;
        this.yearOfStudy = yearOfStudy;
    }

    public boolean registerForCourse(Course course) {
        if (currentCredits + course.getCredits() > 21) {
            System.out.println("Cannot register: exceeds 21 credit limit.");
            return false;
        }
        int fails = failAttempts.getOrDefault(course, 0);
        if (fails >= 3) {
            System.out.println("Cannot register: failed this course 3 times already.");
            return false;
        }
        if (course.registerStudent(this)) {
            enrolledCourses.add(course);
            currentCredits += course.getCredits();
            return true;
        }
        return false;
    }

    public void viewMarks() {
        if (marks.isEmpty()) {
            System.out.println("No marks yet.");
            return;
        }
        for (Map.Entry<Course, Mark> entry : marks.entrySet()) {
            System.out.println(entry.getKey().getCourseName() + ": " + entry.getValue());
        }
    }

    public void viewTranscript() {
        System.out.println("=== TRANSCRIPT for " + getFirstName() + " " + getLastName() + " ===");
        System.out.println("GPA: " + gpa);
        for (Map.Entry<Course, Mark> entry : marks.entrySet()) {
            System.out.println(entry.getKey().getCourseName() + " | " + entry.getValue());
        }
    }

    public void addMark(Course course, Mark mark) {
        marks.put(course, mark);
        recalculateGPA();
    }

    private void recalculateGPA() {
        if (marks.isEmpty()) return;
        double total = 0;
        for (Mark m : marks.values()) total += m.getTotalScore();
        this.gpa = total / marks.size();
    }

    public void rateTeacher(Teacher teacher, int score) {
        if (score < 1 || score > 5) {
            System.out.println("Score must be 1-5.");
            return;
        }
        teacher.addRating(score);
        System.out.println("Rating submitted.");
    }

    public double getGpa() {
        return gpa;
    }

    public String getFaculty() {
        return faculty;
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public Map<Course, Mark> getMarks() {
        return marks;
    }

    public int getCurrentCredits() {
        return currentCredits;
    }

    public int getFailCount(Course course) {
        return failAttempts.getOrDefault(course, 0);
    }

    @Override
    public String toString() {
        return super.toString() + " [GPA: " + gpa + "]";
    }

    public void addCourse(Course course) {
        enrolledCourses.add(course);
    }
}