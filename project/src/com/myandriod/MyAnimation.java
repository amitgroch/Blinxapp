package com.myandriod;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class MyAnimation extends View {

	Bitmap gball;
	float changingY;
	public MyAnimation(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		gball = BitmapFactory.decodeResource(getResources(), R.drawable.greenball);
		changingY=0;
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		canvas.drawColor(Color.WHITE);
		Rect drect= new Rect();
		drect.set(0, 400, canvas.getWidth(), 550);
		Paint paint= new Paint();
		paint.setColor(Color.BLUE);
		canvas.drawRect(drect, paint);
		canvas.drawBitmap(gball, canvas.getWidth()/2, changingY, null);
		if(changingY< canvas.getHeight())
			changingY +=10;
		else {
		changingY=0;	
		}
		invalidate();
	}

	
	
}
