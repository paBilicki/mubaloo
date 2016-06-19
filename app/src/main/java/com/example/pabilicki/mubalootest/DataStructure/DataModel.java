package com.example.pabilicki.mubalootest.DataStructure;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by piotr on 17.06.2016.
 */
public class DataModel {
    private JSONArray data;
    private ArrayList<Team> allTeams = new ArrayList<>();
    private Ceo ceo;
    private String TAG = "pbBilu.DataModel";

    public DataModel(JSONArray data) throws JSONException {
        this.data = data;
        parsingCeo();
        parsingTeams();
        BackupSQL.setAllTeams(allTeams, ceo);
    }

    private void parsingCeo() throws JSONException {
        ceo = new Ceo(data.getJSONObject(0));
        Log.d(TAG, "parsingCeo: " + ceo.getFirstName() + " " + ceo.getLastName());
    }

    private void parsingTeams() throws JSONException {
        for (int i = 1; i < data.length(); i++) {
            JSONObject teamData = data.getJSONObject(i);
            String teamName = teamData.getString("teamName");
            Log.d(TAG, "parsingTeams: " + teamName);
            parsingPeople(teamName, teamData);
        }
    }

    private void parsingPeople(String teamName, JSONObject teamData) throws JSONException {
        JSONArray members = teamData.getJSONArray("members");
        Team team = new Team(teamName);

        for (int j = 0; j < members.length(); j++) {
            TeamMember teamMember = new TeamMember(members.getJSONObject(j));
//            Log.d(TAG, "parsingPeople: " + j + " " + teamMember.getFirstName() + " " + teamMember.getLastName());
            team.addMember(teamMember);
        }
        allTeams.add(team);
    }

    public Ceo getCeo() {
        return ceo;
    }

    public ArrayList<Team> getAllTeams() {
        return allTeams;
    }

}
