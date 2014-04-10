package com.leo.android.util.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;

import com.leo.android.util.*;
import static com.leo.android.util.Constants.*;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

public class HTTPPostFileTask extends AsyncTask< Object, Void, String > {
	
	protected String runingPath;
	protected String uploadInfo;
	protected boolean resultCheck = false;

	public HTTPPostFileTask(String runingPath, String uploadInfo){
		this.runingPath = runingPath;
		this.uploadInfo = uploadInfo;
	}

	@Override
	protected String doInBackground(Object... inputs) {
		try {
			//(new PostFile()).postFile(runingPath, uploadInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		
		return "done";
	}
}
