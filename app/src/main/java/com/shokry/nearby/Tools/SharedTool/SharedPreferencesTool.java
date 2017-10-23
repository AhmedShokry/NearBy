package com.shokry.nearby.Tools.SharedTool;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class SharedPreferencesTool {

    public static final String TAG_FILE_NAME = "com.shokry.nearby.app";
    public static final String clientID = "2TMPDSRFM4YFBNNBIZJTYUH4E1RADW5J11GHA3CZYKERYPG2";
    public static final String clientSecretID = "BSSECC5IKJAJT411HGZ3COXWFBU3JH4PXJGW5FPLFWAG3TUV";


    // change  PREF_NAME to  your file name
    // add  compile 'com.google.code.gson:gson:2.4' yo your gradle


    /**
     * The Editor to write in shared preference
     *
     * @param context app context
     */
    public static SharedPreferences.Editor getEditor(Context context) {
        SharedPreferences preferences = getSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        return editor;
    }

    /**
     * Reference from shared prefernce to read from it
     *
     * @param context app context
     */
    private static SharedPreferences getSharedPreferences(Context context) {
//        String fileName = context.getPackageName();
        SharedPreferences prfs = context.getSharedPreferences(TAG_FILE_NAME, Context.MODE_PRIVATE);
        return prfs;
    }

    public static void saveObject(Context context, String key, Object myObject) {
        SharedPreferences.Editor editor = getEditor(context);
        Gson gson = new Gson();
        String json = gson.toJson(myObject);
        editor.putString(key, json);
        editor.commit();
    }

    public static <H> H getObject(Context context, String key, Class<H> className) {
        Gson gson = new Gson();
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        String json = sharedPreferences.getString(key, "");
        H myObject = gson.fromJson(json, className);
        return myObject;
    }

    public static void setBoolean(Context context, boolean remmberMe, String key) {
        SharedPreferences.Editor editor = getEditor(context);
        editor.putBoolean(key, remmberMe);
        editor.apply();
    }

    public static boolean getBoolean(Context context, String key) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        boolean remmberMe = sharedPreferences.getBoolean(key, false);
        return remmberMe;
    }

    public static boolean getBooleanlang(Context context, String key) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        boolean aBoolean = sharedPreferences.getBoolean(key, true);
        return aBoolean;
    }

    public static int getInt(Context context, String key) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        int value = sharedPreferences.getInt(key, -1);
        return value;
    }

    public static void setInt(Context context, String key, int value) {
        SharedPreferences.Editor editor = getEditor(context);
        editor.putInt(key, value);
        editor.apply();
    }

    public static long getLong(Context context, String key) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        long value = sharedPreferences.getLong(key, -1);
        return value;
    }

    public static void setLong(Context context, String key, long value) {
        SharedPreferences.Editor editor = getEditor(context);
        editor.putLong(key, value);
        editor.apply();
    }

    public static String getString(Context context, String key) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        String value = sharedPreferences.getString(key, "");
        return value;
    }


    public static void setString(Context context, String key, String value) {
        SharedPreferences.Editor editor = getEditor(context);
        editor.putString(key, value);
        editor.apply();
    }


    public static void clearObject(Context context) {
        SharedPreferences settings = context.getSharedPreferences(TAG_FILE_NAME, Context.MODE_PRIVATE);
        settings.edit().clear().commit();
    }
}
