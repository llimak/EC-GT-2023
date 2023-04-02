package com.example.atmservice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.simple.JSONObject;

public class Request {
    private Atm atm;
    private List<RequestType> requestTypes;

    private RequestTypeComparator requestTypeComparator = new RequestTypeComparator();
    // private RequestType requestType;

    public Request(Atm atm, String requestType) {
        this.requestTypes = new ArrayList<>();
        this.atm = atm;
        requestTypes.add(RequestType.valueOf(requestType));
    }

    public Request(Atm atm, RequestType requestType) {
        this.requestTypes = new ArrayList<>();
        this.atm = atm;
        requestTypes.add(requestType);
    }

    public RequestType getRequestType() {
        return requestTypes.get(0);
    }

    public void addRequestType(RequestType requestType) {
        this.requestTypes.add(requestType);
        Collections.sort(this.requestTypes, this.requestTypeComparator);
        // this.requestType = RequestType.valueOf(requestType);
    }

    public int getAtmId() {
        return atm.getId();
    }

    public int getRegionId() {
        return atm.getRegionId();
    }

    public JSONObject toJSONObject() {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("region", this.getRegionId());
        jsonObj.put("atmId", this.getAtmId());
        return jsonObj;
    }
}
