package com.undav.adapter;


import com.undav.cartelera.R;
import com.undav.datos.Comision;

import android.app.Activity;
import android.graphics.Color;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdaptadorComision extends ArrayAdapter<Comision> {
	private Activity context;
	private Comision[] datos;
	
	public AdaptadorComision(Activity context, Comision[] datos) {
        super(context, R.layout.adaptador_comision, datos);
        this.context=context;
        this.datos=datos;
    }
	public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        RecordHolder holder=null;
        
		if(item==null){
			LayoutInflater inflater = context.getLayoutInflater();
			item = inflater.inflate(R.layout.adaptador_comision, null);
			holder = new RecordHolder();

			holder.com = (TextView)item.findViewById(R.id.tvCom);
			holder.dia1 = (TextView)item.findViewById(R.id.tvDia1);
			holder.hi1 = (TextView)item.findViewById(R.id.tvHi1);
			holder.hf1 = (TextView)item.findViewById(R.id.tvHf1);
			holder.aula1 = (TextView)item.findViewById(R.id.tvAula1);
			
			item.setTag(holder);
			
		}else {
			   holder = (RecordHolder) item.getTag();
		}
		
	    if(position%2==0){
			item.setBackgroundColor(Color.parseColor("#00284a"));		
			}else{
				item.setBackgroundColor(Color.parseColor("#006aa5"));
			}
       
	    holder.com.setText(Html.fromHtml(datos[position].getComision()));
        holder.dia1.setText(Html.fromHtml(datos[position].getDia()));
        holder.hi1.setText(Html.fromHtml(datos[position].getHoraInicio()));
        holder.hf1.setText(Html.fromHtml(datos[position].getHoraFin()));
        holder.aula1.setText(Html.fromHtml(datos[position].getAula()));
        return(item);
    }
	
	static class RecordHolder {
		  TextView com;
		  TextView dia1;
		  TextView hi1;
		  TextView hf1;
		  TextView aula1;
		 }
}
