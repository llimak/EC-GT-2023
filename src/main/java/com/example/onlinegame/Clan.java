package com.example.onlinegame;

import org.json.simple.JSONObject;

public class Clan {
    private int numberOfPlayers;
    private int points;

    public Clan(int numberOfPlayers, int points) {
        this.numberOfPlayers = numberOfPlayers;
        this.points = points;
    }

    public int getNumberOfPlayers() {
        return this.numberOfPlayers;
    }

    public int getPoints() {
        return this.points;
    }

    public float getPlayersPointsRatio() {
        return this.points / this.numberOfPlayers;
    }

    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("numberOfPlayers", this.numberOfPlayers);
        jsonObject.put("points", this.points);
        return jsonObject;
    }
}
