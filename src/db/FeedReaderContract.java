package db;

import android.provider.BaseColumns;

public final class FeedReaderContract {
    
    private FeedReaderContract() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "userInfo";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_PASSWORD = "password";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_USERGOAL = "userGoal";
        public static final String COLUMN_NAME_GENDER = "gender";
        public static final String COLUMN_NAME_DAYOFBIRTH = "dayOfBirth";
        public static final String COLUMN_NAME_COUNTRY = "country";
        public static final String COLUMN_NAME_HEIGHT = "height";
        public static final String COLUMN_NAME_WEIGHT = "weight";
        public static final String COLUMN_NAME_GOALOFWEIGHT = "goalOfWeight";
        public static final String COLUMN_NAME_WEEKLYGOAL = "weeklyGoal";
        
        public static final String TABLE_NAME_FOOD = "food";
        public static final String COLUMN_NAME_KIND = "kind";
        public static final String COLUMN_NAME_CATEGORIES = "categories";
        public static final String COLUMN_NAME_GRAMS = "grams";
        public static final String COLUMN_NAME_CALORIES = "calories";
        public static final String COLUMN_NAME_MEALTYPE = "meal_type";
        public static final String COLUMN_NAME_Add_TIME = "add_time";

    }
}

