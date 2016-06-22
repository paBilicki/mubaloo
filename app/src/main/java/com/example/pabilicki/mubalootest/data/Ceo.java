package com.example.pabilicki.mubalootest.data;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Model of the ceo object
 *
 * @author Piotr Aleksander Bilicki
 */
public class Ceo extends TeamMember{
    private final String role = "CEO";
    private final boolean teamLead = false;

    public Ceo(JSONObject member) throws JSONException {
        super(member);
    }

}
