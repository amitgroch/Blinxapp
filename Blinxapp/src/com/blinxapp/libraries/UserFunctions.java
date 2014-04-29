package com.blinxapp.libraries;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class UserFunctions {
	
	private JSONParser jsonParser;
	
	
	//public static String path = "http://hrm.testserver87.com/brumstaxidriver/";
	public static String path = "http://10.0.2.2/blinxwebservice/";
	
	public static String fetchprovince = path+"fetchprovince.php";
	private static String register_tag = path+"user_registration.php";
	private static String login_tag = path+"checkuser.php";
	
	// constructor
	public UserFunctions(){
		jsonParser = new JSONParser();
	}
	
	/**
	 * function make Login Request
	 * @param email
	 * @param password
	 * */
	public JSONObject loginUser(String USERNAME, String PASSWORD){
		// Building Parameters
		//JSONObject json =null;
		
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("tag", login_tag));
        nameValuePairs.add(new BasicNameValuePair("email", USERNAME));
        nameValuePairs.add(new BasicNameValuePair("password", PASSWORD));  
		JSONObject json = jsonParser.getJSONFromUrl(login_tag, nameValuePairs);
		Log.d("JSON", json.toString());
		return json;
	}
	
	/**
	 * function make Login Request
	 * @param name
	 * @param email
	 * @param password
	 * */
	
	//get Province data
	
	public JSONObject getProvince(){
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("request", "province_data"));
		JSONObject json = jsonParser.getJSONFromUrl(fetchprovince,params);
		return json;
	}
	
	public JSONObject registerUser(String name, String username,
			String password,String registrationId,String email ){
		// Building Parameters name, mobile,username,password
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", register_tag));
		params.add(new BasicNameValuePair("fullname", name));
		params.add(new BasicNameValuePair("username", username));
		params.add(new BasicNameValuePair("password", password));
		params.add(new BasicNameValuePair("provinceid", registrationId));
		params.add(new BasicNameValuePair("email", email));
		
		Log.d("BeforeSending", params.toString());
		// getting JSON Object
		JSONObject json = jsonParser.getJSONFromUrl(register_tag, params);
		// return json
		return json;
	}
	
	//connection checker
	public boolean isConnectingToInternet(Context _context){
	    ConnectivityManager connectivity = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
	      if (connectivity != null) 
	      {
	          NetworkInfo[] info = connectivity.getAllNetworkInfo();
	          if (info != null) 
	              for (int i = 0; i < info.length; i++) 
	                  if (info[i].getState() == NetworkInfo.State.CONNECTED)
	                  {
	                      return true;
	                  }

	      }
	      return false;
	}
	
}
