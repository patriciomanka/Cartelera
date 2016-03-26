package com.undav.cartelera;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class Inicio extends AppCompatActivity {
	private TextView kandaz;
	private static final long SPLASH_SCREEN_DELAY = 2000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		
		if (!verificaConexion (this)) {
		    Toast.makeText(getBaseContext(),
		            "Sin conexión a Internet. Saliendo ... ", Toast.LENGTH_LONG)
		            .show();
                     finish();
		}else{
   		Intent intentc = new Intent(Inicio.this, CarreraActivity.class);
   		startActivity(intentc);
   		finish();
		}
	}

	
	 public boolean verificaConexion(Context ctx) {
		 	boolean bConectado =false;
		    ConnectivityManager connec = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
		    NetworkInfo[] redes = connec.getAllNetworkInfo();
		    for (int i = 0; i < redes.length; i++) {
		        if (redes[i].getState() == NetworkInfo.State.CONNECTED) 
		            bConectado= true;
		    }
		    return bConectado;
		}
}
