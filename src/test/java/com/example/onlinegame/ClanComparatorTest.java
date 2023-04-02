package com.example.onlinegame;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class ClanComparatorTest {
    @Test
    public void theSameNumberOfPlayersCase() {
        List<Clan> clans = new ArrayList<>();
        ClanComparator clanComparator = new ClanComparator();
        clans.add(new Clan(2, 20));
        clans.add(new Clan(2, 25));
        clans.add(new Clan(2, 100));
        Collections.sort(clans, clanComparator);

        Assert.assertEquals(100, clans.get(0).getPoints());
        Assert.assertEquals(25, clans.get(1).getPoints());
        Assert.assertEquals(20, clans.get(2).getPoints());
    }

    @Test
    public void notTheSameNumberOfPlayersCase() {
        List<Clan> clans = new ArrayList<>();
        ClanComparator clanComparator = new ClanComparator();
        clans.add(new Clan(2, 20));
        clans.add(new Clan(2, 25));
        clans.add(new Clan(5, 100));
        Collections.sort(clans, clanComparator);

        Assert.assertEquals(100, clans.get(0).getPoints());
        Assert.assertEquals(25, clans.get(1).getPoints());
        Assert.assertEquals(20, clans.get(2).getPoints());
    }

    @Test
    public void notTheSameNumberOfPlayersCaseTheSamePoints() {
        List<Clan> clans = new ArrayList<>();
        ClanComparator clanComparator = new ClanComparator();
        clans.add(new Clan(1, 20));
        clans.add(new Clan(2, 20));
        clans.add(new Clan(2, 25));
        clans.add(new Clan(7, 100));
        clans.add(new Clan(5, 100));
        Collections.sort(clans, clanComparator);

        Assert.assertEquals(100, clans.get(0).getPoints());
        Assert.assertEquals(5, clans.get(0).getNumberOfPlayers());

        Assert.assertEquals(100, clans.get(1).getPoints());
        Assert.assertEquals(7, clans.get(1).getNumberOfPlayers());

        Assert.assertEquals(25, clans.get(2).getPoints());
        Assert.assertEquals(2, clans.get(2).getNumberOfPlayers());

        Assert.assertEquals(20, clans.get(3).getPoints());
        Assert.assertEquals(1, clans.get(3).getNumberOfPlayers());

        Assert.assertEquals(20, clans.get(4).getPoints());
        Assert.assertEquals(2, clans.get(4).getNumberOfPlayers());
    }
}
