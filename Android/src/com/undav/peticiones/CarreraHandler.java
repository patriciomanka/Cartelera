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

public class CarreraHandler extends JsonHttpResponseHandler {
	 private ProgressDialog pDialog;
	 private Context context;
	 private Spinner spc;
	 
	 public CarreraHandler (Context context, Spinner spc) {
		 super ();
		 this.context = context;
		 this.spc = spc;
		 pDialog = new ProgressDialog(context);
	 }
	 
	  @Override
     public void onStart() {
        this.pDialog.setMessage("Cargando Carreras ...");
        this.pDialog.setIndeterminate(false);
        this.pDialog.show();
     }
	 

     @Override
     public void onSuccess(int statusCode, Header[] headers, JSONArray array) {
   	   if(array.length ()==0){
   	    	Toast.makeText(context,
                       "No Hay carreras Activas", Toast.LENGTH_LONG).show ();
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
	               "No hay carreras cargadas", Toast.LENGTH_LONG).show ();
	   		return; 
	   	}
	   	Toast.makeText(context,
              "Error: "+statusCode, Toast.LENGTH_LONG).show ();
	   }
}
