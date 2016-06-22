package com.example.pabilicki.mubalootest.data;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Main class of the data package that creates the model of the company using the downloaded
 * (or loaded from the db) json for further processing and displaying
 *
 * @author Piotr Aleksander Bilicki
 */
public class DataModel {
    private static final String KEY_MEMBERS = "members";
    public static final String KEY_TEAM_NAME = "teamName";
    public static final String PARAM_CEO = "ceo";
    public static final String PARAM_TEAM_MEMBER_SERIALIZABLE = "teamMember";
    public static final String JSON_URL = "http://developers.mub.lu/resources/team.json";

    private JSONArray data;
    private ArrayList<Team> allTeams = new ArrayList<>();
    private Ceo ceo;

    public DataModel(JSONArray data) throws JSONException {
        this.data = data;
        parsingCeo();
        parsingTeams();
    }


    private void parsingCeo() throws JSONException {
        ceo = new Ceo(data.getJSONObject(0));
    }


    /**
     * function called from the constructor that finds team name in the json and call parsingPeople
     * function in order to take corresponding team members
     *
     * @throws JSONException
     */
    private void parsingTeams() throws JSONException {
        for (int i = 1; i < data.length(); i++) {
            JSONObject teamData = data.getJSONObject(i);
            String teamName = teamData.getString(KEY_TEAM_NAME);
            parsingPeople(teamName, teamData);
        }
    }

    /**
     * function called from parsingTeams that adds each team member to the corresponding team
     * and afterwards each team to the ArrayList of teams
     *
     * @param teamName name of the team
     * @param teamData part of the json containing information about the team
     * @throws JSONException
     */
    private void parsingPeople(String teamName, JSONObject teamData) throws JSONException {
        JSONArray members = teamData.getJSONArray(KEY_MEMBERS);
        Team team = new Team(teamName);

        for (int j = 0; j < members.length(); j++) {
            TeamMember teamMember = new TeamMember(members.getJSONObject(j));
            team.addMember(teamMember);
        }
        allTeams.add(team);
    }

    public Ceo getCeo() throws JSONException {
        return ceo;
    }

    public ArrayList<Team> getAllTeams() {
        return allTeams;
    }

}
