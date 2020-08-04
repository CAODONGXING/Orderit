package com.orderit.app.activity;

import com.orderit.app.R;
import com.orderit.app.model.User;
import com.orderit.app.util.OrderDB;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends Activity {
	private OrderDB orderdb;
	private Button register_but;
	private EditText register_user,register_password;
	private TextView login;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.register_layout);
		orderdb=OrderDB.getInstance(this);
		register_but=(Button)findViewById(R.id.register_but);
		register_user=(EditText)findViewById(R.id.register_user);
		register_password=(EditText)findViewById(R.id.register_password);
		login=(TextView)findViewById(R.id.login);
		register_but.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(validate()){
					User user1=new User();
					user1.setAccount(register_user.getText().toString());
					user1.setPassword(register_password.getText().toString());
					orderdb.saveUser(user1);
					showDialog("�������˺ųɹ�");
					   new Thread() {
				            @Override
				            public void run() {
				                super.run();
				                try {
									Thread.sleep(3000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}//����3��
				                /**
				                 * Ҫִ�еĲ���
				                 */
				                finish();
				                         }
				        }.start();
				}
				
				
			}});
			
		login.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent=new Intent(RegisterActivity.this, LoginActivity.class);
				startActivity(intent);
				
			}});
		
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
	
//	�ǿ���֤
	 
	private boolean validate(){
		String username=register_user.getText().toString();
		String psw=register_password.getText().toString();
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
