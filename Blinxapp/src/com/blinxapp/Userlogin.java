package com.blinxapp;

import org.json.JSONException;
import org.json.JSONObject;

import com.blinxapp.Userregister.AttemptRegister;
import com.blinxapp.libraries.UserFunctions;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Userlogin extends Activity implements OnClickListener {

	TextView username,password;
	Button login;
	private ProgressDialog pDialog;
	SharedPreferences preferences;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userlogin);
		initialization();
		 preferences = getSharedPreferences("USER_DETAILS", Context.MODE_WORLD_WRITEABLE);
		
	}

	private void initialization(){

		username =(TextView)findViewById(R.id.loginusername);
		password =(TextView)findViewById(R.id.loginpassword);
		login =(Button)findViewById(R.id.login);
		login.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(username.getText().toString().equals("") || password.getText().toString().equals("")){
			Toast.makeText(getApplicationContext(),
					"empty", Toast.LENGTH_LONG).show();
		}
		else{
			new Attemptlogin().execute();
		}
	}
	
	//user registartion
		class Attemptlogin extends AsyncTask<String, String, String> {

			/**
			 * Before starting background thread Show Progress Dialog
			 * */
			boolean failure = false;
			String errormsg = "";
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				if(pDialog==null){
					pDialog = new ProgressDialog(Userlogin.this);
					pDialog.setMessage("Attempting login...");
					pDialog.setIndeterminate(false);
					pDialog.setCancelable(true);
					pDialog.show();
				}
				
			}

			@Override
			protected String doInBackground(String... args) {
				// TODO Auto-generated method stub
				// Check for success tag
				Log.d("I am in ", "doInBackground Function");
				
				UserFunctions userFunction = new UserFunctions();
				
				JSONObject json = userFunction.loginUser(username.getText().toString(),password.getText().toString());
				Log.d("JSON Received on Register Page=>", json.toString());
				
				// check for login response
				try {
					if (json.getString("success").equalsIgnoreCase(
							"1")) {
						
						if(json.getString("message").equalsIgnoreCase("Incorrect Email and Password")){
							failure = false;
							errormsg = "Incorrect Email and Password";
						}else {
							errormsg = json.getString("message");
						}
						
					} else {
						failure = true;
						errormsg = "Connection is limited.";
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
			/**
			 * After completing background task Dismiss the progress dialog
			 * **/
			protected void onPostExecute(String file_url) {
				// dismiss the dialog once product deleted
				if(pDialog.isShowing()){
					pDialog.dismiss();
					pDialog=null;
				}
				if(!failure){
					if(errormsg.equalsIgnoreCase("Incorrect Email and Password")){
						Toast.makeText(getApplicationContext(), errormsg, Toast.LENGTH_SHORT).show();
									
					}else{
					
						/*Intent login = new Intent(Userregister.this,Userlogin.class);
						startActivity(login);
						finish();*/
						SharedPreferences.Editor   editor = preferences.edit();
						editor.putString("user_Id",errormsg);
						editor.commit();
						Intent loginintent=new Intent(Userlogin.this,Homescreen.class);
						startActivity(loginintent);
						finish();
						Toast.makeText(getApplicationContext(), "Successfully login..", Toast.LENGTH_SHORT).show();
					}
				} else {
					AlertDialog.Builder builder = new AlertDialog.Builder(
							Userlogin.this);
					builder.setTitle("Attention!");
					builder.setMessage(errormsg);
					builder.setPositiveButton("Exit",
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog,
										int which) {
									finish();
								}
							});
					AlertDialog dialog = builder.create();
					dialog.show();
				}
				
			}
		}


	
}
