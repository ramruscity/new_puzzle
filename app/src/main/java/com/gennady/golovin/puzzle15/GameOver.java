package com.gennady.golovin.puzzle15;

import android.app.Activity;
import android.os.Bundle;

import com.gennady.golovin.puzzle15.R;

/**
 * Created by Геннадий on 03.04.2016.
 */
public class GameOver extends Activity{
    public GameOver() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);
    }
}



