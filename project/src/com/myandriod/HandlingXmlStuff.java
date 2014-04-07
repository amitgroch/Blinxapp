package com.myandriod;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class HandlingXmlStuff extends DefaultHandler {

	XMLDatacolleted info = new XMLDatacolleted();
	
	public String getInfomation(){
		return info.dataToString();
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		
		if(localName.equals("city")){
			String city = attributes.getValue("data");
			info.setCity(city);
		}else if (localName.equals("temp_f")) {
			String t = attributes.getValue("data");
			int temp=Integer.parseInt(t);
			info.setTemp(temp);
		}
		
		
	}

	
	
}
