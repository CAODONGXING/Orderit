package com.orderit.app.activity;

import com.orderit.app.R;
import com.orderit.app.model.User;
import com.orderit.app.util.MyDialog;
import com.orderit.app.util.OrderDB;
import com.orderit.app.util.orderpublic;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity{
private Button login_but;
private Button register_but;
private TextView register;
private OrderDB orderdb;
private User user_object;
private String username,psw;
private MyDialog mydialog; 
private EditText dial_edit1,dial_edit2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login_layout);
		orderdb=OrderDB.getInstance(this);
		login_but=(Button)findViewById(R.id.login_but);

		register_but=(Button)findViewById(R.id.register_but);
		login_but.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				shownewdialog("��¼");
			}});
		register_but.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				shownewdialog2("ע��");
			}});
		login_but.performClick();

	}
	
	
//	��ʾ��Ϣ�Ի���
	private void showDialog(String msg){
		AlertDialog.Builder dialog=new AlertDialog.Builder(this);
		dialog.setMessage(msg);
		dialog.setCancelable(false);
		dialog.setPositiveButton("�õ�", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				
			}
		});
		dialog.show();
	}

	
	private void shownewdialog(String name){
		
		mydialog = new MyDialog(this);  
        mydialog.setYesOnclickListener(name, new MyDialog.onYesOnclickListener() {  
            @Override  
            public void onYesClick() {  
            	if(validate2()){
            		orderpublic.publicuser=user_object.getAccount();
            		Log.d("Sess2", "publicuserֵ����="+orderpublic.publicuser);
            		Toast.makeText(LoginActivity.this,"��¼�ɹ�",Toast.LENGTH_LONG).show();
    				Intent intent=new Intent(LoginActivity.this, testactivity.class);
    				startActivity(intent);
    				finish();
            	};
//                Toast.makeText(LoginActivity.this,"�����--ȷ��--��ť",Toast.LENGTH_LONG).show();  
//                mydialog.dismiss();  
            }  
        });  
         
        mydialog.show();  
        dial_edit1=(EditText)mydialog.findViewById(R.id.dial_edit1);
        dial_edit2=(EditText)mydialog.findViewById(R.id.dial_edit2);
        
		
	}
	
private void shownewdialog2(String name){
		
		mydialog = new MyDialog(this);
//		WindowManager.LayoutParams lp=mydialog.getWindow().getAttributes();
//        lp.alpha=0.3f;
//        mydialog.getWindow().setAttributes(lp);
        mydialog.setYesOnclickListener(name, new MyDialog.onYesOnclickListener() {  
            @Override  
            public void onYesClick() {  
				if(validate()){
					if(orderdb.isExistUser(dial_edit1.getText().toString())){
						showDialog("���û����Ѵ��ڣ������");
						dial_edit1.setText("");
						dial_edit2.setText("");
						dial_edit1.requestFocus();
					}
					else {
					User user1=new User();
					user1.setAccount(dial_edit1.getText().toString());
					user1.setPassword(dial_edit2.getText().toString());
					Log.d("Sess2", "ע��usernameֵ="+dial_edit1.getText().toString());
					Log.d("Sess2", "ע��pswֵ="+dial_edit2.getText().toString());
					orderdb.saveUser(user1);
					showDialog("�������˺ųɹ�");}}
            }  
        });  
         
        mydialog.show();  
        dial_edit1=(EditText)mydialog.findViewById(R.id.dial_edit1);
        dial_edit2=(EditText)mydialog.findViewById(R.id.dial_edit2);
        
		
	}
//	�ǿ���֤,�������ݿ���֤2
	private boolean validate2(){
		username=dial_edit1.getText().toString();
		psw=dial_edit2.getText().toString();
		Log.d("Sess2", "usernameֵ="+username);
		Log.d("Sess2", "pswֵ="+psw);
		if(username.equals("")){
			showDialog("����д���ţ�");	
			return false;
		}
		if(psw.equals("")){
			showDialog("����д���룡");
			return false;
		}

		user_object=orderdb.loadUser(username);
		Log.d("Sess2", "���ݿ��û�Ϊ="+user_object.getAccount());
		Log.d("Sess2", "���ݿ�����Ϊ="+user_object.getPassword());
	       if(user_object.getPassword()!=""&& psw.equals(user_object.getPassword())){
	    	   
	    	   return true;
	    	   
	       }
	       
	       else {showDialog("�������");
	       return false;
	       }
		
	}
//	---����---
//	�ǿ���֤
	 
	private boolean validate(){
		String username=dial_edit1.getText().toString();
		String psw=dial_edit2.getText().toString();
		if(username.equals("")){
			showDialog("����д���ţ�");	
			return false;
		}
		if(psw.equals("")){
			showDialog("����д���룡");
			return false;
		}
            return true;

		
	}
//	---����---
}
