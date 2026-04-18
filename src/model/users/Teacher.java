package model.users;

import enums.TeacherPosition;
import enums.UrgencyLevel;
import model.academic.Course;
import model.academic.Mark;
import java.util.*;

public class Teacher extends Employee {
    private TeacherPosition position; // [cite: 21, 157]
    private List<Course> courses = new ArrayList<>(); //

    public Teacher() {}


    public void putMark(Student student, Course course, Mark mark) {

    }


    public void sendComplaint(Student student, UrgencyLevel level) {

    }

    public void viewStudents() {
        for (Course c : courses) {
            System.out.println("Course: " + c.getCourseName());
            System.out.println("Students: " + c.getRegisteredStudents());
        }
    }

    public void manageCourse(Course course) {

    }

    @Override
    public String toString() {
        return super.toString() + " - Position: " + position;
    }
}