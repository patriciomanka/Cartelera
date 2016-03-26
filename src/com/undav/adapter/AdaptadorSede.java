package com.undav.adapter;

import com.undav.cartelera.R;
import com.undav.datos.Sede;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class AdaptadorSede extends ArrayAdapter<Sede>{
	private Activity context;
	private Sede[] datos;
	
	public AdaptadorSede (Activity context,Sede[] datos) {
		super(context, R.layout.layout_sede, datos); 
		this.context = context;
		 this.datos = datos;
	}

	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent)  {

	    View item = convertView;
	    RecordHolder holder=null;
	    
	    if(item == null)
	    {
	        LayoutInflater inflater = context.getLayoutInflater();
	        item = inflater.inflate(R.layout.layout_sede, null);
	        holder = new RecordHolder();
	        holder.nombre = (TextView) item.findViewById(R.id.nombre);
	        holder.direccion=(TextView) item.findViewById(R.id.direccion);
	        holder.btLlamar=(Button)item.findViewById(R.id.btLlamar);
	        holder.btMapa=(Button)item.findViewById(R.id.btMapa);
	        
			item.setTag(holder);
	    }else {
			   holder = (RecordHolder) item.getTag();
		}
	    
	    if(position%2==0){
			item.setBackgroundColor(Color.parseColor("#00284a"));		
			}else{
				item.setBackgroundColor(Color.parseColor("#006aa5"));
			}
       
        holder.nombre.setText(datos[position].getTitulo());
        holder.direccion.setText(datos[position].getCuerpo());
        holder.btLlamar.setOnClickListener (new OnClickListener () {
			public void onClick (View v) {
			   context.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+datos[position].getTelefono())));
         }
        });

        holder.btMapa.setOnClickListener (new OnClickListener () {
			public void onClick (View v) {
				String query = datos[position].getLatitud () + "," +  datos[position].getLongitud ()   + "(" + datos[position].getTitulo() + ")"; 
				String uriString = "geo:" + datos[position].getLatitud () + "," + datos[position].getLongitud () + "?q=" + Uri.encode(query) + "&z=16"; 
				Uri uri = Uri.parse(uriString); 
				Intent i = new Intent(android.content.Intent.ACTION_VIEW, uri);
				i.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
				context.startActivity(i);
         }
        });
        
        return item;
		
	}
	
	static class RecordHolder {
		TextView nombre;
		TextView direccion;
		Button btLlamar;
		Button btMapa;
		 }
}
