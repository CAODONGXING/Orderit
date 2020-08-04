package com.orderit.app.fragment;

import com.orderit.app.R;
import com.orderit.app.model.Menu;
import com.orderit.app.util.OrderDB;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class servicefragment extends Fragment implements OnClickListener{
	private OrderDB orderdb;
	private Button bingtai_but;
	private Button zhuantai_but;
	private Button dingdan_but;
	private ImageView image;
	 private static final int IMAGE = 1;
	public void onAttach(Activity activity) {

		super.onAttach(activity);
		
	}
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		orderdb=OrderDB.getInstance(getActivity());
		
View view=inflater.inflate(R.layout.service_layout, container,false);
bingtai_but=(Button)view.findViewById(R.id.bingtai_but);
zhuantai_but=(Button)view.findViewById(R.id.zhuantai_but);
dingdan_but=(Button)view.findViewById(R.id.dingdan_but);
image=(ImageView)view.findViewById(R.id.image);
bingtai_but.setOnClickListener(this);
zhuantai_but.setOnClickListener(this);
dingdan_but.setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bingtai_but:
			initmenu();
Log.d("Sess2", "��̨�����ɹ����");
			break;
		case R.id.dingdan_but:
	        Intent intent = new Intent(Intent.ACTION_PICK,
	                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
	        startActivityForResult(intent, IMAGE);
			Log.d("Sess3", "ͼƬ�����ɹ����");
			break;
			
	}
		
	}
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //��ȡͼƬ·��
        if (requestCode == IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getActivity().getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            showImage(imagePath);
            c.close();
        }
    }
    
    //����ͼƬ
    private void showImage(String imaePath){
        Bitmap bm = BitmapFactory.decodeFile(imaePath);
        image.setImageBitmap(bm);
    }
	private void initmenu(){
		Menu menu1=new Menu();
		menu1.setName("�Լ�һ���");
		menu1.setPic(R.drawable.doufu);
		menu1.setType("����");
		menu1.setPrice("35");
		
		Menu menu2=new Menu();
		menu2.setName("��������Ϻ��");
		menu2.setType("����");
		menu2.setPic(R.drawable.xilanhua);
		menu2.setPrice("42");

		
		Menu menu3=new Menu();
		menu3.setName("��ʽ���ܲ�");
		menu3.setType("����");
		menu3.setPic(R.drawable.luobo);
		menu3.setPrice("25");
		
		Menu menu4=new Menu();
		menu4.setName("����Ŵ��ź");
		menu4.setType("����");
		menu4.setPic(R.drawable.nuomi);
		menu4.setPrice("45");
		
		Menu menu5=new Menu();
		menu5.setName("��ˮ��");
		menu5.setType("����");
		menu5.setPic(R.drawable.ji);
		menu5.setPrice("68");
		Menu menu6=new Menu();
		menu6.setName("Ƥ������");
		menu6.setType("����");
		menu6.setPic(R.drawable.pidan);
		menu6.setPrice("32");
		
		
		Menu menu7=new Menu();
		menu7.setName("����ʲ��ź��");
		menu7.setType("�Ȳ�");
		menu7.setPic(R.drawable.xiabang);
		menu7.setPrice("58");
		Menu menu8=new Menu();
		menu8.setName("˽��������");
		menu8.setType("�Ȳ�");
		menu8.setPic(R.drawable.hongshao);
		menu8.setPrice("85");
		
		Menu menu9=new Menu();
		menu9.setName("�����Ź�");
		menu9.setType("�Ȳ�");
		menu9.setPic(R.drawable.paigu);
		menu9.setPrice("62");
		
		
		Menu menu10=new Menu();
		menu10.setName("��������");
		menu10.setType("�Ȳ�");
		menu10.setPic(R.drawable.jiding);
		menu10.setPrice("52");
		
		
		Menu menu11=new Menu();
		menu11.setName("��ĩ��˿��");
		menu11.setType("�Ȳ�");
		menu11.setPic(R.drawable.fensi);
		menu11.setPrice("42");
		
		Menu menu12=new Menu();
		menu12.setName("���Ŷ���");
		menu12.setType("�Ȳ�");
		menu12.setPic(R.drawable.mapo);
		menu12.setPrice("38");
		
		Menu menu13=new Menu();
		menu13.setName("�Ź���");
		menu13.setType("����");
		menu13.setPic(R.drawable.tang4);
		menu13.setPrice("20");
		
		Menu menu14=new Menu();
		menu14.setName("ů�������");
		menu14.setType("����");
		menu14.setPic(R.drawable.tang2);
		menu14.setPrice("32");
		
		Menu menu15=new Menu();
		menu15.setName("���ϼ�����");
		menu15.setType("����");
		menu15.setPic(R.drawable.tang1);
		menu15.setPrice("45");
		
		Menu menu16=new Menu();
		menu16.setName("�㹽������");
		menu16.setType("����");
		menu16.setPic(R.drawable.tang5);
		menu16.setPrice("28");
		
		Menu menu17=new Menu();
		menu17.setName("������������");
		menu17.setType("����");
		menu17.setPic(R.drawable.tang3);
		menu17.setPrice("35");
		Menu menu18=new Menu();
		menu18.setName("������");
		menu18.setType("����");
		menu18.setPic(R.drawable.benji);
		menu18.setPrice("98");
		
		Menu menu19=new Menu();
		menu19.setName("ţ���");
		menu19.setType("����");
		menu19.setPic(R.drawable.geng);
		menu19.setPrice("38");
		
		Menu menu20=new Menu();
		menu20.setName("������");
		menu20.setType("����");
		menu20.setPic(R.drawable.suanla);
		menu20.setPrice("36");
		
		Menu menu21=new Menu();
		menu21.setName("�ܲ�Ͳ����");
		menu21.setType("����");
		menu21.setPic(R.drawable.tonggu);
		menu21.setPrice("68");
		
		Menu menu22=new Menu();
		menu22.setName("���������");
		menu22.setType("����");
		menu22.setPic(R.drawable.xiaomi);
		menu22.setPrice("98");
		
		Menu menu23=new Menu();
		menu23.setName("ũ��Ұ��Բ��");
		menu23.setType("����");
		menu23.setPic(R.drawable.yecai);
		menu23.setPrice("38");
		
		Menu menu24=new Menu();
		menu24.setName("��������");
		menu24.setType("����");
		menu24.setPic(R.drawable.guozhi);
		menu24.setPrice("38");
		
		Menu menu25=new Menu();
		menu25.setName("��ˬ����");
		menu25.setType("����");
		menu25.setPic(R.drawable.yinliao3);
		menu25.setPrice("26");
		
		Menu menu26=new Menu();
		menu26.setName("��֭˫Ƥ��");
		menu26.setType("����");
		menu26.setPic(R.drawable.yinliao4);
		menu26.setPrice("15");
		Menu menu27=new Menu();
		menu27.setName("ȫ���ײ�");
		menu27.setType("����");
		menu27.setPic(R.drawable.yinliao2);
		menu27.setPrice("88");
		Menu menu28=new Menu();
		menu28.setName("ѩ��֭");
		menu28.setType("����");
		menu28.setPic(R.drawable.yinliao5);
		menu28.setPrice("13");
		Menu menu29=new Menu();
		menu29.setName("�ʻ�ѩ��");
		menu29.setType("����");
		menu29.setPic(R.drawable.yinliao6);
		menu29.setPrice("22");
		orderdb.saveMenu(menu1);
		orderdb.saveMenu(menu2);
	    orderdb.saveMenu(menu3);
		orderdb.saveMenu(menu4);
		orderdb.saveMenu(menu5);
	    orderdb.saveMenu(menu6);
		orderdb.saveMenu(menu7);
		orderdb.saveMenu(menu8);
	    orderdb.saveMenu(menu9);
		orderdb.saveMenu(menu10);
		orderdb.saveMenu(menu11);
	    orderdb.saveMenu(menu12);
		orderdb.saveMenu(menu13);
		orderdb.saveMenu(menu14);
	    orderdb.saveMenu(menu15);
		orderdb.saveMenu(menu16);
		orderdb.saveMenu(menu17);
	    orderdb.saveMenu(menu18);
		orderdb.saveMenu(menu19);
		orderdb.saveMenu(menu20);
	    orderdb.saveMenu(menu21);
		orderdb.saveMenu(menu22);
		orderdb.saveMenu(menu23);
	    orderdb.saveMenu(menu24);
	    orderdb.saveMenu(menu25);
		orderdb.saveMenu(menu26);
		orderdb.saveMenu(menu27);
	    orderdb.saveMenu(menu28);
	    orderdb.saveMenu(menu29);
	
	}
	
	
}
