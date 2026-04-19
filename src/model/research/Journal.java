package model.research;

import enums.NewsTopic;
import model.communication.News;
import patterns.DataStorage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Journal implements Serializable {
    private String name;
    private List<ResearchPaper> papers;
    private List<Researcher> subscribers;

    public Journal() {
        this.papers = new ArrayList<>();
        this.subscribers = new ArrayList<>();
    }

    public Journal(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<ResearchPaper> getPapers() {
        return papers;
    }

    public List<Researcher> getSubscribers() {
        return subscribers;
    }

    public void subscribe(Researcher user) {
        if (user != null && !subscribers.contains(user)) {
            subscribers.add(user);
        }
    }

    public void unsubscribe(Researcher user) {
        subscribers.remove(user);
    }

    public void publishPaper(ResearchPaper paper) {
        if (paper == null) return;

        papers.add(paper);
        notifySubscribers(paper);
        createPaperNews(paper);
        createTopResearcherNews();
    }

    private void notifySubscribers(ResearchPaper paper) {
        for (Researcher subscriber : subscribers) {
            subscriber.notifyNewPaper(paper, this);
        }
    }

    private void createPaperNews(ResearchPaper paper) {
        News news = new News(
                "New research paper published",
                "Paper \"" + paper.getTitle() + "\" was published in journal " + name,
                NewsTopic.RESEARCH
        );
        DataStorage.getInstance().addNews(news);
    }

    private void createTopResearcherNews() {
        Researcher top = papers.stream()
                .flatMap(p -> p.getAuthors().stream())
                .distinct()
                .max(Comparator.comparingInt(Researcher::calculateHIndex))
                .orElse(null);

        if (top != null) {
            News news = new News(
                    "Top cited researcher",
                    top.getResearcherName() + " is currently the top-cited researcher with h-index = "
                            + top.calculateHIndex(),
                    NewsTopic.RESEARCH
            );
            DataStorage.getInstance().addNews(news);
        }
    }

    @Override
    public String toString() {
        return "Journal{" +
                "name='" + name + '\'' +
                ", papers=" + papers.size() +
                ", subscribers=" + subscribers.size() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Journal journal)) return false;
        return Objects.equals(name, journal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}