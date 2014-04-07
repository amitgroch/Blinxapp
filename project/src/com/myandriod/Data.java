package com.myandriod;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Data extends Activity implements OnClickListener {

	Button start, startfor;
	EditText sendEt;
	TextView gotAnswer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.get);
		initilation();

	}

	private void initilation() {
		start = (Button) findViewById(R.id.bSA);
		startfor = (Button) findViewById(R.id.bSAFR);
		sendEt = (EditText) findViewById(R.id.etSend);
		gotAnswer = (TextView) findViewById(R.id.tvGot);
		start.setOnClickListener(this);
		startfor.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.bSA:
			String bread= sendEt.getText().toString();
			Bundle buket= new Bundle();
			buket.putString("key", bread);
			Intent passstring= new Intent(Data.this,OpenClass.class);
			passstring.putExtras(buket);
			startActivity(passstring);
			break;

		case R.id.bSAFR:

			Intent passstring1= new Intent(Data.this,OpenClass.class);
			startActivityForResult(passstring1, 0);
			break;
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode==RESULT_OK){
			Bundle openclassData=data.getExtras();
			String s=openclassData.getString("getAns");
			gotAnswer.setText(s);
		}
	}
	

}
