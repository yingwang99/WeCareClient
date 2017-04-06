package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class ItemDBDesigner extends SQLiteOpenHelper {

	public static final String ITEM_TABLE = "item_table";
	public static final String COLUMN_ID = "item_id";
	public static final String COLUMN_ITEM_NAME = "name";
	public static final String COLUMN_ITEM_PRICE = "price";
	
	private static final String DATABASE_NAME = "reward_system.db";
	private static final int DATABASE_VERSION = 1;
	
	
	private static final String CREATE_ITEM_TABLE = "CREATE TABLE " + ITEM_TABLE +" ( " 
			+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ COLUMN_ITEM_NAME + " TEXT NOT NULL, "
			+ COLUMN_ITEM_PRICE + " DOUBLE );";
	
	
	public ItemDBDesigner(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_ITEM_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + ITEM_TABLE);
		onCreate(db);
	}

}
