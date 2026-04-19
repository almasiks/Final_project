package model.users;

import enums.Degree;
import exceptions.LowHIndexException;
import model.research.ResearchPaper;
import model.research.ResearchProject;
import model.research.Researcher;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GraduateStudent extends Student implements Researcher {
    private Researcher supervisor;
    private List<ResearchPaper> researchPapers = new ArrayList<>();
    private List<ResearchProject> researchProjects = new ArrayList<>();
    private Degree degree;

    public GraduateStudent(String id, String firstName, String lastName,
                           String email, String password, String faculty,
                           int yearOfStudy, Degree degree) {

        super(id, firstName, lastName, email, password, faculty, yearOfStudy);
        this.degree = degree;
    }

    public void setSupervisor(Researcher supervisor) throws LowHIndexException {
        if (supervisor.calculateHIndex() < 3) {
            throw new LowHIndexException(
                    "Supervisor " + supervisor.getResearcherName() +
                            " has h-index = " + supervisor.calculateHIndex() +
                            " which is less than 3."
            );
        }
        this.supervisor = supervisor;
    }

    public Researcher getSupervisor() {
        return supervisor;
    }

    public Degree getDegree() {
        return degree;
    }

    @Override
    public int calculateHIndex() {
        List<Integer> cites = new ArrayList<>();
        for (ResearchPaper p : researchPapers) cites.add(p.getCitations());
        cites.sort(Comparator.reverseOrder());
        int h = 0;
        for (int i = 0; i < cites.size(); i++) {
            if (cites.get(i) >= i + 1) h = i + 1;
            else break;
        }
        return h;
    }

    @Override
    public void printPapers(Comparator<ResearchPaper> comparator) {
        List<ResearchPaper> sorted = new ArrayList<>(researchPapers);
        sorted.sort(comparator);
        sorted.forEach(System.out::println);
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

    @Override
    public String toString() {
        return super.toString() + " [" + degree + " student]";
    }
}