package com.myandriod;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class SimpleBrower extends Activity implements OnClickListener {

	WebView ourBrow;
	EditText ed;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.brower);
		ourBrow = (WebView) findViewById(R.id.wvBrower);
		ourBrow.getSettings().setJavaScriptEnabled(true);
		ourBrow.getSettings().setLoadWithOverviewMode(true);
		ourBrow.getSettings().setUseWideViewPort(true);
		ourBrow.setWebViewClient(new ourViewClient());
		ourBrow.loadUrl("http://www.google.com");

		Button go = (Button) findViewById(R.id.go);
		Button back = (Button) findViewById(R.id.bback);
		Button forword = (Button) findViewById(R.id.bForword);
		Button history = (Button) findViewById(R.id.bHistory);
		Button refresh = (Button) findViewById(R.id.bRefresh);

		ed = (EditText) findViewById(R.id.eturl);

		go.setOnClickListener(this);
		back.setOnClickListener(this);
		forword.setOnClickListener(this);
		history.setOnClickListener(this);
		refresh.setOnClickListener(this);

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.go:
			String url=ed.getText().toString();
			ourBrow.loadUrl(url);
			//hiding the keyboard
			InputMethodManager imm =(InputMethodManager)getSystemService(Contact.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(ed.getWindowToken(), 0);
			
			break;
		case R.id.bback:
			if(ourBrow.canGoBack())
				ourBrow.goBack();
			break;
		case R.id.bForword:
			if(ourBrow.canGoForward())
				ourBrow.goForward();;

			break;
		case R.id.bHistory:
			ourBrow.clearHistory();
			break;
		case R.id.bRefresh:
			ourBrow.reload();
			break;

		}

	}
	
	public class ourViewClient extends WebViewClient{
		
		@Override
		public boolean shouldOverrideUrlLoading(WebView v, String url){
			v.loadUrl(url);
			return true;
		}
	}
	

}
