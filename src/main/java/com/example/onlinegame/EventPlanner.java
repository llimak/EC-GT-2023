package com.example.onlinegame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.util.LinkedList;
import java.util.Queue;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class EventPlanner {
    private List<Clan> clans = new ArrayList<>();
    private List<EventGroup> groups = new ArrayList<>();
    private int maxNumberOfPlayersInGroup;
    private ClanComparator clanComparator = new ClanComparator();

    public EventPlanner(int maxNumberOfPlayersInGroup) {
        this.maxNumberOfPlayersInGroup = maxNumberOfPlayersInGroup;
    }

    public EventPlanner(JSONObject jObject) {
        this.maxNumberOfPlayersInGroup = Integer.parseInt(jObject.get("groupCount").toString());
        JSONArray clans = (JSONArray) jObject.get("clans");
        for (Object clan : clans) {
            JSONObject clanObj = (JSONObject) clan;
            int points = Integer.parseInt(clanObj.get("points").toString());
            int numberOfPlayers = Integer.parseInt(clanObj.get("numberOfPlayers").toString());
            this.clans.add(new Clan(numberOfPlayers, points));
        }
    }

    public void addClan(Clan clan) {
        this.clans.add(clan);
    }

    public List<Clan> getClans() {
        return this.clans;
    }

    public List<EventGroup> getGroups() {
        return this.groups;
    }

    public JSONArray getGroupsInJSONArray() {
        JSONArray jsonArray = new JSONArray();
        for (EventGroup eventGroup : this.groups) {
            jsonArray.add(eventGroup.toJSONArray());
        }
        return jsonArray;
    }

    private void sortClans() {
        Collections.sort(clans, clanComparator);
    }

    private EventGroup createNewEventGroup(Clan clan) {
        EventGroup newEventGroup = new EventGroup(maxNumberOfPlayersInGroup);
        newEventGroup.addClan(clan);
        return newEventGroup;
    }

    private List<Clan> createGroups(List<Clan> clans) {
        int minNumberOfPlayersInRestClans = Integer.MAX_VALUE;
        Queue<Clan> queueOfClans = new LinkedList<>();
        for (Clan clan : clans) {
            if (groups.size() == 0) {
                groups.add(createNewEventGroup(clan));
            } else {
                EventGroup lastGroup = groups.get(groups.size() - 1);
                if (lastGroup.isFull()) {
                    EventGroup newEventGroup = new EventGroup(maxNumberOfPlayersInGroup);
                    groups.add(newEventGroup);
                    lastGroup = newEventGroup;
                }
                while (!queueOfClans.isEmpty()) {
                    Clan clanFromPreviousLoop = queueOfClans.peek();
                    if (lastGroup.addClan(clanFromPreviousLoop)) {
                        queueOfClans.remove();
                    } else {
                        break;
                    }
                }
                if (!lastGroup.addClan(clan)) {
                    if (minNumberOfPlayersInRestClans > clan.getNumberOfPlayers())
                        minNumberOfPlayersInRestClans = clan.getNumberOfPlayers();
                    queueOfClans.add(clan);
                }
            }
        }
        EventGroup lastGroup = groups.get(groups.size() - 1);
        if (!queueOfClans.isEmpty() && lastGroup.getNumberOfFreeSlots() < minNumberOfPlayersInRestClans)
            groups.add(new EventGroup(maxNumberOfPlayersInGroup));
        List<Clan> restOfClans = new ArrayList<>(queueOfClans);
        Collections.sort(restOfClans, clanComparator);
        return restOfClans;
    }

    public void createGroups() {
        sortClans();
        List<Clan> restOfClans = createGroups(this.clans);
        while (!restOfClans.isEmpty()) {
            restOfClans = createGroups(restOfClans);
        }
    }
}
