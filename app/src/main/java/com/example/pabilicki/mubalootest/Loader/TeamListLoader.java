package com.example.pabilicki.mubalootest.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.example.pabilicki.mubalootest.activities.SplashScreen;
import com.example.pabilicki.mubalootest.data.BackupSQL;
import com.example.pabilicki.mubalootest.data.DataModel;

import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.URL;
import java.nio.charset.Charset;


/**
 * Loader that downloads the data if the Internet connection is available and invoke the function
 * to save downloaded json to the database. In case of not having Internet connection it takes
 * json saved during the last online session.
 *
 * @author Piotr Aleksander Bilicki
 */
public class TeamListLoader extends AsyncTaskLoader<DataModel> {
    private BackupSQL backupSQL;

    public TeamListLoader(Context context) {
        super(context);
    }

    @Override
    public DataModel loadInBackground() {
        try {
            backupSQL = new BackupSQL(getContext());
            JSONArray downloadedJson;
            String jsonString;

            // Checking if there is Internet connection to download the json file
            if (SplashScreen.internetConnection) {
                jsonString = downloadData();
                backupSQL.insertNewJson(jsonString);
            } else {
                // retrieving json from the last saved record
                jsonString = backupSQL.getBackupJson();
            }

            downloadedJson = new JSONArray(jsonString);
            DataModel dataModel = new DataModel(downloadedJson);

            return dataModel;

        } catch (Exception e) {
        }

        return null;
    }

    /**
     * Downloads json from the Internet
     *
     * @return json string downloaded from the internet
     */
    private String downloadData() {
        InputStream inputStream = null;
        try {
            URL url = new URL(DataModel.JSON_URL);
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