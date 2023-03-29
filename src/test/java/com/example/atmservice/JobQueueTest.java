package com.example.atmservice;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class JobQueueTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void firstCase() {
        JobQueue jobQueue = new JobQueue();
        jobQueue.addJob(new Request(new Atm(1, 4), RequestType.STANDARD));
        jobQueue.addJob(new Request(new Atm(1, 1), RequestType.STANDARD));
        jobQueue.addJob(new Request(new Atm(1, 2), RequestType.STANDARD));
        jobQueue.addJob(new Request(new Atm(2, 3), RequestType.PRIORITY));
        jobQueue.addJob(new Request(new Atm(1, 3), RequestType.STANDARD));
        jobQueue.addJob(new Request(new Atm(1, 2), RequestType.SIGNAL_LOW)); // the same atm another signal
        jobQueue.addJob(new Request(new Atm(2, 5), RequestType.STANDARD));
        jobQueue.addJob(new Request(new Atm(1, 5), RequestType.FAILURE_RESTART));

        jobQueue.sortJobs();
        List<Request> requests = jobQueue.getRequests();

        Assert.assertEquals(1, requests.get(0).getRegionId());
        Assert.assertEquals(1, requests.get(0).getAtmId());
        Assert.assertEquals(2, requests.get(1).getRegionId());
        Assert.assertEquals(1, requests.get(1).getAtmId());
        Assert.assertEquals(3, requests.get(2).getRegionId());
        Assert.assertEquals(2, requests.get(2).getAtmId());
        Assert.assertEquals(3, requests.get(3).getRegionId());
        Assert.assertEquals(1, requests.get(3).getAtmId());
        Assert.assertEquals(4, requests.get(4).getRegionId());
        Assert.assertEquals(1, requests.get(4).getAtmId());
        Assert.assertEquals(5, requests.get(5).getRegionId());
        Assert.assertEquals(1, requests.get(5).getAtmId());
        Assert.assertEquals(5, requests.get(6).getRegionId());
        Assert.assertEquals(2, requests.get(6).getAtmId());
    }

    @Test
    public void secondCase() {
        JobQueue jobQueue = new JobQueue();
        jobQueue.addJob(new Request(new Atm(2, 1), RequestType.STANDARD));
        jobQueue.addJob(new Request(new Atm(1, 1), RequestType.STANDARD));
        jobQueue.addJob(new Request(new Atm(3, 2), RequestType.PRIORITY));
        jobQueue.addJob(new Request(new Atm(4, 3), RequestType.STANDARD));
        jobQueue.addJob(new Request(new Atm(5, 4), RequestType.STANDARD));
        jobQueue.addJob(new Request(new Atm(2, 5), RequestType.PRIORITY));
        jobQueue.addJob(new Request(new Atm(1, 5), RequestType.STANDARD));
        jobQueue.addJob(new Request(new Atm(2, 3), RequestType.SIGNAL_LOW));
        jobQueue.addJob(new Request(new Atm(1, 2), RequestType.SIGNAL_LOW));
        jobQueue.addJob(new Request(new Atm(1, 3), RequestType.FAILURE_RESTART));

        jobQueue.sortJobs();
        List<Request> requests = jobQueue.getRequests();

        Assert.assertEquals(1, requests.get(0).getRegionId());
        Assert.assertEquals(2, requests.get(0).getAtmId());
        Assert.assertEquals(1, requests.get(1).getRegionId());
        Assert.assertEquals(1, requests.get(1).getAtmId());
        Assert.assertEquals(2, requests.get(2).getRegionId());
        Assert.assertEquals(3, requests.get(2).getAtmId());
        Assert.assertEquals(2, requests.get(3).getRegionId());
        Assert.assertEquals(1, requests.get(3).getAtmId());
        Assert.assertEquals(3, requests.get(4).getRegionId());
        Assert.assertEquals(1, requests.get(4).getAtmId());
        Assert.assertEquals(3, requests.get(5).getRegionId());
        Assert.assertEquals(2, requests.get(5).getAtmId());
        Assert.assertEquals(3, requests.get(6).getRegionId());
        Assert.assertEquals(4, requests.get(6).getAtmId());
        Assert.assertEquals(4, requests.get(7).getRegionId());
        Assert.assertEquals(5, requests.get(7).getAtmId());
        Assert.assertEquals(5, requests.get(8).getRegionId());
        Assert.assertEquals(2, requests.get(8).getAtmId());
        Assert.assertEquals(5, requests.get(9).getRegionId());
        Assert.assertEquals(1, requests.get(9).getAtmId());
    }
}
