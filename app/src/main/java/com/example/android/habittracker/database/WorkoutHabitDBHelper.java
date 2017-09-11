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

     static final int DATABASE_VERSION = 2;
     static final String DATABASE_NAME = "habit.db";
     Context context = null;
    private static final String SQL_CREATE_WORKOUT_HABIT_TABLE = "CREATE TABLE " + WorkoutHabitDBContract.WorkoutHabitEntry.TABLE_NAME + " ("
            + WorkoutHabitDBContract.WorkoutHabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + WorkoutHabitDBContract.WorkoutHabitEntry.TITLE_COLUMN + " TEXT, "
            + WorkoutHabitDBContract.WorkoutHabitEntry.DATE_WORKOUT_COLUMN + " DATE, "
            + WorkoutHabitDBContract.WorkoutHabitEntry.REPS_COLUMN + " INTEGER, "
            + WorkoutHabitDBContract.WorkoutHabitEntry.DURATION_COLUMN + " INTEGER);";

    public WorkoutHabitDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.i("WorkoutHabitDBHelper", "in WorkoutHabitDBHelper on create");
            Log.i("create table log", SQL_CREATE_WORKOUT_HABIT_TABLE);

            db.execSQL(SQL_CREATE_WORKOUT_HABIT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("DROP TABLE IF EXIST " + WorkoutHabitDBContract.WorkoutHabitEntry.TABLE_NAME +";");
    }


}
