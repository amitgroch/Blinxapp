package com.blinxapp.dto;

import com.blinxapp.customeradpter.Myinterface;

public class Province implements Myinterface {
	
	private String province_id;
	private String province_name;
	
	public String getProvince_id() {
		return province_id;
	}
	public void setProvince_id(String province_id) {
		this.province_id = province_id;
	}
	public String getProvince_name() {
		return province_name;
	}
	public void setProvince_name(String province_name) {
		this.province_name = province_name;
	}
	
	@Override
	public void add() {
		// TODO Auto-generated method stub
		
	}

}
