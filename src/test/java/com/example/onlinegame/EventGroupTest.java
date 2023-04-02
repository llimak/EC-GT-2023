package com.example.onlinegame;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class EventGroupTest {
    @Test
    public void addClanToEmptyGroup() {
        EventGroup eventGroup = new EventGroup(6);
        eventGroup.addClan(new Clan(4, 50));
        Assert.assertEquals(4, eventGroup.getCurrentNumberOfPlayers());
    }

    @Test
    public void addClanToFullGroup() {
        EventGroup eventGroup = new EventGroup(6);
        eventGroup.addClan(new Clan(4, 50));
        Assert.assertEquals(4, eventGroup.getCurrentNumberOfPlayers());
        eventGroup.addClan(new Clan(2, 50));
        Assert.assertEquals(6, eventGroup.getCurrentNumberOfPlayers());
        Assert.assertEquals(false, eventGroup.addClan(new Clan(2, 10)));
        Assert.assertEquals(6, eventGroup.getCurrentNumberOfPlayers());
    }
}
