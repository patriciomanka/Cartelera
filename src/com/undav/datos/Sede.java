package com.undav.datos;

public class Sede {

	private String titulo,cuerpo,telefono;

	
	public Sede (String tit, String cue, String tel){
		titulo=tit;
		cuerpo=cue;
		telefono=tel;
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
