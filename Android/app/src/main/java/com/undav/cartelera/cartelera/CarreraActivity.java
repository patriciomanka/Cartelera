package com.undav.cartelera.cartelera;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.loopj.android.http.AsyncHttpClient;
import com.undav.cartelera.R;
import com.undav.cartelera.R.drawable;
import com.undav.cartelera.peticiones.CarreraHandler;

public class CarreraActivity extends AppCompatActivity {
	private Spinner spc;
	private Button btc;
	private String carrera;
	private ArrayAdapter<String> adaptador;
	private String baseUrl = "kandaz.co.nf/Cartelera";
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.carrera_main);
	    Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
	    setSupportActionBar(myToolbar);
	    myToolbar.setLogo (drawable.icono);
	
	     spc = (Spinner)findViewById(R.id.spc);
        btc = (Button)findViewById(R.id.btc); 
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://"+baseUrl+"/index.php/carrera", new CarreraHandler (CarreraActivity.this, spc));
}
    	
public void buscarMaterias (View v) {
	  carrera = spc.getSelectedItem().toString();
	  Bundle b = new Bundle();
	  b.putString("Carrera",carrera);
	  Intent intentc = new Intent(CarreraActivity.this, MateriaActivity.class);
	  intentc.putExtras(b);
	  startActivity(intentc);
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
				intent = new Intent(CarreraActivity.this, InfoActivity.class);
				startActivity(intent);
				return true;
        case R.id.action_buscar:
      	  intent = new Intent(CarreraActivity.this, CarreraActivity.class);
	        startActivity(intent);
            return true;
            
        case R.id.action_noticias:
	    	   intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.undav.edu.ar/index.php?idcateg=30"));
		      startActivity(intent);
            return true;

        case R.id.action_radio:
				intent = new Intent(CarreraActivity.this, RadioActivity.class);
				startActivity(intent);
            return true;
        case R.id.action_sedes:
      	  intent = new Intent(CarreraActivity.this, SedesActivity.class);				        
	          startActivity(intent);
            return true;
        default:
            return super.onOptionsItemSelected(item);

    }
}
}