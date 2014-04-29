package com.blinxapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Homescreen extends Activity implements OnClickListener {

	Button findexperince;
	SharedPreferences prfs;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homescreen);
		prfs= getSharedPreferences("USER_DETAILS", Context.MODE_PRIVATE);
	    String userid = prfs.getString("user_Id", "");
		 Toast.makeText(getApplicationContext(), userid, Toast.LENGTH_SHORT).show();
	     
		initialization();
	}

	private void initialization() {

		findexperince = (Button) findViewById(R.id.findexp);
		findexperince.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.findexp:
			Intent findexpintent= new Intent(Homescreen.this,Findexperience.class);
			startActivity(findexpintent);
			break;
		
		}
	}
}
