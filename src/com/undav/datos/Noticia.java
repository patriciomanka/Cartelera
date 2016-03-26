package com.undav.datos;

public class Noticia {

	private String titulo;
	private String cuerpo;
	private String link;
	private String foto;

	
	public Noticia (String foto,String tit, String cue, String l){
		
		titulo=tit;
		cuerpo=cue;
		link=l;
		this.foto=foto;
	}
	
	public Noticia (String tit, String cue, String l){
		
		titulo=tit;
		cuerpo=cue;
		link=l;
	
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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
}
