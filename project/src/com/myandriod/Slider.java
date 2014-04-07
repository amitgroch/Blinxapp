package com.myandriod;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerOpenListener;


public class Slider extends Activity implements OnClickListener, OnCheckedChangeListener, OnDrawerOpenListener {

	SlidingDrawer sd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sliding);
		CheckBox ch= (CheckBox)findViewById(R.id.cbsliding);
		ch.setOnCheckedChangeListener(this);
		sd=(SlidingDrawer) findViewById(R.id.slidingD);
		sd.setOnDrawerOpenListener(this);
	Button button1= (Button) findViewById(R.id.handle1);
	Button button2= (Button) findViewById(R.id.handle2);
	Button button3= (Button) findViewById(R.id.handle3);
	
	button1.setOnClickListener(this);
	button2.setOnClickListener(this);
	button3.setOnClickListener(this);
	
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.handle1:
			sd.open();
			break;
			
	case R.id.handle2:
			sd.toggle();
			break;
			
	case R.id.handle3:
		sd.close();
		break;

	}
		
	}

	@Override
	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		// TODO Auto-generated method stub
		if(arg0.isChecked())
			sd.lock();
		else
			sd.unlock();
	}

	@Override
	public void onDrawerOpened() {
		// TODO Auto-generated method stub
		MediaPlayer mp= MediaPlayer.create(this, R.raw.joker);
		mp.start();
	}

	
	
}
