package model.users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StudentOrganization implements Serializable {
    private String name;
    private Student head;
    private List<Student> members = new ArrayList<>();

    public StudentOrganization(String name) {
        this.name = name;
    }

    public void setHead(Student student) {
        this.head = student;
        if (!members.contains(student)) members.add(student);
    }

    public void addMember(Student student) {
        if (!members.contains(student)) {
            members.add(student);
            System.out.println(student.getFirstName() + " joined " + name);
        }
    }

    public void removeMember(Student student) {
        members.remove(student);
    }

    public Student getHead() {
        return head;
    }

    public List<Student> getMembers() {
        return members;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Organization: " + name + " | Head: " +
                (head != null ? head.getFirstName() : "none") +
                " | Members: " + members.size();
    }
}