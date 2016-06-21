package com.example.pabilicki.mubalootest.data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BackupSQL {
    private final BackupSqlHelper opener;

    public BackupSQL(Context context) {
        opener = new BackupSqlHelper(context);
    }

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

    public String getBackupJson() {
        String backupJson = null;
        final SQLiteDatabase db = opener.getReadableDatabase();
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
