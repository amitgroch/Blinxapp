package com.blinxapp;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.blinxapp.customeradpter.CustomAdapter;
import com.blinxapp.dto.Province;
import com.blinxapp.libraries.UserFunctions;

import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class Findexperience extends Activity implements OnClickListener {

	Button on, off, search;
	public ArrayList<Province> CustomListViewprovinceArr;
	EditText city;
	private ProgressDialog pDialog;
	JSONArray quotesJsonArray;
	CustomAdapter adapter;
	Findexperience activity = null;
	Spinner provinceid, experience, defineexperience;
	String provinceid1="";
	LinearLayout provincelayout,citylayout,defineexperiencelayout;
	Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.findexperience);
		this.context=this;
		this.activity=this;
		inilization();
		new getProvince().execute();
		
		
	}

	private void inilization() {
		on = (Button) findViewById(R.id.on);
		off = (Button) findViewById(R.id.off);
		search = (Button) findViewById(R.id.search);
		city = (EditText) findViewById(R.id.city);
		provinceid = (Spinner) findViewById(R.id.provinceid);
		experience = (Spinner) findViewById(R.id.experience);
		defineexperience = (Spinner) findViewById(R.id.defineexperience);
		on.setOnClickListener(this);
		off.setOnClickListener(this);
		search.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.on:
			 provincelayout= (LinearLayout) this.findViewById(R.id.provincelayout);
			 citylayout= (LinearLayout) this.findViewById(R.id.citylayout);
			 defineexperiencelayout=(LinearLayout) this.findViewById(R.id.defineexperiencelayout);
			
			 provincelayout.setVisibility(LinearLayout.VISIBLE);
			 citylayout.setVisibility(LinearLayout.GONE);
			 defineexperiencelayout.setVisibility(LinearLayout.GONE);
			break;

		case R.id.off:
			provincelayout= (LinearLayout) this.findViewById(R.id.provincelayout);
			 citylayout= (LinearLayout) this.findViewById(R.id.citylayout);
			 defineexperiencelayout=(LinearLayout) this.findViewById(R.id.defineexperiencelayout);
			
			 provincelayout.setVisibility(LinearLayout.GONE);
			 citylayout.setVisibility(LinearLayout.VISIBLE);
			 defineexperiencelayout.setVisibility(LinearLayout.VISIBLE);
			
			break;

		case R.id.search:

			break;

		}

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
				pDialog = new ProgressDialog(Findexperience.this);
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
						CustomListViewprovinceArr =new ArrayList<Province>();
							if (jsonResult.getString("success").equalsIgnoreCase(
									"1")) {
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
					provinceid.setAdapter(adapter);
					// Listener called when spinner item selected
					provinceid.setOnItemSelectedListener(new OnItemSelectedListener() {

								@Override
								public void onNothingSelected(
										AdapterView<?> parentView) {
									// your code here
								}

								@Override
								public void onItemSelected(AdapterView<?> arg0,
										View arg1, int arg2, long arg3) {
									// TODO Auto-generated method stub
									provinceid1 = CustomListViewprovinceArr
											.get(arg2).getProvince_id();
									Toast.makeText(getApplicationContext(),
											provinceid1, Toast.LENGTH_LONG).show();
								}

							});
				} else {
					AlertDialog.Builder builder = new AlertDialog.Builder(
							Findexperience.this);
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
