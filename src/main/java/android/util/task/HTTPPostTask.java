package android.util.task;

import android.os.AsyncTask;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.conn.ConnectTimeoutException;

import java.io.IOException;
import java.util.List;

import javalib.tools.HttpServletTool;


/**
 * timout must > 1000 else will be set as default in HttpConnector.postDataInGIP (30000)
 * @author Leo
 *
 */
public class HTTPPostTask extends AsyncTask< List<NameValuePair>, Void, String > {
	
	protected String runingPath;
	protected int timeout = 0;
	protected boolean resultCheck = false;
	
	String action;
	public HTTPPostTask(String runingPath){
		this.runingPath = runingPath;
	}
	public HTTPPostTask(String runingPath, int timeout){
		this.runingPath = runingPath;
		this.timeout = timeout;
	}

	@Override
	protected String doInBackground(List<NameValuePair>... inputs) {
		String response = "";
		
		for (List<NameValuePair> input : inputs) {
			if ( timeout != 0 && timeout > 1000 ){
				try {
					response = HttpServletTool.postDataInGZIP(runingPath, (List<NameValuePair>) input, timeout);
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
			}else{
				try {
					response = HttpServletTool.postDataInGZIP(runingPath, (List<NameValuePair>)input);
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
			
		}
		
		return response;
	}


}


