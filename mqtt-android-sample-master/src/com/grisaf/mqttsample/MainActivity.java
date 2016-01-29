package com.grisaf.mqttsample;

import android.os.Bundle;
import android.os.Looper;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;

@SuppressLint("NewApi")
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		String server = "172.22.228.25";
		
		MQTTUtils.connect(server);
		
		
		Button send = (Button)findViewById(R.id.sendButton);
		send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				EditText serverEditText = (EditText)findViewById(R.id.serverText);
				String msg = serverEditText.getText().toString();
				
				TextView resultText = (TextView)findViewById(R.id.resultTextView);
				resultText.setText(String.format(msg));
				serverEditText.setText("");
				
				String topic = "chat/THUANETHAN";
				MQTTUtils.pub(topic, msg);
			}
		});
			
			//TextView recText = (TextView)findViewById(R.id.recievedTextView);
			//recText.setText(message.toString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
