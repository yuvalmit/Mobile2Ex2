package net.mitrani.markeronmap;

import java.util.ArrayList;
import net.mitrani.markeronmap.Marker;

public class MarkerArray
{
	private static MarkerArray instance ;
	private ArrayList<Marker> theList = new ArrayList<Marker>();
	
	private MarkerArray(){}
	
	public static MarkerArray getInstance()
	{
		if(instance == null)
		{
			instance = new MarkerArray();
		}
		return instance;
	}
	
	public void addItem(float x, float y, int id)
	{
		theList.add(new Marker(x,y,id));
	}
	
	public void addItem(Marker mark) 
	{
		theList.add(mark);
		
	}
	
	public Marker getItem(int index)
	{
		return theList.get(index);
	}
	
	public Marker getItemById(int id)
	{
		for(int i=0; i < getSize(); i++)
		{
			if(getItem(i).getiD() == id)
				return getItem(i);
		}
		return null;
	}
	
	public void delItem(int index)
	{
		 theList.remove(index);
	}
	
	public int getSize()
	{
		return theList.size();
	}

	

	

}
