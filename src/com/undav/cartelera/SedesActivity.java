package com.undav.cartelera;



import com.undav.datos.Sede;
import com.undav.adapter.AdaptadorSede;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import android.widget.ListView;


@SuppressWarnings("deprecation")
public class SedesActivity extends Activity{
	
	private ListView lvs;
	private AdaptadorSede adaptadors;
	private Sede[] sede={new Sede("Sede Espa�a","Espa�a 350 esq. Col�n, Avellaneda","(54 11) 4229-2400"),
			             new Sede("Sede Pi�eyro","Mario Bravo 1460 esq. Isleta, Pi�eyro","(54 11) 5436-7500"),
			             new Sede("Sede 12 de Octubre","12 de Octubre 463, Avellaneda","(54 11) 4201-7797"),
					     new Sede("Sede Constituci�n","Constituci�n 627, Avellaneda","(54 11) 4201-2479")};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sedes_main);
        lvs = (ListView)findViewById(R.id.lvs);
   
        adaptadors = new AdaptadorSede(this,sede);
 	     lvs.setAdapter(adaptadors);
	} 
	  
}
