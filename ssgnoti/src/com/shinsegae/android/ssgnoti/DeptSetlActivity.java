package com.shinsegae.android.ssgnoti;

import java.util.ArrayList;
import java.util.List;

import com.shinsegae.android.ssgnoti.SelsmsActivity.SimpleAdapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DeptSetlActivity extends Activity implements OnItemClickListener {
<<<<<<< HEAD
	ArrayList<String> msgList;
	String[] msg = new String[] { "DATA CENTER", "POS&결제팀", "Web&Blossom",
			"계열사팀", "그룹정보보안팀", "그룹정보전략팀", "백화점팀", "에브리데이 리테일팀", "온라인백화점팀",
=======
	String[] msg = new String[] { "DATA CENTER", "POS&결제팀", "Web&Blossom",
			"계열사팀", "그룹정보보안팀", "그룹정보전략팀", "백화점팀", "에브리데이리테일팀", "온라인백화점팀",
>>>>>>> a2a10d1485252015160db9417cecb8b15b4da398
			"이마트팀", "인사재무팀", "인터내셔날팀" };

	ArrayAdapter<String> adapter;
	SimpleAdapter simpleadapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deptset);

		ListView lv = (ListView) findViewById(R.id.testListView);

		msgList = new ArrayList<String>();
		for (int i = 0; i < msg.length; i++) {
			msgList.add(msg[i]);
		}

		simpleadapter = new SimpleAdapter(this, R.layout.list_row2, msgList);
		lv.setAdapter(simpleadapter);

		lv.setOnItemClickListener(this);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View arg1, int arg2,
			long arg3) {

		// 클릭된 아이템의 포지션을 이용해 스트링어레이에서 아이템을 꺼내온다.

		String selectedDept = simpleadapter.getItem(arg2);
		//String selectedDept = adapter.getItem(arg2);
		new DataSource(this).save(selectedDept);
		finish();

	}
	
	class SimpleAdapter extends ArrayAdapter<String> {

		private Activity activity;
		private ArrayAdapter<String> data;
		private LayoutInflater inflater = null;

		public SimpleAdapter(Context context, int textViewResourceId,
				List<String> objects) {
			super(context, textViewResourceId, objects);
			inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View vi = convertView;
			if (convertView == null)
				vi = inflater.inflate(R.layout.list_row2, null);
			TextView name = (TextView) vi.findViewById(R.id.name2); // duration
			String dept = new String();
			name.setText(simpleadapter.getItem(position));
			return vi;
		}
	}

}
