package com.myandriod;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;

public class Tabs extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tags);
		
		TabHost th=(TabHost) findViewById(R.id.tabhost);
		th.setup();
		TabSpec specs=th.newTabSpec("tab1");
		specs.setContent(R.id.tab1);
		specs.setIndicator("Tab 1");
		th.addTab(specs);
		/*specs=th.newTabSpec("tab2");
		specs.setContent(R.id.tab2);
		specs.setIndicator("Tab 2");
		th.addTab(specs);
		specs=th.newTabSpec("tab3");
		specs.setContent(R.id.tab3);
		specs.setIndicator("Tab 3");
		th.addTab(specs);
		Button b1 =(Button) findViewById(R.id.button1);
		Button b2 =(Button) findViewById(R.id.button2);
		Button b3 =(Button) findViewById(R.id.button3);
		TextView tv1=(TextView) findViewById(R.id.textv);
		TextView tv2=(TextView) findViewById(R.id.textv1);*/
	}

	
}

