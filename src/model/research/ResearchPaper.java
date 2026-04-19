package model.research;

import enums.Format;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ResearchPaper implements Serializable {
    private String title;
    private List<Researcher> authors;
    private String journal;
    private int pages;
    private LocalDate publishDate;
    private String doi;
    private int citations;

    public ResearchPaper() {
        this.authors = new ArrayList<>();
    }

    public ResearchPaper(String title, List<Researcher> authors, String journal,
                         int pages, LocalDate publishDate, String doi, int citations) {
        this.title = title;
        this.authors = authors != null ? new ArrayList<>(authors) : new ArrayList<>();
        this.journal = journal;
        this.pages = pages;
        this.publishDate = publishDate;
        this.doi = doi;
        this.citations = citations;
    }

    public String getTitle() {
        return title;
    }

    public List<Researcher> getAuthors() {
        return authors;
    }

    public String getJournal() {
        return journal;
    }

    public int getPages() {
        return pages;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public String getDoi() {
        return doi;
    }

    public int getCitations() {
        return citations;
    }

    public void setCitations(int citations) {
        this.citations = citations;
    }

    public void addCitation() {
        this.citations++;
    }

    public String getCitation(Format format) {
        String authorsLine = authors.stream()
                .map(Researcher::getResearcherName)
                .collect(Collectors.joining(", "));

        if (format == Format.BIBTEX) {
            return "@article{" + doi + ",\n" +
                    "  title={" + title + "},\n" +
                    "  author={" + authorsLine + "},\n" +
                    "  journal={" + journal + "},\n" +
                    "  year={" + publishDate.getYear() + "},\n" +
                    "  pages={" + pages + "},\n" +
                    "  doi={" + doi + "}\n" +
                    "}";
        }

        return authorsLine + ". \"" + title + "\". " + journal + ", "
                + publishDate.getYear() + ". DOI: " + doi
                + ". Citations: " + citations;
    }

    @Override
    public String toString() {
        return "ResearchPaper{" +
                "title='" + title + '\'' +
                ", journal='" + journal + '\'' +
                ", pages=" + pages +
                ", publishDate=" + publishDate +
                ", doi='" + doi + '\'' +
                ", citations=" + citations +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResearchPaper that)) return false;
        return Objects.equals(doi, that.doi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doi);
    }
}