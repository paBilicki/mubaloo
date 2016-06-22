package com.example.pabilicki.mubalootest.sql;

/**
 * Model of the table that contains 2 columns:
 * 1. with the auto-incremented id
 * 2. with the downloaded String in json format
 *
 * @author Piotr Aleksander Bilicki
 */
public class BackupTable {
    public static final String TABLE_NAME = "backup";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_JSON = "json";


    public static final String SQL_CREATE_V1 = "create table " + TABLE_NAME + "("
            + COLUMN_ID + " integer primary key, "
            + COLUMN_JSON + " text not null)";
}