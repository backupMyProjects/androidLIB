package com.leo.android.util.task;

import static LeoLib.tools.Toolets.downloadFile2;

import java.io.File;

import android.content.Context;
import android.os.AsyncTask;

import com.leo.android.util.CommonFunction;

public class HTTPGetFileTask extends AsyncTask<Object, Void, String> {

	protected String inFilePath, outFilePath;
	protected boolean resultCheck = false;
	CommonFunction cf;
	protected Context activity;
	protected boolean overrideFlag = false;

	public HTTPGetFileTask(String inFilePath, String outFilePath, boolean overrideFlag) {
		this.inFilePath = inFilePath;
		this.outFilePath = outFilePath;
		this.overrideFlag = overrideFlag;
	}
	
	public HTTPGetFileTask(String inFilePath, String outFilePath) {
		this.inFilePath = inFilePath;
		this.outFilePath = outFilePath;
	}

	// inputs is useless
	@Override
	protected String doInBackground(Object... inputs) {
		String response = "";

		// response = HttpClientConnector.getData(runingPath);
		if ( !overrideFlag && new File(outFilePath).isFile() ){return "has file";}
		response = "Download Work : " + downloadFile2(inFilePath, outFilePath);

		return response;
	}
	
	
}
