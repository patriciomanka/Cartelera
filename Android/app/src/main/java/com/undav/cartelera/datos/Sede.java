package com.undav.cartelera.datos;

public class Sede {

	private String titulo,direccion,telefono;
	private double latitud,longitud;
	
	public Sede (String titulo, String direccion, String telefono, double latitud, double longitud){
		this.titulo=titulo;
		this.direccion=direccion;
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}
