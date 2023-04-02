package com.example.onlinegame;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class EventGroup {
    private List<Clan> clans;
    private int maxNumberOfPlayers;
    private int currentNumberOfPlayers;

    EventGroup(int maxNumberOfPlayers) {
        this.clans = new ArrayList<>();
        this.maxNumberOfPlayers = maxNumberOfPlayers;
        this.currentNumberOfPlayers = 0;
    }

    public List<Clan> getClans() {
        return this.clans;
    }

    public int getCurrentNumberOfPlayers() {
        return this.currentNumberOfPlayers;
    }

    public int getNumberOfFreeSlots() {
        return this.maxNumberOfPlayers - this.getCurrentNumberOfPlayers();
    }

    public boolean isFull() {
        return this.getNumberOfFreeSlots() == 0;
    }

    public boolean addClan(Clan clan) {
        if (this.getNumberOfFreeSlots() >= clan.getNumberOfPlayers()) {
            this.clans.add(clan);
            this.currentNumberOfPlayers += clan.getNumberOfPlayers();
            return true;
        }
        return false;
    }

    public JSONArray toJSONArray() {
        JSONArray jsonArray = new JSONArray();
        for (Clan clan : this.clans) {
            jsonArray.add(clan.toJSONObject());
        }
        return jsonArray;
    }
}
