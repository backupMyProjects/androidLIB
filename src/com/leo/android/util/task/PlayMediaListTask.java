package com.leo.android.util.task;

import java.util.*;

import org.apache.http.NameValuePair;

import android.app.ProgressDialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.leo.android.util.*;

import static com.leo.android.util.Constants.*;


public class PlayMediaListTask extends AsyncTask< Object, Void, Boolean > {
	
	protected ArrayList<HashMap<String, String>> resultList;
	protected String hmKey;
	protected String file_prefix;
	protected boolean resultCheck = false;
	CommonFunction cf;
	protected Context activity;

	public PlayMediaListTask(Context activity, CommonFunction cf, ArrayList<HashMap<String, String>> resultList, String hmKey, String file_prefix){
		this.activity = activity;
		this.cf = cf;
		this.cf.logLv = DEBUG;
		this.resultList = resultList;
		this.hmKey = hmKey;
		this.file_prefix = file_prefix;
	}

	@Override
	protected Boolean doInBackground(Object... inputs) {
		Boolean response = false;
		
		if ( resultList != null ){
			/*
			Iterator<HashMap<String, String>> ita = resultList.iterator();
			while( ita.hasNext() ){
				String fileNameString = ita.next().get(hmKey);
				System.out.println(file_prefix + fileNameString);
		    	cf.mediaUrlPlay( file_prefix + fileNameString );
		    	
				
			}
			*/
			
			/*
			Thread tr = new Thread(){
				public void run(){
				    try {
				    	Iterator<HashMap<String, String>> ita = resultList.iterator();
						while( ita.hasNext() ){
							String fileNameString = ita.next().get(hmKey);
							System.out.println("Playing "+file_prefix + fileNameString);
					    	cf.mediaUrlPlay( file_prefix + fileNameString );
					    	while ( cf.getPlayer().isPlaying() ) {
					    		Thread.sleep(30000);
					        }
						}
				    }catch(Exception e){e.printStackTrace();}
				}
			};
			tr.start();
			*/
			
		}

		return response;
	}
}
