package com.orderit.app.fragment;

import java.util.ArrayList;
import java.util.List;

import com.orderit.app.R;
import com.orderit.app.model.Menu;
import com.orderit.app.model.Order;
import com.orderit.app.model.OrderDetail;
import com.orderit.app.util.LishiAdapter;
import com.orderit.app.util.OrderDB;
import com.orderit.app.util.orderpublic;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class lishifrafment extends Fragment {
	private ListView lishi_listview;
	private LishiAdapter adapter;
//	private List<String> lishilist=new ArrayList<String>();
	private OrderDB orderdb;
	private List<OrderDetail> orderdetaillist=new ArrayList<OrderDetail>();
	private List<Order> orderlist;
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		orderdb=OrderDB.getInstance(getActivity());
		setListDate();
		adapter=new LishiAdapter(activity,R.layout.yidian_item,orderdetaillist);
		
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
View view=inflater.inflate(R.layout.lishi_fragment, container,false);
lishi_listview=(ListView)view.findViewById(R.id.lishi_listview);
lishi_listview.setAdapter(adapter);
//setListDate();
	return view;
	}
	private void setListDate(){
		orderlist=orderdb.loadOrder(orderpublic.publicuser);
		for(Order order:orderlist){
			Log.d("Sess2", "空指针问题Orderid="+order.getOrderid());
			if(orderdb.loadOrderDetail(order.getOrderid()).size()>0)
			try {
				orderdetaillist.addAll(orderdb.loadOrderDetail(order.getOrderid()));	
				Log.d("Sess2", "抛出空指针异0");
			} catch (Exception e) {
				Log.d("Sess2", "抛出空指针异常1");
			}
			else Log.d("Sess2", "抛出空指针异常3");
		}
//		for(OrderDetail orderdetail:orderdetaillist){
//			Log.d("Sess2", "空指针问题Orderid="+String.valueOf(orderdetail.getmenuid()));
//			try {Menu menu=orderdb.loadMenu(orderdetail.getmenuid());
//			lishilist.add(orderdetail.getOrderid()+"                     "+menu.getName()+"                                             "+orderdetail.getNum());
//				
//			} catch (Exception e) {
//				Log.d("Sess2", "抛出空指针异常4");
//			}
//			
//		}
		
	}
	
}
