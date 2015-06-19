package com.leo.android.util.task;

import java.io.IOException;
import java.util.*;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.conn.ConnectTimeoutException;

import LeoLib.tools.HttpConnector;
import android.app.*;
import android.content.*;
import android.os.*;

import com.leo.android.util.*;

import static com.leo.android.util.Constants.*;


public class HTTPPostReturnListTask extends AsyncTask< List<NameValuePair>, ArrayList<HashMap<String, String>>, String > {
	
	protected String runingPath;
	protected boolean resultCheck = false;
	
	String action;
	public HTTPPostReturnListTask(String runingPath){
		this.runingPath = runingPath;
	}

	@Override
	protected String doInBackground(List<NameValuePair>... inputs) {
		String response = "";
		
		for (List<NameValuePair> input : inputs) {
			try {
				response = HttpConnector.postDataInGIP(runingPath, (List<NameValuePair>)input);
			} catch (ConnectTimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return response;
	}


}


