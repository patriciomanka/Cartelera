package com.undav.adapter;


import com.undav.cartelera.R;
import com.undav.datos.Noticia;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;



@SuppressLint("InflateParams")
public class AdaptadorNoticia extends ArrayAdapter<Noticia> {
	private Activity context;
	private Noticia[] datos;
	
	public AdaptadorNoticia(Activity context, Noticia[] datos) {
        super(context, R.layout.adaptador_noticia, datos);
        this.context=context;
        this.datos=datos;
    }
	public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        RecordHolder holder=null;
        
		if(item==null){
			LayoutInflater inflater = context.getLayoutInflater();
			item = inflater.inflate(R.layout.adaptador_noticia, null);
			holder = new RecordHolder();

			holder.fecha = (TextView)item.findViewById(R.id.tvFecha);
			holder.noticia = (TextView)item.findViewById(R.id.tvNoticia);
			
			
			item.setTag(holder);
			
		}else {
			   holder = (RecordHolder) item.getTag();
		}
		
	    
        holder.fecha.setText(Html.fromHtml(datos[position].getFecha()));
        holder.noticia.setText(Html.fromHtml(datos[position].getNoticia()));
        
        return(item);
    }
	
	static class RecordHolder {
		  TextView fecha;
		  TextView noticia;
		 }
}
