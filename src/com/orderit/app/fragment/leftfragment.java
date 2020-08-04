package com.orderit.app.fragment;

import com.orderit.app.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class leftfragment extends Fragment {
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
View view=inflater.inflate(R.layout.left_fragment, container,false);
		return view;
	}
}
