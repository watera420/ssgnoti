package com.shinsegae.android.ssgnoti;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	// All static variables
	static final String URL = "http://1.234.89.248/1/birthinfo.xml";
	// XML node keys
	static final String KEY_EMPINFO = "employee"; // parent node
	static final String KEY_ID = "id"; // 사번
	static final String KEY_NAME = "name"; // 이름
	static final String KEY_DEPT = "dept"; // 부서
	static final String KEY_BIRTH = "birth"; // 생년월일
	static final String KEY_PN = "pn"; // 핸드폰번호
	static final String KEY_POSITION = "position"; // 직책
	static final String KEY_THUMB_URL = "thumb_url"; // 이미지 URL
	static final String KEY_D_DAY = "dday"; // D-DAY
	static String KEY_SORT = "000"; // D-DAY

	Calendar yDay = Calendar.getInstance();
	Calendar toDay = Calendar.getInstance();
	ListView list;
	LazyAdapter adapter;
	ArrayList<HashMap<String, String>> songsList;
	private Toast mToast;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Intent intent2 = getIntent();
		String deptsetl = intent2.getStringExtra("dept");
		TextView dept2 = (TextView) findViewById(R.id.dept2);
		dept2.setText(deptsetl);

		songsList = new ArrayList<HashMap<String, String>>();

		XMLParser parser = new XMLParser();
		String xml = parser.getXmlFromUrl(URL); // getting XML from URL
		Document doc = parser.getDomElement(xml); // getting DOM element

		NodeList nl = doc.getElementsByTagName(KEY_EMPINFO);
		// looping through all song nodes <song>
		for (int i = 0; i < nl.getLength(); i++) {
			// creating new HashMap
			HashMap<String, String> map = new HashMap<String, String>();
			Element e = (Element) nl.item(i);
			// adding each child node to HashMap key => value

			String a = parser.getValue(e, KEY_DEPT);

			if (a.equals(deptsetl)) {
				map.put(KEY_ID, parser.getValue(e, KEY_ID));
				map.put(KEY_NAME, parser.getValue(e, KEY_NAME));
				map.put(KEY_DEPT, parser.getValue(e, KEY_DEPT));
				String date = parser.getValue(e, KEY_BIRTH);
				map.put(KEY_BIRTH, date);
				map.put(KEY_PN, parser.getValue(e, KEY_PN));
				map.put(KEY_POSITION, parser.getValue(e, KEY_POSITION));
				map.put(KEY_THUMB_URL, parser.getValue(e, KEY_THUMB_URL));

				Integer diffDay = DateUtil.getDDay(date);
				int aaa = 3 - diffDay.toString().length();
				String sDiffDay = diffDay.toString();
				if (aaa > 0) {
					for (int c = 0; c < aaa; c++) {
						sDiffDay = "0" + diffDay.toString();
					}
				}
				map.put(KEY_D_DAY, "D-" + String.valueOf(diffDay));
				map.put(KEY_SORT, sDiffDay);

				// adding HashList to ArrayList
				songsList.add(map);
			}
		}

		// 이름(KEY_NAME)으로 오름차순 리스트정렬 함수 (해쉬맵)
		class MapComparator implements Comparator<Map<String, String>> {
			private final String key;

			public MapComparator(String key) {
				this.key = key;
			}

			public int compare(Map<String, String> first,
					Map<String, String> second) {
				// TODO: Null checking, both for maps and values
				String firstValue = first.get(key);
				String secondValue = second.get(key);
				return firstValue.compareTo(secondValue);
			}
		}
		Collections.sort(songsList, new MapComparator(KEY_SORT)); // 오름차순 리스트정렬

		list = (ListView) findViewById(R.id.list);

		// Getting adapter by passing xml data ArrayList
		adapter = new LazyAdapter(this, songsList);
		list.setAdapter(adapter);

		// Click event for single list row
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Log.i("TTTT", "songsList.get(position) : " + songsList);
				Intent intent = new Intent(MainActivity.this,
						DetailActivity.class);
				intent.putExtra("songsList",
						(HashMap<String, String>) songsList.get(position));

				startActivity(intent);

			}
		});

		// 알람매니저

		AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
		Intent intent = new Intent(this, AlarmService_Service.class);

		intent.putExtra("songsList", songsList);

		PendingIntent sender = PendingIntent.getBroadcast(this, 0, intent, 0);

		long firstTime = SystemClock.elapsedRealtime();
		firstTime += 1000;
		// Alarm을 설정합니다. 10초마다 어떤 일을 반복해서 하고 싶어요
		// firstTime += 1000;

		am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstTime,
				(24 * 1000 * 3600), sender);

		// 일단 처음 한 번 현재 시간을 출력 합니다.
		final Calendar c = Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis()); // 현재시간 셋팅

		c.add(Calendar.SECOND, 10);

		/*
		 * int mHour = c.get(Calendar.HOUR_OF_DAY); int mMinute =
		 * c.get(Calendar.MINUTE); int mSecond = c.get(Calendar.SECOND);
		 */

		am.set(AlarmManager.RTC, c.getTimeInMillis(), sender);

		/*
		 * if (mToast != null) { mToast.cancel(); } mToast =
		 * Toast.makeText(this, "현재 시간" + mHour + ":" + mMinute + ":" + mSecond,
		 * Toast.LENGTH_LONG); mToast.show();
		 */

	}

	private Integer getDday(String date) {
		String yyyy = date.substring(0, 4);
		String mm = date.substring(5, 7);
		String dd = date.substring(8, 10);
		int i_yyyy = Integer.parseInt(yyyy);
		int i_mm = Integer.parseInt(mm) - 1;
		int i_dd = Integer.parseInt(dd);

		yDay.set(2013, i_mm, i_dd);

		long diffSec = (yDay.getTimeInMillis() - toDay.getTimeInMillis()) / 1000; // 초
		Integer diffDay = (int) (diffSec / (60 * 60 * 24));
		return diffDay;
	}

	public void dept_btn(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, DeptSetlActivity.class);

		// intent.putExtra("phone", phone.getText().toString());
		startActivity(intent);
	}

}
