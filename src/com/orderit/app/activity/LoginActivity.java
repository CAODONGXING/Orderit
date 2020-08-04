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
				shownewdialog("登录");
			}});
		register_but.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				shownewdialog2("注册");
			}});
		login_but.performClick();

	}
	
	
//	提示信息对话框
	private void showDialog(String msg){
		AlertDialog.Builder dialog=new AlertDialog.Builder(this);
		dialog.setMessage(msg);
		dialog.setCancelable(false);
		dialog.setPositiveButton("好的", new DialogInterface.OnClickListener() {
			
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
            		Log.d("Sess2", "publicuser值测试="+orderpublic.publicuser);
            		Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_LONG).show();
    				Intent intent=new Intent(LoginActivity.this, testactivity.class);
    				startActivity(intent);
    				finish();
            	};
//                Toast.makeText(LoginActivity.this,"点击了--确定--按钮",Toast.LENGTH_LONG).show();  
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
						showDialog("该用户名已存在，请更换");
						dial_edit1.setText("");
						dial_edit2.setText("");
						dial_edit1.requestFocus();
					}
					else {
					User user1=new User();
					user1.setAccount(dial_edit1.getText().toString());
					user1.setPassword(dial_edit2.getText().toString());
					Log.d("Sess2", "注册username值="+dial_edit1.getText().toString());
					Log.d("Sess2", "注册psw值="+dial_edit2.getText().toString());
					orderdb.saveUser(user1);
					showDialog("创建新账号成功");}}
            }  
        });  
         
        mydialog.show();  
        dial_edit1=(EditText)mydialog.findViewById(R.id.dial_edit1);
        dial_edit2=(EditText)mydialog.findViewById(R.id.dial_edit2);
        
		
	}
//	非空验证,密码数据库验证2
	private boolean validate2(){
		username=dial_edit1.getText().toString();
		psw=dial_edit2.getText().toString();
		Log.d("Sess2", "username值="+username);
		Log.d("Sess2", "psw值="+psw);
		if(username.equals("")){
			showDialog("请填写工号！");	
			return false;
		}
		if(psw.equals("")){
			showDialog("请填写密码！");
			return false;
		}

		user_object=orderdb.loadUser(username);
		Log.d("Sess2", "数据库用户为="+user_object.getAccount());
		Log.d("Sess2", "数据库密码为="+user_object.getPassword());
	       if(user_object.getPassword()!=""&& psw.equals(user_object.getPassword())){
	    	   
	    	   return true;
	    	   
	       }
	       
	       else {showDialog("密码错误！");
	       return false;
	       }
		
	}
//	---结束---
//	非空验证
	 
	private boolean validate(){
		String username=dial_edit1.getText().toString();
		String psw=dial_edit2.getText().toString();
		if(username.equals("")){
			showDialog("请填写工号！");	
			return false;
		}
		if(psw.equals("")){
			showDialog("请填写密码！");
			return false;
		}
            return true;

		
	}
//	---结束---
}
