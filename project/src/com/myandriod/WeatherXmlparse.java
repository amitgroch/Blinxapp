package com.myandriod;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class WeatherXmlparse extends Activity implements OnClickListener {

	static final String baseurl="http://www.google.com/ig/api?weather="; 
	TextView city,state,result;
	Button b;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weather);
		city = (TextView)findViewById(R.id.city);
		state = (TextView)findViewById(R.id.state);
		result= (TextView)findViewById(R.id.tv);
		//b.setOnClickListener(this);   
		
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		String c=city.getText().toString();
		String s=state.getText().toString();
		
		StringBuilder URL= new StringBuilder(baseurl);
		URL.append(c +" ,  "+s);
		
		String fullurl=URL.toString();
		try{
			URL website = new URL(fullurl);
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp =spf.newSAXParser();
			XMLReader xr= sp.getXMLReader();
			
			HandlingXmlStuff doingwork = new HandlingXmlStuff();
			xr.setContentHandler(doingwork);
			xr.parse(new InputSource(website.openStream()));
			String infomation = doingwork.getInfomation();
			result.setText(infomation);
			
		}catch(Exception e){
			result.setText("error");
		}
	}

	
}
