package net.mitrani.markeronmap;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity 
{	
	
	static int markerId = 0;
	
	GestureDetector myGestureDetector;
	LayoutInflater inflater;
	FrameLayout mainView;
	MarkerArray markerList;
	EditText title;
	int selectedMarker = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		
		super.onCreate(savedInstanceState);
		inflater = LayoutInflater.from(getBaseContext());
		mainView = (FrameLayout)inflater.inflate(R.layout.activity_main,null);
		markerList = MarkerArray.getInstance();
		myGestureDetector = new GestureDetector(getApplicationContext(), gListener);
		setContentView(mainView);
		ImageView mapImage = (ImageView)findViewById(R.id.imageView1);
		mapImage.setOnTouchListener(mapTouch);
		title = (EditText) findViewById(R.id.editText1);
		title.addTextChangedListener(textWatch);
	}

	@Override
	protected void onResume() 
	{
		super.onResume();

	}


	
	GestureDetector.OnGestureListener gListener = new GestureDetector.SimpleOnGestureListener()
	{
		
		@Override
		public boolean onSingleTapUp(MotionEvent e)
		{
			Toast.makeText(getApplicationContext(),"tap "+ e.getX() + " " + e.getY(), Toast.LENGTH_SHORT).show();
			return false;
		}

		@SuppressLint("NewApi")
		@Override
		public void onLongPress(MotionEvent e) 
		{
			Toast.makeText(getApplicationContext(), e.getX() + " " + e.getY(), Toast.LENGTH_SHORT).show();
			
			markerId++;
			Marker mark = new Marker(e.getX(), e.getY(), markerId);
			markerList.addItem(mark);
			
			ImageView marker = (ImageView)inflater.inflate(R.layout.marker,null);
			marker.setId(markerId);
			marker.setX(e.getX()-20);
			marker.setY(e.getY()-20);
			marker.bringToFront();
			marker.setTag(markerId);
			marker.setOnClickListener(pinClick);
			mainView.addView(marker);
			
			marker.getLayoutParams().width = 30;
			marker.getLayoutParams().height = 30;
			ImageView mapImage = (ImageView)findViewById(R.id.imageView1);
			mapImage.setOnTouchListener(mapTouch);
			selectedMarker = markerId;
			title.setText("");
		
		
		}	
		
		
	};
	
	OnTouchListener mapTouch = new OnTouchListener() 
	{
		
		@Override
		public boolean onTouch(View v, MotionEvent e) 
		{
			myGestureDetector.onTouchEvent(e);
			return true;
		}
	};
	
	OnClickListener pinClick = new OnClickListener()
	{

		@Override
		public void onClick(View v)
		{
			
			selectedMarker = (Integer) v.getTag();
			Toast.makeText(getApplicationContext(), "clicked "+selectedMarker, Toast.LENGTH_SHORT).show();
			title.setText(markerList.getItemById(selectedMarker).getTitle());	
		}
		
		
	};
	
	TextWatcher textWatch = new TextWatcher() 
	{
		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) 
		{
			if(selectedMarker != -1)
			{
				markerList.getItemById(selectedMarker).setTitle(title.getText().toString());
				
			}
			
		}
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,	int after) 
		{
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void afterTextChanged(Editable s) 
		{
			// TODO Auto-generated method stub
			
		}
	};
	
	
}
