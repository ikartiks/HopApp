package com.kartiks.sample;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;

public class ScrollViewListenerAndroid extends Fragment {
	
	Context context;
	View someView;
	FrameLayout.LayoutParams fllp;
	GestureDetector gdt;
	
	String[] objects={"12zxc","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd"
			,"asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd",
			"asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd",
			"asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd",
			"asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd",
			"asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd",
			"asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd",
			"asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd",
			"asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd",
			"asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd",
			"asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd",
			"asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd",
			"asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd",
			"asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd",
			"asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd",
			"asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd",
			"asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd",
			"asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd"};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		context=getActivity();
		
		View v=inflater.inflate(R.layout.fragment_scroll, container,false);
		
		ScrollView scrollView=(ScrollView)v.findViewById(R.id.ScrollView1);
		
		
		someView=(View)v.findViewById(R.id.soemView);
		fllp=(FrameLayout.LayoutParams)someView.getLayoutParams();
		
		
		return v;
	}
}
