package com.myandriod;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

public class GFX extends Activity {

	MyAnimation animation;
	WakeLock wL;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		//wake-lock
		PowerManager pM=(PowerManager)getSystemService(Context.POWER_SERVICE);
		wL=pM.newWakeLock(PowerManager.FULL_WAKE_LOCK, "Whatever");
		
		super.onCreate(savedInstanceState);
		wL.acquire();
		animation = new MyAnimation(this);
		setContentView(animation);
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		wL.release();
		super.onPause();
	}

	
}
