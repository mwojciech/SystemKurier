package com.prz.kuriertrack.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.prz.kuriertrack.Model.Pack;

import java.util.ArrayList;

public class DatabaseAdapter extends SQLiteOpenHelper {
	private static final String DEBUG_TAG = "LOG_DATABASE";

	private static final int DB_VERSION = 1;
	private static final String DB_NAME = "database";
	private static final String TABLE = "kurier";



	public static final String KEY_ID = "id";
	public static final String ID_OPTIONS = "INTEGER PRIMARY KEY AUTOINCREMENT";
	public static final int ID_COLUMN = 0;
	
	public static final String KEY_NAME = "name";
	public static final String NAME_OPTIONS = "TEXT";
	public static final int NAME_COLUMN = 1;

    public static final String KEY_DESC = "description";
    public static final String DESC_OPTIONS = "TEXT";
    public static final int DESC_COLUMN = 2;
	
	public static final String KEY_STREET = "street";
	public static final String STREET_OPTIONS = "TEXT";
	public static final int STREET_COLUMN = 3;

	public static final String KEY_ADDRESS = "address";
	public static final String ADDRESS_OPTIONS = "TEXT";
	public static final int ADDRESS_COLUMN = 4;

	public static final String KEY_CITY = "city";
	public static final String CITY_OPTIONS = "TEXT";
	public static final int CITY_COLUMN = 5;

	public static final String KEY_COUNTRY = "country";
	public static final String COUNTRY_OPTIONS = "TEXT";
	public static final int COUNTRY_COLUMN = 6;

	public DatabaseAdapter(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
	}


    @Override
    public void onCreate(SQLiteDatabase db) {
        //tworzenie tabeli
        String DB_CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE + "( "
                + KEY_ID + " " + ID_OPTIONS + ", "
                + KEY_NAME + " " + NAME_OPTIONS + ", "
                + KEY_DESC + " " + DESC_OPTIONS + ", "
                + KEY_STREET + " " + STREET_OPTIONS + ", "
                + KEY_ADDRESS + " " + ADDRESS_OPTIONS + ", "
                + KEY_CITY + " " + CITY_OPTIONS + ", "
                + KEY_COUNTRY + " " + COUNTRY_OPTIONS
                + ");";

        db.execSQL(DB_CREATE_CONTACTS_TABLE);

        Log.d(DEBUG_TAG, "Database creating... Table " + TABLE + " created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_CONTACTS_TABLE = "DROP TABLE IF EXISTS " + TABLE;
        db.execSQL(DROP_CONTACTS_TABLE);                                               //usuwanie starej tabeli

        onCreate(db);                                                                  //utworzenie nowej tabeli

        Log.d("DEBUG_TAG", "Database updating...");
    }

	//usuwanie kontaktu
	public boolean deletePacks() {
		SQLiteDatabase db = this.getWritableDatabase();
		return db.delete(TABLE, null, null) > 0;
	}

    //dodanie kontaktu
	public long insertContact(String name, String desc, String street, String address, String city, String country) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues newContactValues = new ContentValues();
        newContactValues.put(KEY_NAME, name);
        newContactValues.put(KEY_DESC, desc);
        newContactValues.put(KEY_STREET, street);
        newContactValues.put(KEY_ADDRESS, address);
        newContactValues.put(KEY_CITY, city);
        newContactValues.put(KEY_COUNTRY, country);

		return db.insert(TABLE, null, newContactValues);
	}


	public Cursor getAllContacts() {
        SQLiteDatabase db = this.getWritableDatabase();

		String[] columns = { KEY_ID, KEY_NAME, KEY_DESC, KEY_STREET, KEY_ADDRESS, KEY_CITY, KEY_COUNTRY };
		return db.query(TABLE, columns, null, null, null, null, null);
	}

	public ArrayList<Pack> getAllElements() {

		ArrayList<Pack> list = new ArrayList<Pack>();

		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE;

		SQLiteDatabase db = this.getReadableDatabase();
		try {
			Cursor cursor = db.rawQuery(selectQuery, null);
			try {
				// looping through all rows and adding to list
				if (cursor.moveToFirst()) {
					do {
						Pack obj = new Pack();
						//only one column
						//you could add additional columns here..

						list.add(obj);
					} while (cursor.moveToNext());
				}

			} finally {
				try { cursor.close(); } catch (Exception ignore) {}
			}

		} finally {
			try { db.close(); } catch (Exception ignore) {}
		}

		return list;
	}

	public Pack getPack(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

		String[] columns = { KEY_ID, KEY_NAME, KEY_DESC, KEY_STREET, KEY_ADDRESS, KEY_CITY, KEY_COUNTRY };
		String where = KEY_ID + "=" + id;
				
		Cursor cursor = db.query(TABLE, columns, where, null, null, null, null, null);

		Pack contact = null;
		if (cursor != null && cursor.moveToFirst()) {
			String name = cursor.getString(NAME_COLUMN);
            String desc = cursor.getString(DESC_COLUMN);
            String street = cursor.getString(STREET_COLUMN);
            String address = cursor.getString(ADDRESS_COLUMN);
            String city = cursor.getString(CITY_COLUMN);
            String country = cursor.getString(COUNTRY_COLUMN);

			contact = new Pack(id, name, desc, street, address, city, country);
		}
		return contact;
	}
}