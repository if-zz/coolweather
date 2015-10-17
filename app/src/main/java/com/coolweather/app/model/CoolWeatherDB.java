package com.coolweather.app.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.coolweather.app.db.CoolWeatherOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/6.
 */
public class CoolWeatherDB {
/**
 * Database's Name
 */
    public static final String  DB_NAME="cool_weather";
    /**
     * database version
     */
    public static final int VERSION=1;
    private static CoolWeatherDB coolWeatherDB;
    private SQLiteDatabase db;
    /**
     *将构造方法私有化
     */
    private CoolWeatherDB(Context context){
        CoolWeatherOpenHelper dbHelper=new CoolWeatherOpenHelper(context,DB_NAME,null,VERSION);
        db=dbHelper.getWritableDatabase();
    }
    /**
     * 获取CoolWeather的实例
     */
    public synchronized  static CoolWeatherDB getInstance(Context context){
        if(coolWeatherDB ==null){
            coolWeatherDB=new CoolWeatherDB(context);
        }
        return coolWeatherDB;
    }
    /**
     * 将Provience实例存储到数据库
     */
    public void saveProvience(Provience provience){
        if(provience!=null){
            ContentValues values=new ContentValues();
            values.put("provience_name",provience.getProvienceName());
            values.put("provience_code",provience.getProvienceCode());
            db.insert("Provience", null, values);
        }
    }
    /**
     * 从数据库读取全国所有的省份信息
     */
    public List<Provience> loadProviences(){
        List<Provience> list=new ArrayList<Provience>();
        Cursor cursor=db.query("Provience",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                Provience provience=new Provience();
                provience.setId(cursor.getInt(cursor.getColumnIndex("id")));
                provience.setProvienceName(cursor.getString(cursor.getColumnIndex("provience_name")));
                provience.setProvienceCode(cursor.getString(cursor.getColumnIndex("provience_code")));
                list.add(provience);
            }while(cursor.moveToNext());
        }
        if (cursor!=null){
            cursor.close();
        }
        return list;
    }
    /**
     * 将City实例存储到数据库
     */
    public void saveCity(City city){
        if(city!=null){
            ContentValues values=new ContentValues();
            values.put("city_name",city.getCityName());
            values.put("city_code",city.getCityCode());
            values.put("provience_id",city.getProvienceId());
            db.insert("City", null, values);
        }
    }
    /**
     * 从数据库读取某省下所有的城市信息
     */
    public List<City> loadCities(int provienceId){
        List<City> list=new ArrayList<>();
        Cursor cursor=db.query("City",null,"provience_id=?",new String[]{String.valueOf(provienceId)},null,null,null);
        if(cursor.moveToFirst()){
            do{
                City city=new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setProvienceId(provienceId);
                list.add(city);
            }while (cursor.moveToNext());
        }
        if (cursor!=null){
                cursor.close();
            }
            return list;

    }
    /**
     * 将County的实例存储到数据库
     */
    public void saveCounty(County county){
        if (county!=null){
            ContentValues values=new ContentValues();
            values.put("county_name",county.getCountyName());
            values.put("county_code",county.getCountyCode());
            values.put("city_id",county.getCityId());
            db.insert("County", null, values);
        }
    }
    /**
     * 从数库读取某城市下所有县信息
     */
    public List<County> loadCounties(int cityId){
        List<County> list=new ArrayList<>();
        Cursor cursor=db.query("County",null,"city_id=?",new String[]{String.valueOf(cityId)},null,null,null);
        if (cursor.moveToFirst()){
            do {
                County county=new County();
                county.setId(cursor.getInt(cursor.getColumnIndex("id")));
                county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
                county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
                county.setCityId(cityId);
                list.add(county);
            }while(cursor.moveToNext());
        }
        if(cursor!=null){
            cursor.close();
        }
        return list;
    }
}
