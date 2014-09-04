package com.flyme.numberprogressbardemo;

import com.flyme.numberprogressbar.NumberProgressBar;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;

public class MainActivity extends ActionBarActivity {
	
	
	private int plan = 0;
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			content.setProgress(plan);
		};
	};

	private NumberProgressBar content;	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		content = (NumberProgressBar) findViewById(R.id.content);
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					SystemClock.sleep(100);
					plan ++ ;
					handler.sendEmptyMessage(0);
				}
			}
		}).start();
	}
}
