package com.orderit.app.model;

public class OrderDetail {
	private int id;
	private String orderid;
	private int menuid;
	private int num;

	//
	public int getId(){
		
		return id;
	}
	public void setId(int id){
		
		this.id=id;
	}
	//
	public String getOrderid(){
		
		return orderid;
	}
	public void setOrderid(String orderid){
		
		this.orderid=orderid;
	}

	public int getmenuid(){
		
		return menuid;
	}
	public void setmenuid(int menuid){
		
		this.menuid=menuid;
	}

	public int getNum(){
		
		return num;
	}
	public void setNum(int num){
		
		this.num=num;
	}
	
	
}
