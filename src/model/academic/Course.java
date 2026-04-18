package model.academic;

import enums.CourseType;
import model.users.Teacher;
import model.users.Student;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Course implements Serializable {
    private String courseCode;
    private String courseName;
    private int credits;
    private CourseType type;


    private List<Teacher> instructors = new ArrayList<>();
    private List<Student> registeredStudents = new ArrayList<>();


    public Course(String courseCode, String courseName, int credits, CourseType type) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.credits = credits;
        this.type = type;
    }


    public String getCourseCode() { return courseCode; }
    public int getCredits() { return credits; }
    public List<Student> getRegisteredStudents() { return registeredStudents; }


    public boolean registerStudent(Student student) {
        return false;
    }


    @Override
    public String toString() {
        return courseCode + " " + courseName + " (" + credits + " credits, " + type + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return Objects.equals(courseCode, course.courseCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseCode);
    }

    public String getCourseName() {
        return "";
    }
}