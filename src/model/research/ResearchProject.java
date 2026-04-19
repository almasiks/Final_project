package model.research;

import exceptions.NotResearcherException;
import model.users.Teacher;
import model.users.User;
import enums.TeacherPosition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ResearchProject implements Serializable {
    private String topic;
    private List<ResearchPaper> papers;
    private List<Researcher> participants;

    public ResearchProject() {
        this.papers = new ArrayList<>();
        this.participants = new ArrayList<>();
    }

    public ResearchProject(String topic) {
        this();
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    public List<ResearchPaper> getPapers() {
        return papers;
    }

    public List<Researcher> getParticipants() {
        return participants;
    }

    public void addParticipant(User user) throws NotResearcherException {
        if (!(user instanceof Researcher)) {
            throw new NotResearcherException("This user is not a researcher");
        }

        if (user instanceof Teacher teacher &&
                teacher.getPosition() != TeacherPosition.PROFESSOR) {
            throw new NotResearcherException("Only PROFESSOR teacher can join research project");
        }

        Researcher researcher = (Researcher) user;
        if (!participants.contains(researcher)) {
            participants.add(researcher);
        }
    }

    public void publishPaper(ResearchPaper paper) {
        if (paper != null && !papers.contains(paper)) {
            papers.add(paper);
        }
    }

    @Override
    public String toString() {
        return "ResearchProject{" +
                "topic='" + topic + '\'' +
                ", papers=" + papers.size() +
                ", participants=" + participants.size() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResearchProject that)) return false;
        return Objects.equals(topic, that.topic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topic);
    }
}