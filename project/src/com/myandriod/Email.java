package com.myandriod;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Email extends Activity implements OnClickListener {

	Button mailsend;
	String emailse[]={"amitgroch@gmail.com"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.emaillayout);
		mailsend =(Button) findViewById(R.id.ebtn);
		
		mailsend.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.ebtn:
			Intent emailI = new Intent(android.content.Intent.ACTION_SEND);
			emailI.putExtra(android.content.Intent.EXTRA_EMAIL,emailse);
			emailI.putExtra(Intent.EXTRA_SUBJECT,"This is Subject");
			emailI.putExtra(Intent.EXTRA_TEXT, "TEXT");
			emailI.setType("plain/text");
			startActivity(emailI);
			
			break;
		}
		
	}
	
	

}
