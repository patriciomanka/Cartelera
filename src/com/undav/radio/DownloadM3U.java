package com.undav.radio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Vector;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.undav.cartelera.RadioActivity;

import android.net.Uri;
import android.os.AsyncTask;

public class DownloadM3U extends AsyncTask<String, Void, String[]> {
	private RadioActivity activity;
	
	public DownloadM3U(RadioActivity activity) {
		this.activity = activity;
	}
	
	protected String[] doInBackground(String... urls) {				
		BufferedReader in = null;
		Vector<String> lines = new Vector<String>();
		try {
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet();
			request.setURI(new URI(urls[0]));
			HttpResponse response = client.execute(request);
			in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		    String line = "";
		    while ((line = in.readLine()) != null) {
		    	line = line.trim();

		    	if (line.equals("")) continue;
		    	if (line.charAt(0) == '#') continue;
		    	line = Uri.decode(line);
		    	lines.add(line);
		    }	
		    in.close();
		} catch(Exception e) {
			return null;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return ((String[]) lines.toArray(new String[0]));
	}
	
	protected void onPostExecute(String[] lines) {
		(new Thread(new ListPlayer(activity, lines))).start();
	}
}