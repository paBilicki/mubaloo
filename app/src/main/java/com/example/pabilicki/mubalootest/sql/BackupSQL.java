package com.example.pabilicki.mubalootest.sql;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Handling operations on the SQL base
 *
 * @author Piotr Aleksander Bilicki
 */
public class BackupSql {
    private final BackupSqlHelper opener;

    public BackupSql(Context context) {
        opener = new BackupSqlHelper(context);
    }

    /**
     * inserts a json string to the db
     *
     * @param json saved in the database
     */
    public void insertNewJson(String json) {
        final SQLiteDatabase db = opener.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put(BackupTable.COLUMN_JSON, json);
            db.insertOrThrow(BackupTable.TABLE_NAME, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    /**
     * Retrieves json string from the database
     *
     * @return String from the database in json format
     */
    public String getBackupJson() {
        String backupJson = null;
        final SQLiteDatabase db = opener.getReadableDatabase();

        //taking the latest record from the db
        String query = "select * from " + BackupTable.TABLE_NAME + " order by id desc";
        final Cursor cursor = db.rawQuery(query, null);

        try {
            if (cursor.moveToFirst()) {
                backupJson = cursor.getString(cursor.getColumnIndex(BackupTable.COLUMN_JSON));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
            db.close();
        }
        return backupJson;
    }
}
