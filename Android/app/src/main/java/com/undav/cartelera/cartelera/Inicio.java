package com.undav.cartelera.cartelera;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.undav.cartelera.R;

public class Inicio extends AppCompatActivity {
	private TextView kandaz;
	private static final long SPLASH_SCREEN_DELAY = 2000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		
		if (!verificaConexion (this)) {
		    Toast.makeText(getBaseContext(),
		            "Su dispositivo no posee conexion a internet. Por favor, conectese para poder utilizar la app.", Toast.LENGTH_LONG)
		            .show();
                     finish();
		}else{
   		Intent intentc = new Intent(Inicio.this, CarreraActivity.class);
   		startActivity(intentc);
   		finish();
		}
	}

	
	 public boolean verificaConexion(Context ctx) {
		ConnectivityManager cm = (ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		return  activeNetwork != null && activeNetwork.isConnectedOrConnecting();
	}
}
