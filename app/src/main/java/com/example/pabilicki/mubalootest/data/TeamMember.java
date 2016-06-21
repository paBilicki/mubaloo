package com.example.pabilicki.mubalootest.data;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by piotr on 17.06.2016.
 */
public class TeamMember extends JSONObject implements Serializable {
    private String id;
    private String firstName;
    private String lastName;
    private String role;
    private String profileImageURL;

    private boolean teamLead = false;

    public TeamMember(JSONObject member) throws JSONException {
        this.id = member.getString("id");
        this.firstName = member.getString("firstName");
        this.lastName = member.getString("lastName");
        this.role = member.getString("role");
        if (role.contains("Team Lead")){
            this.teamLead = true;
        }
        this.profileImageURL = member.getString("profileImageURL");
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRole() {
        return role;
    }

    public String getProfileImageURL() {
        return profileImageURL;
    }

    public boolean isTeamLead() {
        return teamLead;
    }

    public String getDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append("Just few lines of description about a team member")
                .append("that could have been fetched from a json file ")
                .append("and added to the data structure ")
                .append("in order to fill the space in the more detailed view.");
        return sb.toString();
    }

}

