package com.orderit.app.fragment;

import java.util.ArrayList;
import java.util.List;

import com.orderit.app.R;
import com.orderit.app.model.Order;
import com.orderit.app.model.Table;
import com.orderit.app.util.OrderDB;
import com.orderit.app.util.TableAdapter;
import com.orderit.app.util.orderpublic;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class huantaifragment extends Fragment{
	private Button huantai_button;
	private Button bingtai_button;
	private ListView huantai_listview;
	private EditText yuantai_edit;
	private EditText xiantai_edit;
	private OrderDB orderdb;
	private TableAdapter tableadapter,tableadapter2,tableadapter3;
	private List<Table> tablelist=new ArrayList<Table>();
	String yuantai,xiantai;
	
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		getTableList();
		tableadapter=new TableAdapter(activity,R.layout.table_listview,tablelist);
	}
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.huantai_fragment, container,false);
		huantai_button=(Button)view.findViewById(R.id.huantai_button);
		bingtai_button=(Button)view.findViewById(R.id.bingtai_button);
		yuantai_edit=(EditText)view.findViewById(R.id.yuantai_edit);
		xiantai_edit=(EditText)view.findViewById(R.id.xiantai_edit);

		bingtai_button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				yuantai=yuantai_edit.getText().toString();
				xiantai=xiantai_edit.getText().toString();
				AlertDialog.Builder dialog=new AlertDialog.Builder(getActivity());
				dialog.setTitle("并台");
				dialog.setMessage("原台号"+yuantai+"台"+"合并到"+xiantai+"台");
				dialog.setCancelable(false);
				dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						orderdb.updateyuantai(Integer.valueOf(yuantai));
						orderdb.updatexintai(Integer.valueOf(xiantai));
//						orderdb.updatetable(Integer.valueOf(yuantai),Integer.valueOf(xiantai));
						Order order1=orderdb.loadOrderid(Integer.valueOf(yuantai),orderpublic.publicuser);
						Order order2=orderdb.loadOrderid(Integer.valueOf(xiantai),orderpublic.publicuser);
						Log.d("Sess3", "原订单号="+order1.getOrderid()+"新订单号="+order2.getOrderid());
						orderdb.updateDetailid(order1.getOrderid(), order2.getOrderid());
						orderdb.updatepaystatus(order1.getOrderid());
						
						tablelist.clear();
						tablelist.addAll(orderdb.loadTableList());
						tableadapter3=new TableAdapter(getActivity(),R.layout.table_listview,tablelist);
						huantai_listview.setAdapter(tableadapter3);
						yuantai_edit.setText("");
						xiantai_edit.setText("");
					}
				});
				dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						
					}
				});
				dialog.show();
			
				
			}});
		huantai_listview=(ListView)view.findViewById(R.id.huantai_listview);
		huantai_listview.setAdapter(tableadapter);
		huantai_button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				yuantai=yuantai_edit.getText().toString();
				xiantai=xiantai_edit.getText().toString();
				AlertDialog.Builder dialog=new AlertDialog.Builder(getActivity());
				dialog.setTitle("换台");
				dialog.setMessage("原台号"+yuantai+"台"+"改成"+xiantai+"台");
				dialog.setCancelable(false);
				dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						orderdb.updateyuantai(Integer.valueOf(yuantai));
						orderdb.updatexintai(Integer.valueOf(xiantai));
						orderdb.updatetable(Integer.valueOf(yuantai), Integer.valueOf(xiantai));
						tablelist.clear();
						tablelist.addAll(orderdb.loadTableList());
						tableadapter2=new TableAdapter(getActivity(),R.layout.table_listview,tablelist);
						huantai_listview.setAdapter(tableadapter2);
						yuantai_edit.setText("");
						xiantai_edit.setText("");
					}
				});
				dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						
					}
				});
				dialog.show();
			}});
		
		return view;
		}
	private void getTableList(){
		orderdb=OrderDB.getInstance(getActivity());
		tablelist=orderdb.loadTableList();
	}
}
