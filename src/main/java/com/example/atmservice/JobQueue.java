package com.example.atmservice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JobQueue {
    private List<Request> requests;
    private RequestComparator requestComparator = new RequestComparator();

    public JobQueue() {
        requests = new ArrayList<>();
    }

    public void addJob(Request newRequest) {
        Request foundRequest = null;
        for (Request request : requests) {
            if (request.getRegionId() == newRequest.getRegionId()) {
                if (request.getAtmId() == newRequest.getAtmId()) {
                    foundRequest = request;
                    break;
                }
            }
        }
        if (foundRequest != null) {
            // add new request type to already existing request object
            foundRequest.addRequestType(newRequest.getRequestType());
        } else {
            requests.add(newRequest);
        }
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void sortJobs() {
        Collections.sort(requests, requestComparator);
    }
}
