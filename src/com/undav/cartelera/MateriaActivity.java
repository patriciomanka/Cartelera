package com.undav.cartelera;

import org.json.JSONArray;
import com.undav.adapter.AdaptadorComision;
import com.undav.cartelera.R.drawable;
import com.undav.datos.Comision;
import com.undav.peticiones.Post;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;



public class MateriaActivity extends AppCompatActivity {
	private Spinner spinner_materia;
	private Spinner spinner_turno;
	private Button boton_consultar;
	private String carrera,materia,turno;
	private ListView tabla;
	private ArrayAdapter<String> adaptador_materia;
	private ArrayAdapter<String> adaptador_turno;
	private AdaptadorComision adaptador_comision;
	private LinearLayout cabecera;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.materia_main);
	    Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
	    setSupportActionBar(myToolbar);
	    myToolbar.setLogo (drawable.icono);
    	cabecera=(LinearLayout)findViewById(R.id.cabecera);
		Bundle bundlem = this.getIntent().getExtras();
        carrera=bundlem.getString("Carrera");
        adaptador_turno = new ArrayAdapter<String>(MateriaActivity.this, R.layout.spinner_item,new String[]{"Mañana","Tarde","Noche"});
        getMateria cargarMateria = new getMateria(MateriaActivity.this);
    	try{
    		cargarMateria.execute();
    	}catch(Exception e) {
    		Toast.makeText(getApplicationContext(),
                    "No hay Materias Cargadas", Toast.LENGTH_LONG).show ();
    	}

    	spinner_materia = (Spinner)findViewById(R.id.spinner_materia);
    	spinner_turno = (Spinner)findViewById(R.id.spinner_turno);
    	spinner_turno.setEnabled(false);
    	spinner_turno.setAdapter(adaptador_turno);
        boton_consultar = (Button)findViewById(R.id.boton_consultar);
        tabla = (ListView)findViewById(R.id.lvHorarios);
        spinner_turno.setEnabled(false);
        cabecera.setVisibility(View.INVISIBLE);


	}
	
	public void consultarComision (View v) {
    	getComision cargarComision = new getComision(MateriaActivity.this);
    	try{
    		cargarComision.execute();
    	}catch(Exception e) {
    		Toast.makeText(getApplicationContext(),
                    "No hay Materias en Turno Seleccionado", Toast.LENGTH_LONG).show ();
    	}
	}
	
	public class getMateria extends AsyncTask<Void, Void, ArrayAdapter<String>> {
		
		private Context context;
		private ProgressDialog pDialog;
		
		public getMateria(Context context){
			this.context=context;
		    this.pDialog= new ProgressDialog(context);
		}
		@Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            
	            this.pDialog.setMessage("Cargando Materias ...");
	            this.pDialog.setIndeterminate(false);
	            this.pDialog.show();
	            this.pDialog.setCancelable(true);
	      }
		
		  @Override
		  protected ArrayAdapter<String> doInBackground(Void... arg0) { 
			String[] respuesta=null;
			Post consulta=null;
			JSONArray consultaj=null;
		
			
			try {
				consulta = new Post ("http://kandaz.co.nf/Undav/obtener_materias.php");
				consulta.agregar("carrera", carrera);
				consultaj= consulta.getJSONArray ();
				respuesta = new String [consultaj.length ()];
			   for (int i=0; i<consultaj.length (); i++) 
			   {
				   respuesta[i]= new String(( consultaj.optJSONObject(i).optString("materia").toString()));
			   }
			} catch (Exception e) {
				return null;
			}
			
			adaptador_materia = new ArrayAdapter<String>(MateriaActivity.this, R.layout.spinner_item,respuesta);
			
			return adaptador_materia;
	}
		 
	protected void onPostExecute(ArrayAdapter<String> result) {
		    super.onPostExecute(result);
			spinner_materia.setAdapter(adaptador_materia);
			spinner_turno.setEnabled(true);
		    this.pDialog.dismiss();
		    if(result ==null){
		    	Toast.makeText(getApplicationContext(),
	                    "No Hay Materias Cargadas", Toast.LENGTH_LONG).show ();
		    }
		    
		}
	}

public class getComision extends AsyncTask<Void, Void, AdaptadorComision> {
		
		private Context context;
		private ProgressDialog pDialog;
		
		public getComision(Context context){
			this.context=context;
		    this.pDialog= new ProgressDialog(context);
		}
		@Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            
	            this.pDialog.setMessage("Cargando Materias ...");
	            this.pDialog.setIndeterminate(false);
	            this.pDialog.show();
	      }
		
		  @Override
		  protected AdaptadorComision doInBackground(Void... arg0) { 
			Comision[] respuesta=null;
			Post consultac=null;
			JSONArray consultaj=null;
		
			
			try {
				consultac = new Post ("http://kandaz.co.nf/Undav/obtener_comision.php");
				consultac.agregar("materia", spinner_materia.getSelectedItem().toString());
				consultac.agregar("carrera", carrera);
				consultac.agregar("turno", spinner_turno.getSelectedItem().toString() );
				consultaj= consultac.getJSONArray ();
				respuesta = new Comision [consultaj.length ()];
			   for (int i=0; i<consultaj.length (); i++) 
			   {
				   respuesta[i]= new Comision(( consultaj.optJSONObject(i).optString("com").toString()),
						   ( consultaj.optJSONObject(i).optString("dia1").toString()),
						   ( consultaj.optJSONObject(i).optString("hi1").toString()),
						   ( consultaj.optJSONObject(i).optString("hf1").toString()),
						   ( consultaj.optJSONObject(i).optString("aula").toString()));
			   }
			} catch (Exception e) {
				return null;
			}
			
			adaptador_comision = new AdaptadorComision(MateriaActivity.this, respuesta);
			
			return adaptador_comision;
	}
		 
	protected void onPostExecute(AdaptadorComision result) {
		    super.onPostExecute(result);	
		    this.pDialog.dismiss();
		    tabla.setAdapter(adaptador_comision);
		    cabecera.setVisibility(View.VISIBLE);
		    tabla.setVisibility(View.VISIBLE);
		    if(result ==null){
		    	Toast.makeText(getApplicationContext(),
	                    "No existen Materias en turno seleccionado", Toast.LENGTH_LONG).show ();
		    			tabla.setVisibility(View.INVISIBLE);
		    			cabecera.setVisibility(View.INVISIBLE);
		    			
		    }
		    
		}
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