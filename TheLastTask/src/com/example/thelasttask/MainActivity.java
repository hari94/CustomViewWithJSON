package com.example.thelasttask;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.ViewGroup.LayoutParams;

public class MainActivity extends Activity {
	
	CustomView cv;
	Handler mainHandler;
	int i;
	String json="";
	JSONArray ppl;
	LayoutParams params;
	String[] name=new String[10];
	int[] posX=new int[10];
	int[] posY=new int[10];
	int[] colorR=new int[10];
	int[] colorG=new int[10];
	int[] colorB=new int[10];
	boolean run_thread=true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mainHandler=new Handler();
		Runnable runnable=new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(run_thread)
				{
					
					
					DefaultHttpClient   httpclient = new DefaultHttpClient(new BasicHttpParams());
					DefaultHttpClient defaultClient = new DefaultHttpClient();
					// Setup the get request
					HttpGet httpGetRequest = new HttpGet("http://10.0.2.2:8080");
					try{
						// Execute the request in the client
						HttpResponse httpResponse = defaultClient.execute(httpGetRequest);
						// Grab the response
						BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"));
						json = reader.readLine();						
						}catch(Exception e){
							Log.d("Error",e.toString());
						}
					
					
					
					try {
						ppl = new JSONArray(json);
						for(int i=0;i<ppl.length();i++)
						{					
						name[i]=ppl.getJSONObject(i).getString("name");
						posX[i]=ppl.getJSONObject(i).getInt("X");
						posY[i]=ppl.getJSONObject(i).getInt("Y");
						colorR[i]=ppl.getJSONObject(i).getInt("colorR");
						colorG[i]=ppl.getJSONObject(i).getInt("colorG");
						colorB[i]=ppl.getJSONObject(i).getInt("colorB");
						}
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					
				mainHandler.post(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						
							cv=new CustomView(MainActivity.this,name,posX,posY,colorR,colorG,colorB);
							setContentView(cv);						
						
					}
				});				
				
				delay();
				}	
			}
		
		};
		
		new Thread(runnable).start();
		
		
		
		
	}
	
	
	
	protected void delay() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	

}
