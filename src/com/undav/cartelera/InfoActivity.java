package com.undav.cartelera;

import com.undav.cartelera.R.drawable;
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

public class InfoActivity extends AppCompatActivity {
	private Button fb,web;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info_main);
	    Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
	    setSupportActionBar(myToolbar);
	    myToolbar.setLogo (drawable.icono);
		fb = (Button) findViewById (R.id.btFB);
		fb.setOnClickListener (new OnClickListener () {
			public void onClick (View arg0) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/kandazteam")));
         }
      });
		web = (Button) findViewById (R.id.btWeb);
		web.setOnClickListener (new OnClickListener () {
			public void onClick (View arg0) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://kandazteam.wix.com/kandaz")));
         }
      });
	}
	public void onBackPressed() {
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
					intent = new Intent(InfoActivity.this, InfoActivity.class);
					startActivity(intent);
					return true;
	        case R.id.action_buscar:
	      	  intent = new Intent(InfoActivity.this, CarreraActivity.class);
		        startActivity(intent);
	            return true;
	            
	        case R.id.action_noticias:
		    	   intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.undav.edu.ar/index.php?idcateg=30"));
			      startActivity(intent);
	            return true;

	        case R.id.action_radio:
					intent = new Intent(InfoActivity.this, RadioActivity.class);
					startActivity(intent);
	            return true;
	        case R.id.action_sedes:
	      	  intent = new Intent(InfoActivity.this, SedesActivity.class);				        
		          startActivity(intent);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);

	    }
	}
}