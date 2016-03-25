package com.undav.peticiones;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Post {
	private URL url;
	private String data;
	private Object respuesta;

	public Post (String url) throws MalformedURLException{
		this.url = new URL(url);
	}

	public void agregar (String propiedad, String valor) throws UnsupportedEncodingException{
		if (data != null)
			data += "&"+ URLEncoder.encode(propiedad, "UTF-8")+ "=" +URLEncoder.encode(valor, "UTF-8");
		else
			data = URLEncoder.encode(propiedad, "UTF-8")+ "=" +URLEncoder.encode(valor, "UTF-8");
	}
	/*
	public void agregar (String propiedad, String valor) throws UnsupportedEncodingException{
		if (data != null)
			data += "&"+ propiedad + "=" +valor;
		else
			data = propiedad+ "=" +valor;
	}
	*/


	public void ejecutar () {
		HttpURLConnection conexion;
	   String linea;
	   StringBuilder mensaje=null;
	   int resultadoConexion;
	   BufferedReader lector;
	   OutputStreamWriter salida;
	   
	   try {
		   conexion = (HttpURLConnection) url.openConnection();
		   if (data != null) {
				conexion.setRequestMethod("POST");
				conexion.setDoOutput(true);
				salida= new OutputStreamWriter(conexion.getOutputStream(),"UTF-8");			
				salida.write(data);
				salida.close();
		   }
			resultadoConexion =conexion.getResponseCode(); 
		   lector = new BufferedReader(new InputStreamReader(conexion.getInputStream())); 
		   mensaje = new StringBuilder ();
			if(resultadoConexion==HttpURLConnection.HTTP_OK) {
			    while ((linea = lector.readLine()) != null) 
			    	mensaje.append(linea + "\n");  
				 lector.close();
			}
			else {
			   lector.close();
			   throw new Exception ("Error en la conexion.");
			}
			respuesta = new JSONTokener(mensaje.toString ()).nextValue();
	   }
	   catch (Exception err) {
	   	err.printStackTrace ();
	   }
	}
	
	public JSONObject getJSONObject () {
		if (respuesta == null)
			ejecutar ();
		if (respuesta instanceof JSONObject)
			return (JSONObject) respuesta;
		else 
			return null;
	}
	
	public JSONArray getJSONArray () {
		if (respuesta == null)
			ejecutar ();
		if (respuesta instanceof JSONArray)
			return (JSONArray) respuesta;
		else 
			return null;		
	}	
}
