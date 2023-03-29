package com.example.atmservice;

import java.util.Comparator;

public class RequestComparator implements Comparator<Request> {
    @Override
    public int compare(Request request1, Request request2) {
        int result = Integer.compare(request1.getRegionId(), request2.getRegionId());
        if (result == 0) {
            result = Integer.compare(request2.getRequestType().getValue(), request1.getRequestType().getValue());
        }
        return result;
    }
}