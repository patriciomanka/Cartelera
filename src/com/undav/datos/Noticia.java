package com.undav.datos;

public class Noticia {

	private String fecha;
	private String noticia;
	
	public Noticia(String f, String n){
		noticia =n;
		fecha =f;
	}

	public String getNoticia() {
		return noticia;
	}

	public void setNoticia(String noticia) {
		this.noticia = noticia;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	
}
