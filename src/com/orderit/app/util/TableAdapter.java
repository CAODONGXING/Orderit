package com.orderit.app.util;

import java.util.ArrayList;
import java.util.List;




import com.orderit.app.R;
import com.orderit.app.model.Table;
import com.orderit.app.util.MenuAdapter.ViewHolder;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TableAdapter extends ArrayAdapter<Table> {
	  private List<Table> tablelist=new ArrayList<Table>();
	  private int  resourceId;
	  Table table = null;
	  Table table2 = null;
	  Table table3=null;
	  public TableAdapter(Context context,int textViewResourceId,List<Table> objcts){
			super(context,textViewResourceId,objcts);
			this.tablelist = objcts;
			resourceId=textViewResourceId;
		
		}
		public int getCount() {
			  int row;
			int a=tablelist.size();
			if(a%3==0){
				row=a/3;
			}else{
				row=(a/3)+1;	
			}
			
			return row;
		}
		public View getView(int i, View converView, ViewGroup viewGroup) {
			  View view;
			  ViewHolder viewHolder;
				if(tablelist.size()>(3*i)){
					table=tablelist.get(3*i);
					}
					  if(tablelist.size()>(3*i+1)){
						  table2=tablelist.get(3*i+1);
					  }
					  if(tablelist.size()>(3*i+2)){
						  table3=tablelist.get(3*i+2);
					  }
				        if(converView == null){
				            view = LayoutInflater.from(getContext()).inflate(resourceId,null);
				            viewHolder = new ViewHolder();
				            viewHolder.table_but1=(Button)view.findViewById(R.id.table_but1);
				            viewHolder.table_but2=(Button)view.findViewById(R.id.table_but2);
				            viewHolder.table_but3=(Button)view.findViewById(R.id.table_but3);
				            view.setTag(viewHolder);}
				        else{
				        	view=converView;
					        viewHolder = (ViewHolder)view.getTag();
				        }
				        if(tablelist.size()>(3*i)){
				        	viewHolder.table_but1.setText(table.getTablenum()+"×À");
				        	if(table.getFlag()==1)viewHolder.table_but1.setBackgroundColor(Color.parseColor("#FF9900"));
				        	viewHolder.table_but2.setVisibility(View.GONE);
				        	viewHolder.table_but3.setVisibility(View.GONE);
				        }
				        if(tablelist.size()>(3*i+1)){
				        	if(table2.getFlag()==1)viewHolder.table_but2.setBackgroundColor(Color.parseColor("#FF9900"));
				        	viewHolder.table_but2.setText(table2.getTablenum()+"×À");
				        	viewHolder.table_but2.setVisibility(View.VISIBLE);
				        }
				        if(tablelist.size()>(3*i+2)){
				        	if(table3.getFlag()==1)viewHolder.table_but3.setBackgroundColor(Color.parseColor("#FF9900"));
				        	viewHolder.table_but3.setText(table3.getTablenum()+"×À");
				        	viewHolder.table_but3.setVisibility(View.VISIBLE);
				        }
			  return view;
		}
		   class ViewHolder{

		        Button table_but1;
		        Button table_but2;
		        Button table_but3;

		        
		    }
		
		}
	  

