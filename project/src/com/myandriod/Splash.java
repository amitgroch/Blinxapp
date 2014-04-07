package com.myandriod;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class Splash extends Activity {

	MediaPlayer mysong;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.splash);
		mysong = MediaPlayer.create(Splash.this, R.raw.joker);
		SharedPreferences getperfs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		boolean music =getperfs.getBoolean("checkbox", true);
		if(music)
		mysong.start();
		Thread timer = new Thread(){
			public void run(){
				try {
					sleep(5);
				} catch (Exception e) {
					// TODO: handle exception
				}finally{
					Intent active= new Intent("com.myandriod.SPLASH");
					startActivity(active);
					finish();
				}
			}
		};
		timer.start();
	}
	

}
