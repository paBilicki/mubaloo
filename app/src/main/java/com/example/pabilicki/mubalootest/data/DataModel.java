package com.example.pabilicki.mubalootest.data;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/*
* @see
*
* */
public class DataModel {
    private static final String KEY_MEMBERS = "members";
    public static final String KEY_TEAM_NAME = "teamName";
    public static final String PARAM_CEO = "ceo";
    public static final String PARAM_TEAM_MEMBER_SERIALIZABLE = "teamMember";

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

    private void parsingTeams() throws JSONException {
        for (int i = 1; i < data.length(); i++) {
            JSONObject teamData = data.getJSONObject(i);
            String teamName = teamData.getString(KEY_TEAM_NAME);
            parsingPeople(teamName, teamData);
        }
    }

    /**
     * @doc
     * @param teamName
     * @param teamData
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
