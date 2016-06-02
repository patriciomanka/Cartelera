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
	private int[] colors = {R.color.Aqua, R.color.Gray};
	
	public AdaptadorComision(Activity context, Comision[] datos) {
        super(context, R.layout.adaptador_comision, datos);
        this.context=context;
        this.datos=datos;
    }
	public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        RecordHolder holder=null;
        int colorPos = position % 2;
        
		if(item==null){
			LayoutInflater inflater = context.getLayoutInflater();
			item = inflater.inflate(R.layout.adaptador_comision, null);
			holder = new RecordHolder(item);
			item.setTag(holder);
			
		}else {
			   holder = (RecordHolder) item.getTag();
		}
		
	   item.setBackgroundColor(colors[colorPos]);
       
	    holder.set(datos[position]);
        
        return(item);
    }
	
	private class RecordHolder {
		  private TextView comision, dia, hora1, hora2, aula;
		  
		  public RecordHolder (View item) {
			  comision = (TextView)item.findViewById(R.id.tvComision);
				dia = (TextView)item.findViewById(R.id.tvDia);
				hora1 = (TextView)item.findViewById(R.id.tvHora1);
				hora2 = (TextView)item.findViewById(R.id.tvHora2);
				aula = (TextView)item.findViewById(R.id.tvAula);
		  }

		public void set (Comision c) {
			comision.setText (Html.fromHtml(c.getComision ()));
			dia.setText (Html.fromHtml(c.getDia ()));
			hora1.setText (Html.fromHtml(c.getHoraInicio ()));
			hora2.setText (Html.fromHtml(c.getHoraFin ()));
			aula.setText (Html.fromHtml(c.getAula ()));
		}
	}
}
