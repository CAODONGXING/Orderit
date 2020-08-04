package com.orderit.app.model;

public class Order {
	private int id;
	private String orderid;
	private int tableid;
	private int personNum;
	private String account;
	private int paystatus;

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

	public int getTableid(){
		
		return tableid;
	}
	public void setTableid(int tableid){
		
		this.tableid=tableid;
	}

	public int getPersonNum(){
		
		return personNum;
	}
	public void setPersonNum(int personNum){
		
		this.personNum=personNum;
	}
    public String getAccount(){
		
		return account;
	}
	public void setAccount(String account){
		
		this.account=account;
	}

	public int getPayStatus(){
		
		return paystatus;
	}
	public void setPayStatus(int paystatus){
		
		this.paystatus=paystatus;
	}
	
}
