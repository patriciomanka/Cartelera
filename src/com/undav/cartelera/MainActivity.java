package com.undav.cartelera;

import com.undav.cartelera.MainActivity;


import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

	private Button btAula,btNoticia,btRadio,btSede,btSalir;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		OnClickListener ListenerInfo =new OnClickListener(){
			@Override
		    public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this, InfoActivity.class);
				startActivity(intent);
			}
		};
		
		OnClickListener ListenerRadio =new OnClickListener(){
			@Override
		    public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this, RadioActivity.class);
				startActivity(intent);
			}
		};
		OnClickListener ListenerAula =new OnClickListener(){

			    @Override
			    public void onClick(View arg0) {
						Intent intent = new Intent(MainActivity.this, CarreraActivity.class);
				        startActivity(intent);
					}
			 };
			 
				OnClickListener ListenerSede =new OnClickListener(){

				    @Override
				    public void onClick(View arg0) {
							Intent intent = new Intent(MainActivity.this, SedesActivity.class);				        
					        startActivity(intent);
						}
				 };
					
				 OnClickListener ListenerNoticias =new OnClickListener(){

					    @Override
					    public void onClick(View arg0) {
					    	    Intent intentc = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.undav.edu.ar/index.php?idcateg=30"));
						   		startActivity(intentc);
						   		finish();
							}
					 };
		btAula=(Button)findViewById(R.id.btAula);
		btAula.setOnClickListener(ListenerAula);
		btNoticia=(Button)findViewById(R.id.btNoticia);
		btNoticia.setOnClickListener(ListenerNoticias);
		btRadio=(Button)findViewById(R.id.btRadio);
		btRadio.setOnClickListener(ListenerRadio);
		btSede=(Button)findViewById(R.id.btSede);
		btSede.setOnClickListener(ListenerSede);
		btSalir =(Button) findViewById(R.id.btInfo);
		btSalir.setOnClickListener(ListenerInfo);	
	}
	
}