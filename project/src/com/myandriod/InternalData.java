package com.myandriod;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InternalData extends Activity implements OnClickListener {

	Button pload,psave;
	EditText getet;
	TextView vtext;
	FileOutputStream fos=null;
	FileInputStream fin=null;
	String collected=null;
	String filename="internaldata";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharedpreference);
		insitalition();
		
		
	}

	public void insitalition() {
		
	psave = (Button)findViewById(R.id.save);
	pload=(Button)findViewById(R.id.load);
	getet=(EditText)findViewById(R.id.et);
	vtext=(TextView)findViewById(R.id.textView1);
	psave.setOnClickListener(this);
	pload.setOnClickListener(this);
	try {
		fos =openFileOutput(filename, Contact.MODE_PRIVATE);
		fos.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		case R.id.save:
		String stringData= getet.getText().toString();
		try {
			fos =openFileOutput(filename, Contact.MODE_PRIVATE);
			fos.write(stringData.getBytes());
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
			
		case R.id.load:
			new loadSomeStuff().execute(filename);
			break;
		}
		
	}
	
	public class loadSomeStuff extends AsyncTask<String, Integer, String>{

		ProgressDialog dialog;
		
		protected void onPreExecute() {
			dialog = new ProgressDialog(InternalData.this);
			dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			dialog.setMax(100);
			dialog.show();
		}
		
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			
			String collected = null; 
			FileInputStream fis = null;
			
			for(int i = 0; i < 20 ; i++){
				publishProgress(5);
				try {
					Thread.sleep(88);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			dialog.dismiss();
			try {
				fis = openFileInput(filename);
				byte[] dataArray = new byte[fis.available()];
				while(fis.read(dataArray) != -1){
					collected = new String(dataArray);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					fis.close();
					return collected;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;
	
			
		}
		
		
		protected void onProgressUpdate(Integer... progress) {
			dialog.incrementProgressBy(progress[0]);
		}
		
		protected void onPostExecute(String result){
			vtext.setText(result);
		}
	}
	
}
