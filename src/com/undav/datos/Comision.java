package com.undav.datos;

public class Comision {

	private String hi1;
	private String hf1;
	private String dia1;
	private String aula1;
	private String com;
	
	public Comision(String c,String d1,String i1, String f1, String a1){
		com=c;
		hi1=i1;
		hf1=f1;
		dia1=d1;
		aula1=a1;
	}

	public String getCom(){
		return com;
	}
	
	public void SetCom(String c){
		com =c;
	}
	
	public String getHi1() {
		return hi1;
	}

	public void setHi1(String hi1) {
		this.hi1 = hi1;
	}




	public String getHf1() {
		return hf1;
	}

	public void setHf1(String hf1) {
		this.hf1 = hf1;
	}




	public String getDia1() {
		return dia1;
	}

	public void setDia1(String dia1) {
		this.dia1 = dia1;
	}





	public String getAula1() {
		return aula1;
	}

	public void setAula1(String aula) {
		this.aula1 = aula;
	}
	


	
}
