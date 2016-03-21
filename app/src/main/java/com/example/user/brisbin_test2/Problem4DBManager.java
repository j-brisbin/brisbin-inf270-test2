package com.example.user.brisbin_test2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 12/8/2015.
 */
public class Problem4DBManager extends SQLiteOpenHelper{

    public static final String PROB4_TABLE_NAME = "problem4table";
    public static final String PROB4_ID = "_id";
    public static final String PROB4_USER_INPUT = "user_input";
    public static final String PROB4_DAYOFWEEK = "day_of_week";

    public Problem4DBManager(Context context){
        super(context, "problem4db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE " + PROB4_TABLE_NAME
                + " (" + PROB4_ID + " INTEGER, "
                + PROB4_USER_INPUT + " TEXT, "
                + PROB4_DAYOFWEEK + " INTEGER,"
                + " PRIMARY KEY (" + PROB4_ID + "));";
        db.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PROB4_TABLE_NAME);
        onCreate(db);
    }

    public UserInput addUserInput(UserInput userInput){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        long id = checkUserInputID(userInput.getInputText());
        if(id == -1){
            values.put(PROB4_USER_INPUT, userInput.getInputText());
            values.put(PROB4_DAYOFWEEK, userInput.getDayOfWeek());
            id = db.insert(PROB4_TABLE_NAME, null, values);
            userInput.setId(id);
            db.close();
            return userInput;
        }
        else{
            userInput.setId(id);
            db.close();
            return userInput;
        }
    }

    public UserInput getUserInput(long id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(PROB4_TABLE_NAME,
                new String[]{PROB4_ID, PROB4_USER_INPUT,PROB4_DAYOFWEEK},PROB4_ID + "=?",
                new String[]{String.valueOf(id)},null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
            UserInput userInput = new UserInput(cursor.getString(1),cursor.getInt(2));
            userInput.setId(cursor.getLong(0));
            return userInput;
        }
        return null;
    }

    public long checkUserInputID(String input){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + PROB4_TABLE_NAME +
                " WHERE " + PROB4_USER_INPUT + "='" + input + "';";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            cursor.getLong(0);
        }
        return -1;
    }

    public Cursor getAllInputsByDayOfWeek(int dayOfWeek){
        SQLiteDatabase db = this.getWritableDatabase();
        List<UserInput> userInputList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + PROB4_TABLE_NAME +
                " WHERE " + PROB4_DAYOFWEEK + " = " + dayOfWeek + "";
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;
    }
}
