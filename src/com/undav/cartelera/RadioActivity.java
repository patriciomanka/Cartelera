package com.undav.cartelera;

		

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import com.undav.cartelera.R.drawable;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;


import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;




public class RadioActivity extends AppCompatActivity {
	private Button detener,llamar;
	private MediaPlayer player;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radio_main);
 	    Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
 	    setSupportActionBar(myToolbar);
 	   myToolbar.setLogo (drawable.icono);
        detener = (Button) findViewById (R.id.btPlay);
        llamar = (Button) findViewById (R.id.btLlamar);  
        iniciarMediaPlayer ();
    }
    
    public void reproducir (View v) {
       if (player.isPlaying()) {
           player.stop();
       }
       else {
      	 iniciarMediaPlayer ();
       }
    }
    
    public void iniciarMediaPlayer () {
   	 player = new MediaPlayer();
        try {
      	  player.setDataSource("http://190.221.146.74:8002/radioundav");
      	  player.prepareAsync();

      	  player.setOnPreparedListener(new OnPreparedListener() {

                public void onPrepared(MediaPlayer mp) {
               	 player.start();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void llamar (View v) {
   	 startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:(54 11) 4229-2451")));
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = null;
	    switch (item.getItemId()) {
	        case R.id.action_about:
					intent = new Intent(RadioActivity.this, InfoActivity.class);
					startActivity(intent);
					return true;
	        case R.id.action_buscar:
	      	  intent = new Intent(RadioActivity.this, CarreraActivity.class);
		        startActivity(intent);
	            return true;
	            
	        case R.id.action_noticias:
		    	   intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.undav.edu.ar/index.php?idcateg=30"));
			      startActivity(intent);
	            return true;

	        case R.id.action_radio:
					intent = new Intent(RadioActivity.this, RadioActivity.class);
					startActivity(intent);
	            return true;
	        case R.id.action_sedes:
	      	  intent = new Intent(RadioActivity.this, SedesActivity.class);				        
		          startActivity(intent);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);

	    }
	}
}