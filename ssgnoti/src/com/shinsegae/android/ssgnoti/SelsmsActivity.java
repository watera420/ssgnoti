package com.shinsegae.android.ssgnoti;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SelsmsActivity extends Activity implements OnItemClickListener {
	ArrayList<String> msgList;
	String[] msg = new String[] { "직접입력"
			, "더욱 큰 사랑과 행복이 항상 당신과  함께 하길 바랍니다.\n 생일을 진심으로 축하해요."
			, "뜻 깊은 당신의 생일을 맞이하여\n진심으로 축하합니다"
			, "     *~iiii~*\n    ∴┎★☆┒∴\n	∴┎♡생일♡┒∴\n	┏♣축하해요♧┒\n	┗★:*~**~*:☆┚",
			"생일축하합니다" };

	ArrayAdapter<String> adapter;
	SimpleAdapter simpleadapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selsms);

		ListView lv = (ListView) findViewById(R.id.listView1);

		msgList = new ArrayList<String>();
		for (int i = 0; i < msg.length; i++) {
			msgList.add(msg[i]);
		}

		simpleadapter = new SimpleAdapter(this, R.layout.list_row3, msgList);
		lv.setAdapter(simpleadapter);

		lv.setOnItemClickListener(this);

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Intent intent = getIntent();

		Uri uri = Uri.parse("smsto:" + intent.getExtras().get("phone"));
		Intent it = new Intent(Intent.ACTION_SENDTO, uri);
		if (msg[arg2].equals("직접입력")) {
			it.putExtra("sms_body", "");
		} else {
			it.putExtra("sms_body", msg[arg2]);
		}
		startActivity(it);

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
				vi = inflater.inflate(R.layout.list_row3, null);
			TextView name = (TextView) vi.findViewById(R.id.name3); // duration
			String dept = new String();
			name.setText(simpleadapter.getItem(position));
			return vi;
		}
	}
}
