package com.orderit.app.activity;

import java.util.List;

import com.orderit.app.R;
import com.orderit.app.model.Menu;
import com.orderit.app.util.OrderDB;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

@SuppressLint("NewApi") public class AddDish extends Activity implements OnClickListener {
	private Button add_button;
	private Button add_confirm;
	private ImageView add_Image;
	private EditText add_caiming;
	private Spinner add_leibie;
	private EditText add_jiage;
	private OrderDB orderdb;
	private List<String> data_list;
	private ArrayAdapter<String> arr_adapter;
	private static final int IMAGE = 1;
	private String imagePath;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.adddish_layout);
		orderdb=OrderDB.getInstance(this);
		
		add_button=(Button)findViewById(R.id.add_button);
		add_confirm=(Button)findViewById(R.id.add_confirm);
		add_Image=(ImageView)findViewById(R.id.add_Image);
		add_caiming=(EditText)findViewById(R.id.add_caiming);
		add_leibie=(Spinner)findViewById(R.id.add_leibie);
		add_jiage=(EditText)findViewById(R.id.add_jiage);
		add_button.setOnClickListener(this);
		add_confirm.setOnClickListener(this);
		add_leibie=(Spinner)findViewById(R.id.add_leibie);
		data_list=orderdb.loadMenuTypes();
		//适配器
        arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        add_leibie.setAdapter(arr_adapter);
        
	}
	
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.add_button:
	        Intent intent = new Intent(Intent.ACTION_PICK,
	                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
	        startActivityForResult(intent, IMAGE);
			Log.d("Sess3", "图片按键成功点击");
			break;
		case R.id.add_confirm:
			if(TextUtils.isEmpty(add_caiming.getText().toString()))showDialog("请输入菜名");
			else if(TextUtils.isEmpty(add_jiage.getText().toString()))showDialog("请输入价格");
			else if(TextUtils.isEmpty(imagePath))showDialog("请选择图片");
			else{
			String name=add_caiming.getText().toString();
			String price=add_jiage.getText().toString();
			String type=add_leibie.getSelectedItem().toString();
			String picbitmap=imagePath;
			Log.d("Sess3",name+price+type+" "+picbitmap );
			Menu menu1=new Menu();
			menu1.setName(name);
			menu1.setPicbitmap(picbitmap);
			menu1.setType(type);
			menu1.setPrice(price);
			orderdb.saveMenu(menu1);
			Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();}
			break;
			
	}
}
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取图片路径
        if (requestCode == IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            imagePath = c.getString(columnIndex);
            showImage(imagePath);
            c.close();
        }
    }
    
    //加载图片
    private void showImage(String imaePath){
        Bitmap bm = BitmapFactory.decodeFile(imaePath);
        add_Image.setImageBitmap(bm);

    
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
}
