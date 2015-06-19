package android.util.task;

import android.os.AsyncTask;

import javalib.tools.HttpServletTool;


public class HTTPGetTask extends AsyncTask< Object, Void, String > {
	
	protected String runingPath;
	protected boolean resultCheck = false;

	public HTTPGetTask(String runingPath){
		this.runingPath = runingPath;
	}

	@Override
	protected String doInBackground(Object... inputs) {
		String response = "";

		response = HttpServletTool.getData(runingPath);

		return response;
	}
}
