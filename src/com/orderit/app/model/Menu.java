package com.orderit.app.model;

public class Menu {
	private int id;
	private String name;
	private String price;
	private String type;
	private int pic;
	private String picbitmap;
	//
	public int getId(){
		
		return id;
	}
	public void setId(int id){
		
		this.id=id;
	}
	//
	public String getName(){
		
		return name;
	}
	public void setName(String name){
		
		this.name=name;
	}
	//
	public String getType(){
		
		return type;
	}
	public void setType(String type){
		
		this.type=type;
	}
	//
	public int getPic(){
		
		return pic;
	}
	public void setPic(int pic){
		
		this.pic=pic;
	}
	//
	public String getPrice(){
		
		return price;
	}
	public void setPrice(String price){
		
		this.price=price;
	}

	//
	public String getPicbitmap(){
		
		return picbitmap;
	}
	public void setPicbitmap(String picbitmap){
		
		this.picbitmap=picbitmap;
	}
	//
	
	
	
}
