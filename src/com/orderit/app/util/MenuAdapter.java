package com.orderit.app.util;

import java.util.ArrayList;
import java.util.List;


import com.orderit.app.R;
import com.orderit.app.model.Menu;
import com.orderit.app.model.OrderDetail;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;



public class MenuAdapter extends ArrayAdapter<Menu> implements View.OnClickListener{
	  private int  resourceId;
	  private List<Menu> menulist=new ArrayList<Menu>();
	  Menu menu = null;
	  Menu menu2 = null;
	  Menu menu3=null;
	  AlphaAnimation anim_alpha;
	  
	  public MenuAdapter(Context context,int textViewResourceId,List<Menu> objcts){
			super(context,textViewResourceId,objcts);
			this.menulist = objcts;
			resourceId=textViewResourceId;
		      anim_alpha = new AlphaAnimation(0,1); 
		      anim_alpha.setDuration(1000);//动画时间  
		}
	  
	  @Override
	public int getCount() {
		  int row;
		int a=menulist.size();
		if(a%3==0){
			row=a/3;
		}else{
			row=(a/3)+1;	
		}
		
		return row;
	}


	public View getView(int i, View converView, ViewGroup viewGroup) {
//		  Menu menu=getItem(3*i);
		if(menulist.size()>(3*i)){
		  menu=menulist.get(3*i);
		}
		  if(menulist.size()>(3*i+1)){
			  menu2=menulist.get(3*i+1);
		  }
		  if(menulist.size()>(3*i+2)){
			  menu3=menulist.get(3*i+2);
		  }
		  
		  View view;
		  ViewHolder viewHolder;
	       
	        if(converView == null){
	            view = LayoutInflater.from(getContext()).inflate(resourceId,null);
	            viewHolder = new ViewHolder();
	            viewHolder.ima=(ImageView)view.findViewById(R.id.dish1);
	            viewHolder.but=(Button)view.findViewById(R.id.listviewadd_but1);
	            viewHolder.caimin=(TextView)view.findViewById(R.id.listitem_caiming1);
	            viewHolder.jiage=(TextView)view.findViewById(R.id.listitem_jiage1);
	            
	            viewHolder.ima2=(ImageView)view.findViewById(R.id.dish2);
	            viewHolder.but2=(Button)view.findViewById(R.id.listviewadd_but2);
	            viewHolder.caimin2=(TextView)view.findViewById(R.id.listitem_caiming2);
	            viewHolder.jiage2=(TextView)view.findViewById(R.id.listitem_jiage2);
	            
	            viewHolder.linearlayout3=(LinearLayout)view.findViewById(R.id.listviewitem3);
	            viewHolder.linearlayout2=(LinearLayout)view.findViewById(R.id.listviewitem2);
	            
	            viewHolder.ima3=(ImageView)view.findViewById(R.id.dish3);
	            viewHolder.but3=(Button)view.findViewById(R.id.listviewadd_but3);
	            viewHolder.caimin3=(TextView)view.findViewById(R.id.listitem_caiming3);
	            viewHolder.jiage3=(TextView)view.findViewById(R.id.listitem_jiage3);
	            Log.d("Sess","控件获取1="+viewHolder.caimin);
	            Log.d("Sess","控件获取2="+viewHolder.jiage);

	           
	            
	            view.setTag(viewHolder);
	        }
	        else{view=converView;
	        viewHolder = (ViewHolder)view.getTag();}
//	        try {
	        
	        if(menulist.size()>(3*i)){
        		Log.d("Sess3",menu.getPicbitmap()+menu.getName());
	        	if(!TextUtils.isEmpty(menu.getPicbitmap())){
	        		 Bitmap bm = BitmapFactory.decodeFile(menu.getPicbitmap());
	        		viewHolder.ima.setImageBitmap(bm);
	        	


	        	}
	        	else viewHolder.ima.setImageResource(menu.getPic());
	        	viewHolder.ima.setScaleType(ImageView.ScaleType.CENTER_CROP);
	            viewHolder.but.setOnClickListener(this);
	            viewHolder.caimin.setText(menu.getName());
	            viewHolder.jiage.setText(menu.getPrice());
	            viewHolder.but.setTag(R.id.tv,i);
	            viewHolder.linearlayout2.setVisibility(View.GONE);
	            viewHolder.linearlayout3.setVisibility(View.GONE);
	            }
            if(menulist.size()>(3*i+1)){
	        	if(!TextUtils.isEmpty(menu2.getPicbitmap())){
	        		Bitmap bm = BitmapFactory.decodeFile(menu2.getPicbitmap());
	        		viewHolder.ima2.setImageBitmap(bm);
	        		
	        	}
	        	else viewHolder.ima2.setImageResource(menu2.getPic());
	        	viewHolder.ima2.setScaleType(ImageView.ScaleType.CENTER_CROP);
	            viewHolder.but2.setOnClickListener(this);
	            viewHolder.caimin2.setText(menu2.getName());
	            viewHolder.jiage2.setText(menu2.getPrice());
	            viewHolder.but2.setTag(R.id.tv2,i);
	            viewHolder.linearlayout2.setVisibility(View.VISIBLE);
	            
                }
            if(menulist.size()>(3*i+2)){
	        	if(!TextUtils.isEmpty(menu3.getPicbitmap())){
	        		 Bitmap bm = BitmapFactory.decodeFile(menu3.getPicbitmap());
	        		viewHolder.ima3.setImageBitmap(bm);
	        		
	        	}
	        	else viewHolder.ima3.setImageResource(menu3.getPic());
	        	viewHolder.ima3.setScaleType(ImageView.ScaleType.CENTER_CROP);
                viewHolder.but3.setOnClickListener(this);
                viewHolder.caimin3.setText(menu3.getName());
                viewHolder.jiage3.setText(menu3.getPrice());
                viewHolder.but3.setTag(R.id.tv3,i);

                viewHolder.linearlayout3.setVisibility(View.VISIBLE);
}
  
          
	        return view;
		  
		  
		  
	  }
	   class ViewHolder{
		   LinearLayout linearlayout3;
		   LinearLayout linearlayout2;
		   
	        ImageView ima;
	        Button but;
	        TextView caimin;
	        TextView jiage;

	        ImageView ima2;
	        Button but2;
	        TextView caimin2;
	        TextView jiage2;
	        
	        ImageView ima3;
	        Button but3;
	        TextView caimin3;
	        TextView jiage3;
	        
	    }
	@Override
	public void onClick(View v) {
		Boolean ishave=true;
		switch (v.getId()) {
		case R.id.listviewadd_but1:
 
		      
		      v.startAnimation(anim_alpha);//启动动画  
		    int a = (Integer) v.getTag(R.id.tv);
			Menu menu=menulist.get(3*a);

			for(OrderDetail orderdetail:orderpublic.cartList){
				int i=0;
				if(orderdetail.getmenuid()==menu.getId())
					{orderpublic.cartList.get(i).setNum(orderpublic.cartList.get(i).getNum()+1);
					ishave=false;
					break;}
				i+=1;
				
			}
//			放在了全局静态列表中
			if(ishave){
			OrderDetail orderdetail=new OrderDetail();
			orderdetail.setmenuid(menu.getId());
			orderdetail.setOrderid(orderpublic.publicorderid);
			orderdetail.setNum(1);
			orderpublic.cartList.add(orderdetail);
			ishave=false;
			}
			break;
		case R.id.listviewadd_but2:
			 v.startAnimation(anim_alpha);//启动动画  
//			要加边界判断
			 int b = (Integer) v.getTag(R.id.tv2);
			Menu menu2=menulist.get(3*b+1);
//			防止同一商品在购物车重复出现
			for(OrderDetail orderdetail:orderpublic.cartList){
				int i=0;
				if(orderdetail.getmenuid()==menu2.getId())
					{orderpublic.cartList.get(i).setNum(orderpublic.cartList.get(i).getNum()+1);
					ishave=false;
					break;}
				i+=1;
				
			}

//			放在了全局静态列表中
			if(ishave){
			OrderDetail orderdetail2=new OrderDetail();
			orderdetail2.setmenuid(menu2.getId());
			orderdetail2.setOrderid(orderpublic.publicorderid);
			orderdetail2.setNum(1);
			orderpublic.cartList.add(orderdetail2);
			ishave=false;
			}
			break;

		case R.id.listviewadd_but3:
			 v.startAnimation(anim_alpha);//启动动画  
//			要加边界判断
			 int c = (Integer) v.getTag(R.id.tv3);
			Menu menu3=menulist.get(3*c+2);
//			防止同一商品在购物车重复出现
			for(OrderDetail orderdetail:orderpublic.cartList){
				int i=0;
				if(orderdetail.getmenuid()==menu3.getId())
					{orderpublic.cartList.get(i).setNum(orderpublic.cartList.get(i).getNum()+1);
					ishave=false;
					break;}
				i+=1;
				
			}

//			放在了全局静态列表中
			if(ishave){
			OrderDetail orderdetail3=new OrderDetail();
			orderdetail3.setmenuid(menu3.getId());
			orderdetail3.setOrderid(orderpublic.publicorderid);
			orderdetail3.setNum(1);
			orderpublic.cartList.add(orderdetail3);
			ishave=false;
			}
			break;
		default:
			break;
		}
		
	}
	  
	  
}
