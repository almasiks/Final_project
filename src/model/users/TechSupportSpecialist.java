package model.users;

import enums.RequestStatus;
import model.communication.Request;
import patterns.DataStorage;

import java.util.List;

public class TechSupportSpecialist extends Employee {

    public TechSupportSpecialist(String id, String firstName, String lastName,
                                 String email, String password) {
        super(id, firstName, lastName, email, password, "Tech Support");
    }

    public void viewNewRequests() {
        List<Request> requests = DataStorage.getInstance().getRequests();
        if (requests.isEmpty()) {
            System.out.println("No new requests.");
            return;
        }
        System.out.println("=== NEW REQUESTS ===");
        for (Request r : requests) {
            if (r.getStatus() == RequestStatus.NEW) {
                r.setStatus(RequestStatus.VIEWED);
                System.out.println(r);
            }
        }
    }

    public void acceptRequest(Request r) {
        r.setStatus(RequestStatus.ACCEPTED);
        System.out.println("Request accepted: " + r.getRequestid());
    }

    public void rejectRequest(Request r) {
        r.setStatus(RequestStatus.REJECTED);
        System.out.println("Request rejected: " + r.getRequestid());
    }

    public void markDone(Request r) {
        r.setStatus(RequestStatus.DONE);
        System.out.println("Request done: " + r.getRequestid());
    }
}