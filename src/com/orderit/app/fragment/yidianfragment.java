package com.orderit.app.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ListView;

import com.orderit.app.R;
import com.orderit.app.activity.MainActivity;
import com.orderit.app.model.Menu;
import com.orderit.app.model.OrderDetail;
import com.orderit.app.util.OrderDB;
import com.orderit.app.util.YidianAdapter;
import com.orderit.app.util.orderpublic;

public class yidianfragment extends Fragment{
	private ListView jiezhang_listview;
	private Bundle yidian_but;
	private YidianAdapter adapter;
	
	private OrderDB orderdb;
	private List<OrderDetail> orderdetaillist;
	private Button jiezhang_but;
	private int total;
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		orderdb=OrderDB.getInstance(getActivity());
		orderdetaillist=orderdb.loadOrderDetail(orderpublic.publicorderid);
		Log.d("Sess3","订单值"+orderpublic.publicorderid.toString());
		adapter=new YidianAdapter(activity,R.layout.yidian_item,orderdetaillist);
		
	}
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
View view=inflater.inflate(R.layout.yidian_fragment, container,false);
jiezhang_but=(Button)view.findViewById(R.id.jiezhang_but);
jiezhang_but.setOnClickListener(new OnClickListener() {
	public void onClick(View v) {
		counttotal();
		String msg="感谢您的惠顾 "+"本次共消费"+String.valueOf(total)+"元";
		showDialog(msg);
		
	}
});
jiezhang_listview=(ListView)view.findViewById(R.id.jiezhang_listview);
jiezhang_listview.setAdapter(adapter);
//if(orderdb.loadpaystatus(orderpublic.publicorderid)==1)

		return view;
	}

	private void counttotal(){
		total=0;
		for(int i=0;i<orderdetaillist.size();i++)
		{
			int num=orderdetaillist.get(i).getNum();
			Menu menu=orderdb.loadMenu(orderdetaillist.get(i).getmenuid());
			int price=Integer.parseInt(menu.getPrice());
			total+=price*num;
		}
		
	}
	
//	提示信息对话框
	private void showDialog(String msg){
		AlertDialog.Builder dialog=new AlertDialog.Builder(getActivity());
		dialog.setMessage(msg);
		dialog.setCancelable(false);
		dialog.setPositiveButton("确认结账", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {

//				点击结账后逻辑
				orderdb.updatepaystatus(orderpublic.publicorderid);
				orderdb.updateflag(orderpublic.publicorderid);
				try {
					Intent finishpay=new Intent(getActivity(),MainActivity.class);
					startActivity(finishpay);
					getActivity().finish();
				} catch (Exception e) {
					Log.d("Sess2", "yidian_fra跳转活动出错");
				}

				
				
			}
		});
		dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				
			}
		});
		dialog.show();
	}
	
}
