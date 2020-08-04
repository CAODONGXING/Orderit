package com.orderit.app.util;

import java.util.ArrayList;
import java.util.List;

import com.orderit.app.db.OrderitOpenHelper;
import com.orderit.app.model.Menu;
import com.orderit.app.model.MenuType;
import com.orderit.app.model.Order;
import com.orderit.app.model.OrderDetail;
import com.orderit.app.model.Table;
import com.orderit.app.model.User;




import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class OrderDB{

//	数据库名
	public static final String DB_NAME="order_it";
//	数据库版本
	public static final int VERSION=1;
	private static OrderDB orderdb;
	private SQLiteDatabase db;

	private OrderDB(Context context){ 
		OrderitOpenHelper dbHelper=new OrderitOpenHelper(context,DB_NAME,null,VERSION);
		db=dbHelper.getWritableDatabase();
		
	}
	
//	获取orderdb实例
	public synchronized static OrderDB getInstance(Context context){
		if(orderdb==null){
			orderdb= new OrderDB(context);
		}
		return orderdb;
		
	}
//	将六个实例存入数据库
//	---开始---
	public void saveMenu(Menu menu){
		if(menu!=null){
			ContentValues values=new ContentValues();
			values.put("name", menu.getName());
			values.put("pic", menu.getPic());
			values.put("price", menu.getPrice());
			values.put("type", menu.getType());
			values.put("picbitmap", menu.getPicbitmap());
			db.insert("MenuTbl", null, values);
			
		}
		}
	
	public void saveMenuType(MenuType menutype){
		if(menutype!=null){
			ContentValues values=new ContentValues();

			values.put("type", menutype.getType());
			db.insert("MenuTypeTbl", null, values);
			
		}
		}
	
	public void saveOrder(Order order){
		if(order!=null){
			ContentValues values=new ContentValues();
			values.put("orderid", order.getOrderid());
			values.put("tableid", order.getTableid());
			values.put("personNum", order.getPersonNum());
			values.put("account", order.getAccount());
			values.put("paystatus", order.getPayStatus());	
			db.insert("OrderTbl", null, values);
			
		}
		}
	
	public void saveOrderDetail(OrderDetail orderdetail){
		if(orderdetail!=null){
			ContentValues values=new ContentValues();
			values.put("orderid", orderdetail.getOrderid());
			values.put("menuid", orderdetail.getmenuid());
			values.put("num", orderdetail.getNum());
			db.insert("OrderDetailTbl", null, values);
			
		}
		}
	
	public void saveTable(Table table){
		if(table!=null){
			ContentValues values=new ContentValues();
			values.put("tablenum", table.getTablenum());
			values.put("flag", table.getFlag());
			
			
			db.insert("TableTbl", null, values);
			
		}
		}
	
	public void saveUser(User user){
		if(user!=null){
			ContentValues values=new ContentValues();
			values.put("account", user.getAccount());
			values.put("password", user.getPassword());
			
			
			db.insert("UserTbl", null, values);
			
		}
		}
//	---结束---
	
	
//	读取指定一条服务员数据
	public User loadUser(String name){
		User user=new User();
		Cursor cursor=db.query("UserTbl",null,"account= ?", new String[] { name},null, null, null);
		if(cursor.moveToFirst()){
		do {

			user.setPassword(cursor.getString(cursor.getColumnIndex("password")));
			user.setAccount(cursor.getString(cursor.getColumnIndex("account")));
			
		} while (cursor.moveToNext());	
		
		}
		if(cursor!=null){
			cursor.close();
			
		}
		return user;
		}
//	---结束---
	
//	注册时是否已存在该用户
	public Boolean isExistUser(String name){
		
		Cursor cursor=db.query("UserTbl",null,"account= ?", new String[] { name},null, null, null);
		Boolean a=cursor.moveToFirst();
	
		return a;
		}
//	---结束---
	
//	读取指定一条菜品数据
	public Menu loadMenu(int id){
		Menu menu=new Menu();
		Cursor cursor=db.query("MenuTbl",null,"id= ?", new String[] { String.valueOf(id)},null, null, null);
		if(cursor.moveToFirst()){
		do {

			menu.setId(cursor.getInt(cursor.getColumnIndex("id")));
			menu.setName(cursor.getString(cursor.getColumnIndex("name")));
			menu.setPic(cursor.getInt(cursor.getColumnIndex("pic")));
			menu.setPrice(cursor.getString(cursor.getColumnIndex("price")));
			menu.setType(cursor.getString(cursor.getColumnIndex("type")));
			menu.setPicbitmap(cursor.getString(cursor.getColumnIndex("picbitmap")));
		} while (cursor.moveToNext());	
		
		}
		if(cursor!=null){
			cursor.close();
			
		}
		return menu;
		}
//	---结束---
	
//	读取指定一条餐桌数据
	public Table loadTable(int tablenum){
		Table table=new Table();
		Cursor cursor=db.query("TableTbl",null,"tablenum= ?", new String[] { String.valueOf(tablenum)},null, null, null);
		if(cursor.moveToFirst()){
		do {

			table.setFlag(cursor.getInt(cursor.getColumnIndex("flag")));
			table.setId(cursor.getInt(cursor.getColumnIndex("id")));
			table.setTablenum(cursor.getInt(cursor.getColumnIndex("tablenum")));
			
		} while (cursor.moveToNext());	
		
		}
		if(cursor!=null){
			cursor.close();
			
		}
		return table;
		}
//	---结束---
	
//	读取指定一条餐桌数据
	public Boolean isExistTable(int tablenum){
		
		Cursor cursor=db.query("TableTbl",null,"tablenum= ?", new String[] { String.valueOf(tablenum)},null, null, null);
		Boolean a=cursor.moveToFirst();
		
		return a;
		}
//	---结束---
	
//	读取所有餐桌数据
	public List<Table> loadTableList(){
		List<Table> list=new ArrayList<Table>();
		Cursor cursor=db.query("TableTbl",null,null, null,null, null, null);
		if(cursor.moveToFirst()){
		do {
			Table table=new Table();
			table.setFlag(cursor.getInt(cursor.getColumnIndex("flag")));
			table.setId(cursor.getInt(cursor.getColumnIndex("id")));
			table.setTablenum(cursor.getInt(cursor.getColumnIndex("tablenum")));
			list.add(table);
		} while (cursor.moveToNext());	
		
		}
		if(cursor!=null){
			cursor.close();
			
		}
		return list;
		}
//	---结束---
	
//	读取指定一条订单
	public  List<Order> loadOrder(int tableid,String account,int paystatus){
		List<Order> list=new ArrayList<Order>();
//		Order order=new Order();
//		Cursor cursor=db.query("OrderTbl",null,"tableid= ?"+"AND account=?"+"AND paystatus=?", new String[] { String.valueOf(tableid),account,String.valueOf(paystatus)},null, null, null);
		Cursor cursor=db.rawQuery("select * from OrderTbl where tableid=? and account=? and paystatus=?", new String[]{String.valueOf(tableid),account, String.valueOf(paystatus)});

		if(cursor.moveToFirst()){
		do {
			Order order=new Order();
			order.setTableid(cursor.getInt(cursor.getColumnIndex("tableid")));
			order.setAccount(cursor.getString(cursor.getColumnIndex("account")));
			order.setPayStatus(cursor.getInt(cursor.getColumnIndex("paystatus")));
			order.setId(cursor.getInt(cursor.getColumnIndex("id")));
			order.setOrderid(cursor.getString(cursor.getColumnIndex("orderid")));
			order.setPersonNum(cursor.getInt(cursor.getColumnIndex("personNum")));
			list.add(order);
		} while (cursor.moveToNext());	
		
		}
		if(cursor!=null){
			cursor.close();
			
		}
		return list;
		}
//	---结束---
//	读取订单号
	public  Order loadOrderid(int tableid,String user){
		Order order=new Order();

		Cursor cursor=db.rawQuery("select * from OrderTbl where tableid=? and account=? and paystatus=?", new String[]{String.valueOf(tableid),user,String.valueOf(1)});

		if(cursor.moveToFirst()){
		do {
			
			order.setTableid(cursor.getInt(cursor.getColumnIndex("tableid")));
			order.setAccount(cursor.getString(cursor.getColumnIndex("account")));
			order.setPayStatus(cursor.getInt(cursor.getColumnIndex("paystatus")));
			order.setId(cursor.getInt(cursor.getColumnIndex("id")));
			order.setOrderid(cursor.getString(cursor.getColumnIndex("orderid")));
			order.setPersonNum(cursor.getInt(cursor.getColumnIndex("personNum")));
			
		} while (cursor.moveToNext());	
		
		}
		if(cursor!=null){
			cursor.close();
			
		}
		return order;
		}
//	---结束---
//	读取用户名下所有订单
	public  List<Order> loadOrder(String account){
		List<Order> list=new ArrayList<Order>();

		Cursor cursor=db.rawQuery("select * from OrderTbl where account=?", new String[]{account});

		if(cursor.moveToFirst()){
		do {
			Order order=new Order();
			order.setTableid(cursor.getInt(cursor.getColumnIndex("tableid")));
			order.setAccount(cursor.getString(cursor.getColumnIndex("account")));
			order.setPayStatus(cursor.getInt(cursor.getColumnIndex("paystatus")));
			order.setId(cursor.getInt(cursor.getColumnIndex("id")));
			order.setOrderid(cursor.getString(cursor.getColumnIndex("orderid")));
			order.setPersonNum(cursor.getInt(cursor.getColumnIndex("personNum")));
			list.add(order);
		} while (cursor.moveToNext());	
		
		}
		if(cursor!=null){
			cursor.close();
			
		}
		return list;
		}
//	---结束---
	
//	读取订单结账状态码
	public  int loadpaystatus(String orderid){

Order order=new Order();
//		Cursor cursor=db.query("OrderTbl",null,"tableid= ?"+"AND account=?"+"AND paystatus=?", new String[] { String.valueOf(tableid),account,String.valueOf(paystatus)},null, null, null);
		Cursor cursor=db.rawQuery("select * from OrderTbl where orderid=? ", new String[]{orderid});

		if(cursor.moveToFirst()){
		do {
			
			order.setTableid(cursor.getInt(cursor.getColumnIndex("tableid")));
			order.setAccount(cursor.getString(cursor.getColumnIndex("account")));
			order.setPayStatus(cursor.getInt(cursor.getColumnIndex("paystatus")));
			order.setId(cursor.getInt(cursor.getColumnIndex("id")));
			order.setOrderid(cursor.getString(cursor.getColumnIndex("orderid")));
			order.setPersonNum(cursor.getInt(cursor.getColumnIndex("personNum")));
			
		} while (cursor.moveToNext());	
		
		}
		if(cursor!=null){
			cursor.close();
			
		}
		return order.getPayStatus();
		}
//	---结束---
	
//	读取订单结账桌号
	public  int loadtableid(String orderid){

Order order=new Order();
//		Cursor cursor=db.query("OrderTbl",null,"tableid= ?"+"AND account=?"+"AND paystatus=?", new String[] { String.valueOf(tableid),account,String.valueOf(paystatus)},null, null, null);
		Cursor cursor=db.rawQuery("select * from OrderTbl where orderid=? ", new String[]{orderid});

		if(cursor.moveToFirst()){
		do {
			
			order.setTableid(cursor.getInt(cursor.getColumnIndex("tableid")));
			order.setAccount(cursor.getString(cursor.getColumnIndex("account")));
			order.setPayStatus(cursor.getInt(cursor.getColumnIndex("paystatus")));
			order.setId(cursor.getInt(cursor.getColumnIndex("id")));
			order.setOrderid(cursor.getString(cursor.getColumnIndex("orderid")));
			order.setPersonNum(cursor.getInt(cursor.getColumnIndex("personNum")));
			
		} while (cursor.moveToNext());	
		
		}
		if(cursor!=null){
			cursor.close();
			
		}
		return order.getTableid();
		}
//	---结束---
	
//	按订单号读取订单详细
	public List<OrderDetail> loadOrderDetail(String orderid){
		List<OrderDetail> list=new ArrayList<OrderDetail>();
		Cursor cursor=db.query("OrderDetailTbl",null,"orderid= ?", new String[] {orderid},null, null, null);
		if(cursor.moveToFirst()){
		do {
			OrderDetail orderdetail=new OrderDetail();
			orderdetail.setId(cursor.getInt(cursor.getColumnIndex("id")));
			orderdetail.setOrderid(cursor.getString(cursor.getColumnIndex("orderid")));
			orderdetail.setNum(cursor.getInt(cursor.getColumnIndex("num")));
			orderdetail.setmenuid(cursor.getInt(cursor.getColumnIndex("menuid")));
			list.add(orderdetail);
		} while (cursor.moveToNext());	
		
		}
		if(cursor!=null){
			cursor.close();
			
		}
		return list;
		}
//	---结束---
	
//	根据菜品类型，读取菜品数据list数值
	public List<Menu> loadMenus(String type){
		List<Menu> list=new ArrayList<Menu>();
		Cursor cursor=db.query("MenuTbl",null,"type=?",new String[] {type}, null, null, null);
		if(cursor.moveToFirst()){
			do {	
				Menu menu=new Menu();
				menu.setId(cursor.getInt(cursor.getColumnIndex("id")));
				menu.setName(cursor.getString(cursor.getColumnIndex("name")));
				menu.setPic(cursor.getInt(cursor.getColumnIndex("pic")));
				menu.setPrice(cursor.getString(cursor.getColumnIndex("price")));
				menu.setType(cursor.getString(cursor.getColumnIndex("type")));
				menu.setPicbitmap(cursor.getString(cursor.getColumnIndex("picbitmap")));
				list.add(menu);
				
			} while (cursor.moveToNext());	
			
			}
			if(cursor!=null){
				cursor.close();
				
			}
			return list;
			}
		
	
//	---结束---
//	查找菜品类型
	public List<String> loadMenuTypes(){
		List<String> list=new ArrayList<String>();
	    String sql = "SELECT DISTINCT type FROM MenuTbl";  
	    Cursor cursor = db.rawQuery(sql, null);  
		if(cursor.moveToFirst()){
			do {	

				list.add(cursor.getString(cursor.getColumnIndex("type")));
				
			} while (cursor.moveToNext());	
			
			}
			if(cursor!=null){
				cursor.close();
				
			}
			return list;
			}
		
	
//	---结束---
//	修改结账状态码
	public void updatepaystatus(String orderid){
	ContentValues values=new ContentValues();
	values.put("paystatus", 0);
	db.update("OrderTbl", values, "orderid=?", new String[]{orderid});
	}
	
//	修改订单表中的桌号
	public void updatetable(int ordtable,int newtable){
	ContentValues values=new ContentValues();
	values.put("tableid", newtable);
	db.update("OrderTbl", values, "tableid=? AND paystatus=?", new String[]{String.valueOf(ordtable),String.valueOf(1)});
	}
	
//	修改订单表中的桌号
	public void updateDetailid(String ordorderid,String neworderid){
	ContentValues values=new ContentValues();
	values.put("orderid", neworderid);
	db.update("OrderDetailTbl", values, "orderid=?", new String[]{ordorderid});
	}
//	修改餐桌占用状态
	public void updateflag(String orderid){
		
	ContentValues values=new ContentValues();
	int tablenum=loadtableid(orderid);
	values.put("flag", 0);
	db.update("TableTbl", values, "tablenum=?", new String[]{String.valueOf(tablenum)});
	}
	
//	修改餐桌占用状态
	public void updateyuantai(int tablenum){
		
	ContentValues values=new ContentValues();
	values.put("flag", 0);
	db.update("TableTbl", values, "tablenum=?", new String[]{String.valueOf(tablenum)});
	}
//	修改餐桌占用状态
	public void updatexintai(int tablenum){
		
	ContentValues values=new ContentValues();
	values.put("flag", 1);
	db.update("TableTbl", values, "tablenum=?", new String[]{String.valueOf(tablenum)});
	}
	
	public void isrunDB(){
		Log.d("Sess2", "数据库方法调用成功");
		
	}
	
}
