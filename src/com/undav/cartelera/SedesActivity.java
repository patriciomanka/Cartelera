package com.undav.cartelera;



import com.undav.datos.Sede;
import com.undav.adapter.AdaptadorSede;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import android.widget.ListView;


public class SedesActivity extends ActionBarActivity {
	
	private ListView lvs;
	private AdaptadorSede adaptadors;
	private Sede[] sede={new Sede("Sede España","España 350 esq. Colón, Avellaneda","(54 11) 4229-2400"),
			             new Sede("Sede Piñeyro","Mario Bravo 1460 esq. Isleta, Piñeyro","(54 11) 5436-7500"),
			             new Sede("Sede 12 de Octubre","12 de Octubre 463, Avellaneda","(54 11) 4201-7797"),
					     new Sede("Sede Constitución","Constitución 627, Avellaneda","(54 11) 4201-2479")};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sedes_main);
        lvs = (ListView)findViewById(R.id.lvs);
   
        adaptadors = new AdaptadorSede(this,sede);
 	     lvs.setAdapter(adaptadors);
	} 
	  
}
