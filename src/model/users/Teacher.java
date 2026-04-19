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

    private void ensureProfessor() {
        if (position != TeacherPosition.PROFESSOR) {
            throw new IllegalStateException("Only PROFESSOR can use researcher functionality");
        }
    }

    @Override
    public int calculateHIndex() {
        ensureProfessor();

        List<Integer> citationCounts = new ArrayList<>();
        for (ResearchPaper paper : researchPapers) {
            citationCounts.add(paper.getCitations());
        }

        citationCounts.sort(Comparator.reverseOrder());

        int h = 0;
        for (int i = 0; i < citationCounts.size(); i++) {
            if (citationCounts.get(i) >= i + 1) {
                h = i + 1;
            } else {
                break;
            }
        }

        return h;
    }

    @Override
    public void printPapers(Comparator<ResearchPaper> comparator) {
        ensureProfessor();

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