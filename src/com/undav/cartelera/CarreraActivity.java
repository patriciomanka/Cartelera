package com.undav.cartelera;

import org.json.JSONArray;

import com.undav.peticiones.Post;
import com.undav.cartelera.R;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class CarreraActivity extends Activity {
	private Spinner spc;
	private Button btc;
	private String carrera;
	private ArrayAdapter<String> adaptador;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.carrera_main);
		
		 OnClickListener ListenerCarrera =new OnClickListener(){
        	 @Override
  		  public void onClick(View v) {
  			  carrera = spc.getSelectedItem().toString();
  			  Bundle b = new Bundle();
  			  b.putString("Carrera",carrera);
  			  Intent intentc = new Intent(CarreraActivity.this, MateriaActivity.class);
  			  intentc.putExtras(b);
  			  startActivity(intentc);
  		    }
  		  };
		
	    spc = (Spinner)findViewById(R.id.spc);
        btc = (Button)findViewById(R.id.btc);
        btc.setOnClickListener(ListenerCarrera);        
       

        //Obtener carreras
        getCarrera cargarCarrera = new getCarrera(CarreraActivity.this);
    	try{
    	cargarCarrera.execute();
    	spc.setAdapter(adaptador);
    	}catch(Exception e) {
    		Toast.makeText(getApplicationContext(),
                    "No hay Materias Cargadas", Toast.LENGTH_LONG).show ();
    	} 
}
    	
	
	
public class getCarrera extends AsyncTask<Void, Void, ArrayAdapter<String>> {
	
	@SuppressWarnings("unused")
	private Context context;
	private ProgressDialog pDialog;
	
	public getCarrera(Context context){
		this.context=context;
	    this.pDialog= new ProgressDialog(context);
	}
	@Override
        protected void onPreExecute() {
            super.onPreExecute();
            
            this.pDialog.setMessage("Cargando Carreras ...");
            this.pDialog.setIndeterminate(false);
            this.pDialog.show();
      }
	
	  @Override
	  protected ArrayAdapter<String> doInBackground(Void... arg0) { 
		String[] respuesta=null;
		Post consulta=null;
		JSONArray consultaj=null;
	
		
		try {
			consulta = new Post ("http://kandaz.co.nf/Undav/obtener_carreras.php");
			consultaj= consulta.getJSONArray ();
			respuesta = new String [consultaj.length ()];
		   for (int i=0; i<consultaj.length (); i++) 
		   {
			   respuesta[i]= new String(( consultaj.optJSONObject(i).optString("carrera").toString()));
		   }
		} catch (Exception e) {
			return null;
		}
		
		adaptador = new ArrayAdapter<String>(CarreraActivity.this, R.layout.spinner_item, respuesta);
		
		return adaptador;
}
	 
protected void onPostExecute(ArrayAdapter<String> result) {
	    super.onPostExecute(result);
		spc.setAdapter(adaptador);
	    this.pDialog.dismiss();
	    if(result ==null){
	    	Toast.makeText(getApplicationContext(),
                    "No Hay carreras Activas", Toast.LENGTH_LONG).show ();
	    }
	    
	}
}

}