package com.shinsegae.android.ssgnoti;
import java.util.ArrayList;
import java.util.HashMap; 

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class LazyAdapter extends BaseAdapter {
 
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader; 
 
    public LazyAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(activity.getApplicationContext());
    }
 
    public int getCount() {
        return data.size();
    }
 
    public Object getItem(int position) {
        return position;
    }
 
    public long getItemId(int position) {
        return position;
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.list_row, null);
 
        TextView name = (TextView)vi.findViewById(R.id.name); // title
        TextView birthday = (TextView)vi.findViewById(R.id.birthday); // artist name
        TextView d_day = (TextView)vi.findViewById(R.id.d_day); // duration
        ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); // thumb image
 
        HashMap<String, String> song = new HashMap<String, String>();
        song = data.get(position);
 
        // Setting all values in listview
        name.setText(song.get(MainActivity.KEY_NAME)+" "+song.get(MainActivity.KEY_POSITION));
        birthday.setText(song.get(MainActivity.KEY_BIRTH));
        d_day.setText(song.get(MainActivity.KEY_POSITION));
        imageLoader.DisplayImage(song.get(MainActivity.KEY_THUMB_URL), thumb_image);
        return vi;
    }
}

