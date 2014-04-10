package com.leo.android.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;

//import org.codehaus.jackson.map.ObjectMapper;
//import org.codehaus.jackson.type.TypeReference;








import android.app.Activity;
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
	
	/*
	public static boolean downloadFile(String inFileURL, String outFilePath) {
		try {

			//boolean eof = false;

			HttpURLConnection connect = (HttpURLConnection) (new URL(inFileURL))
					.openConnection();
			connect.setRequestMethod("GET");
			connect.setDoOutput(true);
			connect.connect();

			// String PATH_op = Environment.getExternalStorageDirectory() +
			// "/download/" + targetFileName;
			
			LeoLib.tools.Toolets.createParentFolder(outFilePath);
			FileOutputStream file = new FileOutputStream(new File(outFilePath));

			InputStream is = connect.getInputStream();
			byte[] buffer = new byte[1024];
			int len1 = 0;
			while ((len1 = is.read(buffer)) > 0) {
				file.write(buffer, 0, len1);
			}

			file.close();
			return true;

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			//e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			//e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			//e.printStackTrace();
		}
		return false;
	}
	*/
	
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
