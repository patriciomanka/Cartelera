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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.undav.cartelera.R;
import com.undav.cartelera.R.drawable;
import com.undav.cartelera.adapter.AdaptadorComision;
import com.undav.cartelera.peticiones.ComisionHandler;
import com.undav.cartelera.peticiones.MateriaHandler;



public class MateriaActivity extends AppCompatActivity {
	private Spinner spinner_materia;
	private Spinner spinner_turno;
	private Button boton_consultar;
	private String carrera,materia,turno;
	private ListView tabla;
	private LinearLayout cabecera;
	private ArrayAdapter<String> adaptador_materia;
	private ArrayAdapter<String> adaptador_turno;
	private AdaptadorComision adaptador_comision;
	private String baseUrl = "kandaz.co.nf/Cartelera";
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.materia_main);
	    Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
	    setSupportActionBar(myToolbar);
	    myToolbar.setLogo (drawable.icono);
    	cabecera=(LinearLayout)findViewById(R.id.cabecera);
		Bundle bundlem = this.getIntent().getExtras();
        carrera=bundlem.getString("Carrera");
        adaptador_turno = new ArrayAdapter<String>(MateriaActivity.this, R.layout.spinner_item,new String[]{"mañana","tarde","noche"});
      spinner_materia = (Spinner)findViewById(R.id.spinner_materia);
    	spinner_turno = (Spinner)findViewById(R.id.spinner_turno);
    	spinner_turno.setAdapter(adaptador_turno);
        boton_consultar = (Button)findViewById(R.id.boton_consultar);
        tabla = (ListView)findViewById(R.id.lvHorarios);
        cabecera.setVisibility(View.INVISIBLE);

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams ();
        params.add ("carrera", carrera);
        client.post("http://"+baseUrl+"/index.php/carrera/materia", params, new MateriaHandler (MateriaActivity.this, spinner_materia));

	}
	
	public void consultarComision (View v) {
      AsyncHttpClient client = new AsyncHttpClient();
      RequestParams params = new RequestParams ();
      params.add("materia", spinner_materia.getSelectedItem().toString());
      params.add("carrera", carrera);
      params.add("turno", spinner_turno.getSelectedItem().toString() );
      client.post("http://"+baseUrl+"/index.php/carrera/comision", params, new ComisionHandler (MateriaActivity.this, tabla, cabecera));
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
				intent = new Intent(MateriaActivity.this, InfoActivity.class);
				startActivity(intent);
				return true;
        case R.id.action_buscar:
      	  intent = new Intent(MateriaActivity.this, CarreraActivity.class);
	        startActivity(intent);
            return true;
            
        case R.id.action_noticias:
	    	   intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.undav.edu.ar/index.php?idcateg=30"));
		      startActivity(intent);
            return true;

        case R.id.action_radio:
				intent = new Intent(MateriaActivity.this, RadioActivity.class);
				startActivity(intent);
            return true;
        case R.id.action_sedes:
      	  intent = new Intent(MateriaActivity.this, SedesActivity.class);				        
	          startActivity(intent);
            return true;
        default:
            return super.onOptionsItemSelected(item);

    }
    
}
}