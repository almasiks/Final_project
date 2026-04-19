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

public class Teacher extends Employee implements Researcher {
    private TeacherPosition position; // [cite: 21, 157]
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
        if (!course.getRegisteredStudents().contains(student)) {
            System.out.println("Student is not enrolled in this course.");
            return;
        }
        student.addMark(course, mark);
        System.out.println("Mark added for " + student.getFirstName()
                + ": " + mark);
    }

    public void sendComplaint(Student student, UrgencyLevel level) {
        String msg = "[COMPLAINT][" + level + "] Teacher "
                + getFirstName() + " " + getLastName()
                + " filed a complaint about student: "
                + student.getFirstName() + " " + student.getLastName();
        System.out.println(msg);
        // сохраняем в лог
        patterns.DataStorage.getInstance().addLog(msg);
    }

    public void viewStudents() {
        for (Course c : courses) {
            System.out.println("Course: " + c.getCourseName());
            System.out.println("Students: " + c.getRegisteredStudents());
        }
    }

    public void manageCourse(Course course) {
        if (!courses.contains(course)) {
            courses.add(course);
            course.addInstructor(this);
            System.out.println("Course " + course.getCourseName() + " assigned to " + getFirstName());
        } else {
            System.out.println("Already teaching this course.");
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
        return super.toString() + " - Position: " + position;
    }
}