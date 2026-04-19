package model.research;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

public interface Researcher extends Serializable {
    int calculateHIndex();
    void printPapers(Comparator<ResearchPaper> comparator);
    List<ResearchPaper> getResearchPapers();
    List<ResearchProject> getResearchProjects();
    String getResearcherName();

    default void notifyNewPaper(ResearchPaper paper, Journal journal) {
        System.out.println("[Notification] " + getResearcherName()
                + ", new paper was published in " + journal.getName()
                + ": " + paper.getTitle());
    }
}