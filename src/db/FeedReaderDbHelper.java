package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import db.FeedReaderContract.FeedEntry;

public class FeedReaderDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "User.db";
    /**
	 * define user table fields
	 */
    private static final String SQL_CREATE_ENTRIES =
    	    "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
    	    FeedEntry._ID + " INTEGER PRIMARY KEY," +
    	    FeedEntry.COLUMN_NAME_USERNAME + " TEXT," +
    	    FeedEntry.COLUMN_NAME_PASSWORD + " TEXT," +
    	    FeedEntry.COLUMN_NAME_EMAIL + " TEXT," +
    	    FeedEntry.COLUMN_NAME_USERGOAL + " TEXT," +
    	    FeedEntry.COLUMN_NAME_GENDER + " TEXT," +
    	    FeedEntry.COLUMN_NAME_DAYOFBIRTH + " TEXT," +
    	    FeedEntry.COLUMN_NAME_COUNTRY + " TEXT," +
    	    FeedEntry.COLUMN_NAME_HEIGHT + " TEXT," +
    	    FeedEntry.COLUMN_NAME_WEIGHT + " TEXT," +
    	    FeedEntry.COLUMN_NAME_GOALOFWEIGHT + " TEXT," +
    	    FeedEntry.COLUMN_NAME_WEEKLYGOAL + " TEXT)";
    
    private static final String SQL_CREATE_FOOD_ENTRIES = 
    		"CREATE TABLE " + FeedEntry.TABLE_NAME_FOOD + " (" +
    	    FeedEntry._ID + " INTEGER PRIMARY KEY," +
    	    FeedEntry.COLUMN_NAME_KIND + " TEXT," +
    	    FeedEntry.COLUMN_NAME_CATEGORIES + " TEXT," +
    	    FeedEntry.COLUMN_NAME_GRAMS + " TEXT," +
    	    FeedEntry.COLUMN_NAME_CALORIES + " TEXT," +
    	    FeedEntry.COLUMN_NAME_MEALTYPE + " TEXT," +
    	    FeedEntry.COLUMN_NAME_Add_TIME + " TEXT)"; 

    private static final String SQL_DELETE_ENTRIES =
    	    "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;
    
    private static final String SQL_DELETE_FOOD_ENTRIES =
    	    "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME_FOOD;

    public FeedReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
   
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_FOOD_ENTRIES);

	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		 db.execSQL(SQL_DELETE_ENTRIES);
		 db.execSQL(SQL_DELETE_FOOD_ENTRIES);

	     onCreate(db);
	}

}
