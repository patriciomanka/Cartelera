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
				float lat=0;
				float lng=0;
				String label = datos[position].getTitulo();
				if(position==0){
					lat=(float) -34.665217847204694;
					lng=(float) -58.37436263930203;
				}else if (position==1){
					lat=(float) -34.668618910983106;
					lng=(float) -58.398067200000014;
				}else if (position==2){
					lat=(float) -34.6607651109799;
					lng=(float) -58.35657015000004;
				}else if (position==3){
					lat=(float) -34.66698611098245;
					lng=(float) -58.37000664999999;
				}
				String uriBegin = "geo:" + lat + "," + lng; 
				String query = lat + "," + lng + "(" + label + ")"; 
				String encodedQuery = Uri.encode(query); 
				String uriString = uriBegin + "?q=" + encodedQuery + "&z=16"; 
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
