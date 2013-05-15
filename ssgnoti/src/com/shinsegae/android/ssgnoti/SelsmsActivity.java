package com.shinsegae.android.ssgnoti;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class SelsmsActivity extends Activity implements OnItemClickListener  {
	String[] msg = new String[] { "당신의 생일을 축하합니다"
			, "당신의 생일을 축하합니다"
			, "당신의 생일을 축하합니다"
			, "당신의 생일을 축하합니다"
			, "당신의 생일을 축하합니다" };

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selsms);
		
		ListView lv =(ListView) findViewById(R.id.listView1);
		
		ArrayAdapter<String> adapter;
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, msg);

		lv.setAdapter(adapter);
		
		lv.setOnItemClickListener(this);
		
	}


	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}
}
