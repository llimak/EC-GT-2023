package com.example.onlinegame;

import java.util.Comparator;

public class ClanComparator implements Comparator<Clan> {
    @Override
    public int compare(Clan clan1, Clan clan2) {
        int compareResult = Integer.compare(clan2.getPoints(), clan1.getPoints());
        if (compareResult != 0)
            return compareResult;
        else
            return Float.compare(clan2.getPlayersPointsRatio(), clan1.getPlayersPointsRatio());
    }
}
