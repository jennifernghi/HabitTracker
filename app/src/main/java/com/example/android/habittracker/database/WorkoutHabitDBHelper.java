package com.example.android.habittracker.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;

/**
 * Created by jennifernghinguyen on 9/9/17.
 */

public class WorkoutHabitDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "habit.db";
    public Context context = null;
    public WorkoutHabitDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        if(!isDBEXist(DATABASE_NAME, context)) {
            String SQL_CREATE_WORKOUT_HABIT_TABLE = "create table " + WorkoutHabitDBContract.WorkoutHabitEntry.TABLE_NAME + " ("
                    + WorkoutHabitDBContract.WorkoutHabitEntry._ID + " int primary key autoincrement, "
                    + WorkoutHabitDBContract.WorkoutHabitEntry.TITLE_COLUMN + " text, "
                    + WorkoutHabitDBContract.WorkoutHabitEntry.DATE_WORKOUT_COLUMN + " date, "
                    + WorkoutHabitDBContract.WorkoutHabitEntry.REPS_COLUMN + " int, "
                    + WorkoutHabitDBContract.WorkoutHabitEntry.DURATION_COLUMN + " int, );";

            db.execSQL(SQL_CREATE_WORKOUT_HABIT_TABLE);
        }else {
            Log.i("Helper", DATABASE_NAME + "exist");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private boolean isDBEXist(String DBname, Context context){
        File dbFile = context.getDatabasePath(DBname);
        return dbFile.exists();
    }
}
