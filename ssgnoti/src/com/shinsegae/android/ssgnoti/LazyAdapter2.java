package com.shinsegae.android.ssgnoti;
import java.util.ArrayList;
import java.util.HashMap; 

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class LazyAdapter2 extends BaseAdapter {
 
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader; 
 
   // public LazyAdapter2(Activity a, ArrayAdapter<String> d) {
   //     activity = a;
   //     data=d;
   //     inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
   //     imageLoader=new ImageLoader(activity.getApplicationContext());
  //  }
 
    public LazyAdapter2(DeptSetlActivity a, ArrayList<HashMap<String, String>> d) {
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
            vi = inflater.inflate(R.layout.list_row2, null);
 
//        TextView name = (TextView)vi.findViewById(R.id.name); // title
 //       TextView birthday = (TextView)vi.findViewById(R.id.birthday); // artist name
         TextView name = (TextView)vi.findViewById(R.id.name2); // duration
 //       ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); // thumb image
 
        HashMap<String, String> dept = new HashMap<String, String>();
        //ArrayAdapter<String> dept = new ArrayAdapter<String>(activity, position);
        dept = data.get(position);
        // Setting all values in listview
        //d_day.setText(dept.get(MainActivity.KEY_D_DAY));
       // name.setText(dept.get(DeptSetlActivity.KEY_DEPT));
        return vi;
    }
}

