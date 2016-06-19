package com.example.pabilicki.mubalootest.DataStructure;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by piotr on 17.06.2016.
 */
public class TeamMember extends JSONObject {
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

    public boolean isTeamlead() {
        return teamLead;
    }
}

