package com.example.pabilicki.mubalootest.data;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by piotr on 17.06.2016.
 */
public class Team {
    private String teamName;
    private ArrayList<TeamMember> members;

    public Team(String teamName) throws JSONException {
        this.teamName = teamName;
        this.members = new ArrayList<>();
    }

    public void addMember(TeamMember teamMember){
        this.members.add(teamMember);
    }

    public ArrayList<TeamMember> getMembers() {
        return members;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getNumberOfMembers(){
        return members.size();
    }
}

