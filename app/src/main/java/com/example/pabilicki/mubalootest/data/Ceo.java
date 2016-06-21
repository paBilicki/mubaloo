package com.example.pabilicki.mubalootest.data;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by piotr on 17.06.2016.
 */
public class Ceo extends TeamMember{
    private final String role = "CEO";
    private final boolean teamLead = false;

    public Ceo(JSONObject member) throws JSONException {
        super(member);
    }

}
