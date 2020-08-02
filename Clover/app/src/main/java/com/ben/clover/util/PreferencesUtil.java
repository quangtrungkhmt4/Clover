package com.ben.clover.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesUtil {
    private static final String KEY_APP = "CLOVER";
    public static final String LOGIN_RESPONSE_KEY = "LOGIN_RESPONSE_KEY";
    public static final String CITIES_KEY = "CITIES_KEY";
    public static final String CATEGORY_KEY = "CATEGORY_KEY";

    private static final String EMPTY_VALUE = "";


    public static void saveData(String key, String value, Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_APP, Context.MODE_PRIVATE);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getData(String key, Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_APP, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, "");
    }

    public static void removeData(String key, Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_APP, Context.MODE_PRIVATE);
        sharedPreferences.edit().remove(key).apply();
    }
}
