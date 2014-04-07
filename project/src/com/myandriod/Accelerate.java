package com.myandriod;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Accelerate extends Activity implements SensorEventListener {

	float x,y,sensorX,sensorY=0;
	Bitmap ball;
	SensorManager sm;
	MySurface ourSurfaceview;
	public class MySurface extends SurfaceView implements Runnable {

		SurfaceHolder ourHolder;
		Thread mythread=null;
		boolean isRunnig=false;
		public MySurface(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			ourHolder = getHolder();
			
		}

		public void pause(){
			isRunnig=false;
			while (true) {
				try {
					mythread.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				
			}
			mythread = null;
		}
		
		public void resume(){
			isRunnig=true;
			mythread= new Thread(this);
			mythread.start();
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (isRunnig) {
				if(!ourHolder.getSurface().isValid())
					continue;
				Canvas canvas = ourHolder.lockCanvas();
				canvas.drawRGB(02, 02, 150);
				float centreX=canvas.getWidth()/2;
				float centreY=canvas.getHeight()/2;
				canvas.drawBitmap(ball, centreX +sensorX *20, centreY +sensorY *20, null);
				ourHolder.unlockCanvasAndPost(canvas);
				
			}
		}

		

	}
	
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		sm.unregisterListener(this);
		super.onPause();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(new MySurface(this));
		sm = (SensorManager) getSystemService(Contact.SENSOR_SERVICE);
		if(sm.getSensorList(Sensor.TYPE_ACCELEROMETER).size()!=0){
			Sensor s = sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
			sm.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);
		}
		ball = BitmapFactory.decodeResource(getResources(), R.drawable.greenball);
		x=y=sensorX=sensorY=0;
		ourSurfaceview = new MySurface(this);
		ourSurfaceview.resume();
		setContentView(ourSurfaceview);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		sensorX=event.values[0];
		sensorY= event.values[1];
		
	}

}
