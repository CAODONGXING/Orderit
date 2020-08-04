package com.orderit.app.util;

import java.util.List;

import com.orderit.app.R;
import com.orderit.app.model.Menu;
import com.orderit.app.model.OrderDetail;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class CartAdapter extends ArrayAdapter<OrderDetail> implements View.OnClickListener{
      private TextView cartprice;
	  private int  resourceId;
	  private ViewHolder viewHolder;
	private OrderDB orderdb;
	  public CartAdapter(Context context,int textViewResourceId,List<OrderDetail> objcts){
			super(context,textViewResourceId,objcts);
			orderdb=OrderDB.getInstance(context);
			resourceId=textViewResourceId;
		
		}
	

public View getView(int i, View converView, ViewGroup viewGroup) {
	OrderDetail orderdetail=getItem(i);
	  View view;

     
      if(converView == null){
          view = LayoutInflater.from(getContext()).inflate(resourceId,null);
          viewHolder = new ViewHolder();
          viewHolder.cartshuliang=(EditText)view.findViewById(R.id.cartshuliang);
          
          viewHolder.cartpinming=(TextView)view.findViewById(R.id.cartpinming);
          viewHolder.cartjiage=(TextView)view.findViewById(R.id.cartjiage);
          viewHolder.cartminus=(Button)view.findViewById(R.id.cartminus);
          viewHolder.cartplus=(Button)view.findViewById(R.id.cartplus);
          viewHolder.delete_but=(Button)view.findViewById(R.id.delete_but);
          view.setTag(viewHolder);
      }
      else{view=converView;
      viewHolder = (ViewHolder)view.getTag();}

      

//	        viewHolder.cartshuliang.setText(menu.getPic());
Menu menu=orderdb.loadMenu(orderdetail.getmenuid());
          viewHolder.cartpinming.setText(menu.getName());
          viewHolder.cartjiage.setText(menu.getPrice());
          viewHolder.cartshuliang.setText(String.valueOf(orderdetail.getNum()));
          viewHolder.cartminus.setOnClickListener(this);
          viewHolder.cartplus.setOnClickListener(this);
          viewHolder.delete_but.setOnClickListener(this);
          viewHolder.cartminus.setTag(R.id.mins,i);
          viewHolder.cartplus.setTag(R.id.plus,i);
          viewHolder.delete_but.setTag(R.id.del,i);
//          在values中创建mins plus两个id，i 的值将存入其中，则可知道是list中第几个数据进行操作
      return view;
	  
	  
	  
}
 class ViewHolder{
	  EditText cartshuliang;
      TextView cartpinming;
      TextView cartjiage;
      
      Button cartminus;
      Button cartplus;
      Button delete_but;
  }
 
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.cartminus:
int a=(Integer) v.getTag(R.id.mins);


int a2=orderpublic.cartList.get(a).getNum()-1;
if(a2>0){
orderpublic.cartList.get(a).setNum(a2);}

notifyDataSetChanged();
			break;

		case R.id.cartplus:
			int b=(Integer) v.getTag(R.id.plus);
			
			int b2=orderpublic.cartList.get(b).getNum()+1;
			orderpublic.cartList.get(b).setNum(b2);
			try {
				notifyDataSetChanged();
			} catch (Exception e) {
				// TODO: handle exception
			}

			break;
		case R.id.delete_but:
			int c=(Integer) v.getTag(R.id.del);
			orderpublic.cartList.remove(c);
			notifyDataSetChanged();
			break;
		default:
			break;
		}
		
	}
 }