package com.gennady.golovin.puzzle15.Puzzles;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

import com.gennady.golovin.puzzle15.Ads;
import com.gennady.golovin.puzzle15.Fifteen;
import com.gennady.golovin.puzzle15.Outmod;
import com.gennady.golovin.puzzle15.Point;
import com.gennady.golovin.puzzle15.R;
import com.gennady.golovin.puzzle15.Verify;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.widget.EditText;
import com.gennady.golovin.puzzle15.Music;
import com.gennady.golovin.puzzle15.Options;
import com.gennady.golovin.puzzle15.Prefs;
import com.gennady.golovin.puzzle15.database.DBManager;
import com.gennady.golovin.puzzle15.database.DatabaseHelper;


public class OctalPuzzle extends Fifteen implements Verify,Outmod {
    String[][] octal = new String[4][4];

    public OctalPuzzle() {
    }

    public OctalPuzzle(int CURRENT_STEP, String statusStep, String statusTime, String bestStatusTime, int bestStatusSteps, int currentLevel, Button b11, Button b12, Button b13, Button b14, Button b21, Button b22, Button b23, Button b24, Button b31, Button b32, Button b33, Button b34, Button b41, Button b42, Button b43, Button b44, Button[][] buttons, int[][] array, Point emptySpace, TextView stepView, Chronometer mChronometer, TextView besttime, TextView beststeps, int passedLevel) {
        super(CURRENT_STEP, statusStep, statusTime, bestStatusTime, bestStatusSteps, currentLevel, b11, b12, b13, b14, b21, b22, b23, b24, b31, b32, b33, b34, b41, b42, b43, b44, buttons, array, emptySpace, stepView, mChronometer, besttime, beststeps, passedLevel);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stepView = (TextView)findViewById(R.id.currentsteps);
        mChronometer = (Chronometer)findViewById(R.id.chronometer);
        besttime = (TextView)findViewById(R.id.Best_chronometer);
        beststeps = (TextView)findViewById(R.id.Best_steps_result);
        currentLevel = 9;
        passedLevel = 10;
        TextView titleLevel = (TextView)findViewById(R.id.statTitle);
        titleLevel.setText(getResources().getText(R.string.level_nine));
        getBestResult(currentLevel);
        initArray();
        generateArray();
        paintTable();
        generationButton(Options.getRotationLevel(this));
        setListenersOnButtons();
        mChronometer.setBase(SystemClock.elapsedRealtime());
        mChronometer.start();
        showMessage(getResources().getString(R.string.name_level_nine));
    }

    @Override
    public void generateArray() {
        octal[0][0] = "1";
        octal[0][1] = "2";
        octal[0][2] = "3";
        octal[0][3] = "4";
        octal[1][0] = "5";
        octal[1][1] = "6";
        octal[1][2] = "7";
        octal[1][3] = "10";
        octal[2][0] = "11";
        octal[2][1] = "12";
        octal[2][2] = "13";
        octal[2][3] = "14";
        octal[3][0] = "15";
        octal[3][1] = "16";
        octal[3][2] = "17";
        emptySpace.x = 3;
        emptySpace.y = 3;
        octal[3][3] = "-1";
    }

    @Override
    public void paintTable() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Button button = buttons[i][j];
                String number = octal[i][j];
                if (!number.equals("-1")) {
                    button.setText(number);
                } else {
                    button.setText(" ");
                    button.setVisibility(View.INVISIBLE);
                }
            }
        }
    }

    @Override
    public boolean checkOverGame() {
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
                if (button == buttons[1][3] && getTitleButton(button).equals("10")) { count++; }
                if (button == buttons[2][0] && getTitleButton(button).equals("11")) { count++; }
                if (button == buttons[2][1] && getTitleButton(button).equals("12")){ count++; }
                if (button == buttons[2][2] && getTitleButton(button).equals("13")){ count++; }
                if (button == buttons[2][3] && getTitleButton(button).equals("14")){ count++; }
                if (button == buttons[3][0] && getTitleButton(button).equals("15")){ count++; }
                if (button == buttons[3][1] && getTitleButton(button).equals("16")){ count++; }
                if (button == buttons[3][2] && getTitleButton(button).equals("17")){ count++; }
            }
        }

        if (count == 15){
            mChronometer.stop();
            statusTime = mChronometer.getText().toString();
            return true;
        }else{return false;}
    }

    @Override
    protected void resultDriver() {
        Music.stop(this);
        Music.onlylPlay(getApplicationContext(), R.raw.win);
        Ads.hideAd(this);
        if (Prefs.getSave(this)) {saveLevel();}else{continueLevel();}
    }




    public void saveLevel() {

        final String svTime, svSteps;
        final String Key_Level = "";
        final String[] svName = new String[1];

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

        final TextInputLayout tilName = (TextInputLayout) container.findViewById(R.id.save_name_gamer);

        final EditText etName = tilName.getEditText();


        tilName.setHint(getResources().getString(R.string.dialog_hint));

        builder.setView(container);

        builder.setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                svName[0] = etName.getText().toString();
                saveToDB(svName[0], svTime, svSteps, currentLevel);
                nextLevel();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                nextLevel();
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
        if (Options.getPassedLevel(getApplicationContext())< passedLevel){
            Options.putPasswdLevel(getApplicationContext(), passedLevel);
        }
    }

    public void continueLevel(){

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.dialog_title);
        LayoutInflater inflater = this.getLayoutInflater();
        View container = inflater.inflate(R.layout.contin_layout, null);

        dialog.setView(container);

        dialog.setPositiveButton(R.string.dialog_continue, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                nextLevel();
                dialog.dismiss();
            }
        }).setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                dialog.cancel();
            }
        });

        dialog.show();
        if (Options.getPassedLevel(getApplicationContext())< passedLevel){
            Options.putPasswdLevel(getApplicationContext(), passedLevel);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Options.putLastLevel(getApplicationContext(), currentLevel);
    }

    @Override
    public void nextLevel() {
        Intent intent_cont = new Intent(getApplicationContext(), HexadecimalPuzzle.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent_cont);
        finish();
    }

    @Override
    public void saveToDB(String svName, String svTime, String svSteps, Integer currentLevel) {
        DBManager dbManager;
        dbManager = new DBManager(getApplicationContext());
        dbManager.open();
        DatabaseHelper dbHelper = new DatabaseHelper(getApplication());
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NAME, svName);
        contentValues.put(DatabaseHelper.TIME, svTime);
        contentValues.put(DatabaseHelper.STEPS, svSteps);
        contentValues.put(DatabaseHelper.LEVEL, currentLevel);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
        dbManager.close();
    }

}
