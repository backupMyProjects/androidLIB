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
import static LeoLib.tools.Toolets.*;


public class BatchDownloadFileTask extends AsyncTask< Object, Void, Boolean > {
	
	protected ArrayList<HashMap<String, String>> resultList;
	protected String hmKey;
	protected String fileURL_prefix;
	protected String fileLocal_prefix;
	protected boolean resultCheck = false;
	CommonFunction cf;
	protected Context activity;

	public BatchDownloadFileTask(Context activity, CommonFunction cf, ArrayList<HashMap<String, String>> resultList, String hmKey, String fileURL_prefix, String fileLocal_prefix){
		this.activity = activity;
		this.cf = cf;
		this.cf.logLv = DEBUG;
		this.resultList = resultList;
		this.hmKey = hmKey;
		this.fileURL_prefix = fileURL_prefix;
		this.fileLocal_prefix = fileLocal_prefix;
	}

	@Override
	protected Boolean doInBackground(Object... inputs) {
		Boolean response = false;
		
		if ( resultList != null ){
			Iterator<HashMap<String, String>> ita = resultList.iterator();
			while( ita.hasNext() ){
				String fileNameString = ita.next().get(hmKey);
				//System.out.println(fileNameString);
				//response = Toolets.download2File(, fileLocal);
				
				
				response = downloadFile(fileURL_prefix + fileNameString, fileLocal_prefix + fileNameString);
			}
		}
		
		//response = Toolets.download2File("http://192.168.43.52/~leo/voice/uploaded/test.mp3", "/sdcard/voice/test.mp3");

		return response;
	}
}
