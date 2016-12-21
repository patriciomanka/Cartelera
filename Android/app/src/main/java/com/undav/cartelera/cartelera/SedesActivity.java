package com.undav.cartelera.cartelera;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.undav.cartelera.R;
import com.undav.cartelera.R.drawable;
import com.undav.cartelera.adapter.AdaptadorSede;
import com.undav.cartelera.datos.Sede;


public class SedesActivity extends AppCompatActivity {
	
	private ListView lvs;
	private AdaptadorSede adaptadors;
	private Sede[] sede={new Sede("Sede España","España 350 esq. Colón, Avellaneda","(54 11) 4229-2400", -34.665217847204694, -58.37436263930203),
			             new Sede("Sede Piñeyro","Mario Bravo 1460 esq. Isleta, Piñeyro","(54 11) 5436-7500",-34.668618910983106, -58.398067200000014),
			             new Sede("Sede 12 de Octubre","12 de Octubre 463, Avellaneda","(54 11) 4201-7797",-34.6607651109799, -58.35657015000004),
					     new Sede("Sede Constitución","Constitución 627, Avellaneda","(54 11) 4201-2479",-34.66698611098245, -58.37000664999999)};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sedes_main);
		
	    Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
	    setSupportActionBar(myToolbar);
	    myToolbar.setLogo (drawable.icono);
        lvs = (ListView)findViewById(R.id.lvs);
   
        adaptadors = new AdaptadorSede(this,sede);
 	     lvs.setAdapter(adaptadors);
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
					intent = new Intent(SedesActivity.this, InfoActivity.class);
					startActivity(intent);
					return true;
	        case R.id.action_buscar:
	      	  intent = new Intent(SedesActivity.this, CarreraActivity.class);
		        startActivity(intent);
	            return true;
	            
	        case R.id.action_noticias:
		    	   intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.undav.edu.ar/index.php?idcateg=30"));
			      startActivity(intent);
	            return true;

	        case R.id.action_radio:
					intent = new Intent(SedesActivity.this, RadioActivity.class);
					startActivity(intent);
	            return true;
	        case R.id.action_sedes:
	      	  intent = new Intent(SedesActivity.this, SedesActivity.class);				        
		          startActivity(intent);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);

	    }
	}
}
