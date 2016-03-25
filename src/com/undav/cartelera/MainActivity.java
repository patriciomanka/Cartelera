package com.undav.cartelera;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.undav.cartelera.MainActivity;
import com.undav.peticiones.Post;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

@SuppressWarnings("unused")
private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
	
	public static final String EXTRA_MESSAGE = "message";
    private static final String PROPERTY_REG_ID = "registration_id";
    private static final String PROPERTY_APP_VERSION = "appVersion";
    private static final String PROPERTY_EXPIRATION_TIME = "onServerExpirationTimeMs";
    private static final String PROPERTY_USER = "user";

    public static final long EXPIRATION_TIME_MS = 1000 * 3600 * 24 * 7;

    String SENDER_ID = "449692312591";

    static final String TAG = "GCMDemo"; 
    
    private Context context;
    private String regid;
    private GoogleCloudMessaging gcm;	

	
	private Button btAula,btNoticia,btRadio,btSede,btSalir;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		context = getApplicationContext();
		
        gcm = GoogleCloudMessaging.getInstance(MainActivity.this);
	
        //Obtenemos el Registration ID guardado
        regid = getRegistrationId(context);
        //Si no disponemos de Registration ID comenzamos el registro
        if (regid.equals("")) {
	    		TareaRegistroGCM tarea = new TareaRegistroGCM();
	    		tarea.execute("Usuario");
	        }

		
		
		OnClickListener ListenerInfo =new OnClickListener(){
			@Override
		    public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this, InfoActivity.class);
				startActivity(intent);
			}
		};
		
		OnClickListener ListenerRadio =new OnClickListener(){
			@Override
		    public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this, RadioActivity.class);
				startActivity(intent);
			}
		};
		OnClickListener ListenerAula =new OnClickListener(){

			    @Override
			    public void onClick(View arg0) {
						Intent intent = new Intent(MainActivity.this, CarreraActivity.class);
				        startActivity(intent);
					}
			 };
			 
				OnClickListener ListenerSede =new OnClickListener(){

				    @Override
				    public void onClick(View arg0) {
							Intent intent = new Intent(MainActivity.this, SedesActivity.class);				        
					        startActivity(intent);
						}
				 };
					
				 OnClickListener ListenerNoticias =new OnClickListener(){

					    @Override
					    public void onClick(View arg0) {
							Intent intent = new Intent(MainActivity.this, NoticiasActivity.class);				        
					        startActivity(intent);
						}
					 };
		btAula=(Button)findViewById(R.id.btAula);
		btAula.setOnClickListener(ListenerAula);
		btNoticia=(Button)findViewById(R.id.btNoticia);
		btNoticia.setOnClickListener(ListenerNoticias);
		btRadio=(Button)findViewById(R.id.btRadio);
		btRadio.setOnClickListener(ListenerRadio);
		btSede=(Button)findViewById(R.id.btSede);
		btSede.setOnClickListener(ListenerSede);
		btSalir =(Button) findViewById(R.id.btInfo);
		btSalir.setOnClickListener(ListenerInfo);	
	}
	private String getRegistrationId(Context context) 
	{
	    SharedPreferences prefs = getSharedPreferences(
	    		MainActivity.class.getSimpleName(), 
	            Context.MODE_PRIVATE);
	    
	    String registrationId = prefs.getString(PROPERTY_REG_ID, "");
	    
	    if (registrationId.length() == 0) 
	    {
	        Log.d(TAG, "Registro GCM no encontrado.");
	        return "";
	    }
	    
	    //String registeredUser = 
	    	//	prefs.getString(PROPERTY_USER, "user");
	    
	    int registeredVersion = 
	    		prefs.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
	    
	    long expirationTime =
	            prefs.getLong(PROPERTY_EXPIRATION_TIME, -1);
	    
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
	    String expirationDate = sdf.format(new Date(expirationTime));
	    //Log.d(TAG, "Registro GCM encontrado (usuario=" + registeredUser +
	    Log.d(TAG, "Registro GCM encontrado (usuario=" +  
	    		", version=" + registeredVersion + 
	    		", expira=" + expirationDate + ")");
	    
	    int currentVersion = getAppVersion(context);
	    
	    if (registeredVersion != currentVersion) 
	    {
	        Log.d(TAG, "Nueva versión de la aplicación.");
	        return "";
	    }
	    else if (System.currentTimeMillis() > expirationTime)
	    {
	    	Log.d(TAG, "Registro GCM expirado.");
	        return "";
	    }
	    /*else if (!txtUsuario.getText().toString().equals(registeredUser))
	    {
	    	Log.d(TAG, "Nuevo nombre de usuario.");
	        return "";
	    }*/
	    
	    return registrationId;
	}

	private static int getAppVersion(Context context) 
	{
	    try
	    {
	        PackageInfo packageInfo = context.getPackageManager()
	                .getPackageInfo(context.getPackageName(), 0);
	        
	        return packageInfo.versionCode;
	    } 
	    catch (NameNotFoundException e) 
	    {
	        throw new RuntimeException("Error al obtener versión: " + e);
	    }
	}

	private class TareaRegistroGCM extends AsyncTask<String,Integer,String>
	{
		@Override
	    protected String doInBackground(String... params) 
		{
	        String msg = "";
	        
	        try 
	        {
	            if (gcm == null) 
	            {
	                gcm = GoogleCloudMessaging.getInstance(context);
	            }
	            
	            //Nos registramos en los servidores de GCM
	            regid = gcm.register(SENDER_ID);
	            
	            Log.d(TAG, "Registrado en GCM: registration_id=" + regid);

	            //Nos registramos en nuestro servidor
	            boolean registrado = registroServidor(params[0], regid);

	            //Guardamos los datos del registro
	            if(registrado)
	            {
	            	setRegistrationId(context, params[0], regid);
	            }
	        } 
	        catch (IOException ex) 
	        {
	        	Log.d(TAG, "Error registro en GCM:" + ex.getMessage());
	        }
	        
	        return msg;
	    }
	}

	private void setRegistrationId(Context context, String user, String regId) 
	{
	    SharedPreferences prefs = getSharedPreferences(
	    		MainActivity.class.getSimpleName(), 
	            Context.MODE_PRIVATE);
	    
	    int appVersion = getAppVersion(context);
	    
	    SharedPreferences.Editor editor = prefs.edit();
	    editor.putString(PROPERTY_USER, user);
	    editor.putString(PROPERTY_REG_ID, regId);
	    editor.putInt(PROPERTY_APP_VERSION, appVersion);
	    editor.putLong(PROPERTY_EXPIRATION_TIME, 
	    		System.currentTimeMillis() + EXPIRATION_TIME_MS);
	    
	    editor.commit();
	}

	private boolean registroServidor(String usuario, String regId) throws UnsupportedEncodingException
	{
		boolean reg = false;

		@SuppressWarnings("unused")
		Post consulta;
		try {
			Post insertar = new Post ("http://www.kandaz.co.nf/Undav/regId.php");
			insertar.agregar("regId", regId);
			insertar.ejecutar();
			reg=true;
		} catch (MalformedURLException e) {
	    	Log.d(TAG, "Error en carga PHP:" + e.getMessage());
			
			return reg;
		}
		return reg;
	}

	
}