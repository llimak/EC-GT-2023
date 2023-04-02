package com.example.atmservice;

enum RequestType {
    STANDARD(1),
    SIGNAL_LOW(2),
    PRIORITY(3),
    FAILURE_RESTART(4);

    private int value;

    private RequestType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}