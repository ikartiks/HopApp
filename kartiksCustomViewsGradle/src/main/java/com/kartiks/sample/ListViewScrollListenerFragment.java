package com.kartiks.sample;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.kartiks.utility.LoggerGeneral;

public class ListViewScrollListenerFragment extends Fragment {
	
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
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		
		context=getActivity();
		
		View v=inflater.inflate(R.layout.fragment_lv, container,false);
		
		someView=(View)v.findViewById(R.id.soemView);
		fllp=(FrameLayout.LayoutParams)someView.getLayoutParams();
		
		gdt=new GestureDetector(context, new CustomGestureDetector());
		ListView lv=(ListView)v.findViewById(R.id.ListView1);
		lv.setAdapter(new ArrayAdapter(context, R.layout.inflate_list, R.id.text, objects));
		lv.setOnScrollListener(new AbsListView.OnScrollListener() {
			
			int previousFirstItem=0;
			
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				
				LoggerGeneral.e("scroll state us "+scrollState);
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,int visibleItemCount,
					int totalItemCount) {
				
				if(firstVisibleItem-previousFirstItem>=0){
					LoggerGeneral.e("goin down");
					someView.setVisibility(View.VISIBLE);
				}else {
					LoggerGeneral.e("goin up");
					someView.setVisibility(View.GONE);
				}
				previousFirstItem=firstVisibleItem;
				
			}
		});
		
		/*lv.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				gdt.onTouchEvent(event);
				//LoggerGeneral.e("on touch");
				return false;
			}
		});
		
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				LoggerGeneral.e("click");
			}
		});
		*/
		return v;
		
	}
	
	public class CustomGestureDetector extends GestureDetector.SimpleOnGestureListener{
		
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			
			if(e1.getX()-e2.getX()>0){
				fllp.topMargin+=1;
				someView.setLayoutParams(fllp);
				
			}else{
				fllp.topMargin-=1;
				someView.setLayoutParams(fllp);
				
			}
			//LoggerGeneral.e("scrolling...");
			return super.onScroll(e1, e2, distanceX, distanceY);
		}
		
		
	}

}
