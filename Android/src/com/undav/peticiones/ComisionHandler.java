package com.undav.peticiones;

import org.json.JSONArray;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.undav.adapter.AdaptadorComision;
import com.undav.cartelera.R;
import com.undav.datos.Comision;
import cz.msebera.android.httpclient.Header;

public class ComisionHandler extends JsonHttpResponseHandler {
	 private ProgressDialog pDialog;
	 private Activity activity;
	 private ListView tabla;
	 private LinearLayout cabecera;
	 
	 
	 public ComisionHandler (AppCompatActivity activity, ListView tabla, LinearLayout cabecera) {
		 this.activity = activity;
		 this.tabla = tabla;
		 this.cabecera = cabecera;
		 pDialog = new ProgressDialog(activity);
	 }
	 
	  @Override
     public void onStart() {
        this.pDialog.setMessage("Cargando comision ...");
        this.pDialog.setIndeterminate(false);
        this.pDialog.show();
     }
	 

     @Override
     public void onSuccess(int statusCode, Header[] headers, JSONArray array) {
   	   if(array.length ()==0){
   	    	Toast.makeText(activity,
                       "No Hay comisiones cargadas de la carrera,materia y turno seleccionados", Toast.LENGTH_LONG).show ();
   	    	tabla.setVisibility(View.INVISIBLE);
    			cabecera.setVisibility(View.INVISIBLE);
   	    	return;
   	    }
     	
	  		Comision[] respuesta = new Comision [array.length ()];
		   for (int i=0; i<array.length (); i++) 
		   {
			   respuesta[i]= new Comision(( array.optJSONObject(i).optString("comision").toString()),
					   ( array.optJSONObject(i).optString("dia").toString()),
					   ( array.optJSONObject(i).optString("horaInicio").toString()),
					   ( array.optJSONObject(i).optString("horaFin").toString()),
					   ( array.optJSONObject(i).optString("aula").toString()));
		   }
	  		AdaptadorComision adaptador = new AdaptadorComision(activity, respuesta);
	   	pDialog.dismiss();
		    tabla.setAdapter(adaptador);
		    cabecera.setVisibility(View.VISIBLE);
		    tabla.setVisibility(View.VISIBLE);
     }

     @Override
     public void onRetry(int retryNo) {
    	   Toast.makeText(activity,
                       "Reintentando", Toast.LENGTH_LONG).show ();
     }
     
	   public void onFailure (int statusCode, Header[] headers, String responseString, Throwable throwable) {
	   	pDialog.dismiss();
	   	if (statusCode==404) {
	   		Toast.makeText(activity,
	               "No hay comisiones cargadas", Toast.LENGTH_LONG).show ();
	   		return; 
	   	}
	   	Toast.makeText(activity,
              "Error: "+statusCode, Toast.LENGTH_LONG).show ();
	   }
}
