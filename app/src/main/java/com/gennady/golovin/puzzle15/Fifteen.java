package com.gennady.golovin.puzzle15;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.gennady.golovin.puzzle15.database.DBManager;
import com.gennady.golovin.puzzle15.database.DatabaseHelper;
import com.google.android.gms.ads.AdView;

/**
 * Created by Геннадий on 26.02.2016.
 */
public class Fifteen extends AppCompatActivity {
    protected int CURRENT_STEP=0;
    protected String statusStep;
    protected String statusTime;
    protected String bestStatusTime;
    protected int bestStatusSteps;
    public static  int currentLevel = 0;
    protected Button b11;
    protected Button b12;
    protected Button b13;
    protected Button b14;

    protected Button b21;
    protected Button b22;
    protected Button b23;
    protected Button b24;

    protected Button b31;
    protected Button b32;
    protected Button b33;
    protected Button b34;

    protected Button b41;
    protected Button b42;
    protected Button b43;
    protected Button b44;

    protected Button[][] buttons = new Button[4][4];
    protected int[][] array = new int[4][4];
    protected Point emptySpace = new Point();

    protected TextView stepView;
    protected Chronometer mChronometer;
    protected TextView besttime;
    protected TextView beststeps;
    protected int passedLevel;

    public Fifteen() {
    }

    public Fifteen(int CURRENT_STEP, String statusStep, String statusTime,String bestStatusTime, int bestStatusSteps,int currentLevel, Button b11, Button b12, Button b13, Button b14,
                   Button b21, Button b22, Button b23, Button b24, Button b31, Button b32, Button b33, Button b34, Button b41,
                   Button b42, Button b43, Button b44, Button[][] buttons, int[][] array, Point emptySpace,
                   TextView stepView, Chronometer mChronometer,TextView besttime,TextView beststeps,int passedLevel) {

        this.CURRENT_STEP = CURRENT_STEP;
        this.statusStep = statusStep;
        this.statusTime = statusTime;
        this.bestStatusSteps = bestStatusSteps;
        this.bestStatusTime = bestStatusTime;
        this.b11 = b11;
        this.b12 = b12;
        this.b13 = b13;
        this.b14 = b14;
        this.b21 = b21;
        this.b22 = b22;
        this.b23 = b23;
        this.b24 = b24;
        this.b31 = b31;
        this.b32 = b32;
        this.b33 = b33;
        this.b34 = b34;
        this.b41 = b41;
        this.b42 = b42;
        this.b43 = b43;
        this.b44 = b44;
        this.buttons = buttons;
        this.array = array;
        this.emptySpace = emptySpace;
        this.stepView = stepView;
        this.mChronometer = mChronometer;
        this.besttime = besttime;
        this.beststeps = beststeps;
        this.passedLevel = passedLevel;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fifteen);
        Ads.showBanner(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_game:
                Intent level_intent = new Intent(getApplicationContext(), Levels.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(level_intent);
                this.finish();
                break;
            case R.id.menu:
                this.finish();
                break;
            case R.id.button_refresh:
                generationButton(Options.getRotationLevel(this));
                setListenersOnButtons();
                CURRENT_STEP = 0;
                statusStep = Integer.toString(CURRENT_STEP);
                stepView.setText(statusStep);
                mChronometer.setBase(SystemClock.elapsedRealtime());
                mChronometer.start();
                break;
        }
        return false;
    }

    protected void setListenersOnButtons() {
        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View myView) {
                Button clickedButton = (Button) myView;
                Point clickedPoint = getClickedPoint(clickedButton);
                if (clickedPoint != null && canMove(clickedPoint)) {
                    clickedButton.setVisibility(View.INVISIBLE);
                    String numberStr = clickedButton.getText().toString();
                    clickedButton.setText(" ");

                    Button button = buttons[emptySpace.x][emptySpace.y];
                    button.setVisibility(View.VISIBLE);
                    button.setText(numberStr);

                    emptySpace.x = clickedPoint.x;
                    emptySpace.y = clickedPoint.y;

                    CURRENT_STEP++;
                    statusStep = Integer.toString(CURRENT_STEP);
                    stepView.setText(statusStep);
                    if (checkOverGame()){

                        resultDriver();
                    }


                }
            }
        };

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Button button = buttons[i][j];
                button.setOnClickListener(listener);
            }
        }
    }

    protected Point getClickedPoint(Button clickedButton) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (clickedButton == buttons[i][j]) {
                    Point point = new Point();
                    point.x = i;
                    point.y = j;
                    return point;
                }
            }
        }
        return null;
    }

    protected boolean canMove(Point clicked) {
        if (clicked.equals(emptySpace)) {
            return false;
        }

        if (clicked.x == emptySpace.x) {
            int diff = Math.abs(clicked.y - emptySpace.y);
            if (diff == 1) {
                return true;
            }
        } else if (clicked.y == emptySpace.y) {
            int diff = Math.abs(clicked.x - emptySpace.x);
            if (diff == 1) {
                return true;
            }
        }

        return false;
    }

    protected String getTitleButton (Button checkButton){
        String title = checkButton.getText().toString().trim();
        return title;
    }

    protected void initArray() {
        b11 = (Button) findViewById(R.id.button11);
        buttons[0][0] = b11;
        b12 = (Button) findViewById(R.id.button12);
        buttons[0][1] = b12;
        b13 = (Button) findViewById(R.id.button13);
        buttons[0][2] = b13;
        b14 = (Button) findViewById(R.id.button14);
        buttons[0][3] = b14;

        b21 = (Button) findViewById(R.id.button21);
        buttons[1][0] = b21;
        b22 = (Button) findViewById(R.id.button22);
        buttons[1][1] = b22;
        b23 = (Button) findViewById(R.id.button23);
        buttons[1][2] = b23;
        b24 = (Button) findViewById(R.id.button24);
        buttons[1][3] = b24;

        b31 = (Button) findViewById(R.id.button31);
        buttons[2][0] = b31;
        b32 = (Button) findViewById(R.id.button32);
        buttons[2][1] = b32;
        b33 = (Button) findViewById(R.id.button33);
        buttons[2][2] = b33;
        b34 = (Button) findViewById(R.id.button34);
        buttons[2][3] = b34;

        b41 = (Button) findViewById(R.id.button41);
        buttons[3][0] = b41;
        b42 = (Button) findViewById(R.id.button42);
        buttons[3][1] = b42;
        b43 = (Button) findViewById(R.id.button43);
        buttons[3][2] = b43;
        b44 = (Button) findViewById(R.id.button44);
        buttons[3][3] = b44;

    }

    public boolean checkOverGame(){
        int count=0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Button button = buttons[i][j];
                if (button == buttons[0][0] && getTitleButton(button).equals("1")) { count++; }
                if (button == buttons[0][1] && getTitleButton(button).equals("2")) { count++; }
                if (button == buttons[0][2] && getTitleButton(button).equals("3")) { count++; }
                if (button == buttons[0][3] && getTitleButton(button).equals("4")) { count++; }
                if (button == buttons[1][0] && getTitleButton(button).equals("5")) { count++; }
                if (button == buttons[1][1] && getTitleButton(button).equals("6")) { count++; }
                if (button == buttons[1][2] && getTitleButton(button).equals("7")) { count++; }
                if (button == buttons[1][3] && getTitleButton(button).equals("8")) { count++; }
                if (button == buttons[2][0] && getTitleButton(button).equals("9")) { count++; }
                if (button == buttons[2][1] && getTitleButton(button).equals("10")){ count++; }
                if (button == buttons[2][2] && getTitleButton(button).equals("11")){ count++; }
                if (button == buttons[2][3] && getTitleButton(button).equals("12")){ count++; }
                if (button == buttons[3][0] && getTitleButton(button).equals("13")){ count++; }
                if (button == buttons[3][1] && getTitleButton(button).equals("14")){ count++; }
                if (button == buttons[3][2] && getTitleButton(button).equals("15")){ count++; }
            }
        }

        if (count == 15){
            mChronometer.stop();
            statusTime = mChronometer.getText().toString();
            return true;
        }else{return false;}
    }


    protected void rotation (Button button) {

        Button clickedButton = button;
        Point clickedPoint = getClickedPoint(clickedButton);
        if (clickedPoint != null && canMove(clickedPoint)) {
            clickedButton.setVisibility(View.INVISIBLE);
            String numberStr = clickedButton.getText().toString();
            clickedButton.setText(" ");

            Button buttonEmpy = buttons[emptySpace.x][emptySpace.y];
            buttonEmpy.setVisibility(View.VISIBLE);
            buttonEmpy.setText(numberStr);

            emptySpace.x = clickedPoint.x;
            emptySpace.y = clickedPoint.y;
        }
    }


    protected void generationButton (int iterations){
        int x,y;
        Button button;

        for (int i = 0; i < iterations; i++) {
            x = (int)(Math.random()*4);
            y = (int)(Math.random()*4);
            button = buttons[x][y];
            rotation(button);
        }
    }




    protected void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onResume() {
        super.onResume();
        statusStep = Integer.toString(CURRENT_STEP);
        stepView.setText(statusStep);
        Music.play(this, R.raw.game);
    }


    @Override
    protected void onPause() {
        super.onPause();
        Music.stop(this);
        checkOverGame();
    }


    protected void resultDriver(){

        final String svTime,svSteps,svName;
        DBManager dbManager;
        dbManager = new DBManager(getApplicationContext());
        dbManager.open();

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(R.string.dialog_title);

        LayoutInflater inflater = this.getLayoutInflater();

        View container = inflater.inflate(R.layout.save_result, null);

        final TextView saveTime = (TextView) container.findViewById(R.id.result_Time);
        final TextView saveSteps = (TextView) container.findViewById(R.id.result_Steps);

        saveTime.setText(statusTime);
        saveSteps.setText(statusStep);

        svTime = saveTime.getText().toString();
        svSteps = saveSteps.getText().toString();
        Music.stop(this);
        Music.onlylPlay(getApplicationContext(),R.raw.win);

        final TextInputLayout tilName = (TextInputLayout) container.findViewById(R.id.save_name_gamer);

        final EditText etName = tilName.getEditText();


        tilName.setHint(getResources().getString(R.string.dialog_hint));

        builder.setView(container);

        builder.setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                DatabaseHelper dbHelper = new DatabaseHelper(getApplication());

                SQLiteDatabase database = dbHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(DatabaseHelper.NAME,etName.getText().toString());
                contentValues.put(DatabaseHelper.TIME, svTime);
                contentValues.put(DatabaseHelper.STEPS, svSteps);
                database.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
                startActivity(new Intent(getApplicationContext(), RatingView.class));
                finish();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(getApplicationContext(), Main.class));
                finish();
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                final Button positiveButton = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_POSITIVE);
                if (etName.length() == 0) {
                    positiveButton.setEnabled(false);
                    tilName.setError(getResources().getString(R.string.dialog_error_empy_title));
                }
                etName.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        if (s.length() == 0) {
                            positiveButton.setEnabled(false);
                            tilName.setError(getResources().getString(R.string.dialog_error_empy_title));
                        } else {
                            positiveButton.setEnabled(true);
                            tilName.setErrorEnabled(false);
                        }
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
        });

        alertDialog.show();
    }


    protected void getBestResult(int level) {
        DBManager dbManager;
        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursorLevel = dbManager.getLevel(level);
        if (cursorLevel.moveToFirst()) {

            bestStatusSteps = cursorLevel.getInt(cursorLevel.getColumnIndex(DatabaseHelper.STEPS));
            bestStatusTime = cursorLevel.getString(cursorLevel.getColumnIndex(DatabaseHelper.TIME));
            cursorLevel.close();
            besttime.setText(bestStatusTime);
            beststeps.setText(Integer.toString(bestStatusSteps));
        }else {
            besttime.setText("59:59");
            beststeps.setText("999");
        }

    }


}
