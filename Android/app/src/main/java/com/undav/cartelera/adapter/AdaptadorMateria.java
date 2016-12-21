package com.undav.cartelera.adapter;


import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.undav.cartelera.R;
import com.undav.cartelera.datos.Materia;

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
			holder = new RecordHolder(item);
			item.setTag(holder);
		}else {
			   holder = (RecordHolder) item.getTag();
		}
        
        holder.set(datos[position]);
        return(item);
    }
	
	private class RecordHolder {
		  private TextView materia;
		  
		  public RecordHolder (View item) {
			  materia = (TextView)item.findViewById(R.id.tvMateria);
		  }

		public void set (Materia m) {
			materia.setText (Html.fromHtml(m.getMateria ()));
		}
	}
}
