package com.example.pabilicki.mubalootest.data;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Model of the team member object
 *
 * @author Piotr Aleksander Bilicki
 */
public class TeamMember extends JSONObject implements Serializable {
    private final static String KEY_ID = "id";
    private final static String KEY_FIRST_NAME ="firstName";
    private final static String KEY_LAST_NAME = "lastName";
    private final static String KEY_ROLE = "role";
    private final static String KEY_TEAM_LEAD = "Team Lead";
    private final static String KEY_PROFILE_IMG_URL = "profileImageURL";
    private String id;
    private String firstName;
    private String lastName;
    private String role;
    private String profileImageURL;

    private boolean teamLead = false;

    /**
     * sets the fields of a single team member
     *
     * @param member part of the downloaded json describing a single member
     * @throws JSONException
     */
    public TeamMember(JSONObject member) throws JSONException {
        this.id = member.getString(KEY_ID);
        this.firstName = member.getString(KEY_FIRST_NAME);
        this.lastName = member.getString(KEY_LAST_NAME);
        this.role = member.getString(KEY_ROLE);
        if (role.contains(KEY_TEAM_LEAD)){
            this.teamLead = true;
        }
        this.profileImageURL = member.getString(KEY_PROFILE_IMG_URL);
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

