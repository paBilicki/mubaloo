package com.example.pabilicki.mubalootest.DataStructure;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by piotr on 21.06.2016.
 */
public class BackupSQLhelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "MubalooTest";
    public static final int DB_VER = 1;


    public BackupSQLhelper(Context context) {
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
