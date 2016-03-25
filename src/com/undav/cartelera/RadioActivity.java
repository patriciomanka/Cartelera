package com.undav.cartelera;

		

import com.undav.radio.DownloadM3U;
import com.undav.radio.ListPlayer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;


import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;




public class RadioActivity extends Activity {
	public ListPlayer listPlayer;
	private Button detener,llamar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radio_main);
        detener = (Button) findViewById (R.id.btDetener);
        llamar = (Button) findViewById (R.id.btLlamar);
        detener.setOnClickListener (new OnClickListener () {
			public void onClick (View arg0) {
				listPlayer.stopPlaying();
         }
        });
        llamar.setOnClickListener (new OnClickListener () {
			public void onClick (View arg0) {
			   startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:4229-2451")));
         }
        });       
			new DownloadM3U(this).execute("http://undav.edu.ar/radioundav/radioundav.m3u");  
    }

    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
    	synchronized(listPlayer) {
    		listPlayer.notifyAll();
    	}
    }
	public void onBackPressed() {
		listPlayer.stopPlaying();
		listPlayer = null;
	   finish();
	}

}