package com.gennady.golovin.puzzle15;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.gennady.golovin.puzzle15.Puzzles.AlphabetPuzzle;
import com.gennady.golovin.puzzle15.Puzzles.BinaryPuzzle;
import com.gennady.golovin.puzzle15.Puzzles.ClassicPuzzle;
import com.gennady.golovin.puzzle15.Puzzles.FivefoldPuzzle;
import com.gennady.golovin.puzzle15.Puzzles.HellenicPuzzle;
import com.gennady.golovin.puzzle15.Puzzles.HexadecimalPuzzle;
import com.gennady.golovin.puzzle15.Puzzles.MagicQuadrantPuzzle;
import com.gennady.golovin.puzzle15.Puzzles.OctalPuzzle;
import com.gennady.golovin.puzzle15.Puzzles.RomePuzzle;
import com.gennady.golovin.puzzle15.Puzzles.TernaryPuzzle;
import com.gennady.golovin.puzzle15.Puzzles.VerticalPuzzle;
import com.gennady.golovin.puzzle15.Puzzles.WordsPuzzle;


/**
 * Created by Геннадий on 20.02.2016.
 */
public class Main extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        final TextView textStartGame = (TextView)findViewById(R.id.start);
        final TextView textRetingGame = (TextView)findViewById(R.id.reting);
        final TextView textAboutGame = (TextView)findViewById(R.id.about);
        final TextView textExitGame = (TextView)findViewById(R.id.exit);
        final TextView textSetGame = (TextView)findViewById(R.id.settings);

        textStartGame.setTypeface(Typeface.createFromAsset(
                getAssets(), "fonts/Tusch_Touch_3_Regular.ttf"));
        textRetingGame.setTypeface(Typeface.createFromAsset(
                getAssets(), "fonts/Tusch_Touch_3_Regular.ttf"));
        textAboutGame.setTypeface(Typeface.createFromAsset(
                getAssets(), "fonts/Tusch_Touch_3_Regular.ttf"));
        textExitGame.setTypeface(Typeface.createFromAsset(
                getAssets(), "fonts/Tusch_Touch_3_Regular.ttf"));
        textSetGame.setTypeface(Typeface.createFromAsset(
                getAssets(), "fonts/Tusch_Touch_3_Regular.ttf"));

        textExitGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Main.this.finish();

            }
        });
    }

    public void startRating (View v){
        Fifteen.currentLevel = 1;
        Intent intent = new Intent(getApplicationContext(), RatingView.class);
        intent.putExtra(RatingView.Key_Level,  Fifteen.currentLevel);
        startActivity(intent);
    }

    public void startPuzzle (View v){
        switch (Options.getLastLevel(getApplicationContext())) {
            case 1:
                Intent pz1_intent = new Intent(getApplicationContext(), ClassicPuzzle.class);
                startActivity(pz1_intent);
                break;
            case 2:
                Intent pz2_intent = new Intent(getApplicationContext(), VerticalPuzzle.class);
                startActivity(pz2_intent);
                break;
            case 3:
                Intent pz3_intent = new Intent(getApplicationContext(), RomePuzzle.class);
                startActivity(pz3_intent);
                break;
            case 4:
                Intent pz4_intent = new Intent(getApplicationContext(), AlphabetPuzzle.class);
                startActivity(pz4_intent);
                break;
            case 5:
                Intent pz5_intent = new Intent(getApplicationContext(), HellenicPuzzle.class);
                startActivity(pz5_intent);
                break;
            case 6:
                Intent pz6_intent = new Intent(getApplicationContext(), BinaryPuzzle.class);
                startActivity(pz6_intent);
                break;
            case 7:
                Intent pz7_intent = new Intent(getApplicationContext(), TernaryPuzzle.class);
                startActivity(pz7_intent);
                break;
            case 8:
                Intent pz8_intent = new Intent(getApplicationContext(), FivefoldPuzzle.class);
                startActivity(pz8_intent);
                break;
            case 9:
                Intent pz9_intent = new Intent(getApplicationContext(), OctalPuzzle.class);
                startActivity(pz9_intent);
                break;
            case 10:
                Intent pz10_intent = new Intent(getApplicationContext(), HexadecimalPuzzle.class);
                startActivity(pz10_intent);
                break;
            case 11:
                Intent pz11_intent = new Intent(getApplicationContext(), MagicQuadrantPuzzle.class);
                startActivity(pz11_intent);
                break;
            case 12:
                Intent pz12_intent = new Intent(getApplicationContext(), WordsPuzzle.class);
                startActivity(pz12_intent);
                break;
        }

    }

    public void startAbout (View v){
        Intent ab_intent = new Intent(getApplicationContext(), About.class);
        startActivity(ab_intent);
    }

    public void startSettings(View v){

        startActivity(new Intent(getApplicationContext(), Prefs.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        Music.play(this, R.raw.game);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Music.stop(this);
    }

}

