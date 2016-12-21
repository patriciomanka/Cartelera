package com.undav.cartelera.adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.undav.cartelera.R;
import com.undav.cartelera.datos.Sede;

public class AdaptadorSede extends ArrayAdapter<Sede>{
	private Activity context;
	private Sede[] datos;
	private int[] colors = {R.color.Aqua, R.color.Gray};
	
	public AdaptadorSede (Activity context,Sede[] datos) {
		super(context, R.layout.layout_sede, datos); 
		this.context = context;
		 this.datos = datos;
	}

	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent)  {

	    View item = convertView;
	    RecordHolder holder=null;
	    int colorPos = position % 2;
	    
	    if(item == null)
	    {
	        LayoutInflater inflater = context.getLayoutInflater();
	        item = inflater.inflate(R.layout.layout_sede, null);
	        holder = new RecordHolder(item);
	        
			item.setTag(holder);
	    }else {
			   holder = (RecordHolder) item.getTag();
		}
	    holder.set (datos[position]);
	    item.setBackgroundColor(colors[colorPos]);
        
        return item;
		
	}
	private class RecordHolder {
		  private TextView nombre, direccion;
		  private Button btLlamar, btMapa;
		  
		  public RecordHolder (View item) {
			  nombre = (TextView)item.findViewById(R.id.nombre);
			  direccion = (TextView)item.findViewById(R.id.direccion);
			  btLlamar=(Button)item.findViewById(R.id.btLlamar);
	        btMapa=(Button)item.findViewById(R.id.btMapa);
		  }

		public void set (final Sede s) {
			nombre.setText (Html.fromHtml(s.getTitulo ()));
			direccion.setText (Html.fromHtml (s.getDireccion ()));
			
	        btLlamar.setOnClickListener (new OnClickListener () {
	  			public void onClick (View v) {
					try {
                        context.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + s.getTelefono())));
                    }
                    catch (SecurityException e) {

                    }
	           }
	          });

	         btMapa.setOnClickListener (new OnClickListener () {
	  			public void onClick (View v) {
	  				String query = s.getLatitud () + "," +  s.getLongitud ()   + "(" + s.getTitulo() + ")"; 
	  				String uriString = "geo:" + s.getLatitud () + "," + s.getLongitud () + "?q=" + Uri.encode(query) + "&z=16"; 
	  				Uri uri = Uri.parse(uriString); 
	  				Intent i = new Intent(Intent.ACTION_VIEW, uri);
	  				i.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
	  				context.startActivity(i);
	           }
	          });
		}
	}
}
