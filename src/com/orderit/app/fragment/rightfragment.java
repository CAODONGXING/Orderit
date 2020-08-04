package com.orderit.app.fragment;

import java.util.ArrayList;
import java.util.List;

import com.orderit.app.R;
import com.orderit.app.model.Menu;
import com.orderit.app.model.OrderDetail;
import com.orderit.app.util.MenuAdapter;
import com.orderit.app.util.OrderDB;
import com.orderit.app.util.orderpublic;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class rightfragment extends Fragment implements OnClickListener,OnItemClickListener{
	private List<Menu> menuList;
	private MenuAdapter menuadapter;
	private ListView listview_fragment;
	private Button liangcai;
	private Button recai;
	private Button tanglei;
	private Button yinliao;
	private OrderDB orderdb;
	
	@Override
	public void onAttach(Activity activity) {

		super.onAttach(activity);
		orderdb=OrderDB.getInstance(getActivity());
//		initMenu();
		menuList=orderdb.loadMenus("热菜");
		menuadapter=new MenuAdapter(activity,R.layout.listviewfragment_item,menuList);
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		
		
View view=inflater.inflate(R.layout.main_rightfra, container,false);
//按键监听
liangcai=(Button)view.findViewById(R.id.liangcai_but);
recai=(Button)view.findViewById(R.id.recai_but);
tanglei=(Button)view.findViewById(R.id.tanglei_but);
yinliao=(Button)view.findViewById(R.id.drink_but);
recai.setOnClickListener(this);
liangcai.setOnClickListener(this);
tanglei.setOnClickListener(this);
yinliao.setOnClickListener(this);



listview_fragment=(ListView)view.findViewById(R.id.listview_fragment);


	listview_fragment.setAdapter(menuadapter);

//listview 点击时间代码，并将数据加入list数值中实现购物车逻辑

return view;
		
		
		
	}

	
public void onClick(View v){
	switch (v.getId()) {
	case R.id.liangcai_but:
		liangcai.setTextColor(Color.parseColor("#ff881d"));
		recai.setTextColor(Color.parseColor("#ffffff"));
		tanglei.setTextColor(Color.parseColor("#ffffff"));
		yinliao.setTextColor(Color.parseColor("#ffffff"));
		Log.d("Sess2", "凉菜按键成功点击");
		menuList.clear();
		
		menuList=orderdb.loadMenus("凉菜");
		Log.d("Sess2","数据数组值="+menuList.size());
		menuadapter=new MenuAdapter(getActivity(),R.layout.listviewfragment_item,menuList);
		listview_fragment.setAdapter(menuadapter);
//		menuadapter.notifyDataSetChanged();
		break;
		
	case R.id.recai_but:
		liangcai.setTextColor(Color.parseColor("#ffffff"));
		recai.setTextColor(Color.parseColor("#ff881d"));
		tanglei.setTextColor(Color.parseColor("#ffffff"));
		yinliao.setTextColor(Color.parseColor("#ffffff"));
		menuList.clear();
		menuList=orderdb.loadMenus("热菜");
		menuadapter=new MenuAdapter(getActivity(),R.layout.listviewfragment_item,menuList);
		listview_fragment.setAdapter(menuadapter);
		break;
		
	case R.id.tanglei_but:

		liangcai.setTextColor(Color.parseColor("#ffffff"));
		recai.setTextColor(Color.parseColor("#ffffff"));
		tanglei.setTextColor(Color.parseColor("#ff881d"));
		yinliao.setTextColor(Color.parseColor("#ffffff"));
//		orderdb.isrunDB();
		menuList.clear();
		menuList=orderdb.loadMenus("汤类");
		menuadapter=new MenuAdapter(getActivity(),R.layout.listviewfragment_item,menuList);
		listview_fragment.setAdapter(menuadapter);
		break;
		
	case R.id.drink_but:
		liangcai.setTextColor(Color.parseColor("#ffffff"));
		recai.setTextColor(Color.parseColor("#ffffff"));
		tanglei.setTextColor(Color.parseColor("#ffffff"));
		yinliao.setTextColor(Color.parseColor("#ff881d"));
		menuList.clear();
		menuList=orderdb.loadMenus("饮料");
		menuadapter=new MenuAdapter(getActivity(),R.layout.listviewfragment_item,menuList);
		listview_fragment.setAdapter(menuadapter);
		break;

	default:
		break;
	}
	
	
}

@Override
public void onItemClick(AdapterView<?> parent, View view, int position,
		long id) {
	

	
	
	
}



	
}
