package com.undav.radio;



import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;


import com.undav.cartelera.RadioActivity;
import android.annotation.SuppressLint;
import android.content.Context;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;


public class ListPlayer implements Runnable {
	private String [] uris;
	private RadioActivity activity;
	private MediaPlayer player;
	private boolean keepPlaying = true;
	
	public ListPlayer(RadioActivity activity, String[] uris) {
		this.uris = uris;
		this.activity = activity;
		activity.listPlayer = this;
	}
	
	public void run() {
    	for (String uri:uris) {
    		if (!keepPlaying) {
    			break;
    		}
    		play1(uri);
    		synchronized(this) {
    			try {	
    				this.wait();
    			} catch (Exception e) {
    			}
    		}
    	}
	}
	
	private void play1(String uriStr) {
        try {   
        	tryMediaPlayer(uriStr);
        } catch(Exception e) {
			try {
				downloadToLocalFile(uriStr);
	        	File localFile = activity.getFileStreamPath("audiofile.ogg");
	        	tryMediaPlayer(localFile.getAbsolutePath());
			} catch(Exception e2) {
				System.out.println("File error " + e2.toString());
			}
        }
	}
	
	@SuppressLint("WorldReadableFiles")
	private void downloadToLocalFile(String uriStr) throws Exception {
		URL url = new URL(Uri.encode(uriStr, ":/"));
		BufferedInputStream reader = 
				new BufferedInputStream(url.openStream());
		
    	@SuppressWarnings("unused")
		File f = new File("audiofile.ogg");
		@SuppressWarnings("deprecation")
		FileOutputStream fOut = activity.openFileOutput("audiofile.ogg",
                Context.MODE_WORLD_READABLE);
		BufferedOutputStream writer = new BufferedOutputStream(fOut); 
		
		byte[] buff = new byte[512]; 
		int nread;
		System.out.println("Downloading");
		while ((nread = reader.read(buff, 0, 512)) != -1) {
			writer.write(buff, 0, nread);
		}
		writer.close();
	}
	
	private void tryMediaPlayer(String uriStr) throws Exception {
       	player = new MediaPlayer();
    	player.setOnCompletionListener(new OnCompletionListener() {
    		public void onCompletion(MediaPlayer player) {
    			synchronized(ListPlayer.this) {
    				ListPlayer.this.notifyAll();
    			}
    		}
    	});
    	player.setAudioStreamType(AudioManager.STREAM_MUSIC);
    	player.setDataSource(uriStr);
    	player.prepare();
    	player.start();		
	}
	
	public void stopCurrentTrack() {
		player.stop();
		synchronized(this) {
			notifyAll();
		}
	}
	
	public void stopPlaying() {
		keepPlaying = false;
		player.stop();
		synchronized(this) {
			notifyAll();
		}
	}
}