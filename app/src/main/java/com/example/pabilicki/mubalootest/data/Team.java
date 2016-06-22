package com.example.pabilicki.mubalootest.data;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * Model of the team object containing name of the team and list of members of the team
 *
 * @author Piotr Aleksander Bilicki
 */
public class Team {
    public static final String TEAM_IOS = "iOS";
    public static final String TEAM_ANDROID = "Android";
    public static final String TEAM_WEB = "Web";
    public static final String TEAM_DESIGN = "Design";

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

