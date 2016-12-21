package com.undav.cartelera.datos;



public class Carrera {
	
	private String carrera;
	private String idCarrera;
	
	public Carrera(String id,String car){
		idCarrera=id;
		carrera= car;
	}
	
	public String getCarrera(){
		return carrera;
	}
	
	
	public void setCarrera(String car){
		carrera=car;
	}
	
	
	public String getIdCarrera(){
		return idCarrera;
	}
	
	
	public void setIdCarrera(String id){
		idCarrera=id;
	}
	
	
}
