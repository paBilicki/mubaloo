package com.example.pabilicki.mubalootest.data;

/**
 * Created by piotr on 21.06.2016.
 */
public class BackupTable {
    public static final String TABLE_NAME = "backup";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_JSON = "json";


    public static final String SQL_CREATE_V1 = "create table " + TABLE_NAME + "("
            + COLUMN_ID + " integer primary key, "
            + COLUMN_JSON + " text not null)";
}