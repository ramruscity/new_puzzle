package com.gennady.golovin.puzzle15;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

public class Prefs extends AppCompatActivity {
    // Option names and default values
    private static final String OPT_MUSIC = "music";
    private static final boolean OPT_MUSIC_DEF = true;
    private static final String OPT_SAVE = "save";
    private static final boolean OPT_SAVE_DEF = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();
    }


    public static class MyPreferenceFragment extends PreferenceFragment
    {
        @Override
        public void onCreate(final Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings);
        }
    }

    /** Get the current value of the music option */
    public static boolean getMusic(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(OPT_MUSIC, OPT_MUSIC_DEF);
    }

    public static boolean getSave(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(OPT_SAVE, OPT_SAVE_DEF);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(this);
        String regular = prefs.getString(getString(R.string.depth_rotation),"");
        if (regular.contains(getString(R.string.low_depth))){
            Options.putRotationLevel(this,400);
        }

        if (regular.contains(getString(R.string.average_depth))){
            Options.putRotationLevel(this,6000);
        }

        if (regular.contains(getString(R.string.high_depth))){
            Options.putRotationLevel(this,24000);
        }

    }
}