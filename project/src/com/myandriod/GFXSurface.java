package com.myandriod;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class GFXSurface extends Activity implements OnTouchListener {
	MySurface ourview;
	float x,y,sX,sY,fX,fY=0;
	Bitmap plus,test;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		test=BitmapFactory.decodeResource(getResources(), R.drawable.greenball);
		plus =BitmapFactory.decodeResource(getResources(), R.drawable.greenball);
		ourview = new MySurface(this);
		ourview.setOnTouchListener(this);
		setContentView(ourview);
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		ourview.pause();
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ourview.resume();
	}
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		x= event.getX();
		y=event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
		sX=event.getX();
		sY=event.getY();
			break;

		case MotionEvent.ACTION_UP:
			fX=event.getX();
			fY=event.getY();
			break;
		}
		return true;
	}

	//second class
	
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
				if(x!=0 && y!=0){
					canvas.drawBitmap(test, x-test.getWidth()/2, y-test.getHeight()/2, null);
				}
				if(sX!=0 && sY!=0){
					canvas.drawBitmap(plus, sX-plus.getWidth()/2, sY-plus.getHeight()/2, null);
				}
				if(fX!=0 && fY!=0){
					canvas.drawBitmap(plus, fX-plus.getWidth()/2, fY-plus.getHeight()/2, null);
				}
				ourHolder.unlockCanvasAndPost(canvas);
				
			}
		}

		

	}

}
