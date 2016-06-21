package com.example.pabilicki.mubalootest.loader;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.example.pabilicki.mubalootest.data.BackupSQL;
import com.example.pabilicki.mubalootest.data.DataModel;
import com.example.pabilicki.mubalootest.activities.SplashScreen;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.URL;
import java.nio.charset.Charset;

public class TeamListLoader extends AsyncTaskLoader<DataModel> {
    private String TAG = "pbBilu.TeamListLoader";
    private BackupSQL backupSQL;

    public TeamListLoader(Context context) {
        super(context);
    }

    @Override
    public DataModel loadInBackground() {
        try {
            BackupSQL backupSQL = new BackupSQL(getContext());
            JSONArray fetchedJson = new JSONArray();
            String jsonString;

            // Checking if there is Internet connection to decide between downloading json and
            // trying to get data from the database if it exists

            if (SplashScreen.internetConnection) {
                Log.d(TAG, "loadInBackground: from Internet");
                jsonString = fetchingData();
                backupSQL.insertNewJson(jsonString);
            } else {
                Log.d(TAG, "loadInBackground: from SQL ");
                jsonString = backupSQL.getBackupJson();
            }

            fetchedJson = new JSONArray(jsonString);

            Log.d(TAG, "loadInBackground: Creating Data Model...");

            DataModel fetchedData = new DataModel(fetchedJson);

//            List<Team> result = fetchedData.getAllTeams();
            return fetchedData;

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