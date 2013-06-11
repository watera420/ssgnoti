package com.shinsegae.android.ssgnoti;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DeptSetlActivity extends Activity implements OnItemClickListener {
	String[] msg = new String[] { "온라인백화점", "백화점", "POS&결제", "인사재무",
			"WEB&BLOSSOM" };

	ArrayAdapter<String> adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); //액션바없앰
		setContentView(R.layout.activity_deptset);

		ListView lv = (ListView) findViewById(R.id.testListView);

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, msg);

		lv.setAdapter(adapter);

		lv.setOnItemClickListener(this);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View arg1, int arg2,
			long arg3) {

		// 클릭된 아이템의 포지션을 이용해 스트링어레이에서 아이템을 꺼내온다.

		String selectedDept = adapter.getItem(arg2);
		new DataSource(this).save(selectedDept);
		finish();

	}

}
