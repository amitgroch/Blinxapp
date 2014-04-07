package com.myandriod;

public class XMLDatacolleted {

	int temp=0;
	String city =null;
	
	
	public void setCity(String c){
		city = c;
	}
	
	public void setTemp(int t){
		temp =t;
	}
	
	public String dataToString(){
		
		return "in "+ city +"  the Current time in F is " + temp + "degree";
	}
	
}
