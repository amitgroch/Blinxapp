package com.myandriod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class Getmethodex {

	
	public String getINternetData() throws IOException, URISyntaxException{
		BufferedReader in=null;
		String data=null;
		StringBuffer sb = null;
		try {
			HttpClient client = new DefaultHttpClient();
			URI website = new URI("http://www.google.com");
			HttpGet request = new HttpGet();
			request.setURI(website);
			HttpResponse response=client.execute(request);
			in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			sb = new StringBuffer("");
			String l ="";
			String nl=System.getProperty("line.separator");
			while((l=in.readLine())!=null){
				sb.append(l+nl);
			}
			in.close();
			data = sb.toString();
		} finally {
			// TODO: handle exception
			data = sb.toString();
		}
		return data;
		
	}
}
