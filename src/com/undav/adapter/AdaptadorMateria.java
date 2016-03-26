package com.undav.adapter;


import com.undav.cartelera.R;
import com.undav.datos.Materia;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdaptadorMateria extends ArrayAdapter<Materia> {
	private Activity context;
	private Materia[] datos;
	
	public AdaptadorMateria(Activity context, Materia[] datos) {
        super(context, R.layout.adaptador_materia, datos);
        this.context=context;
        this.datos=datos;
    }
	public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        RecordHolder holder=null;
        
		if(item==null){
			LayoutInflater inflater = context.getLayoutInflater();
			item = inflater.inflate(R.layout.adaptador_materia, null);
			holder = new RecordHolder();
			
			holder.materia = (TextView)item.findViewById(R.id.tvMateria);
			item.setTag(holder);
		}else {
			   holder = (RecordHolder) item.getTag();
		}
        
        holder.materia.setText(datos[position].getMateria());
        return(item);
    }
	
	static class RecordHolder {
		  TextView materia;
		 }
}
