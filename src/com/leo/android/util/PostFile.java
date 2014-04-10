package com.leo.android.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.DataOutputStream;
import java.net.URL;
import java.net.HttpURLConnection;

/*
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;
*/

public class PostFile {

	/*
	 * public void postFile(String uploadUrl, String inputFile) throws
	 * Exception{ HttpClient httpclient = new DefaultHttpClient();
	 * httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION,
	 * HttpVersion.HTTP_1_1);
	 * 
	 * HttpPost httppost = new HttpPost(uploadUrl); File file = new
	 * File(inputFile);
	 * 
	 * MultipartEntity mpEntity = new MultipartEntity(); ContentBody cbFile =
	 * new FileBody(file, "image/jpeg"); mpEntity.addPart("userfile", cbFile);
	 * 
	 * httppost.setEntity(mpEntity); System.out.println("executing request " +
	 * httppost.getRequestLine()); HttpResponse response =
	 * httpclient.execute(httppost); HttpEntity resEntity =
	 * response.getEntity();
	 * 
	 * System.out.println(response.getStatusLine()); if (resEntity != null) {
	 * System.out.println(EntityUtils.toString(resEntity)); } if (resEntity !=
	 * null) { resEntity.consumeContent(); }
	 * 
	 * httpclient.getConnectionManager().shutdown(); }
	 */
	public int postFile(String uploadUrl, String filePath, String fileName) throws Exception {
		//final String uploadFilePath = "/mnt/sdcard/";
		//final String uploadFileName = "service_lifecycle.png";

		HttpURLConnection conn = null;
		DataOutputStream dos = null;
		String lineEnd = "\r\n";
		String twoHyphens = "--";
		String boundary = "*****";
		int bytesRead, bytesAvailable, bufferSize;
		byte[] buffer;
		int maxBufferSize = 1 * 1024 * 1024;
		File sourceFile = new File(filePath + fileName);

		if (!sourceFile.isFile()) {
			return -1;
		} else {
			// open a URL connection to the Servlet
			FileInputStream fileInputStream = new FileInputStream(filePath + fileName);
			URL url = new URL(uploadUrl);

			// Open a HTTP connection to the URL
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true); // Allow Inputs
			conn.setDoOutput(true); // Allow Outputs
			conn.setUseCaches(false); // Don't use a Cached Copy
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("ENCTYPE", "multipart/form-data");
			conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
			conn.setRequestProperty("uploaded_file", fileName);

			dos = new DataOutputStream(conn.getOutputStream());
			dos.writeBytes(twoHyphens + boundary + lineEnd);
			dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
					+ fileName + "\"" + lineEnd);

			dos.writeBytes(lineEnd);

			// create a buffer of maximum size
			bytesAvailable = fileInputStream.available();

			bufferSize = Math.min(bytesAvailable, maxBufferSize);
			buffer = new byte[bufferSize];

			// read file and write it into form...
			bytesRead = fileInputStream.read(buffer, 0, bufferSize);

			while (bytesRead > 0) {

				dos.write(buffer, 0, bufferSize);
				bytesAvailable = fileInputStream.available();
				bufferSize = Math.min(bytesAvailable, maxBufferSize);
				bytesRead = fileInputStream.read(buffer, 0, bufferSize);

			}

			// send multipart form data necesssary after file data...
			dos.writeBytes(lineEnd);
			dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

			// Responses from the server (code and message)
			int serverResponseCode = conn.getResponseCode();
			String serverResponseMessage = conn.getResponseMessage();

			if (serverResponseCode == 200) {
				String msg = "File Upload Completed." + filePath + fileName;
			}

			// close the streams //
			fileInputStream.close();
			dos.flush();
			dos.close();
			return serverResponseCode;
		} // End else block
	}

	private static String uploadUrl = "http://bactria.i234.me/LeoAndroidUtil/UploadToServer.php";
	private static String filePath = "/Users/leo/Pictures/";
	private static String fileName = "大頭.jpg";

	public static void main(String[] args) throws Exception {
		(new PostFile()).postFile(uploadUrl, filePath , fileName);
	}

}
