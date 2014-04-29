package com.leo.android.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;


public class Toolets {
	
	public static Bitmap getBitmapFromURL(String link) {
	    try {
	        HttpURLConnection connection = (HttpURLConnection) (new URL(link)).openConnection();
	        connection.setDoInput(true);
	        connection.connect();
	        InputStream input = connection.getInputStream();
	        Bitmap myBitmap = BitmapFactory.decodeStream(input);

	        return myBitmap;

	    } catch (IOException e) {
	        e.printStackTrace();
	        Log.e("getBmpFromUrl error: ", e.getMessage().toString());
	        return null;
	    }
	}
	
	public static boolean hasNetwork(Context context, boolean toastIt){
		ConnectivityManager conMan = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		
		//mobile
		State mobile = (conMan.getNetworkInfo(0) != null) ? conMan.getNetworkInfo(0).getState() : null;
		//wifi
		State wifi = ( conMan.getNetworkInfo(1) != null ) ? conMan.getNetworkInfo(1).getState() : null;
		
		if ( (mobile != null) && mobile == NetworkInfo.State.CONNECTED || mobile == NetworkInfo.State.CONNECTING) {
		    //mobile
			if(toastIt)Toast.makeText(context, R.string.three_g, Toast.LENGTH_SHORT).show();
			return true;
		}else if ( (wifi != null) && wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING) {
		    //wifi
			if(toastIt)Toast.makeText(context, R.string.wifi, Toast.LENGTH_SHORT).show();
			return true;
		}else{
			// no network
			if(toastIt)Toast.makeText(context, R.string.nonetwork, Toast.LENGTH_SHORT).show();
			return false;
		}

	}
	
    
    public static String getMyUUID(Context context){
    	  final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);   
    	  final String tmDevice, tmSerial, tmPhone, androidId;   
    	  tmDevice = "" + tm.getDeviceId();  
    	  tmSerial = "" + tm.getSimSerialNumber();   
    	  androidId = "" + android.provider.Settings.Secure.getString(context.getContentResolver(),android.provider.Settings.Secure.ANDROID_ID);   
    	  UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());   
    	  String uniqueId = deviceUuid.toString();
    	  return uniqueId;
    }
}
