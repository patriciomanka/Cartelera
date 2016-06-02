package com.undav.peticiones;

import org.json.JSONArray;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.undav.cartelera.R;
import cz.msebera.android.httpclient.Header;

public class MateriaHandler extends JsonHttpResponseHandler {
	 private ProgressDialog pDialog;
	 private Context context;
	 private Spinner spc;
	 
	 public MateriaHandler (Context context, Spinner spc) {
		 this.context = context;
		 this.spc = spc;
		 pDialog = new ProgressDialog(context);
	 }
	 
	  @Override
     public void onStart() {
        this.pDialog.setMessage("Cargando Materias ...");
        this.pDialog.setIndeterminate(false);
        this.pDialog.show();
     }
	 

     @Override
     public void onSuccess(int statusCode, Header[] headers, JSONArray array) {
   	   if(array.length ()==0){
   	    	Toast.makeText(context,
                       "No Hay materias cargadas de la carrera seleccionada", Toast.LENGTH_LONG).show ();
   	    	return;
   	    }
   	
     	
	  		String[] respuesta = new String [array.length ()];
	  		for (int i=0; i<array.length (); i++) 
	  			respuesta[i]= new String(( array.optJSONObject(i).optString("descripcion").toString()));
	  		
	  		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(context, R.layout.spinner_item, respuesta);
	  		spc.setAdapter (adaptador);
	   	pDialog.dismiss();
     }

     @Override
     public void onRetry(int retryNo) {
    	   Toast.makeText(context,
                       "Reintentando", Toast.LENGTH_LONG).show ();
     }
     
	   public void onFailure (int statusCode, Header[] headers, String responseString, Throwable throwable) {
	   	pDialog.dismiss();
	   	if (statusCode==404) {
	   		Toast.makeText(context,
	               "No hay materias cargadas", Toast.LENGTH_LONG).show ();
	   		return; 
	   	}
	   	Toast.makeText(context,
              "Error: "+statusCode, Toast.LENGTH_LONG).show ();
	   }
}
