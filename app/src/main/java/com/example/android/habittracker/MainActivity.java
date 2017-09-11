package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.android.habittracker.database.WorkoutHabitDBContract;
import com.example.android.habittracker.database.WorkoutHabitDBHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private WorkoutHabitDBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //dbHelper.getWritableDatabase();
        displayDBInfo();
        insertData("push up", 3, 20);
        insertData("sit up", 3, 30);
        insertData("jumping jack",5, 15);
    }

    private void displayDBInfo(){
        dbHelper = new WorkoutHabitDBHelper(this);

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String projection[] = {WorkoutHabitDBContract.WorkoutHabitEntry._ID,
                WorkoutHabitDBContract.WorkoutHabitEntry.TITLE_COLUMN,
                WorkoutHabitDBContract.WorkoutHabitEntry.DATE_WORKOUT_COLUMN,
                WorkoutHabitDBContract.WorkoutHabitEntry.REPS_COLUMN,
                WorkoutHabitDBContract.WorkoutHabitEntry.DURATION_COLUMN};

        Cursor cursor = db.query(WorkoutHabitDBContract.WorkoutHabitEntry.TABLE_NAME, projection, null, null, null, null, null);

        try {
            int idColumnIndex = cursor.getColumnIndex(WorkoutHabitDBContract.WorkoutHabitEntry._ID);
            int titleColumnIndex = cursor.getColumnIndex(WorkoutHabitDBContract.WorkoutHabitEntry.TITLE_COLUMN);
            int dateWorkoutColumnIndex = cursor.getColumnIndex(WorkoutHabitDBContract.WorkoutHabitEntry.DATE_WORKOUT_COLUMN);
            int repsColumnIndex = cursor.getColumnIndex(WorkoutHabitDBContract.WorkoutHabitEntry.REPS_COLUMN);
            int dureationColumnIndex = cursor.getColumnIndex(WorkoutHabitDBContract.WorkoutHabitEntry.DURATION_COLUMN);

            Log.i("db log", WorkoutHabitDBContract.WorkoutHabitEntry._ID + " - " +
                    WorkoutHabitDBContract.WorkoutHabitEntry.TITLE_COLUMN + " - " +
                    WorkoutHabitDBContract.WorkoutHabitEntry.DATE_WORKOUT_COLUMN + " - " +
                    WorkoutHabitDBContract.WorkoutHabitEntry.REPS_COLUMN + " - " +
                    WorkoutHabitDBContract.WorkoutHabitEntry.DURATION_COLUMN);


            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentTitle = cursor.getString(titleColumnIndex);
                String currentDateWorkout = cursor.getString(dateWorkoutColumnIndex);
                int currentReps = cursor.getInt(repsColumnIndex);
                int currentDuration = cursor.getInt(dureationColumnIndex);

                Log.i("data log", currentID + " - " +
                        currentTitle + " - " +
                        currentDateWorkout + " - " +
                        currentReps + " - " +
                        currentDuration);
            }

        }finally {
            cursor.close();
        }
        Log.i("db log", WorkoutHabitDBContract.WorkoutHabitEntry.TABLE_NAME+" has " + cursor.getCount() + "records");




    }

    private String getDate(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        return format.format(date);
    }

    private void insertData(String title, int reps, int duration){
            SQLiteDatabase db = dbHelper.getWritableDatabase();
        // Create a new map of values, where column names are the keys
        ContentValues contentValues = new ContentValues();
        contentValues.put(WorkoutHabitDBContract.WorkoutHabitEntry.DATE_WORKOUT_COLUMN, getDate());
        contentValues.put(WorkoutHabitDBContract.WorkoutHabitEntry.REPS_COLUMN, reps);
        contentValues.put(WorkoutHabitDBContract.WorkoutHabitEntry.TITLE_COLUMN, title);
        contentValues.put(WorkoutHabitDBContract.WorkoutHabitEntry.DURATION_COLUMN, duration);

        long newRowId = db.insert(WorkoutHabitDBContract.WorkoutHabitEntry.TABLE_NAME, null, contentValues);
    }
}
