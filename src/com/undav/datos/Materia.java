package com.undav.datos;



public class Materia {
	
	private String materia;
	private String idMateria;
	
	public Materia(String mar, String id){
		materia= mar;
		idMateria=id;
	}
	
	public String getMateria(){
		return materia;
	}
	
	
	public void setMateria(String mar){
		materia=mar;
	}
	
	
	public String getIdMateria(){
		return idMateria;
	}
	
	
	public void setIdMateria(String id){
		idMateria=id;
	}
	
	
}
