package model.research;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class ResearchUtils {
    private ResearchUtils() {
    }

    public static List<ResearchPaper> getAllPapersSorted(List<? extends Researcher> researchers,
                                                         Comparator<ResearchPaper> comparator) {
        List<ResearchPaper> all = new ArrayList<>();
        for (Researcher researcher : researchers) {
            all.addAll(researcher.getResearchPapers());
        }
        all.sort(comparator);
        return all;
    }
}