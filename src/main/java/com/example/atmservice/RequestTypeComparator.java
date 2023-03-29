package com.example.atmservice;

import java.util.Comparator;

public class RequestTypeComparator implements Comparator<RequestType> {
    @Override
    public int compare(RequestType p1, RequestType p2) {
        return Integer.compare(p2.getValue(), p1.getValue());
    }
}
