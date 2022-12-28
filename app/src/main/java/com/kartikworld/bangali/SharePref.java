package com.kartikworld.bangali;


import android.content.Context;
import android.content.SharedPreferences;

public class SharePref {

    private static final String MY_PREFERENCE_NAME = "com.kartik.hare_krishna";
    private static final String image_data = "my_preference_total_key";

    public static void SaveSharePreference(Context context,int total){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFERENCE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(image_data,total);
        editor.apply();
    }
    public static int LoadSharePreference(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFERENCE_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getInt(image_data, 0);
    }
    public static void RemoveSharePreference(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFERENCE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(image_data);
        editor.clear();
        //editor.remove("unique_data");
        editor.apply();
    }
}
