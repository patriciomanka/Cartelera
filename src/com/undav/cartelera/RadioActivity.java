package com.undav.cartelera;

		

import com.undav.cartelera.R.drawable;
import com.undav.radio.DownloadM3U;
import com.undav.radio.ListPlayer;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;


import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;




public class RadioActivity extends AppCompatActivity {
	public ListPlayer listPlayer;
	private Button detener,llamar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radio_main);
 	    Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
 	    setSupportActionBar(myToolbar);
 	   myToolbar.setLogo (drawable.icono);
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