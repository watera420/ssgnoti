package com.shinsegae.android.ssgnoti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class SelsmsActivity extends Activity implements OnItemClickListener  {
	ArrayList<String> msgList;
	String[] msg = new String[] { ""								
								, "당신의 생일을 축하합니다2"
								, "당신의 생일을 축하합니다3"
								, "당신의 생일을 축하합니다4"
								, "당신의 생일을 축하합니다5" };


	ArrayAdapter<String> adapter;
	SimpleAdapter simpleadapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selsms);
		
		ListView lv =(ListView) findViewById(R.id.listView1);
		
		msgList= new ArrayList<String>();
		for(int i=0;i<msg.length;i++) {
			msgList.add(msg[i]);
		}
		
		
		simpleadapter = new SimpleAdapter(this, R.layout.list_row3, msgList);
		lv.setAdapter(simpleadapter);
		
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
		
		Intent intent = getIntent();
		
		Uri uri = Uri.parse("smsto:"+intent.getExtras().get("phone"));   
		Intent it = new Intent(Intent.ACTION_SENDTO, uri);   
		it.putExtra("sms_body", msg[arg2]);   
		startActivity(it); 
		
	}
	

	class SimpleAdapter extends ArrayAdapter<String> {

	    private Activity activity;
	    private ArrayAdapter<String> data;
	    private LayoutInflater inflater=null;
	
		public SimpleAdapter(Context context, int textViewResourceId,
				List<String> objects) {
			super(context, textViewResourceId, objects);	
	        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	  	
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
	        View vi=convertView;
	        if(convertView==null)
	         vi = inflater.inflate(R.layout.list_row3, null);
	        TextView name = (TextView)vi.findViewById(R.id.name3); // duration
	        String dept = new String();
	        name.setText(simpleadapter.getItem(position));
	        return vi;	
		}
	}
}
