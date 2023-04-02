package com.example.onlinegame;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class EventPlannerTest {
    @Test
    public void firstCase() {
        EventPlanner eventPlanner = new EventPlanner(6);
        eventPlanner.addClan(new Clan(4, 50));
        eventPlanner.addClan(new Clan(2, 70));
        eventPlanner.addClan(new Clan(6, 60));
        eventPlanner.addClan(new Clan(1, 15));
        eventPlanner.addClan(new Clan(5, 40));
        eventPlanner.addClan(new Clan(3, 45));
        eventPlanner.addClan(new Clan(1, 12));
        eventPlanner.addClan(new Clan(4, 40));

        eventPlanner.createGroups();
        List<EventGroup> groups = eventPlanner.getGroups();

        // 1st group
        Assert.assertEquals(70, groups.get(0).getClans().get(0).getPoints());
        Assert.assertEquals(2, groups.get(0).getClans().get(0).getNumberOfPlayers());
        Assert.assertEquals(50, groups.get(0).getClans().get(1).getPoints());
        Assert.assertEquals(4, groups.get(0).getClans().get(1).getNumberOfPlayers());

        // 2nd group
        Assert.assertEquals(60, groups.get(1).getClans().get(0).getPoints());
        Assert.assertEquals(6, groups.get(1).getClans().get(0).getNumberOfPlayers());

        // 3rd group
        Assert.assertEquals(45, groups.get(2).getClans().get(0).getPoints());
        Assert.assertEquals(3, groups.get(2).getClans().get(0).getNumberOfPlayers());
        Assert.assertEquals(15, groups.get(2).getClans().get(1).getPoints());
        Assert.assertEquals(1, groups.get(2).getClans().get(1).getNumberOfPlayers());
        Assert.assertEquals(12, groups.get(2).getClans().get(2).getPoints());
        Assert.assertEquals(1, groups.get(2).getClans().get(2).getNumberOfPlayers());

        // 4th group
        Assert.assertEquals(40, groups.get(3).getClans().get(0).getPoints());
        Assert.assertEquals(4, groups.get(3).getClans().get(0).getNumberOfPlayers());

        // 5th group
        Assert.assertEquals(40, groups.get(4).getClans().get(0).getPoints());
        Assert.assertEquals(5, groups.get(4).getClans().get(0).getNumberOfPlayers());

        Assert.assertEquals(5, groups.size());
    }

    @Test
    public void threeClansDoesNotFitInFirstRound() {
        EventPlanner eventPlanner = new EventPlanner(6);
        eventPlanner.addClan(new Clan(4, 50));
        eventPlanner.addClan(new Clan(2, 70));
        eventPlanner.addClan(new Clan(6, 60));
        eventPlanner.addClan(new Clan(1, 15));
        eventPlanner.addClan(new Clan(5, 40));
        eventPlanner.addClan(new Clan(3, 45));
        eventPlanner.addClan(new Clan(1, 12));
        eventPlanner.addClan(new Clan(4, 40));
        eventPlanner.addClan(new Clan(2, 10));

        eventPlanner.createGroups();
        List<EventGroup> groups = eventPlanner.getGroups();

        // 4th group
        Assert.assertEquals(40, groups.get(3).getClans().get(0).getPoints());
        Assert.assertEquals(4, groups.get(3).getClans().get(0).getNumberOfPlayers());
        Assert.assertEquals(10, groups.get(3).getClans().get(1).getPoints());
        Assert.assertEquals(2, groups.get(3).getClans().get(1).getNumberOfPlayers());

        // 5th group
        Assert.assertEquals(40, groups.get(4).getClans().get(0).getPoints());
        Assert.assertEquals(5, groups.get(4).getClans().get(0).getNumberOfPlayers());

        Assert.assertEquals(5, groups.size());
    }
}
