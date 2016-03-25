package com.undav.cartelera;


import org.json.JSONArray;

import com.undav.adapter.AdaptadorNoticia;
import com.undav.datos.Noticia;
import com.undav.peticiones.Post;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

public class NoticiasActivity extends Activity{

	private AdaptadorNoticia adaptadorn;
	private ListView lvNoticias;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_noticias);
		lvNoticias=(ListView)findViewById(R.id.lvNoticias);
		getNoticias cargarNoticias = new getNoticias(NoticiasActivity.this);
    	try{
    		cargarNoticias.execute();
    	}catch(Exception e) {
    		Toast.makeText(getApplicationContext(),
                    "No hay Noticias Cargadas", Toast.LENGTH_LONG).show ();
    	}
		}
	
public class getNoticias extends AsyncTask<Void, Void, AdaptadorNoticia> {
		
		@SuppressWarnings("unused")
		private Context context;
		private ProgressDialog pDialog;
		
		public getNoticias(Context context){
			this.context=context;
		    this.pDialog= new ProgressDialog(context);
		}
		@Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            
	            this.pDialog.setMessage("Cargando Noticias ...");
	            this.pDialog.setIndeterminate(false);
	            this.pDialog.show();
	      }
		
		  @Override
		  protected AdaptadorNoticia doInBackground(Void... arg0) { 
			Noticia[] respuesta=null;
			Post consulta=null;
			JSONArray consultaj=null;
		
			
			try {
				consulta = new Post ("http://kandaz.co.nf/Undav/obtener_noticias.php");
				consultaj= consulta.getJSONArray ();
				respuesta = new Noticia [consultaj.length ()];
			   for (int i=0; i<consultaj.length (); i++) 
			   {
				   respuesta[i]= new Noticia(( consultaj.optJSONObject(i).optString("fecha").toString()),
						   ( consultaj.optJSONObject(i).optString("mensaje").toString()));
						   
			   }
			          
			    
			} catch (Exception e) {
				return null;
			}
			
			adaptadorn = new AdaptadorNoticia(NoticiasActivity.this, respuesta);
			
			return adaptadorn;
	}
		 
	protected void onPostExecute(AdaptadorNoticia result) {
		    super.onPostExecute(result);
		    lvNoticias.setAdapter(adaptadorn);
		    this.pDialog.dismiss();
		    if(result ==null){
		    	Toast.makeText(getApplicationContext(),
	                    "No Hay Noticias Cargadas", Toast.LENGTH_LONG).show ();
		    }
		    
		}
	}

	
}
