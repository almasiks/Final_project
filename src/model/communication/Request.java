package model.communication;

import enums.RequestStatus;

import java.io.Serializable;
import java.util.Date;

public class Request implements Serializable {
    private String requestid;
    private String description;
    private RequestStatus status;
    private Date createdAt;


    public  Request(String requestid, String description, RequestStatus status, Date createdAt) {
        this.description=description;
        this.status=status;
        this.createdAt=createdAt;
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
}

