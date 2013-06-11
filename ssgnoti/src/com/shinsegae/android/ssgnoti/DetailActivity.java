package com.shinsegae.android.ssgnoti;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends Activity {
	
	TextView phone;
	
//	ImageView
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); //액션바없앰
		setContentView(R.layout.detail);
		
		Intent intent = getIntent();
		
		HashMap<String, String> hashMap = (HashMap<String, String>)intent.getExtras().get("songsList");
		//String  hashMap.get("id");
		TextView empno = (TextView)findViewById(R.id.empno);
		empno.setText(hashMap.get("id"));
		
		TextView name = (TextView)findViewById(R.id.name);
		name.setText(hashMap.get("name")+" "+hashMap.get("position"));
		
		TextView dept = (TextView)findViewById(R.id.dept);
		dept.setText(hashMap.get("dept"));
		
		TextView birthday = (TextView)findViewById(R.id.birthday);
		birthday.setText(hashMap.get("birth"));
		
		phone = (TextView)findViewById(R.id.phone);
		phone.setText(hashMap.get("pn"));
		
		ImageView imageView = (ImageView)findViewById(R.id.faceImage);
		
		URL url;
		
		try {
			url = new URL(hashMap.get("thumb_url"));
			
			Bitmap bitmap = BitmapFactory.decodeStream(url.openStream());
		    imageView.setImageBitmap(bitmap);
		
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		ImageView  
		/*
		TextView name = (TextView)findViewById(R.id.name);
		name.setText(intent.getExtras().get("name").toString()+" "+intent.getExtras().get("pst").toString());
		
		TextView dept = (TextView)findViewById(R.id.dept);
		dept.setText(intent.getExtras().get("dept").toString());
		
		TextView birthday = (TextView)findViewById(R.id.birthday);
		birthday.setText(intent.getExtras().get("birty").toString());
		
		phone = (TextView)findViewById(R.id.phone);
		phone.setText(intent.getExtras().get("pn").toString());*/
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
		
		intent.putExtra("phone", phone.getText().toString());		
		startActivity(intent);
	}
}
