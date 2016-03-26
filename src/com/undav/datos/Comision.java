package com.undav.datos;

public class Comision {

	private String horaInicio;
	private String horaFin;
	private String dia;
	private String aula;
	private String comision;
	

	public Comision (String comision, String dia, String horaInicio, String horaFin, String aula) {
	   super ();
	   this.horaInicio = horaInicio;
	   this.horaFin = horaFin;
	   this.dia = dia;
	   this.aula = aula;
	   this.comision = comision;
   }
	
	public String getHoraInicio () {
		return horaInicio;
	}
	public void setHoraInicio (String horaInicio) {
		this.horaInicio = horaInicio;
	}
	public String getHoraFin () {
		return horaFin;
	}
	public void setHoraFin (String horaFin) {
		this.horaFin = horaFin;
	}
	public String getDia () {
		return dia;
	}
	public void setDia (String dia) {
		this.dia = dia;
	}
	public String getAula () {
		return aula;
	}
	public void setAula (String aula) {
		this.aula = aula;
	}
	public String getComision () {
		return comision;
	}
	public void setComision (String comision) {
		this.comision = comision;
	}
	
	
	
}
