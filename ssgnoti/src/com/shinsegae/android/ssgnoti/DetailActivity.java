package com.shinsegae.android.ssgnoti;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DetailActivity extends Activity {
	
	TextView phone;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);
		
		Intent intent = getIntent();
		
		TextView empno = (TextView)findViewById(R.id.empno);
		empno.setText(intent.getExtras().get("id").toString());
		
		TextView name = (TextView)findViewById(R.id.name);
		name.setText(intent.getExtras().get("name").toString());
		
		TextView dept = (TextView)findViewById(R.id.dept);
		dept.setText(intent.getExtras().get("dept").toString());
		
		TextView birthday = (TextView)findViewById(R.id.birthday);
		birthday.setText(intent.getExtras().get("birty").toString());
		
		phone = (TextView)findViewById(R.id.phone);
		phone.setText(intent.getExtras().get("pn").toString());
	}
	
	
	
	
	public void callBtn(View v) {
		// TODO Auto-generated method stub
		 Uri number = Uri.parse("tel:" + phone.getText().toString());
		Intent intent = new Intent(Intent.ACTION_CALL, number);
        startActivity(intent);
	}
	
	public void shopBtn(View v) {
		// TODO Auto-generated method stub
		Uri uri = Uri.parse("http://mw.cybermall.co.kr");
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(intent);
	}
	
	public void smsBtn(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, SelsmsActivity.class);
		startActivity(intent);
	}
}
