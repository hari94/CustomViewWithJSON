package com.example.thelasttask;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

public class CustomView extends View{
String[] name=new String[5];
int x[]=new int[5];
int y[]=new int[5];
int r[]=new int[5];
int g[]=new int[5];
int b[]=new int[5];


Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
	public CustomView(Context context,String[] text,int X[],int Y[], int[] colorR, int[] colorG, int[] colorB) {
		super(context);
		x=X;
		y=Y;
		r=colorR;
		g=colorG;
		b=colorB;
		name=text;
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		for(int i=0;i<5;i++)
		{	
			Log.d("onDraw", name[i]);
			paint.setColor(Color.rgb(r[i], g[i], b[i]));
			canvas.drawText(name[i], x[i], y[i], paint);
			
		}
		
	}
	
	
	
	

}
