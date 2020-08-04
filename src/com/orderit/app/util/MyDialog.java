package com.orderit.app.util;

import com.orderit.app.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class MyDialog extends Dialog{
	private Button yes;//ȷ����ť
	 private String yesStr;
	 private onYesOnclickListener yesOnclickListener;//ȷ����ť������˵ļ�����  
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
	        //���հ״�����ȡ������  
	        setCanceledOnTouchOutside(true);  
	  
	        //��ʼ������ؼ�  
	        initView();  
	        //��ʼ����������  
	        initData();  
	        //��ʼ������ؼ����¼�  
	        initEvent();  
	          
	    }   
	    
	    private void initEvent() {  
	        //����ȷ����ť�������������ṩ����  
	        yes.setOnClickListener(new View.OnClickListener() {  
	            @Override  
	            public void onClick(View v) {  
	                if (yesOnclickListener != null) {  
	                    yesOnclickListener.onYesClick();  
	                }  
	            }  
	        });  }
	    
	    private void initData() {  
	       
	        //������ð�ť������  
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
