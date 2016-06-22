package com.example.pabilicki.mubalootest.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * SQLiteOpenHelper class to create a database based on the BackupTable class
 *
 * @author Piotr Aleksander Bilicki
 */
public class BackupSqlHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "MubalooTest";
    public static final int DB_VER = 1;


    public BackupSqlHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BackupTable.SQL_CREATE_V1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
