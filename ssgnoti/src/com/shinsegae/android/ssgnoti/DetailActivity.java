package com.shinsegae.android.ssgnoti;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);
		
		TextView empno = (TextView)findViewById(R.id.empno);
		empno.setText("111814");
		TextView name = (TextView)findViewById(R.id.name);
		name.setText("서옥수 주임");
		TextView dept = (TextView)findViewById(R.id.dept);
		dept.setText("온라인백화점");
		TextView birthday = (TextView)findViewById(R.id.birthday);
		birthday.setText("1983년 1월 1일");
		TextView phone = (TextView)findViewById(R.id.phone);
		phone.setText("010-7166-4173");
	}
}
