package com.example.android.habittracker.database;

import android.provider.BaseColumns;

/**
 * Created by jennifernghinguyen on 9/9/17.
 */

public class WorkoutHabitDBContract {

    private WorkoutHabitDBContract(){}

    public static final class WorkoutHabitEntry implements BaseColumns{
        public static final String TABLE_NAME = "workouthabit";
        public static final String _ID = BaseColumns._ID;
        public static final String TITLE_COLUMN = "title";
        public static final String DATE_WORKOUT_COLUMN = "date_workout";
        public static final String REPS_COLUMN = "reps";
        public static final String DURATION_COLUMN = "duration";
    }
}
