package android.util;

import android.app.DownloadManager;
import android.app.DownloadManager.Query;
import android.app.DownloadManager.Request;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;


/**
 * 0. New the DownloadBroadcastReceiver at top level, because you have to calling Working function before override downloadSuccessful function.
 * 1. override downloadSuccessful function
 * 1.1 if necessary, override downloadFailed, downloadPending function.
 * 2. register receiver via calling registerReceiver context function 
 * 3. Calling working function within the downlaod uri string, if you do not calling this function, the receiver should print out err.
 * 3.1 Calling workingRequest function to customize the download path.
 * example : 
 * #1
 		receiver = new DownloadBroadcastReceiver(this) {
			@Override
			public void downloadSuccessful(Context context, Intent intent) {
				ImageView view = (ImageView) findViewById(R.id.imageView1);
                String uriString = this.getCursor().getString(this.getCursor().getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                view.setImageURI(Uri.parse(uriString));
			}
        };
 * #2
 		registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
 		
 * #3
 		receiver.Working("http://xxxxx", "http://xxxxx");
 		
 * #3.1
 		Request req1 = new Request(Uri.parse("http://xxxxx"));
 		req1.setDestinationInExternalPublicDir("RRR/1", "xxx.png");
 		receiver.WorkingRequest(req1, req2);
 * 
 * 999. At last, be sure to unregister this receiver at onDestroy
 * 
 * @author leo
 *
 */
public abstract class DownloadBroadcastReceiver extends BroadcastReceiver {
	protected String TAG = DownloadBroadcastReceiver.class.getName();
	protected long enqueue;
    protected DownloadManager dm;
    protected Cursor cursor;
	public Cursor getCursor(){return cursor;}
	public DownloadBroadcastReceiver(Context context){
		this.dm = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
	}
	
	protected boolean flag = false;
	public void working(String... uris){
		for( String uri : uris ){
			Request request = new Request(Uri.parse(uri));
			this.enqueue = dm.enqueue(request);
		}
		flag = true;
	}
	public void workingRequest(Request... reqs){
		for( Request req : reqs ){
			this.enqueue = dm.enqueue(req);
		}
		flag = true;
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		if(flag){
			if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(intent.getAction())) {
	            long downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0);
	            Query query = new Query();
	            query.setFilterById(enqueue);
	            cursor = dm.query(query);
	            if (cursor.moveToFirst()) {
	                int columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
	                if ( DownloadManager.STATUS_SUCCESSFUL == cursor.getInt(columnIndex)){
	                	downloadSuccessful(context, intent);
	                }else if( DownloadManager.STATUS_FAILED == cursor.getInt(columnIndex) ){
	                	downloadFailed(context, intent);
	                }else if( DownloadManager.STATUS_PENDING == cursor.getInt(columnIndex) ){
	                	downloadPending(context, intent);
	                }else if( DownloadManager.STATUS_RUNNING == cursor.getInt(columnIndex) ){
	                	 try {
							Thread.sleep(1500);
							Log.d(TAG,"RUNNING...");
							onReceive(context, intent);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                	
	                }
	            }
	        }
		}else{
			System.err.println("Calling working/workingRequest first.");
		}
        
	}
	
	public abstract void downloadSuccessful(Context context, Intent intent);
	public void downloadFailed(Context context, Intent intent){};
	public void downloadPending(Context context, Intent intent){};

}
