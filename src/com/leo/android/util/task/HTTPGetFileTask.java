package com.leo.android.util.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import android.content.Context;
import android.os.AsyncTask;

import com.leo.android.util.CommonFunction;
import com.leo.android.util.Toolets;

public class HTTPGetFileTask extends AsyncTask<Object, Void, String> {

	protected String inFilePath, outFilePath;
	protected boolean resultCheck = false;
	CommonFunction cf;
	protected Context activity;

	public HTTPGetFileTask(String inFilePath, String outFilePath) {
		this.inFilePath = inFilePath;
		this.outFilePath = outFilePath;
	}

	@Override
	protected String doInBackground(Object... inputs) {
		String response = "";

		// response = HttpClientConnector.getData(runingPath);
		response = "Download Work : " + LeoLib.tools.Toolets.downloadFile(inFilePath, outFilePath);

		return response;
	}

	
}
