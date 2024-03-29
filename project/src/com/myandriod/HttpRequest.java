package com.myandriod;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

public class HttpRequest extends Activity{

	TextView httpstuff;
	HttpClient client ;
	JSONObject json;
	final static String URL="http://api.twitter.com/1/statuses/user_timeline.json?screen_name=";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.httpex);
		httpstuff = (TextView)findViewById(R.id.tvHttp);
		client = new DefaultHttpClient();
		new Read().execute("text");
		
	}

	public JSONObject lastTweet(String username) throws ClientProtocolException,IOException,JSONException{
		StringBuilder url = new StringBuilder(URL);
		url.append(username);
		
		HttpGet get = new HttpGet(url.toString());
		HttpResponse response= client.execute(get);
		int status =response.getStatusLine().getStatusCode();
		if(status==200){
			HttpEntity e = response.getEntity();
			String data= EntityUtils.toString(e);
			JSONArray timeline = new JSONArray(data);
			JSONObject last=timeline.getJSONObject(0);
			return last;
		}else{
			return null;
		}
		
	}
	
	public class Read extends AsyncTask<String, integer, String>{

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			
			try {
				json = lastTweet("Amitgroch");
				return json.getString(params[0]);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			httpstuff.setText(result);
			super.onPostExecute(result);
		}
		
	}
	
}
