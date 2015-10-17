package com.coolweather.app.util;

import android.text.TextUtils;

import com.coolweather.app.model.City;
import com.coolweather.app.model.CoolWeatherDB;
import com.coolweather.app.model.County;
import com.coolweather.app.model.Provience;

/**
 * Created by Administrator on 2015/10/14.
 */
public class Utility {
    /**
     * 解析和处理服务器返回的省级数据
     */
    public synchronized static boolean handleProviencesResponse(CoolWeatherDB
                                                                coolWeatherDB,String response){
        if(!TextUtils.isEmpty(response)){
            String[] allProviences=response.split(",");
            if(allProviences!=null&&allProviences.length>0){
                for (String p:allProviences) {
                    String[] array=p.split("\\|");
                    Provience provience=new Provience();
                    provience.setProvienceCode(array[0]);
                    provience.setProvienceName(array[1]);
                    //将解析出来的数据存储到Provience表
                    coolWeatherDB.saveProvience(provience);
                }
                return true;
            }
        }
        return false;
    }
    /**
     * 解析和处理服务器返回的市级数据
     */
    public static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB,String response,int provienceId){
        if (!TextUtils.isEmpty(response)){
            String[] allCities=response.split(",");
            if (allCities!=null&&allCities.length>0){
                for (String c:allCities){
                    String[] array=c.split("\\|");
                    City city=new City();
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setProvienceId(provienceId);
                    //将解析出的数据存储到City表
                    coolWeatherDB.saveCity(city);
                }
                return true;
            }
        }
        return false;
    }
    /**
     * 解析和处理服务器返回的县级数据
     */
    public static boolean handleCountiesResponse(CoolWeatherDB coolWeatherDB,String response,int cityId){
        if (!TextUtils.isEmpty(response)){
            String[] allCounties=response.split(",");
            if (allCounties!=null&&allCounties.length>0){
                for (String c:allCounties){
                    String[] array=c.split("\\|");
                    County county=new County();
                    county.setCountyCode(array[0]);
                    county.setCountyName(array[1]);
                    county.setCityId(cityId);
                    //将解析出的数据存储到City表
                    coolWeatherDB.saveCounty(county);
                }
                return true;
            }
        }
        return false;
    }
}
