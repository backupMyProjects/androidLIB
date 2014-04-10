package com.leo.android.util.task;

import java.util.*;

import org.apache.http.NameValuePair;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.leo.android.util.*;
import static com.leo.android.util.Constants.*;


public class HTTPGetTask extends AsyncTask< Object, Void, String > {
	
	protected String runingPath;
	protected boolean resultCheck = false;

	public HTTPGetTask(String runingPath){
		this.runingPath = runingPath;
	}

	@Override
	protected String doInBackground(Object... inputs) {
		String response = "";

		response = HttpClientConnector.getData(runingPath);

		return response;
	}
}
