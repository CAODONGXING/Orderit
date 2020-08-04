package com.orderit.app.util;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.orderit.app.R;
import com.orderit.app.model.Menu;
import com.orderit.app.model.OrderDetail;


public class LishiAdapter extends ArrayAdapter<OrderDetail> {
	  private int  resourceId;
	  private ViewHolder viewHolder;
	  private OrderDB orderdb;
	  public LishiAdapter(Context context,int textViewResourceId,List<OrderDetail> objcts){
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
	          viewHolder.yidian_item1=(TextView)view.findViewById(R.id.yidian_item1);
	          viewHolder.yidian_item2=(TextView)view.findViewById(R.id.yidian_item2);
	          viewHolder.yidian_item3=(TextView)view.findViewById(R.id.yidian_item3);
	          view.setTag(viewHolder);
	      } else{
	    	  view=converView;
	    	  viewHolder = (ViewHolder)view.getTag();
	      }
	      Menu menu=orderdb.loadMenu(orderdetail.getmenuid());
        viewHolder.yidian_item1.setText(orderdetail.getOrderid());
        viewHolder.yidian_item2.setText(menu.getName().toString());
        viewHolder.yidian_item3.setText(String.valueOf(orderdetail.getNum()));
        
        return view;
  	  
  	  
  	  
	  }
		  
	  class ViewHolder{
		  
	      TextView yidian_item1;
	      TextView yidian_item2;
	      TextView yidian_item3;
	  }
	  
}
