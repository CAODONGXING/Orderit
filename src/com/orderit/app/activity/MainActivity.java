package com.orderit.app.activity;



import com.orderit.app.R;
import com.orderit.app.util.orderpublic;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Boolean isorder=false;
	private EditText zhuohao_edit;
	private EditText renshu_edit;
	private String zhuohao,renshu;
	private TextView taishu_left,renshu_left;
	private Button fuwuyuan_but,diancai_but;
	private ImageView user_logo1;
	private TextView user_logo2,user_logo3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.first_layout);
		user_logo1=(ImageView)findViewById(R.id.user_logo1);
		user_logo2=(TextView)findViewById(R.id.user_logo2);
		user_logo3=(TextView)findViewById(R.id.user_logo3);
		fuwuyuan_but=(Button)findViewById(R.id.fuwuyuan_but);
		diancai_but=(Button)findViewById(R.id.diancai_but);
		
		fuwuyuan_but.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v) {
				if(TextUtils.isEmpty(orderpublic.publicuser)){
					Intent intent=new Intent(MainActivity.this,LoginForAdd.class);
					startActivity(intent);
				}
				else{
				Intent intent=new Intent(MainActivity.this,AddDish.class);
				startActivity(intent);}
			}
			
			
		});
		
		diancai_but.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {				
				if(TextUtils.isEmpty(orderpublic.publicuser)){
				Intent intent=new Intent(MainActivity.this,LoginActivity.class);
				startActivity(intent);
			}
			else{
			Intent intent=new Intent(MainActivity.this,testactivity.class);
			startActivity(intent);
			}
		}	
		});
		
		user_logo3.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				showDialog("确认注销当前账号？");
				
         }});
		
	}
	private void inituser(){
		if(!TextUtils.isEmpty(orderpublic.publicuser)){
			user_logo1.setImageResource(R.drawable.user_logo2);
			user_logo2.setText("工号: "+orderpublic.publicuser);
			user_logo3.setVisibility(View.VISIBLE);
		}	
	}
	
//	提示信息对话框
	private void showDialog(String msg){
		AlertDialog.Builder dialog=new AlertDialog.Builder(this);
		dialog.setMessage(msg);
		dialog.setCancelable(false);
		dialog.setPositiveButton("注销", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				orderpublic.publicuser="";
				Toast.makeText(MainActivity.this, "注销成功", Toast.LENGTH_SHORT).show();
				user_logo1.setImageResource(R.drawable.user);
				user_logo2.setText("未登陆");
				user_logo3.setVisibility(View.INVISIBLE);
			}
		});
		dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				
			}
		});
		dialog.show();
	}
	@Override
	protected void onStart() {
		super.onStart();
		inituser();
	}

}
