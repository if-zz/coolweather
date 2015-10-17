package com.coolweather.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2015/10/6.
 */
public class CoolWeatherOpenHelper extends SQLiteOpenHelper{
    /**
     * Create Table Provience
     */
    public static final String CREATE_PROVIENCE="create table Provience (" +
            "id integer primary key autoincrement," +
            "provience_name text," +
            "provience_code text)";
    /**
     * Create Table City
     */
    public static final String CREATE_CITY="create table City(" +
            "id integer primary key autoincrement," +
            "city_name text," +
            "city_code text," +
            "provience_id integer)";
    /**
     * Create Table County
     */
    public static final String CREATE_COUNTY="create table County(" +
            "id integer primary key autoincrement," +
            "county_name text," +
            "county_code text," +
            "city_id integer)";
    public CoolWeatherOpenHelper(Context context,String name,SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PROVIENCE);
        db.execSQL(CREATE_CITY);
        db.execSQL(CREATE_COUNTY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

