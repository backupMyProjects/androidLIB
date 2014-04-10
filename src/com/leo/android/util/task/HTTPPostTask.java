package com.leo.android.util.task;

import java.util.*;

import org.apache.http.NameValuePair;
import android.app.*;
import android.content.*;
import android.os.*;

import com.leo.android.util.*;
import static com.leo.android.util.Constants.*;



public class HTTPPostTask extends AsyncTask< List<NameValuePair>, Void, String > {
	
	protected String runingPath;
	protected boolean resultCheck = false;
	
	String action;
	public HTTPPostTask(String runingPath){
		this.runingPath = runingPath;
	}

	@Override
	protected String doInBackground(List<NameValuePair>... inputs) {
		String response = "";
		
		for (List<NameValuePair> input : inputs) {
			response = HttpClientConnector.postDataInGIP(runingPath, (List<NameValuePair>)input);
		}
		
		return response;
	}


}


