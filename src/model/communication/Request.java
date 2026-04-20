package model.communication;


import enums.RequestStatus;
import model.users.Employee;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDate;
import java.util.Objects;

public class Request implements Serializable {
    private String requestid;
    private Employee author;
    private String description;
    private RequestStatus status;
    private LocalDate createdAt;

    public Request(String requestid, String description, RequestStatus status, LocalDate createdAt) {
        this.requestid = requestid;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
    }

    public String getRequestid() {
        return requestid;
    }
    public void setRequestid(String requestid) {
        this.requestid = requestid;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public RequestStatus getStatus() {
        return status;
    }
    public void setStatus(RequestStatus status) {
        this.status = status;
    }
    public LocalDate getCreatedAt() {
        return createdAt;
    }


    public void updateStatus(RequestStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Request{id='" + requestid + "', desc='" + description
                + "', status=" + status + ", created=" + createdAt + "}";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(requestid, request.requestid) && Objects.equals(description, request.description) && status == request.status;
    }
    @Override
    public int hashCode() {
        return Objects.hash(requestid);
    }
}

