package com.myandriod;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ViewFlipper;

public class Flipper extends Activity implements OnClickListener {

	ViewFlipper flipper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.flipperxml);
		flipper = (ViewFlipper)findViewById(R.id.viewFlipper1);
		flipper.setOnClickListener(this);
		flipper.setFlipInterval(500);
		flipper.startFlipping();
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		flipper.showNext();
	}

	
}
