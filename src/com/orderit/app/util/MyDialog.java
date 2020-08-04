package com.orderit.app.util;

import com.orderit.app.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class MyDialog extends Dialog{
	private Button yes;//确定按钮
	 private String yesStr;
	 private onYesOnclickListener yesOnclickListener;//确定按钮被点击了的监听器  
	 private View customView;
	    public void setYesOnclickListener(String str, onYesOnclickListener onYesOnclickListener) {  
	        if (str != null) {  
	            yesStr = str;  
	        }  
	        this.yesOnclickListener = onYesOnclickListener;  
	    }  
	    public MyDialog(Context context) {  
	        super(context, R.style.MyDialog);  
	        LayoutInflater inflater= LayoutInflater.from(context);  
	        this.customView = inflater.inflate(R.layout.mydialog_layout, null);
	    }  
	    
	    protected void onCreate(Bundle savedInstanceState) {  
	        super.onCreate(savedInstanceState);  
	        setContentView(R.layout.mydialog_layout);  
	        //按空白处不能取消动画  
	        setCanceledOnTouchOutside(true);  
	  
	        //初始化界面控件  
	        initView();  
	        //初始化界面数据  
	        initData();  
	        //初始化界面控件的事件  
	        initEvent();  
	          
	    }   
	    
	    private void initEvent() {  
	        //设置确定按钮被点击后，向外界提供监听  
	        yes.setOnClickListener(new View.OnClickListener() {  
	            @Override  
	            public void onClick(View v) {  
	                if (yesOnclickListener != null) {  
	                    yesOnclickListener.onYesClick();  
	                }  
	            }  
	        });  }
	    
	    private void initData() {  
	       
	        //如果设置按钮的文字  
	        if (yesStr != null) {  
	            yes.setText(yesStr);  
	        }       
	    } 
	    
	    private void initView() {  
	        yes = (Button) findViewById(R.id.yes);   
	    } 
	    public interface onYesOnclickListener {  
	        public void onYesClick();  
	    }  
	    public View getCustomView() {  
	    	return customView;  
	    	}  
}
