package it.unibo.radarSystem22.sprintuno;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import it.unibo.radarSystem22.domain.utils.ColorsOut;



public class RadarSystemConfig {
 	public static boolean tracing         = false;	
	public static boolean testing         = false;			
	public static int DLIMIT              =  15;     
	
	public static boolean  RadarGuiRemote = false;
	
	public static void setTheConfiguration(  ) throws java.io.FileNotFoundException, JSONException {
		setTheConfiguration("../RadarSystemConfig.json");
	}
	
	public static void setTheConfiguration( String resourceName ) throws java.io.FileNotFoundException, JSONException {
		//Nella distribuzione resourceName � in una dir che include la bin  
		FileInputStream fis = null;
		ColorsOut.out("%%% setTheConfiguration from file:" + resourceName);
		if(  fis == null ) {
			 fis = new FileInputStream(new File(resourceName));
		}
		Reader reader = new InputStreamReader(fis);
		JSONTokener tokener = new JSONTokener(reader);
		JSONObject object   = new JSONObject(tokener);
		
		tracing          = object.getBoolean("tracing");
		testing          = object.getBoolean("testing");
		RadarGuiRemote   = object.getBoolean("RadarGuiRemote");
		DLIMIT           = object.getInt("DLIMIT");

	}	
	 
}
