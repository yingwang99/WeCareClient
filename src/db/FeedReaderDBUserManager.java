package db;

import com.example.wecareclient.dto.Food;
import com.example.wecareclient.dto.User;

import android.content.ContentValues;
import db.FeedReaderContract.FeedEntry;
import util.DateUtil;

public class FeedReaderDBUserManager {
	private ContentValues values;

	// Create a new map of values, where column names are the keys
	public ContentValues getUserValues(User user){	
		values = new ContentValues();
	
		values.put(FeedEntry.COLUMN_NAME_USERNAME, user.getUsername());
		values.put(FeedEntry.COLUMN_NAME_PASSWORD, user.getPassword());
		values.put(FeedEntry.COLUMN_NAME_EMAIL, user.getEmail());
		values.put(FeedEntry.COLUMN_NAME_USERGOAL, user.getUserGoal());
		values.put(FeedEntry.COLUMN_NAME_GENDER, user.getGender());
		values.put(FeedEntry.COLUMN_NAME_DAYOFBIRTH, DateUtil.getConvertDateToString(user.getDayOfBirth()));
		values.put(FeedEntry.COLUMN_NAME_COUNTRY, user.getCountry());
		values.put(FeedEntry.COLUMN_NAME_HEIGHT, user.getHeight());
		values.put(FeedEntry.COLUMN_NAME_WEIGHT, user.getWeight());
		values.put(FeedEntry.COLUMN_NAME_GOALOFWEIGHT, user.getGoalOfWeight());
		values.put(FeedEntry.COLUMN_NAME_WEEKLYGOAL, user.getWeeklyGoal());
		
		return values;
	}
	
	public ContentValues getFoodValues(Food food){
		values = new ContentValues();
		
		values.put(FeedEntry.COLUMN_NAME_KIND, food.getKind());
		values.put(FeedEntry.COLUMN_NAME_CATEGORIES, food.getCategray());
		values.put(FeedEntry.COLUMN_NAME_GRAMS, food.getG());
		values.put(FeedEntry.COLUMN_NAME_CALORIES, food.getCalorie());
		values.put(FeedEntry.COLUMN_NAME_MEALTYPE, food.getMeal_type());
		values.put(FeedEntry.COLUMN_NAME_Add_TIME, food.getAdd_time());
		
		return values;
	}
	
	
}
