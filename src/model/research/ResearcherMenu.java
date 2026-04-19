package model.research;

import patterns.DataStorage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ResearcherMenu {

    public static void open(Scanner sc, Researcher researcher) {
        while (true) {
            System.out.println("\n=== RESEARCHER MENU ===");
            System.out.println("1. View h-index");
            System.out.println("2. Print papers by date");
            System.out.println("3. Print papers by citations");
            System.out.println("4. Print papers by length");
            System.out.println("5. Subscribe to journal");
            System.out.println("6. Publish paper");
            System.out.println("7. View projects");
            System.out.println("0. Exit");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> System.out.println("H-index: " + researcher.calculateHIndex());
                case 2 -> researcher.printPapers(ResearchPaperComparators.BY_DATE);
                case 3 -> researcher.printPapers(ResearchPaperComparators.BY_CITATIONS);
                case 4 -> researcher.printPapers(ResearchPaperComparators.BY_LENGTH);
                case 5 -> subscribeToJournal(sc, researcher);
                case 6 -> publishPaper(sc, researcher);
                case 7 -> researcher.getResearchProjects().forEach(System.out::println);
                case 0 -> { return; }
                default -> System.out.println("Invalid option");
            }
        }
    }

    private static void subscribeToJournal(Scanner sc, Researcher researcher) {
        List<Journal> journals = DataStorage.getInstance().getJournals();
        if (journals.isEmpty()) {
            System.out.println("No journals available");
            return;
        }

        for (int i = 0; i < journals.size(); i++) {
            System.out.println((i + 1) + ". " + journals.get(i).getName());
        }

        System.out.print("Choose journal: ");
        int index = Integer.parseInt(sc.nextLine()) - 1;
        journals.get(index).subscribe(researcher);
        System.out.println("Subscribed successfully");
    }

    private static void publishPaper(Scanner sc, Researcher researcher) {
        List<Journal> journals = DataStorage.getInstance().getJournals();
        if (journals.isEmpty()) {
            System.out.println("No journals available");
            return;
        }

        System.out.print("Title: ");
        String title = sc.nextLine();

        System.out.print("DOI: ");
        String doi = sc.nextLine();

        System.out.print("Pages: ");
        int pages = Integer.parseInt(sc.nextLine());

        System.out.print("Citations: ");
        int citations = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < journals.size(); i++) {
            System.out.println((i + 1) + ". " + journals.get(i).getName());
        }

        System.out.print("Choose journal: ");
        int journalIndex = Integer.parseInt(sc.nextLine()) - 1;
        Journal journal = journals.get(journalIndex);

        List<Researcher> authors = new ArrayList<>();
        authors.add(researcher);

        ResearchPaper paper = new ResearchPaper(
                title,
                authors,
                journal.getName(),
                pages,
                LocalDate.now(),
                doi,
                citations
        );

        researcher.getResearchPapers().add(paper);
        journal.publishPaper(paper);

        System.out.println("Paper published successfully");
    }
}