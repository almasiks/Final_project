package model.academic;

import enums.CourseType;
import enums.LessonType;
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
    private Teacher lectureTeacher;
    private Teacher practiceTeacher;



    private List<Teacher> instructors = new ArrayList<>();
    private List<Student> students = new ArrayList<>();

    public Course(String courseCode, String courseName, int credits, CourseType type) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.credits = credits;
        this.type = type;

    }
    public void addTeacher(Teacher teacher, LessonType lessonType) {
        if (lessonType == LessonType.LECTURE) {
            this.lectureTeacher = teacher;
        }else  if (lessonType == LessonType.PRACTICE) {
            this.practiceTeacher = teacher;
        }
    }

    public void enrollStudent(Student student) {
        if (!students.contains(student)){
            students.add(student);
        }
    }
    public boolean registerStudent(Student student) {
        if (!students.contains(student)) {
            students.add(student);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return courseCode + " " + courseName + " (" + credits + " credits, " + type + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return  false;
        Course course = (Course) o;
        return credits == course.credits &&
                Objects.equals(courseCode, course.courseCode) &&
                Objects.equals(courseName, course.courseName) &&
                type == course.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseCode,  courseName, credits, type);
    }

    public String getCourseCode() { return courseCode; }
    public String getCourseName() { return courseName; }
    public int getCredits() { return credits; }
    public CourseType getType() { return type; }
    public Teacher getLectureTeacher() { return lectureTeacher; }
    public Teacher getPracticeTeacher() { return practiceTeacher; }
    public List<Student> getStudents() { return students; }
}