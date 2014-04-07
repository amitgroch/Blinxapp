package com.myandriod;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class TextPlay extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		final EditText gettext=(EditText) findViewById(R.id.et);
		final TextView seterro=(TextView) findViewById(R.id.tv);
		Button butn=(Button) findViewById(R.id.bcommand);
	   final ToggleButton tb=(ToggleButton) findViewById(R.id.tb);
	
		tb.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			
				if(tb.isChecked()){
					
					gettext.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
					
				}else{
					gettext.setInputType(InputType.TYPE_CLASS_TEXT);	
				}
			}
		});
		
	butn.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			String str=gettext.getText().toString();
			
			seterro.setText(str);
		}
	});	
	}
	


}
