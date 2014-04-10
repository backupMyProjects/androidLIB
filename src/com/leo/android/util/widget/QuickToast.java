package com.leo.android.util.widget;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

public class QuickToast{
	public static void shortToast(Context context, String msg){
		Toast.makeText(context, msg,Toast.LENGTH_SHORT).show();
	}
}
