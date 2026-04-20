package model.users;

import enums.TeacherPosition;
import enums.UrgencyLevel;
import model.academic.Course;
import model.academic.Mark;
import model.research.ResearchPaper;
import model.research.ResearchProject;
import model.research.Researcher;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Teacher extends Employee implements Researcher {
    private TeacherPosition position;
    private double rating;
    private List<Course> courses = new ArrayList<>();

    private List<ResearchPaper> researchPapers = new ArrayList<>();
    private List<ResearchProject> researchProjects = new ArrayList<>();

    private List<Integer> ratings = new ArrayList<>();
    public void addRating(int score) {
        ratings.add(score);
    }
    public double getAverageRating() {
        return ratings.stream().mapToInt(i -> i).average().orElse(0.0);
    }

    public Teacher(String id, String firstName, String lastName,
                   String email, String password, String department,
                   TeacherPosition position) {
        super(id, firstName, lastName, email, password, department);
        this.position = position;
    }

    public void putMark(Student student, Course course, Mark mark) {
        if (!courses.contains(course)) {
            System.out.println("You don't teach this course.");
            return;
        }
        if (!course.getStudents().contains(student)) {
            System.out.println("Student is not enrolled in this course.");
            return;
        }
        student.addMark(course, mark);
        System.out.println("Mark added for " + student.getFirstName()
                + ": " + mark);
    }

    public void sendComplaint(Student student, Manager dean,UrgencyLevel level) {
        System.out.println("Complaint sent to" + dean.getFirstName() + "regarding" + student.getFirstName());
    }

    public void viewStudents() {
        for (Course c : courses) {
            System.out.println("Course: " + c.getCourseName());
            System.out.println("Students: " + c.getStudents());
        }
    }

    public void manageCourse(Course course) {
        if (!courses.contains(course)) {
            courses.add(course);
        }
    }
    public void viewCourses() {
        for (Course c : courses) {
            System.out.println("Course: " + c.getCourseName());
        }
    }

    @Override
    public int calculateHIndex() {
        List<Integer> citationCounts = new ArrayList<>();
        for (ResearchPaper paper : researchPapers) {
            citationCounts.add(paper.getCitations());
        }
        citationCounts.sort(Comparator.reverseOrder());
        int h = 0;
        for (int i = 0; i < citationCounts.size(); i++) {
            if (citationCounts.get(i) >= i + 1) h = i + 1;
            else break;
        }
        return h;
    }


    @Override
    public void printPapers(Comparator<ResearchPaper> comparator) {
        List<ResearchPaper> sortedPapers = new ArrayList<>(researchPapers);
        sortedPapers.sort(comparator);
        for (ResearchPaper paper : sortedPapers) {
            System.out.println(paper);
        }
    }

    @Override
    public List<ResearchPaper> getResearchPapers() {
        return researchPapers;
    }

    @Override
    public List<ResearchProject> getResearchProjects() {
        return researchProjects;
    }

    @Override
    public String getResearcherName() {
        return getFirstName() + " " + getLastName();
    }

    public TeacherPosition getPosition() {
        return position;
    }

    public void setPosition(TeacherPosition position) {
        this.position = position;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<ResearchPaper> getResearchPapersList() {
        return researchPapers;
    }

    public void setResearchPapers(List<ResearchPaper> researchPapers) {
        this.researchPapers = researchPapers;
    }

    public List<ResearchProject> getResearchProjectsList() {
        return researchProjects;
    }

    public void setResearchProjects(List<ResearchProject> researchProjects) {
        this.researchProjects = researchProjects;
    }

    @Override
    public String toString() {
        return "Teacher{" + getFirstName() + " " + getLastName() + ", position=" + position + ", rating=" + rating + "}";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher)) return false;
        Teacher teacher = (Teacher) o;
        return Double.compare(teacher.rating, rating) == 0 && position == teacher.position;
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), position, rating);
    }
    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }

}