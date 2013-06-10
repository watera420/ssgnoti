package com.shinsegae.android.ssgnoti;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

public class DeptSetlActivity extends Activity implements OnItemClickListener {
	String[] msg = new String[] { "온라인백화점", "백화점", "POS&결제", "인사재무",
			"WEB&BLOSSOM" };

	ArrayAdapter<String> adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deptset);
		
		ListView lv = (ListView) findViewById(R.id.testListView);

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, msg);

		lv.setAdapter(adapter);

		lv.setOnItemClickListener(this);

	}

	
	@Override
	public void onItemClick(AdapterView<?> parent, View arg1, int arg2, long arg3) {

	     
	    // 클릭된 아이템의 포지션을 이용해 스트링어레이에서 아이템을 꺼내온다.
	    
		Intent intent = new Intent(this, MainActivity.class);
		intent.putExtra("dept", adapter.getItem(arg2));	
		
		startActivity(intent);

	}
	
	
	
	
}
