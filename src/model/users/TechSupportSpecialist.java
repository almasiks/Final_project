package model.users;

import enums.RequestStatus;
import model.communication.Request;
import patterns.DataStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TechSupportSpecialist extends Employee {
    private List<Request> assignedrequests = new ArrayList<Request>();

    public TechSupportSpecialist(String id, String firstName, String lastName,
                                 String email, String password) {
        super(id, firstName, lastName, email, password, "Tech Support");
    }

    public List<Request> viewNewRequests() {
        return DataStorage.getInstance().getRequests().stream()
                .filter(r -> r.getStatus() == RequestStatus.NEW)
                .collect(Collectors.toList());
    }

    public void acceptRequest(Request r) {
        r.setStatus(RequestStatus.ACCEPTED);
        if(!assignedrequests.contains(r)) {
            assignedrequests.add(r);
        }
        System.out.println("Request accepted: " + r.getRequestid());
    }

    public void rejectRequest(Request r) {
        r.setStatus(RequestStatus.REJECTED);
        assignedrequests.remove(r);
        System.out.println("Request rejected: " + r.getRequestid());
    }

    public void markDone(Request r) {
        r.setStatus(RequestStatus.DONE);
        System.out.println("Request done: " + r.getRequestid());
    }

    @Override
    public String toString() {
        return "TechSupportSpecialist{" +
                "name=" + getFirstName() + " " + getLastName() +
                ", assignedCount=" + assignedrequests.size() +
                '}';
    }
    public  List<Request> getAssignedRequests() {
        return assignedrequests;
    }
}