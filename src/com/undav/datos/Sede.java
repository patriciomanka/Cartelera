package com.undav.datos;

public class Sede {

	private String titulo,cuerpo,telefono;
	private double latitud,longitud;
	
	public Sede (String titulo, String cuerpo, String telefono, double latitud, double longitud){
		this.titulo=titulo;
		this.cuerpo=cuerpo;
		this.telefono=telefono;
		this.latitud=latitud;
		this.longitud=longitud;
	}
	
	
	
	public double getLatitud () {
		return latitud;
	}



	public void setLatitud (double latitud) {
		this.latitud = latitud;
	}



	public double getLongitud () {
		return longitud;
	}



	public void setLongitud (double longitud) {
		this.longitud = longitud;
	}



	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}
	
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}
