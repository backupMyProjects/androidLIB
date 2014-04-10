package com.leo.android.util.widget;

import java.util.HashMap;
import java.util.List;

import com.leo.android.util.CommonFunction;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class AdapterFace extends BaseAdapter {
	protected String TAG = AdapterFace.class.getName();
	protected View view;
	
	protected CommonFunction cf;
	protected Activity activity;
	protected Context context;
	protected List<HashMap<String, String>> data;
	
	public AdapterFace(Activity activity, List<HashMap<String, String>> data, int layoutInflect) {
		this.data = data;
		this.context = activity;
		this.activity = activity;
		this.cf = new CommonFunction(activity);
		this.view = (View) LayoutInflater.from(activity).inflate(layoutInflect, null);
	}
	public AdapterFace(Context context, List<HashMap<String, String>> data, int layoutInflect) {
		this.data = data;
		this.context = context;
		this.activity = (Activity)context;
		this.cf = new CommonFunction(context);
		this.view = (View) LayoutInflater.from(context).inflate(layoutInflect, null);
	}
	
	public AdapterFace(Activity activity, List<HashMap<String, String>> data) {
		this.data = data;
		this.context = activity;
		this.activity = activity;
		this.cf = new CommonFunction(activity);
	}
	public AdapterFace(Context context, List<HashMap<String, String>> data) {
		this.data = data;
		this.context = context;
		this.activity = (Activity)context;
		this.cf = new CommonFunction(context);
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int arg0) {
		return data.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		HashMap<String, String> itemHM = data.get(arg0);
		return Integer.parseInt(itemHM.get("id"));
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup paren) {
		if ( null == view ) {
			System.out.println("Initialization didn't finished");
		}
		return view;
	}


}
