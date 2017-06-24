package com.example.georgekaraolanis.project9_habittracker.data;

import android.provider.BaseColumns;

public final class HabitContract {

    /*Empty constructor*/
    public HabitContract(){

    }

    /*Define constant values for habit database table*/
    public static final class HabitEntry implements BaseColumns{

        /*Name of database habit table*/
        public final static String TABLE_NAME = "habits";

        /*ID for habits table. Is of type INTEGER*/
        public final static String _ID = BaseColumns._ID;

        /*Habit name, for example medicine, workout etc. Is of type TEXT*/
        public final static String COLUMN_HABIT_NAME = "name";

        /*Time of day that habit is being done. Is of type TEXT*/
        public final static String COLUMN_HABIT_TIME = "time";

        /*Category of the habit. Is of type INTEGER. Values can be:
        * 0: hobby
        * 1: medication
        * 2: pet
        * 3: work
        * 4: other*/
        public final static String COLUMN_HABIT_CATEGORY = "category";

        /*Possible values for habit, just for reference*/
        private static final int CATEGORY_HOBBY = 0;
        private static final int CATEGORY_MEDICATION = 1;
        private static final int CATEGORY_PET = 2;
        private static final int CATEGORY_WORK = 3;
        private static final int CATEGORY_OTHER = 4;

    }

}
