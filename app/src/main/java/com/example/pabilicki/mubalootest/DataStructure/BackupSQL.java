package com.example.pabilicki.mubalootest.DataStructure;

import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by piotr on 18.06.2016.
 */
public final class BackupSQL {
    private static String TAG = "pbBilu.BackupSQL";
    private static ArrayList<Team> allTeams;
    private static Ceo ceo;
    private static boolean dbExists = true;

    public static String dbPath = null;
    private static String dbName = "Mubaloo.db";


    public static void setAllTeams(ArrayList<Team> inputTeams, Ceo inputCeo) {
        allTeams = inputTeams;
        ceo = inputCeo;
    }

    public static void saveToSQL() {
        SQLiteDatabase db = openOrCreateSQL();
        savingCeo(db);
        savingTeamsWithMembers(db);
        db.close();
        setDbExists(true);
    }


    private static SQLiteDatabase openOrCreateSQL() {
        SQLiteDatabase.CursorFactory factory = new SQLiteDatabase.CursorFactory() {
            @Override
            public Cursor newCursor(SQLiteDatabase db, SQLiteCursorDriver masterQuery, String editTable, SQLiteQuery query) {
                return new SQLiteCursor(masterQuery, editTable, query);
            }
        };
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbPath + dbName, factory);
        Log.d(TAG, "openOrCreateSQL: returning DB to open");
        return db;
    }


    private static void savingCeo(SQLiteDatabase db) {
        Log.d(TAG, "savingCeo");
        db.execSQL("CREATE TABLE IF NOT EXISTS Ceo (Id VARCHAR, FirstName VARCHAR, LastName VARCHAR, Role VARCHAR, ProfileImageURL VARCHAR);");
        db.execSQL("DELETE FROM Ceo;");

        db.execSQL("INSERT INTO Ceo VALUES('"
                + ceo.getId() + "', '" + ceo.getFirstName() + "', '" + ceo.getLastName() + "', '"
                + ceo.getRole() + "', '" + ceo.getProfileImageURL() + "')");
    }


    private static void savingTeamsWithMembers(SQLiteDatabase db) {
        Log.d(TAG, "savingTeamsWithMembers");
        db.execSQL("CREATE TABLE IF NOT EXISTS Teams (TeamName VARCHAR, NumberOfMembers INT(3));");
        db.execSQL("DELETE FROM Teams;");

//        for (int i = allTeams.size() - 1; i >= 0; i--) {
        for (int i = 0; i < allTeams.size(); i++) {

            String teamName = allTeams.get(i).getTeamName();
            int numberOfMembers = allTeams.get(i).getNumberOfMembers();

            Log.d(TAG, "saveToSQL: " + teamName);
            db.execSQL("INSERT INTO Teams VALUES('" + teamName + "', '" + numberOfMembers + "')");
            db.execSQL("CREATE TABLE IF NOT EXISTS " + teamName + " (Id VARCHAR, FirstName VARCHAR, LastName VARCHAR, Role VARCHAR, ProfileImageURL VARCHAR);");
            db.execSQL("DELETE FROM " + teamName + ";");

//            for (int j = numberOfMembers - 1; j >= 0; j--) {
            for (int j = 0; j < numberOfMembers; j++) {

                String id = allTeams.get(i).getMembers().get(j).getId();
                String firstName = allTeams.get(i).getMembers().get(j).getFirstName();
                String lastName = allTeams.get(i).getMembers().get(j).getLastName();
                String role = allTeams.get(i).getMembers().get(j).getRole();
                String profileImgUrl = allTeams.get(i).getMembers().get(j).getProfileImageURL();

                db.execSQL("INSERT INTO " + teamName + " VALUES('"
                        + id + "', '" + firstName + "', '" + lastName + "', '"
                        + role + "', '" + profileImgUrl + "')");
            }
        }
    }


    public static JSONArray loadFromSQL() {
        Log.d(TAG, "loadFromSQL");
        JSONArray jsonData = new JSONArray();

        if (dbExists) {
            SQLiteDatabase db = openOrCreateSQL();

            JSONObject jsonCeo = loadingCeo(db);
            jsonData.put(jsonCeo);

            ArrayList<String> teamNames = retrievingNames(db);
            for (String teamName : teamNames) {
                JSONObject jsonTeam = loadingTeams(db, teamName);
                jsonData.put(jsonTeam);
            }
            db.close();
        } else {
            Log.d(TAG, "loadFromSQL: There is no DB!");
        }
        setDbExists(true);

        return jsonData;
    }


    private static JSONObject loadingCeo(SQLiteDatabase db) {
        Log.d(TAG, "loadingCeo");
        JSONObject jsonCeo = new JSONObject();
        Cursor c = db.rawQuery("SELECT * FROM Ceo", null);


//        jsonCeo = parsingPerson(c);


        if (c.moveToFirst()) {
            while (c.isAfterLast() == false) {
                String id = c.getString(c.getColumnIndex("Id"));
                while (id.length() < 3) {
                    id = "0" + id;
                }
                String firstName = c.getString(c.getColumnIndex("FirstName"));
                String lastName = c.getString(c.getColumnIndex("LastName"));
                String role = c.getString(c.getColumnIndex("Role"));
                String profileImageURL = c.getString(c.getColumnIndex("ProfileImageURL"));

                try {
                    jsonCeo.put("id", id);
                    jsonCeo.put("firstName", firstName);
                    jsonCeo.put("lastName", lastName);
                    jsonCeo.put("role", role);
                    jsonCeo.put("profileImageURL", profileImageURL);

                } catch (JSONException e) {
                    e.getMessage();
                }

                c.moveToNext();

            }
        }

        return jsonCeo;
    }

    private static ArrayList<String> retrievingNames(SQLiteDatabase db) {
        ArrayList<String> teamNames = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM Teams", null);

        // Retrieving names of teams
        if (c.moveToFirst()) {
            while (c.isAfterLast() == false) {
                String teamName = c.getString(c.getColumnIndex("TeamName"));
                teamNames.add(teamName);
                c.moveToNext();
            }
        } else {
            c.close();
        }
        return teamNames;
    }

    private static JSONObject loadingTeams(SQLiteDatabase db, String teamName) {

        Cursor cur = db.rawQuery("SELECT * FROM " + teamName + "", null);
        JSONArray members = new JSONArray();
        JSONObject jsonTeam = new JSONObject();
        if (cur.moveToFirst()) {

            //trying
//                JSONObject jsonTeamMember = parsingPerson(cur);
//                members.put(jsonTeamMember);
            // end trying


            while (!cur.isAfterLast()) {
                JSONObject teamMember = new JSONObject();
                String id = cur.getString(cur.getColumnIndex("Id"));
                while (id.length() < 3) {
                    id = "0" + id;
                }
                String firstName = cur.getString(cur.getColumnIndex("FirstName"));
                String lastName = cur.getString(cur.getColumnIndex("LastName"));
                String role = cur.getString(cur.getColumnIndex("Role"));
                String profileImageURL = cur.getString(cur.getColumnIndex("ProfileImageURL"));

                try {
                    teamMember.put("id", id);
                    teamMember.put("firstName", firstName);
                    teamMember.put("lastName", lastName);
                    teamMember.put("role", role);
                    if (role.contains("Team Lead")) {
                        teamMember.put("teamLead", true);
                    }
                    teamMember.put("profileImageURL", profileImageURL);

                } catch (JSONException e) {
                    e.getMessage();
                }
                members.put(teamMember);
                cur.moveToNext();
            }

        }
        try {
            jsonTeam.put("teamName", teamName);
            jsonTeam.put("members", members);

        } catch (JSONException e) {
            e.getMessage();
        }

        JSONObject jsonAllTeams = new JSONObject();
        return jsonTeam;
    }


    // TODO: 19.06.2016 encapsulation of a stub that grabs data from db about CEO and each team member
    private static JSONObject parsingPerson(Cursor c) {
        JSONObject jsonPerson = new JSONObject();

        while (!c.isAfterLast()) {
            JSONObject teamMember = new JSONObject();
            String id = c.getString(c.getColumnIndex("Id"));
            while (id.length() < 3) {
                id = "0" + id;
            }
            String firstName = c.getString(c.getColumnIndex("FirstName"));
            String lastName = c.getString(c.getColumnIndex("LastName"));
            String role = c.getString(c.getColumnIndex("Role"));
            String profileImageURL = c.getString(c.getColumnIndex("ProfileImageURL"));

            try {
                teamMember.put("id", id);
                teamMember.put("firstName", firstName);
                teamMember.put("lastName", lastName);
                teamMember.put("role", role);
                if (role.contains("Team Lead")) {
                    teamMember.put("teamLead", true);
                }
                teamMember.put("profileImageURL", profileImageURL);

            } catch (JSONException e) {
                e.getMessage();
            }
        }
        return jsonPerson;
    }


    private static void setDbExists(boolean dbExists) {
        BackupSQL.dbExists = dbExists;
    }


    public static boolean isDbExists() {
        return dbExists;
    }


    public static void setDbPath(String path) {
        dbPath = "/" + path + "/";
    }


    public static Ceo getCeo() {
        return ceo;
    }
}
