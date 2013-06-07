package com.shinsegae.android.ssgnoti;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.Toast;

public class AlarmService_Service extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) // 10초마다 이리루 들어옵니다
	{
		
		String noti_birth = "";
		String noti_name = "";
		URL url;
		
		if(intent.hasExtra("songsList")){
			
			ArrayList<HashMap<String, String>> songsList = (ArrayList<HashMap<String, String>>)intent.getExtras().get("songsList");
			
			Iterator<HashMap<String, String>> iterator = songsList.iterator();
			
			while (iterator.hasNext()) {
				HashMap<java.lang.String, java.lang.String> hashMap = (HashMap<java.lang.String, java.lang.String>) iterator
						.next();
				noti_birth = hashMap.get("birth");
				noti_name = hashMap.get("name")+" "+hashMap.get("dept");
				Calendar cal = Calendar.getInstance();
			    cal.setTime(new java.util.Date(System.currentTimeMillis())); 
			    String toDay = new java.text.SimpleDateFormat("yyyy.MM.dd").format(cal.getTime());
			    
			    // 생일인 날짜 비교하여 노티생성
			    if(toDay.equals(noti_birth)){
			    	NotificationManager notiManager = (NotificationManager) context
							.getSystemService(Context.NOTIFICATION_SERVICE);
					Notification noti = new Notification(R.drawable.ic_launcher,"생일알리미 노티",System.currentTimeMillis());
					noti.flags = noti.flags|Notification.FLAG_AUTO_CANCEL;
					noti.contentView = new RemoteViews(context.getPackageName(), R.layout.noti);
					noti.contentView.setTextViewText(R.id.noti_birthday, noti_birth);
					noti.contentView.setTextViewText(R.id.noti_name, noti_name);
					try {
						url = new URL(hashMap.get("thumb_url"));
						Bitmap bitmap = BitmapFactory.decodeStream(url.openStream());
						noti.contentView.setImageViewBitmap(R.id.noti_list_image, bitmap);
					} catch (MalformedURLException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					noti.tickerText = toDay;
					Intent intent1 = new Intent(context,MainActivity.class);
					PendingIntent pi = PendingIntent.getActivity(context, 0, intent1, 0);
					noti.contentIntent = pi;
					notiManager.notify(1,noti);
			    }
			}
		}
		
		/*NotificationManager notiManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification noti = new Notification(R.drawable.ic_launcher,"생일알리미 노티",System.currentTimeMillis());
		
		noti.flags = noti.flags|Notification.FLAG_AUTO_CANCEL;
		
		noti.contentView = new RemoteViews(context.getPackageName(), R.layout.activity_main);
		
		Intent intent1 = new Intent(context,MainActivity.class);
		PendingIntent pi = PendingIntent.getActivity(context, 0, intent1, 0);
		
		noti.contentIntent = pi;
		
		//noti.contentView.setImageViewResource(R.id, srcId)
		noti.contentView.setTextViewText(R.id.birthday, "1983-01-01");
		notiManager.notify(1,noti);*/
		
	/*	final Calendar c = Calendar.getInstance();
		int mHour = c.get(Calendar.HOUR_OF_DAY);
		int mMinute = c.get(Calendar.MINUTE);
		int mSecond = c.get(Calendar.SECOND);

		Toast.makeText(context,
				"현재 시간" + mHour + ":" + mMinute + ":" + mSecond,
				Toast.LENGTH_SHORT).show();*/
	}
}