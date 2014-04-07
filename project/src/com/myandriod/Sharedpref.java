package com.myandriod;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Sharedpref extends Activity implements OnClickListener {

	Button pload,psave;
	EditText getet;
	TextView vtext;
	SharedPreferences prefdata;
	static public String filename="Mysharedstring";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharedpreference);
		insitalition();
		prefdata=getSharedPreferences(filename, 0);
		
	}

	public void insitalition() {
		
	psave = (Button)findViewById(R.id.save);
	pload=(Button)findViewById(R.id.load);
	getet=(EditText)findViewById(R.id.et);
	vtext=(TextView)findViewById(R.id.textView1);
	psave.setOnClickListener(this);
	pload.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		case R.id.save:
		String stringData= getet.getText().toString();
		SharedPreferences.Editor editor=prefdata.edit();
		editor.putString("sharedstring", stringData);
		editor.commit();
			break;
			
		case R.id.load:
			prefdata=getSharedPreferences(filename, 0);
			String showdata=prefdata.getString("sharedstring", "coundnot found data");
			vtext.setText(showdata);
			break;
		}
		
	}
}
