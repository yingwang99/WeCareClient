package db;

import java.util.ArrayList;
import java.util.List;

import com.example.wecareclient.dto.Item;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ItemDBManager {

	private SQLiteDatabase db;
	private ItemDBDesigner itemDBDesigner;
	
	public ItemDBManager(Context context){
		
		itemDBDesigner = new ItemDBDesigner(context);
		
	}
	
	public void open(){
		db = itemDBDesigner.getWritableDatabase();
	}
	
	public void close(){
		db.close();
	}
	
	public void insert(Item item){
		
		ContentValues values = new ContentValues();
		values.put(ItemDBDesigner.COLUMN_ITEM_NAME, item.getName());
		values.put(ItemDBDesigner.COLUMN_ITEM_PRICE, item.getPrice());
		
		//long insertID = db.insert(ItemDBDesigner.ITEM_TABLE, null, values);
		db.insert(ItemDBDesigner.ITEM_TABLE, null, values);
		
	}
	
	public void delete(int id){
		db.delete(ItemDBDesigner.ITEM_TABLE, ItemDBDesigner.COLUMN_ID +" = "+id , null);
	}
	
	public void deleteAll(){
		
		//make sure there is a prompt to confirm this operation
		
		db.execSQL("DELETE FROM " + ItemDBDesigner.ITEM_TABLE);
	}
	
	public void update(Item item){
		
		ContentValues values = new ContentValues();
		values.put(ItemDBDesigner.COLUMN_ITEM_NAME, item.getName());
		values.put(ItemDBDesigner.COLUMN_ITEM_PRICE, item.getPrice());
		
		//long insertId = db.update(ItemDBDesigner.ITEM_TABLE, values, ItemDBDesigner.COLUMN_ID+" = "+item.getId(), null);
		db.update(ItemDBDesigner.ITEM_TABLE, values, ItemDBDesigner.COLUMN_ID+" = "+item.getId(), null);

	}
	
	
	/*
	 * @deprecated this method is used for test only
	 */
	public List<Item> getAll(){
		List<Item> items = new ArrayList<Item>();
		Cursor cursor = db.rawQuery("SELECT * FROM " + ItemDBDesigner.ITEM_TABLE, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			Item item = this.toItem(cursor);
			items.add(item);
			cursor.moveToNext();
		}
		cursor.close();
		return items;
	}
	
	public Item get(int id){
		
		Item target = null;
		Cursor cursor = db.rawQuery("SELECT * FROM "+ItemDBDesigner.ITEM_TABLE +
				" WHERE "+ ItemDBDesigner.COLUMN_ID + " = " +id, null);
		
		while (!cursor.isAfterLast()) {
			Item item = this.toItem(cursor);
			target = item;
			cursor.moveToNext();
		}
		
		cursor.close();
		return target;
		
	}
	
	private Item toItem(Cursor cursor){
		
		Item item = new Item();
		item.setId(cursor.getInt(0));
		item.setItmeName(cursor.getString(1));
		item.setPrice(cursor.getDouble(2));
		
		return item;
	}
	
	public void init(){
		
		Log.v("db", "db init");
		this.insert(new Item("item 1",1.0));
		this.insert(new Item("item 2",2.0));
		this.insert(new Item("item 3",3.0));
	}
	
	
	
}
