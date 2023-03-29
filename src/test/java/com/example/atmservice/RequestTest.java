package com.example.atmservice;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class RequestTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void comparingStandardAndPriority() {
        Request request = new Request(new Atm(0, 0), RequestType.STANDARD);
        request.addRequestType(RequestType.PRIORITY);

        Assert.assertEquals(RequestType.PRIORITY, request.getRequestType());
    }

    @Test
    public void comparingPriorityAndFailureRestart() {
        Request request = new Request(new Atm(0, 0), RequestType.FAILURE_RESTART);
        request.addRequestType(RequestType.PRIORITY);

        Assert.assertEquals(RequestType.FAILURE_RESTART, request.getRequestType());
    }
}
