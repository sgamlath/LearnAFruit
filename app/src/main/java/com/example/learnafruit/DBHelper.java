package com.example.learnafruit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * DBHelper
 *
 * The helper class for handling database
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "learnafruit.db";

    /**
     * Constructor to create db
     */
    public DBHelper(Context context) {
        super(context.getApplicationContext(), DB_NAME, null, 1);
    }

    /**
     * Method to create scores table and app_var table
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE scores (id INTEGER PRIMARY KEY, name TEXT, score TEXT, timestamp DATETIME DEFAULT CURRENT_TIMESTAMP)");
        db.execSQL("CREATE TABLE app_var (id INTEGER PRIMARY KEY, variable TEXT, value TEXT)");
        db.execSQL("INSERT INTO app_var(variable, value) VALUES('username','John Doe')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    /**
     * Method to insert score records to db
     */
    public void saveScore(String name, String score) {
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("score", score);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("scores", null, cv);
        db.close();
    }

    /**
     * Method to read all score records from db
     */
    public List<ScoreModel> readAllScores() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM scores", null);
        cur.moveToNext();
        List<ScoreModel> data = new ArrayList<>();
        while (!cur.isAfterLast()) {
            Integer id = cur.getInt(cur.getColumnIndex("id"));
            String name = cur.getString(cur.getColumnIndex("name"));
            String score = cur.getString(cur.getColumnIndex("score"));
            String timestamp = cur.getString(cur.getColumnIndex("timestamp"));

            data.add(new ScoreModel(id, name, score, timestamp));
            cur.moveToNext();
        }

        return data;
    }

    /**
     * Method to delete all score records from db
     */
    public void deleteAllScores() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM scores");
    }

    /**
     * Method to change username
     */
    public void changeUsername(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE app_var SET value='" + name + "' WHERE variable='username'");
    }

    /**
     * Method to read username
     */
    public String readUsername() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT value FROM app_var WHERE variable='username'", null);
        cur.moveToNext();
        return cur.getString(0);
    }

}
