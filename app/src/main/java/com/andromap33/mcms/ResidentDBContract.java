package com.andromap33.mcms;

import android.provider.BaseColumns;

public final class ResidentDBContract {
    public ResidentDBContract() {
    }

    public static abstract class ResidentEntry1 implements BaseColumns {
        public static final String TABLE_NAME = "resident";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_BLOCK = "block_no";
        public static final String COLUMN_NAME_ROOM = "room_no";
        public static final String COLUMN_NAME_SECTION = "section";
        public static final String COLUMN_NAME_PHONE = "mob_no";
        public static final String COLUMN_NAME_ROLLNO = "roll_no";
        public static final String COLUMN_NAME_PASSWORD = "password";
        public static final String COLUMN_NAME_USERNAME = "username";
    }


    public static abstract class Diet implements BaseColumns {
        public static final String TABLE_NAME = "diet_entry";
        public static final String COLUMN_NAME_ROLLNO = "roll_no";
        public static final String COLUMN_NAME_DIET = "diet_taken";
        public static final String COLUMN_NAME_PRICE = "price";
    }
    public static abstract class Users implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME = "username";
        public static final String COLUMN_PASSWORD = "password";
    }
}
