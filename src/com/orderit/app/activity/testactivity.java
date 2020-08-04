package com.orderit.app.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.orderit.app.R;
import com.orderit.app.fragment.cartfragment;
import com.orderit.app.fragment.huantaifragment;
import com.orderit.app.fragment.leftfragment;
import com.orderit.app.fragment.lishifrafment;
import com.orderit.app.fragment.rightfragment;
import com.orderit.app.fragment.servicefragment;
import com.orderit.app.fragment.yidianfragment;
import com.orderit.app.model.Order;
import com.orderit.app.model.Table;
import com.orderit.app.util.MyDialog;
import com.orderit.app.util.OrderDB;
import com.orderit.app.util.SecondMyDialog;
import com.orderit.app.util.orderpublic;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;


import android.os.Bundle;


import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class testactivity extends Activity implements OnClickListener{
	private Button caiping_left;
	private Button jiezhang_left;
	private Button yidian_left;
	private Button lishi_left;
	private Button huantai_left;
	private TextView fuwuyuan_left,dingdanhao_left;
	
	private String zhuohao,renshu;
	private EditText zhuohao_edit;
	private EditText renshu_edit;
	private TextView taishu_left,renshu_left;
	
	private AlertDialog.Builder dialog;
	private OrderDB orderdb;
	private SecondMyDialog secondmydialog; 
	private EditText seconddial_edit1,seconddial_edit2;
	private ImageView setting;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_layout);
		orderdb=OrderDB.getInstance(this);
		caiping_left=(Button)findViewById(R.id.caiping_left);
		jiezhang_left=(Button)findViewById(R.id.jiezhang_left);
		yidian_left=(Button)findViewById(R.id.yidian_left);
		lishi_left=(Button)findViewById(R.id.lishi_left);
		huantai_left=(Button)findViewById(R.id.huantai_left);
		fuwuyuan_left=(TextView)findViewById(R.id.fuwuyuan_left);
		dingdanhao_left=(TextView)findViewById(R.id.dingdanhao_left);
		setting=(ImageView)findViewById(R.id.setting);
		fuwuyuan_left.setOnClickListener(this);
		caiping_left.setOnClickListener(this);
		yidian_left.setOnClickListener(this);
		jiezhang_left.setOnClickListener(this);
		lishi_left.setOnClickListener(this);
		huantai_left.setOnClickListener(this);
		setting.setOnClickListener(this);

		taishu_left=(TextView)findViewById(R.id.taishu_left);
		renshu_left=(TextView)findViewById(R.id.renshu_left);
		orderdb=OrderDB.getInstance(this);
		shownewdialog("��̨");
//		setorderdetail();
//		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
	}

	@Override
	public void onClick(View v) {
		FragmentManager fragmentManager1=getFragmentManager();
	    FragmentTransaction transation1=fragmentManager1.beginTransaction();
		switch(v.getId()){
		case R.id.caiping_left:
			rightfragment caipin=new rightfragment();
			caiping_left.setTextColor(Color.parseColor("#ff881d"));
			jiezhang_left.setTextColor(Color.parseColor("#ffffff"));
			yidian_left.setTextColor(Color.parseColor("#ffffff"));
			lishi_left.setTextColor(Color.parseColor("#ffffff"));
			huantai_left.setTextColor(Color.parseColor("#ffffff"));
		    transation1.replace(R.id.right_layout, caipin);
		    transation1.commit();
			break;
			
		case R.id.jiezhang_left:

			yidianfragment yidian=new yidianfragment();
//			
			caiping_left.setTextColor(Color.parseColor("#ffffff"));
			jiezhang_left.setTextColor(Color.parseColor("#ff881d"));
			yidian_left.setTextColor(Color.parseColor("#ffffff"));
			lishi_left.setTextColor(Color.parseColor("#ffffff"));
			huantai_left.setTextColor(Color.parseColor("#ffffff"));
//			
		    transation1.replace(R.id.right_layout, yidian);
		    transation1.commit();

			break;
			
		case R.id.yidian_left:
			cartfragment cart=new cartfragment();
			caiping_left.setTextColor(Color.parseColor("#ffffff"));
			jiezhang_left.setTextColor(Color.parseColor("#ffffff"));
			yidian_left.setTextColor(Color.parseColor("#ff881d"));
			lishi_left.setTextColor(Color.parseColor("#ffffff"));
			huantai_left.setTextColor(Color.parseColor("#ffffff"));
//			Drawable drawableLeft = getResources().getDrawable(  
//			        R.drawable.cart_2);    
//			yidian_left.setCompoundDrawablesWithIntrinsicBounds(drawableLeft,  
//			        null, null, null); 
		    transation1.replace(R.id.right_layout, cart);
		    transation1.commit();
			break;
			
		case R.id.fuwuyuan_left:
			servicefragment service=new servicefragment();
			
		    transation1.replace(R.id.right_layout, service);
		    transation1.commit();
			break;
			
		case R.id.lishi_left:
			lishifrafment lishi=new lishifrafment();
			caiping_left.setTextColor(Color.parseColor("#ffffff"));
			jiezhang_left.setTextColor(Color.parseColor("#ffffff"));
			yidian_left.setTextColor(Color.parseColor("#ffffff"));
			lishi_left.setTextColor(Color.parseColor("#ff881d"));
			huantai_left.setTextColor(Color.parseColor("#ffffff"));
		    transation1.replace(R.id.right_layout, lishi);
		    transation1.commit();
			break;
			
		case R.id.huantai_left:
			huantaifragment huantai=new huantaifragment();
			caiping_left.setTextColor(Color.parseColor("#ffffff"));
			jiezhang_left.setTextColor(Color.parseColor("#ffffff"));
			yidian_left.setTextColor(Color.parseColor("#ffffff"));
			lishi_left.setTextColor(Color.parseColor("#ffffff"));
			huantai_left.setTextColor(Color.parseColor("#ff881d"));
		    transation1.replace(R.id.right_layout, huantai);
		    transation1.commit();
			break;
			
		case R.id.setting:

			break;
			default:
				break;
		
		}
		
	}

	
	private String makeorderid(String zhuohao,String renshu){
	   Long millisecond=System.currentTimeMillis();
	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
	        Date date = new Date(millisecond);
	        String dateStr = simpleDateFormat.format(date);
	        String makedorderid=dateStr+zhuohao+renshu;
	        return makedorderid;
	}
	
	private int istableopen(String zhuohao){
		Table table=orderdb.loadTable(Integer.valueOf(zhuohao));
		return table.getFlag();
	}
	
//	��ʾ��Ϣ�Ի���
	private void showDialog2(String msg){
		AlertDialog.Builder dialog=new AlertDialog.Builder(this);
		dialog.setMessage(msg);
		dialog.setCancelable(false);
		dialog.setPositiveButton("ע��", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				orderpublic.publicuser="";
				Toast.makeText(testactivity.this, "ע���ɹ�", Toast.LENGTH_SHORT).show();
				Intent intent=new Intent(testactivity.this,MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
		dialog.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {}
		});
		dialog.show();
	}
	
//	����һ����ʾ��Ϣ�ĶԻ���
	private void showDialog(String msg){
		AlertDialog.Builder builder=new AlertDialog.Builder(this);
		builder.setMessage(msg);
		builder.setCancelable(false);
		builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				

			}
		});
		builder.show();
		
	}
	
	private void shownewdialog(String name){
		
		secondmydialog = new SecondMyDialog(this); 
		
		secondmydialog.setYesOnclickListener(name, new SecondMyDialog.onYesOnclickListener() {  
            @Override  
            public void onYesClick() {  
//--��ʼ

				if(validate()){
	
				if(istableopen(zhuohao)==1){
					Log.d("Sess2", "1");
					List<Order> list=new ArrayList<Order>();
					list=orderdb.loadOrder(Integer.valueOf(zhuohao), orderpublic.publicuser, 1);
					for(Order order:list){
						Log.d("Sess2", "table="+String.valueOf(order.getTableid()));
					}
					if(list.size()>0){
//						���ö����ŷ���ȫ�ֱ���
						orderpublic.publicorderid=list.get(0).getOrderid();
						Log.d("Sess2", "2");
						Log.d("Sess2", "���ݿ��ж�ȡ���ɵ�="+list.get(0).getOrderid());
						secondmydialog.dismiss();
						caiping_left.performClick();
					}
					else{
						showDialog("�������ѱ�ռ��");
						
						Log.d("Sess2", "3");
						}
				}
				else {
					
					Log.d("Sess2", "4");
//					���ɶ�����
					String makedorderid=makeorderid(zhuohao,renshu);
					Log.d("Sess2", "��ʱ�����ɵ�="+makedorderid);
					orderpublic.publicorderid=makedorderid;
					Order order=new Order();
					order.setAccount(orderpublic.publicuser);
					order.setOrderid(makedorderid);
					order.setPayStatus(1);
					order.setPersonNum(Integer.valueOf(renshu));
					order.setTableid(Integer.valueOf(zhuohao));
//					�涩��
					orderdb.saveOrder(order);
					Log.d("Sess3", "��⿪̨"+zhuohao);
					if(orderdb.isExistTable(Integer.valueOf(zhuohao))){
//						��������
						orderdb.updatexintai(Integer.valueOf(zhuohao));
						Log.d("Sess3", "̨���Ѵ���"+zhuohao);
					}
					else{
//					���ݿ����½�һ��������Ϣ
					Table table=new Table();
					table.setTablenum(Integer.valueOf(zhuohao));
					table.setFlag(1);
					orderdb.saveTable(table);
					}
					secondmydialog.dismiss(); 
					caiping_left.performClick();
					
				}
//				����ϢUI
				renshu_left.setText("̨ ��:   "+zhuohao);
				taishu_left.setText("�� ��:   "+renshu);
				fuwuyuan_left.setText("����Ա���ţ�"+orderpublic.publicuser);
				dingdanhao_left.setText("�����ţ�"+orderpublic.publicorderid);
				 	
//--����       	
            	
            	
//                Toast.makeText(LoginActivity.this,"�����--ȷ��--��ť",Toast.LENGTH_LONG).show();  
//                mydialog.dismiss();  
            }}  
        });  
         
		secondmydialog.show();  
		secondmydialog.setCanceledOnTouchOutside(false);
        seconddial_edit1=(EditText)secondmydialog.findViewById(R.id.seconddial_edit1);
        seconddial_edit2=(EditText)secondmydialog.findViewById(R.id.seconddial_edit2);
        
		
	}
	private boolean validate(){
		zhuohao =seconddial_edit1.getText().toString();
		renshu =seconddial_edit2.getText().toString();
		Log.d("", "�����̨��="+zhuohao);
		if(TextUtils.isEmpty(zhuohao)){
			showDialog("����д̨�ţ�");	
			return false;
		}
		if(TextUtils.isEmpty(renshu)){
			showDialog("����д������");
			return false;
		}
            return true;

		
	}
}
