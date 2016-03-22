package com.gennady.golovin.puzzle15.database;

/**
 * Created by gennady.golovin on 17.02.2016.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "RATING";

    // Table columns
    public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String TIME = "time";
    public static final String STEPS = "steps";
    public static final String LEVEL = "level";

    // Database Information
    static final String DB_NAME = "RATING.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT NOT NULL, " + TIME + " TEXT NOT NULL, " + STEPS + " INTEGER NOT NULL, " + LEVEL + " INTEGER NOT NULL);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}