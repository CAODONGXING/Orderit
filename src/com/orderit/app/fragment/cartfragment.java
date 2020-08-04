package com.orderit.app.fragment;

import java.io.ObjectInputStream.GetField;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.orderit.app.R;
import com.orderit.app.model.Menu;
import com.orderit.app.model.OrderDetail;
import com.orderit.app.util.CartAdapter;
import com.orderit.app.util.OrderDB;

import com.orderit.app.util.orderpublic;

public class cartfragment extends Fragment{
	
	private CartAdapter cartadapter;
	private ListView cart_listview;
	private Button cartsubmit_but;
	private TextView cartprice;
	private OrderDB orderdb;
	private View view;
	private int total;
	
	public void onAttach(Activity activity) {

		super.onAttach(activity);
		
		cartadapter=new CartAdapter(activity,R.layout.cart_listview,orderpublic.cartList);
		orderdb=OrderDB.getInstance(getActivity());
		try {

			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
View view=inflater.inflate(R.layout.cart_layout, container,false);
//ʵʱ����۸�

cartsubmit_but=(Button)view.findViewById(R.id.cartsubmit_but);
cartprice=(TextView)view.findViewById(R.id.cartprice);
cartsubmit_but.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		
		// �ύ��������߼�
		counttotal();
		saveOrderPublic();
		orderpublic.cartList.clear();
		
		try {
			Toast.makeText(getActivity(), "�µ��ɹ�",Toast.LENGTH_SHORT).show();
			
		} catch (Exception e) {
			// TODO: handle exception
		}

		
		
		try {
//			�����ܼ�ui
			cartprice.setText("�ܼ� :"+String.valueOf(total)+"Ԫ");
		} catch (Exception e) {
			// TODO: handle exception
		}
	
	}
});

cart_listview=(ListView)view.findViewById(R.id.cart_listview);


cart_listview.setAdapter(cartadapter);

		return view;
		
	}
	private void saveOrderPublic(){
		for(int i=0;i<orderpublic.cartList.size();i++){
			OrderDetail orderdetail=orderpublic.cartList.get(i);
			orderdb.saveOrderDetail(orderdetail);
		}
		
		
	}
	
    public void onStart() {
        // TODO Auto-generated method stub
    	Log.d("Sess3", "��ƬonStart����");
    	new Thread(new MyThread()).start(); 
    	
        super.onStart();
    }
	
    public class MyThread implements Runnable {  
        @Override  
        public void run() {  
            // TODO Auto-generated method stub  
            while (true) {  
                try {  
                    Thread.sleep(500);// �߳���ͣ10�룬��λ����  
                    Message message = new Message();  
                    message.what = 1;  
                    handler.sendMessage(message);// ������Ϣ  
                } catch (InterruptedException e) {  
                    // TODO Auto-generated catch block  
                    e.printStackTrace();  
                }  
            }  
        }  
    }  
    
    Handler handler = new Handler() {  
        public void handleMessage(Message msg) {  
            // Ҫ��������  
        	counttotal();
        	cartprice.setText("�ܼ� :"+String.valueOf(total)+"Ԫ");
            super.handleMessage(msg);  
        }  
    };  
    
	private void counttotal(){
		total=0;
		for(int i=0;i<orderpublic.cartList.size();i++)
			{
//			����۸�
			OrderDetail orderdetail=orderpublic.cartList.get(i);
			int num=orderpublic.cartList.get(i).getNum();
			
			Menu menu=orderdb.loadMenu(orderdetail.getmenuid());

			int price=Integer.parseInt(menu.getPrice());
			Log.d("Sess2", "price="+price);
			Log.d("Sess2", "num="+num);
			Log.d("Sess2", "total="+total);
			total+=price*num;
			}
		
		
	}
	
	
	
	
//	
}
