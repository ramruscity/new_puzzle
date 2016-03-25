package com.gennady.golovin.puzzle15;

import android.content.Context;
import android.content.SharedPreferences;

public class Options {

    private static final String PUZZLE_OPTIONS = Main.class.getName();
    private static final String OPT_MUSIC = "music";
    private static final boolean OPT_MUSIC_DEF = true;
    private static final String OPT_SAVE = "save";
    private static final boolean OPT_SAVE_DEF = true;
    private static final String OPT_LAST_LEVEL = "lastLevel";
    private static final int OPT_LAST_LEVEL_DEF = 1;
    private static final String OPT_PASSED_LEVEL = "passedLevel";
    private static final int OPT_PASSED_LEVEL_DEF = 1;
    private static final String OPT_ROTATION_LEVEL = "rotationLevel";
    private static final int OPT_ROTATION_LEVEL_DEF = 6000;


    private static SharedPreferences getPuzzlePreferences(
            Context context) {
        return context.getSharedPreferences(PUZZLE_OPTIONS,
                Context.MODE_PRIVATE);
    }

    public static boolean getMusic(Context context) {
        return getPuzzlePreferences(context).getBoolean(
                OPT_MUSIC, OPT_MUSIC_DEF);
    }



    public static boolean putMusic(Context context, boolean value) {
        return getPuzzlePreferences(context)
                .edit()
                .putBoolean(OPT_MUSIC, value)
                .commit();
    }


    public static boolean getSave(Context context) {
        return getPuzzlePreferences(context).getBoolean(
                OPT_SAVE, OPT_SAVE_DEF);
    }



    public static boolean putSave(Context context, boolean value) {
        return getPuzzlePreferences(context)
                .edit()
                .putBoolean(OPT_SAVE, value)
                .commit();
    }

    public static int getLastLevel(Context context) {
        return getPuzzlePreferences(context).getInt(
                OPT_LAST_LEVEL, OPT_LAST_LEVEL_DEF);
    }

    public static boolean putLastLevel(Context context, int value) {
        return getPuzzlePreferences(context)
                .edit()
                .putInt(OPT_LAST_LEVEL, value)
                .commit();
    }

    public static int getPassedLevel(Context context) {
        return getPuzzlePreferences(context).getInt(
                OPT_PASSED_LEVEL, OPT_PASSED_LEVEL_DEF);
    }

    public static boolean putPasswdLevel(Context context, int value) {
        return getPuzzlePreferences(context)
                .edit()
                .putInt(OPT_PASSED_LEVEL, value)
                .commit();
    }

    public static int getRotationLevel(Context context) {
        return getPuzzlePreferences(context).getInt(
                OPT_ROTATION_LEVEL, OPT_ROTATION_LEVEL_DEF);
    }

    public static boolean putRotationLevel(Context context, int value) {
        return getPuzzlePreferences(context)
                .edit()
                .putInt(OPT_ROTATION_LEVEL, value)
                .commit();
    }

}
