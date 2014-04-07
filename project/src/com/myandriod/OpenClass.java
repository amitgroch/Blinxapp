package com.myandriod;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioGroup;
import android.widget.TextView;

public class OpenClass extends Activity implements OnClickListener,
		android.widget.RadioGroup.OnCheckedChangeListener {

	TextView question, test;
	Button retrunData;
	RadioGroup selected;
	String result,displayText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send);
		initilation();
		SharedPreferences getData= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		String et=getData.getString("name", "default");
		String value=getData.getString("list", "4");
		if(value.contentEquals("1")){
			question.setText(et);
		}
		//Bundle getbread=getIntent().getExtras();
		//result=getbread.getString("key");
		//question.setText(result);
	}

	private void initilation() {
		question = (TextView) findViewById(R.id.tvQuestion);
		test = (TextView) findViewById(R.id.tvText);
		retrunData = (Button) findViewById(R.id.bReturn);
		selected = (RadioGroup) findViewById(R.id.rgAnswer);
		retrunData.setOnClickListener(this);
		selected.setOnCheckedChangeListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent ans=new Intent();
		Bundle store=new Bundle();
		store.putString("getAns", displayText);
		ans.putExtras(store);
		setResult(RESULT_OK,ans);
		finish();
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		switch (checkedId) {
		case R.id.rbCraz:
				displayText="Amit is cray";
			break;
		case R.id.rbSexy:
			displayText="Amit is cray for Android";
			break;
		case R.id.rbBoth:
			displayText="Sure Amit is cray for Android";
			break;
 		}
		test.setText(displayText);
	}
}
