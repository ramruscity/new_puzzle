package com.gennady.golovin.puzzle15.database;

/**
 * Created by gennady.golovin on 17.02.2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String name, String time, int steps, int level) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.NAME, name);
        contentValue.put(DatabaseHelper.TIME, time);
        contentValue.put(DatabaseHelper.STEPS, steps);
        contentValue.put(DatabaseHelper.LEVEL, level);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] {DatabaseHelper._ID, DatabaseHelper.NAME, DatabaseHelper.TIME,DatabaseHelper.STEPS,DatabaseHelper.LEVEL};
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor getLevel(int level){
        String lev = Integer.toString(level);
        String query = "SELECT " + DatabaseHelper._ID + ", " + DatabaseHelper.NAME + ", " + DatabaseHelper.TIME + ", " + DatabaseHelper.STEPS + "," + DatabaseHelper.LEVEL
                + " FROM " + DatabaseHelper.TABLE_NAME + " WHERE " + DatabaseHelper.LEVEL + " = " + lev + " ORDER BY " + DatabaseHelper.STEPS + " ASC";
        Cursor cursor = database.rawQuery(query, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String name, String time, int steps, int level) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NAME, name);
        contentValues.put(DatabaseHelper.TIME, time);
        contentValues.put(DatabaseHelper.STEPS, steps);
        contentValues.put(DatabaseHelper.LEVEL, level);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }

    public void clear(){
        database.execSQL("DROP TABLE IF EXISTS " + DatabaseHelper.TABLE_NAME);
        dbHelper.onCreate(database);
    }

    public long fetchPlacesCount() {
        String sql = "SELECT COUNT(*) FROM " + DatabaseHelper.TABLE_NAME;
        SQLiteStatement statement = database.compileStatement(sql);
        long count = statement.simpleQueryForLong();
        return count;
    }

}
