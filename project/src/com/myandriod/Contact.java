package com.myandriod;

import java.io.FileInputStream;
import java.io.IOException;

import com.myandriod.InternalData.loadSomeStuff;
import com.myandriod.R.string;

import android.R.integer;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Contacts.People;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Contact extends Activity implements OnClickListener {

	Button create;
	EditText from,to1,groupname;
	int tonumber;
	int i=1;
	Long fromnumber;
	String group;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contactinfo);
		from = (EditText) findViewById(R.id.from);
		to1 = (EditText) findViewById(R.id.to);
		groupname=(EditText)findViewById(R.id.group);
		create= (Button) findViewById(R.id.create);
		create.setOnClickListener(this);
		
		
	}
	
	private void addContact(String name, String phone,String group) {
        ContentValues values = new ContentValues();
        values.put(People.NUMBER, phone);
        values.put(People.TYPE, Phone.TYPE_CUSTOM);
        values.put(People.LABEL, name);
        values.put(People.NAME, name);
        values.put(ContactsContract.Groups.TITLE, group);
        Uri dataUri = getContentResolver().insert(People.CONTENT_URI, values);
        Uri updateUri = Uri.withAppendedPath(dataUri, People.Phones.CONTENT_DIRECTORY);
        values.clear();
        values.put(People.Phones.TYPE, People.TYPE_MOBILE);
        values.put(People.NUMBER, phone);
        updateUri = getContentResolver().insert(updateUri, values);
      }
	
	@Override
	public void onClick(View arg0) {
		
		// TODO Auto-generated method stub	
		fromnumber = Long.parseLong(from.getText().toString().trim());
		tonumber = Integer.parseInt(to1.getText().toString().trim());
		
		group = groupname.getText().toString();
		new loadSomeStuff().execute();
		
		
		
	}

	public class loadSomeStuff extends AsyncTask<String, Integer, String>{

		ProgressDialog dialog;
		
		protected void onPreExecute() {
			dialog = new ProgressDialog(Contact.this);
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
			for(i=1;i<=tonumber;i++){
				try {
					String storenumber=String.valueOf(fromnumber+i);
					Thread.sleep(1);
					addContact(storenumber,storenumber,group);
					//Toast.makeText(getApplicationContext(),storenumber , Toast.LENGTH_SHORT).show();
				} catch (InterruptedException e) {
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
			Toast.makeText(getApplicationContext(),"Thanks" , Toast.LENGTH_SHORT).show();
		}
	}
	
	
}
