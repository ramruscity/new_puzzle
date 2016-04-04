package com.gennady.golovin.puzzle15;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.gennady.golovin.puzzle15.database.DBManager;
import com.gennady.golovin.puzzle15.database.DatabaseHelper;

public class RatingView extends AppCompatActivity{

    private DBManager dbManager;

    private ListView listView;

    private SimpleCursorAdapter adapter;

    private Menu menu;

    public static final String Key_Level = "keyLevel";
    public static final int Level = 0;






    final String[] from = new String[] { DatabaseHelper._ID,
            DatabaseHelper.NAME, DatabaseHelper.TIME,DatabaseHelper.STEPS,DatabaseHelper.LEVEL};

    final int[] to = new int[] { R.id._id, R.id.nameGamers, R.id.time_gamer, R.id.currentsteps, R.id.curent_level };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_emp_list);
        dbManager = new DBManager(this);
        dbManager.open();

        if (dbManager.fetchPlacesCount() > 0) {

            int currentLevel = (getIntent().getIntExtra(Key_Level, Level));

            dbManager = new DBManager(this);
            dbManager.open();
            Cursor cursor = dbManager.getLevel(currentLevel);
            String showLevel = Integer.toString(currentLevel);
            showMessage(getResources().getText(R.string.rating_levels) + " " + showLevel);

            listView = (ListView) findViewById(R.id.list_view);
            listView.setEmptyView(findViewById(R.id.empty_logo));

            adapter = new SimpleCursorAdapter(this, R.layout.activity_view_row, cursor, from, to, 0);
            adapter.notifyDataSetChanged();

            listView.setAdapter(adapter);
            LayoutAnimationController controller = AnimationUtils
                    .loadLayoutAnimation(this, R.anim.list_layout_controller);
            listView.setLayoutAnimation(controller);

            // OnCLickListiner For List Items
            listView.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                    TextView idTextView = (TextView) view.findViewById(R.id._id);

                    final String id = idTextView.getText().toString();
                    final long number = Long.parseLong(id);

                    final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(RatingView.this);
                    dialogBuilder.setMessage(R.string.dialog_removing_record);

                    dialogBuilder.setPositiveButton(R.string.dialog_delete, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dbManager.delete(number);
                            Intent intent_del = new Intent(getApplicationContext(), RatingView.class)
                                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent_del.putExtra(Key_Level, Fifteen.currentLevel);
                            startActivity(intent_del);
                            dialog.dismiss();
                        }
                    }).setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    dialogBuilder.show();

                }

            });
        }else{

            int currentLevel = (getIntent().getIntExtra(Key_Level, Level));
            Cursor cursor = dbManager.getLevel(currentLevel);
            listView = (ListView) findViewById(R.id.list_view);
            listView.setEmptyView(findViewById(R.id.empty_logo));

            adapter = new SimpleCursorAdapter(this, R.layout.activity_view_row, cursor, from, to, 0);
            adapter.notifyDataSetChanged();

            listView.setAdapter(adapter);
            LayoutAnimationController controller = AnimationUtils
                    .loadLayoutAnimation(this, R.anim.list_layout_controller);
            listView.setLayoutAnimation(controller);
            showMessage(getResources().getString(R.string.no_data));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_rating_view, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.button_back:
                downLevel();
                break;

            case R.id.button_forward:
                upLevel();
                break;

            case R.id.action_clear:
               dbManager.clear();
               Intent home_intent = new Intent(getApplicationContext(), RatingView.class)
                       .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
               startActivity(home_intent);
               break;

       }
        return false;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        this.menu=menu;
        updateMenuItems(menu);
        return super.onPrepareOptionsMenu(menu);
    }

    private void updateMenuItems(Menu menu){

        if (dbManager.fetchPlacesCount() > 0) {
            menu.findItem(R.id.action_clear).setVisible(true);
            menu.findItem(R.id.button_back).setVisible(true);
            menu.findItem(R.id.button_forward).setVisible(true);

        } else {
            menu.findItem(R.id.action_clear).setVisible(false);
            menu.findItem(R.id.button_back).setVisible(false);
            menu.findItem(R.id.button_forward).setVisible(false);

        }
        invalidateOptionsMenu();
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void upLevel(){
            switch (Fifteen.currentLevel){
                case 1:
                    Fifteen.currentLevel =2;
                    Intent intent_one = new Intent(getApplicationContext(), RatingView.class)
                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent_one.putExtra(Key_Level, Fifteen.currentLevel);
                    startActivity(intent_one);
                    break;
                case 2:
                    Fifteen.currentLevel =3;
                    Intent intent_two = new Intent(getApplicationContext(), RatingView.class)
                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent_two.putExtra(Key_Level, Fifteen.currentLevel);
                    startActivity(intent_two);
                    break;
                case 3:
                    Fifteen.currentLevel =4;
                    Intent intent_three = new Intent(getApplicationContext(), RatingView.class)
                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent_three.putExtra(Key_Level, Fifteen.currentLevel);
                    startActivity(intent_three);
                    break;
                case 4:
                    Fifteen.currentLevel =5;
                    Intent intent_four = new Intent(getApplicationContext(), RatingView.class)
                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent_four.putExtra(Key_Level, Fifteen.currentLevel);
                    startActivity(intent_four);
                    break;
                case 5:
                    Fifteen.currentLevel =6;
                    Intent intent_five = new Intent(getApplicationContext(), RatingView.class)
                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent_five.putExtra(Key_Level, Fifteen.currentLevel);
                    startActivity(intent_five);
                    break;
                case 6:
                    Fifteen.currentLevel =7;
                    Intent intent_six = new Intent(getApplicationContext(), RatingView.class)
                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent_six.putExtra(Key_Level, Fifteen.currentLevel);
                    startActivity(intent_six);
                    break;
                case 7:
                    Fifteen.currentLevel =8;
                    Intent intent_seven = new Intent(getApplicationContext(), RatingView.class)
                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent_seven.putExtra(Key_Level, Fifteen.currentLevel);
                    startActivity(intent_seven);
                    break;
                case 8:
                    Fifteen.currentLevel =9;
                    Intent intent_eight = new Intent(getApplicationContext(), RatingView.class)
                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent_eight.putExtra(Key_Level, Fifteen.currentLevel);
                    startActivity(intent_eight);
                    break;
                case 9:
                    Fifteen.currentLevel =10;
                    Intent intent_nine = new Intent(getApplicationContext(), RatingView.class)
                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent_nine.putExtra(Key_Level, Fifteen.currentLevel);
                    startActivity(intent_nine);
                    break;
                case 10:
                    Fifteen.currentLevel =11;
                    Intent intent_ten = new Intent(getApplicationContext(), RatingView.class)
                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent_ten.putExtra(Key_Level, Fifteen.currentLevel);
                    startActivity(intent_ten);
                    break;
                case 11:
                    Fifteen.currentLevel =12;
                    Intent intent_eleven = new Intent(getApplicationContext(), RatingView.class)
                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent_eleven.putExtra(Key_Level, Fifteen.currentLevel);
                    startActivity(intent_eleven);
                    break;

            }
    }

    private void downLevel(){
        switch (Fifteen.currentLevel){
            case 2:
                Fifteen.currentLevel =1;
                Intent intent_two = new Intent(getApplicationContext(), RatingView.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent_two.putExtra(Key_Level, Fifteen.currentLevel);
                startActivity(intent_two);
                break;
            case 3:
                Fifteen.currentLevel =2;
                Intent intent_three = new Intent(getApplicationContext(), RatingView.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent_three.putExtra(Key_Level, Fifteen.currentLevel);
                startActivity(intent_three);
                break;
            case 4:
                Fifteen.currentLevel =3;
                Intent intent_four = new Intent(getApplicationContext(), RatingView.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent_four.putExtra(Key_Level, Fifteen.currentLevel);
                startActivity(intent_four);
                break;
            case 5:
                Fifteen.currentLevel =4;
                Intent intent_five = new Intent(getApplicationContext(), RatingView.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent_five.putExtra(Key_Level, Fifteen.currentLevel);
                startActivity(intent_five);
                break;
            case 6:
                Fifteen.currentLevel =5;
                Intent intent_six = new Intent(getApplicationContext(), RatingView.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent_six.putExtra(Key_Level, Fifteen.currentLevel);
                startActivity(intent_six);
                break;
            case 7:
                Fifteen.currentLevel =6;
                Intent intent_seven = new Intent(getApplicationContext(), RatingView.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent_seven.putExtra(Key_Level, Fifteen.currentLevel);
                startActivity(intent_seven);
                break;
            case 8:
                Fifteen.currentLevel =7;
                Intent intent_eight = new Intent(getApplicationContext(), RatingView.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent_eight.putExtra(Key_Level, Fifteen.currentLevel);
                startActivity(intent_eight);
                break;
            case 9:
                Fifteen.currentLevel =8;
                Intent intent_nine = new Intent(getApplicationContext(), RatingView.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent_nine.putExtra(Key_Level, Fifteen.currentLevel);
                startActivity(intent_nine);
                break;
            case 10:
                Fifteen.currentLevel =9;
                Intent intent_ten = new Intent(getApplicationContext(), RatingView.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent_ten.putExtra(Key_Level, Fifteen.currentLevel);
                startActivity(intent_ten);
                break;
            case 11:
                Fifteen.currentLevel =10;
                Intent intent_eleven = new Intent(getApplicationContext(), RatingView.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent_eleven.putExtra(Key_Level, Fifteen.currentLevel);
                startActivity(intent_eleven);
                break;
            case 12:
                Fifteen.currentLevel =11;
                Intent intent_twelve = new Intent(getApplicationContext(), RatingView.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent_twelve.putExtra(Key_Level, Fifteen.currentLevel);
                startActivity(intent_twelve);
                break;
        }
    }





}
