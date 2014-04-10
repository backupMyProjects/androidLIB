package com.leo.android.util;

import java.io.IOException;

import com.leo.android.util.Constants.Motions;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;

public class CommonFunction {
	public Activity activity;
	public Context context;
	
	public CommonFunction(Activity activity){
		this.activity = activity;
		this.context = activity;
	}
	public CommonFunction(Context context){
		this.activity = (Activity) context;
		this.context = context;
	}
	
	//boolean finishedPlay = false;
	MediaPlayer player;
	public void setPlayer(MediaPlayer player){this.player = player;}
	public MediaPlayer getPlayer(){
		if (this.player == null) {this.player = new MediaPlayer();}
		return player;
	}
	public void mediaPlay(String input){
		if (this.player == null) {this.player = new MediaPlayer();}
		if ( !player.isPlaying() ){
			try {
				player.setDataSource(input);
				player.prepare();
				player.start();
	        } catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public boolean mediaUrlPlay(String inputUrl){
		player = MediaPlayer.create(this.activity, Uri.parse(inputUrl));
		player.setOnCompletionListener(new OnCompletionListener(){
			@Override
			public void onCompletion(MediaPlayer mp) {
			}

        });
	    player.setOnPreparedListener(new OnPreparedListener() { 
	        @Override
	        public void onPrepared(MediaPlayer mp) {
	            mp.start();
	        }
	    });
	    return player.isPlaying();
	}
	public void mediaStop(){
		if ( player != null ){
			if ( player.isPlaying() ){
				player.stop();
				player.reset();
			}
		}else{
			System.out.println("NullPoint : MediaPlayer is null. Calling mediaPlay function first.");
		}
	}

	
	public void jump2Activity(Class<?> target, Bundle bundle, boolean finished) {
		Intent intent = new Intent();
		intent.putExtras(bundle);
		intent.setClass(this.activity, target);
		
		this.activity.startActivityForResult(intent,0);
		if (finished){
			this.activity.finish();
		}
	}
	
	public void back2Activity() {
		this.activity.setResult(this.activity.RESULT_OK, this.activity.getIntent());
		this.activity.finish();
	}
	
	public void slide2Activity(Class target, Bundle bundle, boolean finished) {
		
		this.jump2Activity(target, bundle, finished);
		

		this.activity.overridePendingTransition( R.anim.slide_in_left, R.anim.slide_out_left );
	}
	
	public void slideback2Activity() {
		this.activity.setResult(this.activity.RESULT_OK, this.activity.getIntent());
		this.activity.finish();
		this.activity.overridePendingTransition( R.anim.slide_in_right, R.anim.slide_out_right );
	}
	
	
	float startXPos = 0;
    float startYPos = 0;
    float endXPos = 0;
    float endYPos = 0;
	public Motions checkMotion(MotionEvent me){
		int action = me.getAction();
		Motions re = Motions.None;
		if (action == MotionEvent.ACTION_DOWN) {
	         // do something
	    	startXPos = me.getX();
	    	startYPos = me.getY();
	    	//Log.v("MotionEvent", "X = " + startXPos + "Y = " + startYPos );
	    }


	    if (action == MotionEvent.ACTION_UP) {
	         // do something
	    	endXPos = me.getX();
	    	endYPos = me.getY();
	    	//Log.v("MotionEvent", "X = " + endXPos + "Y = " + endYPos );
	    	//Log.v( "MotionEvent", "" +  ( Math.abs(endXPos - startXPos) - Math.abs(endYPos - startYPos) )  );
	    	
	    	if ( ( Math.abs(endXPos - startXPos) - Math.abs(endYPos - startYPos) ) > 0  ){
		    	if ( startXPos - endXPos > 0 ){
		    		//Log.v("MotionEvent", "to Left" );
		    		re = Motions.Left;
		    	}else{
		    		//Log.v("MotionEvent", "to Right" );
		    		re = Motions.Right;
		    	}
	    	}else{
	    		if ( startYPos - endYPos > 0 ){
		    		//Log.v("MotionEvent", "to Up" );
		    		re = Motions.Up;
		    	}else{
		    		//Log.v("MotionEvent", "to Down" );
		    		re = Motions.Down;
		    	}
	    	}
	    }
	    
	    return re;
	}
	
	public int logLv = Constants.NONE;
	public void log(String msg){
		String tag = activity.getClass().getName();
		switch(logLv){
			case Constants.ERROR :
				Log.e(tag, msg);
				break;
			case Constants.INFO : 
				Log.i(tag, msg);
				break;
			case Constants.WARNING : 
				Log.w(tag, msg);
				break;
			case Constants.DEBUG :
				Log.d(tag, msg);
				break;
			case Constants.VERBOSE : 
				Log.v(tag, msg);
				break;
		}
	}
	
	public boolean hasNetwork(){return hasNetwork(false);}
	public boolean hasNetwork(boolean toastIt){
		ConnectivityManager conMan = (ConnectivityManager) this.activity.getSystemService(Context.CONNECTIVITY_SERVICE);
		
		//mobile
		State mobile = (conMan.getNetworkInfo(0) != null) ? conMan.getNetworkInfo(0).getState() : null;
		//wifi
		State wifi = ( conMan.getNetworkInfo(1) != null ) ? conMan.getNetworkInfo(1).getState() : null;
		
		if ( (mobile != null) && mobile == NetworkInfo.State.CONNECTED || mobile == NetworkInfo.State.CONNECTING) {
		    //mobile
			if(toastIt)Toast.makeText(this.activity, R.string.three_g, Toast.LENGTH_SHORT).show();
			log("mobile");
			return true;
		}else if ( (wifi != null) && wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING) {
		    //wifi
			if(toastIt)Toast.makeText(this.activity, R.string.wifi, Toast.LENGTH_SHORT).show();
			log("wifi");
			return true;
		}else{
			// no network
			Toast.makeText(this.activity, R.string.nonetwork, Toast.LENGTH_SHORT).show();
			return false;
		}

	}

}
