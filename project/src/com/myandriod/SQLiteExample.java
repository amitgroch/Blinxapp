package com.myandriod;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SQLiteExample extends Activity implements View.OnClickListener{

	Button sqlUpdate, sqlView, sqlModify, sqlDelete, sqlGetInfo;
	EditText sqlName, sqlHotness, sqlRow;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqliteexample);
		Initializer();
		sqlUpdate.setOnClickListener(this);
		sqlView.setOnClickListener(this);
		sqlDelete.setOnClickListener(this);
		sqlGetInfo.setOnClickListener(this);
		sqlModify.setOnClickListener(this);
	}

	private void Initializer() {
		// TODO Auto-generated method stub
		sqlUpdate = (Button) findViewById(R.id.bSQLUpdate);
		sqlView = (Button) findViewById(R.id.bSQLopenView);
		sqlName = (EditText) findViewById(R.id.etSQLName);
		sqlHotness = (EditText) findViewById(R.id.etSQLHotness);
		sqlDelete = (Button) findViewById(R.id.bSQLdelete);
		sqlGetInfo = (Button) findViewById(R.id.bSQLgetrowInfo);
		sqlModify = (Button) findViewById(R.id.bSQLmodify);
		sqlRow = (EditText) findViewById(R.id.etSQLrowInfo);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		
		case R.id.bSQLUpdate:
			boolean didItwork = true;
			try{
			String name = sqlName.getText().toString();
			String hotness = sqlHotness.getText().toString();
			
			HotOrNot entry = new HotOrNot(SQLiteExample.this);
			entry.open();
			entry.createEntry(name, hotness);
			entry.close();
			}catch (Exception e) {
				// TODO: handle exception
				
				didItwork = false;
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("SQL Execption");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			
				
			}finally{
				if(didItwork){
					Dialog d = new Dialog(this);
					d.setTitle("Heck Yea !");
					TextView tv = new TextView(this);
					tv.setText("Success");
					d.setContentView(tv);
					d.show();
				}
			}
				
			break;

		case R.id.bSQLopenView:
			
			Intent i = new Intent("com.myandriod.SQLView");
			startActivity(i);
			
			break;
			
		case R.id.bSQLgetrowInfo:
			
			
			try{
			String s = sqlRow.getText().toString();
			long l = Long.parseLong(s);
			HotOrNot hon = new HotOrNot(this);
			hon.open();
			String returnedName = hon.getName(l);
			String returnedHotness = hon.getHotness(l);
			hon.close();
			
			TextView tv3 = (TextView) findViewById(R.id.tvInfo);
			tv3.setText("check");
			sqlName.setText(returnedName);
			sqlHotness.setText(returnedHotness);
			
			}catch (Exception e) {
			// TODO: handle exception
			
			String error = e.toString();
			Dialog d = new Dialog(this);
			d.setTitle("SQL Execption");
			TextView tv = new TextView(this);
			tv.setText(error);
			d.setContentView(tv);
			d.show();	
			}
			break;
			
		case R.id.bSQLmodify:
			
			
			try{
			String mName = sqlName.getText().toString();
			String mHotness = sqlHotness.getText().toString();
			String sRow = sqlRow.getText().toString();
			long lRow = Long.parseLong(sRow);
			HotOrNot ex = new HotOrNot(this);
			ex.open();
			ex.updateEntry(lRow, mName, mHotness);
			ex.close();
			}catch (Exception e) {
				// TODO: handle exception
		
				
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("SQL Execption");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
	
		
				}
			break;
			
		case R.id.bSQLdelete:
			
			
			try{
			String sRow1 = sqlRow.getText().toString();
			long lRow1 = Long.parseLong(sRow1);
			HotOrNot ex1 = new HotOrNot(this);
			ex1.open();
			ex1.deleteEntry(lRow1);
			ex1.close();
			}catch (Exception e) {
			// TODO: handle exception
			
			
			String error = e.toString();
			Dialog d = new Dialog(this);
			d.setTitle("SQL Execption");
			TextView tv = new TextView(this);
			tv.setText(error);
			d.setContentView(tv);
			d.show();
		
			
			}
			break;
			
		}
	}

}
