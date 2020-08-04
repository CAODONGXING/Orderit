package com.orderit.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class OrderitOpenHelper extends SQLiteOpenHelper {
	public static final String CREATE_UserTbl="create table UserTbl(" +
			"id integer primary key autoincrement," +
			"account text," +
			"password text)";
	
	public static final String CREATE_MenuTypeTbl="create table MenuTypeTbl(" +
			"id integer primary key autoincrement," +
			"type text)";
	
	public static final String CREATE_TableTbl="create table TableTbl(" +
			"id integer primary key autoincrement," +
			"tablenum integer," +
			"flag integer)";
	
	public static final String CREATE_MenuTbl="create table MenuTbl(" +
			"id integer primary key autoincrement," +
			"name text," +
			"price text," +
			"type text," +
			"picbitmap text," +
			"pic integer)";
	
	public static final String CREATE_OrderTbl="create table OrderTbl(" +
			"id integer primary key autoincrement," +
			"orderid text," +
			"tableid integer," +
			"account text," +
			"paystatus integer," +
			"personNum integer)";
//	应该新增下单用户信息关联
	
	public static final String CREATE_OrderDetailTbl="create table OrderDetailTbl(" +
			"id integer primary key autoincrement," +
			"orderid text," +
			"menuid integer," +
			"num integer)";
	

	public OrderitOpenHelper(Context context,String name,CursorFactory factory,int version){
		super(context, name, factory, version); 
		
		}
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_UserTbl);
		db.execSQL(CREATE_MenuTypeTbl);
		db.execSQL(CREATE_TableTbl);
		db.execSQL(CREATE_MenuTbl);
		db.execSQL(CREATE_OrderTbl);
		db.execSQL(CREATE_OrderDetailTbl);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		
	}

}
