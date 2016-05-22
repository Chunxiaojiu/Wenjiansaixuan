package com.example.Db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Dbwenjian extends SQLiteOpenHelper {

	public Dbwenjian(Context context) {
		super(context, "db", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE WENJIAN("
				+ "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "DATA TEXT DEFAULT \"\"," + "NAME TEXT DEFAULT \"\","
				+ "TYPE TEXT DEFAULT \"\")");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
