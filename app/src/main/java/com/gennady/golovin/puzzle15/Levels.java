package com.gennady.golovin.puzzle15;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
 * Created by Геннадий on 27.02.2016.
 */
public class Levels extends Activity {
   public static int passedLevel;
    private de.hdodenhof.circleimageview.CircleImageView circleImageView1;
    private de.hdodenhof.circleimageview.CircleImageView circleImageView2;
    private de.hdodenhof.circleimageview.CircleImageView circleImageView3;
    private de.hdodenhof.circleimageview.CircleImageView circleImageView4;
    private de.hdodenhof.circleimageview.CircleImageView circleImageView5;
    private de.hdodenhof.circleimageview.CircleImageView circleImageView6;
    private de.hdodenhof.circleimageview.CircleImageView circleImageView7;
    private de.hdodenhof.circleimageview.CircleImageView circleImageView8;
    private de.hdodenhof.circleimageview.CircleImageView circleImageView9;
    private de.hdodenhof.circleimageview.CircleImageView circleImageView10;
    private de.hdodenhof.circleimageview.CircleImageView circleImageView11;
    private de.hdodenhof.circleimageview.CircleImageView circleImageView12;

    public Levels() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levels_activity);
        circleImageView1 = (de.hdodenhof.circleimageview.CircleImageView)findViewById(R.id.level_one);
        circleImageView2 = (de.hdodenhof.circleimageview.CircleImageView)findViewById(R.id.level_two);
        circleImageView3 = (de.hdodenhof.circleimageview.CircleImageView)findViewById(R.id.level_three);
        circleImageView4 = (de.hdodenhof.circleimageview.CircleImageView)findViewById(R.id.level_four);
        circleImageView5 = (de.hdodenhof.circleimageview.CircleImageView)findViewById(R.id.level_five);
        circleImageView6 = (de.hdodenhof.circleimageview.CircleImageView)findViewById(R.id.level_six);
        circleImageView7 = (de.hdodenhof.circleimageview.CircleImageView)findViewById(R.id.level_seven);
        circleImageView8 = (de.hdodenhof.circleimageview.CircleImageView)findViewById(R.id.level_eight);
        circleImageView9 = (de.hdodenhof.circleimageview.CircleImageView)findViewById(R.id.level_nine);
        circleImageView10 = (de.hdodenhof.circleimageview.CircleImageView)findViewById(R.id.level_ten);
        circleImageView11 = (de.hdodenhof.circleimageview.CircleImageView)findViewById(R.id.level_eleven);
        circleImageView12 = (de.hdodenhof.circleimageview.CircleImageView)findViewById(R.id.level_twelve);

        passedLevel = Options.getPassedLevel(getApplicationContext());
        bullerCircle();

    }


    public void startLevel1(View v){

        if (passedLevel >= 1){
        startActivity(new Intent(getApplicationContext(), ClassicPuzzle.class));
        this.finish();}
    }

    public void startLevel2(View v){
        if (passedLevel >= 2){
        startActivity(new Intent(getApplicationContext(), VerticalPuzzle.class));
        this.finish();}
    }

    public void startLevel3(View v){
        if (passedLevel >= 3){
        startActivity(new Intent(getApplicationContext(), RomePuzzle.class));
        this.finish();}
    }

    public void startLevel4(View v){
        if (passedLevel >= 4)
        {startActivity(new Intent(getApplicationContext(), AlphabetPuzzle.class));
        this.finish();}
    }

    public void startLevel5(View v){
        if (passedLevel >= 5){
        startActivity(new Intent(getApplicationContext(),HellenicPuzzle.class));
        this.finish();}
    }

    public void startLevel6(View v){
        if (passedLevel >= 6){
            startActivity(new Intent(getApplicationContext(),BinaryPuzzle.class));
            this.finish();}
    }

    public void startLevel7(View v){
        if (passedLevel >= 7){
            startActivity(new Intent(getApplicationContext(),TernaryPuzzle.class));
            this.finish();}
    }

    public void startLevel8(View v){
        if (passedLevel >= 8){
            startActivity(new Intent(getApplicationContext(),FivefoldPuzzle.class));
            this.finish();}
    }

    public void startLevel9(View v){
        if (passedLevel >= 9){
            startActivity(new Intent(getApplicationContext(),OctalPuzzle.class));
            this.finish();}
    }

    public void startLevel10(View v){
        if (passedLevel >= 10){
            startActivity(new Intent(getApplicationContext(),HexadecimalPuzzle.class));
            this.finish();}
    }

    public void startLevel11(View v){
        if (passedLevel >= 11){
            startActivity(new Intent(getApplicationContext(),MagicQuadrantPuzzle.class));
            this.finish();}
    }

    public void startLevel12(View v){
        if (passedLevel >= 12){
            startActivity(new Intent(getApplicationContext(),WordsPuzzle.class));
            this.finish();}
    }






    public void cancelApp(View v){
        this.finish();
    }

    private void bullerCircle(){
        if (passedLevel == 1){
            circleImageView2.setImageResource(R.drawable.lock);
            circleImageView3.setImageResource(R.drawable.lock);
            circleImageView4.setImageResource(R.drawable.lock);
            circleImageView5.setImageResource(R.drawable.lock);
            circleImageView6.setImageResource(R.drawable.lock);
            circleImageView7.setImageResource(R.drawable.lock);
            circleImageView8.setImageResource(R.drawable.lock);
            circleImageView9.setImageResource(R.drawable.lock);
            circleImageView10.setImageResource(R.drawable.lock);
            circleImageView11.setImageResource(R.drawable.lock);
            circleImageView12.setImageResource(R.drawable.lock);
        }
        if (passedLevel == 2){
            circleImageView3.setImageResource(R.drawable.lock);
            circleImageView4.setImageResource(R.drawable.lock);
            circleImageView5.setImageResource(R.drawable.lock);
            circleImageView6.setImageResource(R.drawable.lock);
            circleImageView7.setImageResource(R.drawable.lock);
            circleImageView8.setImageResource(R.drawable.lock);
            circleImageView9.setImageResource(R.drawable.lock);
            circleImageView10.setImageResource(R.drawable.lock);
            circleImageView11.setImageResource(R.drawable.lock);
            circleImageView12.setImageResource(R.drawable.lock);
        }

        if (passedLevel == 3){
            circleImageView4.setImageResource(R.drawable.lock);
            circleImageView5.setImageResource(R.drawable.lock);
            circleImageView6.setImageResource(R.drawable.lock);
            circleImageView7.setImageResource(R.drawable.lock);
            circleImageView8.setImageResource(R.drawable.lock);
            circleImageView9.setImageResource(R.drawable.lock);
            circleImageView10.setImageResource(R.drawable.lock);
            circleImageView11.setImageResource(R.drawable.lock);
            circleImageView12.setImageResource(R.drawable.lock);
        }

        if (passedLevel == 4){
            circleImageView5.setImageResource(R.drawable.lock);
            circleImageView6.setImageResource(R.drawable.lock);
            circleImageView7.setImageResource(R.drawable.lock);
            circleImageView8.setImageResource(R.drawable.lock);
            circleImageView9.setImageResource(R.drawable.lock);
            circleImageView10.setImageResource(R.drawable.lock);
            circleImageView11.setImageResource(R.drawable.lock);
            circleImageView12.setImageResource(R.drawable.lock);
        }

        if (passedLevel == 5){
            circleImageView6.setImageResource(R.drawable.lock);
            circleImageView7.setImageResource(R.drawable.lock);
            circleImageView8.setImageResource(R.drawable.lock);
            circleImageView9.setImageResource(R.drawable.lock);
            circleImageView10.setImageResource(R.drawable.lock);
            circleImageView11.setImageResource(R.drawable.lock);
            circleImageView12.setImageResource(R.drawable.lock);
        }


        if (passedLevel == 6){
            circleImageView7.setImageResource(R.drawable.lock);
            circleImageView8.setImageResource(R.drawable.lock);
            circleImageView9.setImageResource(R.drawable.lock);
            circleImageView10.setImageResource(R.drawable.lock);
            circleImageView11.setImageResource(R.drawable.lock);
            circleImageView12.setImageResource(R.drawable.lock);
        }

        if (passedLevel == 7){
            circleImageView8.setImageResource(R.drawable.lock);
            circleImageView9.setImageResource(R.drawable.lock);
            circleImageView10.setImageResource(R.drawable.lock);
            circleImageView11.setImageResource(R.drawable.lock);
            circleImageView12.setImageResource(R.drawable.lock);
        }

        if (passedLevel == 8){
            circleImageView9.setImageResource(R.drawable.lock);
            circleImageView10.setImageResource(R.drawable.lock);
            circleImageView11.setImageResource(R.drawable.lock);
            circleImageView12.setImageResource(R.drawable.lock);
        }

        if (passedLevel == 9){
            circleImageView10.setImageResource(R.drawable.lock);
            circleImageView11.setImageResource(R.drawable.lock);
            circleImageView12.setImageResource(R.drawable.lock);
        }

        if (passedLevel == 10){
            circleImageView11.setImageResource(R.drawable.lock);
            circleImageView12.setImageResource(R.drawable.lock);
        }

        if (passedLevel == 11){
           circleImageView12.setImageResource(R.drawable.lock);
        }
    }
}
