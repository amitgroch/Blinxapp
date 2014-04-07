package com.myandriod;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Main extends ListActivity {

	String classes[] = { "MainActivity", "Splash", "TextPlay", "Email",
			"Camera", "Data","GFX","GFXSurface","SoundStuff","Slider","Tabs","SimpleBrower"
			,"Contact","Flipper","Sharedpref","InternalData","SQLiteExample","HttpRequest","WeatherXmlparse" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//full screen code
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setListAdapter(new ArrayAdapter<String>(Main.this,
				android.R.layout.simple_list_item_1, classes));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		String classpostion = classes[position];
		try {
			Class ourclass = Class.forName("com.myandriod." + classpostion);
			Intent openclass = new Intent(Main.this, ourclass);
			startActivity(openclass);

		} catch (ClassNotFoundException e) {
			// TODO: handle exception

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		MenuInflater getmenu = getMenuInflater();
		getmenu.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case R.id.aboutUs:
		Intent i= new Intent("com.myandriod.ABOUTCALL");
		startActivity(i);
			break;

		case R.id.perference:
			Intent p = new Intent("com.myandriod.PRFES");
			startActivity(p);
			break;
		
		case R.id.exit:
			finish();
		}
		

		return false;
	}

}
