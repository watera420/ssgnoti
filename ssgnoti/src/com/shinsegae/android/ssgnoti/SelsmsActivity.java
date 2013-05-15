package com.shinsegae.android.ssgnoti;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
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
		// arg1는 현재 리스트에 뿌려지고 있는 정보
		// arg2는 현재 리스트에 뿌려지고 있는 해당 id 값

		// 값 출력을 위해 불러온 도구를 id값을 통해 불러옴
		//TextView a = (TextView) findViewById(R.id.textView1);

		// 현재 리스트뷰에 있는 해당 값을 보기
		//TextView tv = (TextView) arg1;

		// 현재 리스트뷰에 나오는 문자열과 해당 라인의 id값을 확인
		//a.setText("선택된 값 : " + tv.getText() + "\n선택된 id값: " + arg2);*/
		
		Uri uri = Uri.parse("smsto:01071664173");   
		Intent it = new Intent(Intent.ACTION_SENDTO, uri);   
		it.putExtra("sms_body", msg);   
		startActivity(it); 
		
	}
}
