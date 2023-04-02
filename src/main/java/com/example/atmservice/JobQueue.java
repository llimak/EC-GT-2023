package com.example.atmservice;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JobQueue {
    private List<Request> requests;
    private RequestComparator requestComparator = new RequestComparator();
    JSONParser parser = new JSONParser();

    public JobQueue() {
        requests = new ArrayList<>();
    }

    public void loadJobs(JSONArray jsonArray) throws org.json.simple.parser.ParseException {
        for (Object obj : jsonArray) {
            JSONObject jsonObj = (JSONObject) parser.parse(obj.toString());
            int region = (int) (long) jsonObj.get("region");
            int atmId = (int) (long) jsonObj.get("atmId");
            String requestType = jsonObj.get("requestType").toString();
            Atm atm = new Atm(atmId, region);
            Request request = new Request(atm, requestType);
            this.addJob(request);
        }
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

    public JSONArray toJSONObject() {
        JSONArray requestsArray = new JSONArray();
        for (Request request : requests) {
            requestsArray.add(request.toJSONObject());
        }
        return requestsArray;
    }
}
