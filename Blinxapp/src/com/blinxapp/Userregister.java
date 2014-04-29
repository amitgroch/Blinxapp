package com.blinxapp;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.andreabaccega.widget.FormEditText;
import com.blinxapp.customeradpter.CustomAdapter;
import com.blinxapp.customeradpter.Myinterface;
import com.blinxapp.dto.Province;
import com.blinxapp.libraries.UserFunctions;

import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Userregister extends Activity implements OnClickListener {

	public ArrayList<Province> CustomListViewprovinceArr;
	private ProgressDialog pDialog;
	String provinceid = "";
	FormEditText fullname, username, password, emailid;
	Button register;
	JSONArray quotesJsonArray;
	Spinner SpinnerExample;
	CustomAdapter adapter;
	Context context;
	Userregister activity = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userregister);
		this.context = this;
		this.activity = this;
		SpinnerExample = (Spinner) findViewById(R.id.province);
		new getProvince().execute();
		initilization();
		register.setOnClickListener(this);

	}

	private void initilization() {
		fullname = (FormEditText) findViewById(R.id.fullname);
		username = (FormEditText) findViewById(R.id.username);
		password = (FormEditText) findViewById(R.id.password);
		emailid = (FormEditText) findViewById(R.id.emailid);
		register = (Button) findViewById(R.id.register);
	}

	// get Province value from database
	public class getProvince extends AsyncTask<String, integer, String> {

		UserFunctions userFunction = new UserFunctions();
		boolean failure = false;
		JSONObject jsonResult;
		String errormsg = "";

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Userregister.this);
			pDialog.setMessage("Fetching  Data...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
			Log.d("THREADTEST",
					"PRE=>" + Long.toString(Thread.currentThread().getId()));
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub

			if (!userFunction.isConnectingToInternet(context)) {
				failure = true;
				errormsg = "Connection to Server failed.";
				// Log.e("isNetworkAvailable",String.valueOf(isNetworkAvailable()));
			} else {
				try {
					jsonResult = userFunction.getProvince();
					CustomListViewprovinceArr = new ArrayList<Province>();
					if (jsonResult.getString("success").equalsIgnoreCase("1")) {
						String quotesString = jsonResult.getString("data");
						quotesJsonArray = new JSONArray(quotesString);
						for (int i = 0; i < quotesJsonArray.length(); i++) {
							Province province = new Province();
							JSONObject jsonObject = quotesJsonArray
									.getJSONObject(i);
							province.setProvince_id(jsonObject
									.getString("province_id"));
							province.setProvince_name(jsonObject
									.getString("province"));
							CustomListViewprovinceArr.add(province);
						}
					} else {
						failure = true;
						errormsg = "Connection is limited.";
					}

				} catch (JSONException e) {
					// TODO Auto-generated catch block

					e.printStackTrace();
				}
				// Create custom adapter object ( see below CustomAdapter.java )

			}

			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			if (pDialog.isShowing()) {
				pDialog.dismiss();
			}

			if (!failure) {
				adapter = new CustomAdapter(activity, R.layout.spinner_rows,
						CustomListViewprovinceArr);
				// Set adapter to spinner
				SpinnerExample.setAdapter(adapter);
				Province province = new Province();
				// Listener called when spinner item selected
				SpinnerExample
						.setOnItemSelectedListener(new OnItemSelectedListener() {

							@Override
							public void onNothingSelected(
									AdapterView<?> parentView) {
								// your code here
							}

							@Override
							public void onItemSelected(AdapterView<?> arg0,
									View arg1, int arg2, long arg3) {
								// TODO Auto-generated method stub
								provinceid = CustomListViewprovinceArr
										.get(arg2).getProvince_id();
								Toast.makeText(getApplicationContext(),
										provinceid, Toast.LENGTH_LONG).show();
							}

						});
			} else {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						Userregister.this);
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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Log.i("button", "clicked");
		FormEditText[] allFields = { fullname, username, password, emailid };
		boolean allValid = true;
		for (FormEditText field : allFields) {
			allValid = field.testValidity() && allValid;
		}
		if (allValid) {

			if (fullname.getText().toString().equals("")
					|| username.getText().toString().equals("")
					|| password.getText().toString().equals("")
					|| emailid.getText().toString().equals("")) {
				Toast.makeText(getApplicationContext(), "empty",
						Toast.LENGTH_LONG).show();
			} else {
				new AttemptRegister().execute();
			}

		}

	}

	// user registartion
	class AttemptRegister extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		boolean failure = false;
		String errormsg = "";

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			if (pDialog == null) {
				pDialog = new ProgressDialog(Userregister.this);
				pDialog.setMessage("Attempting Register...");
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

			JSONObject json = userFunction.registerUser(fullname.getText()
					.toString(), username.getText().toString(), password
					.getText().toString(), provinceid, emailid.getText()
					.toString());
			Log.d("JSON Received on Register Page=>", json.toString());

			// check for login response
			try {
				if (json.getString("success").equalsIgnoreCase("1")) {

					if (json.getString("message").equalsIgnoreCase(
							"User Already Exist")) {
						failure = false;
						errormsg = "User Already Exist";
					} else if (json.getString("message").equalsIgnoreCase(
							"You are successfully Registered")) {
						failure = false;
						errormsg = "You are successfully Registered";
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
			if (pDialog.isShowing()) {
				pDialog.dismiss();
				pDialog = null;
			}
			if (!failure) {
				if (errormsg
						.equalsIgnoreCase("You are successfully Registered")) {
					Toast.makeText(getApplicationContext(), errormsg,
							Toast.LENGTH_SHORT).show();
					Intent login = new Intent(Userregister.this,
							Userlogin.class);
					startActivity(login);
					finish();

				} else {
					Toast.makeText(getApplicationContext(), errormsg,
							Toast.LENGTH_SHORT).show();
				}
			} else {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						Userregister.this);
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
