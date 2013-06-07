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

	
	// 부서값을 받아서 다시 MAIN으로 넘겨줘야 함 ㅠㅠ 어떻게 하지?
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

		Intent intent = getIntent();

		startActivity(intent);

	}
	
	
	
	
}
