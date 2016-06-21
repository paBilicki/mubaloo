package com.example.pabilicki.mubalootest.Loader;


import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.example.pabilicki.mubalootest.DataStructure.BackupSQL;
import com.example.pabilicki.mubalootest.DataStructure.DataModel;
import com.example.pabilicki.mubalootest.DataStructure.Team;
import com.example.pabilicki.mubalootest.SplashScreen;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.util.List;

public class TeamListLoader extends AsyncTaskLoader<List<Team>> {
    private String TAG = "pbBilu.TeamListLoader";

    public TeamListLoader(Context context) {
        super(context);
    }

    @Override
    public List<Team> loadInBackground() {
        try {
            JSONArray fetchedJson = new JSONArray();

            // Checking if there is Internet connection to decide between downloading json and
            // trying to get data from the database if it exists
            if (SplashScreen.internetConnection) {
                try {
                    Log.d(TAG, "loadInBackground: from Internet");
                    fetchedJson = new JSONArray(fetchingData());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if (BackupSQL.isDbExists()) {
                Log.d(TAG, "loadInBackground: from SQL ");
                try {
                    fetchedJson = BackupSQL.loadFromSQL();
                } catch (SQLiteException e) {
                    Log.d(TAG, "loadInBackground: sorry SQl problem: " + e.getMessage());
                }
            }
            Log.d(TAG, "loadInBackground: Creating Data Model...");
            DataModel fetchedData = new DataModel(fetchedJson);

            if (SplashScreen.internetConnection) {
                BackupSQL.saveToSQL();
            }
            List<Team> result = fetchedData.getAllTeams();
            return result;
        } catch (JSONException e) {
            e.getMessage();
        }

        return null;
    }


    private String fetchingData() {
        Log.d(TAG, "fetchingData... ");
        InputStream inputStream = null;
        try {
            URL url = new URL("http://developers.mub.lu/resources/team.json");
            inputStream = url.openStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            StringWriter stringWriter = new StringWriter();
            char[] buffer = new char[8192];
            int count;

            while ((count = inputStreamReader.read(buffer, 0, buffer.length)) != -1) {
                stringWriter.write(buffer, 0, count);
            }
            return stringWriter.toString();

        } catch (IOException e) {
            Log.d(TAG, "fetchingData: " + e.getMessage());
            return null;

        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception ignored) {
            }
        }
    }

}