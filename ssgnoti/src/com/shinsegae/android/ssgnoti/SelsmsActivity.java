package com.shinsegae.android.ssgnoti;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class SelsmsActivity extends ListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		
		String[] smslist = getResources().getStringArray(R.array.smslist_array);
		setListAdapter(new ArrayAdapter<String>(this, R.layout.row,	smslist));	
		
		
		ListView lv = getListView();
		lv.setTextFilterEnabled(true);
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				Toast.makeText(getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
				
			}
		});	
	}
}
