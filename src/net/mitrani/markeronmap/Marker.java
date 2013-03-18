package net.mitrani.markeronmap;



public class Marker
{
	
	private int iD;
	private float posX;
	private float posY;
	private String title="";
	
	
	public Marker()
	{
		setPosX(0);
		setPosY(0);

	}
	public Marker(float x , float y, int id) 
	{
		
		setPosX(x);
		setPosY(y);
		setiD(id);
	}
	 
	public float getPosX() 
	{
		return posX;
	}
	private void setPosX(float posX) 
	{
		this.posX = posX;
	}
	public float getPosY() 
	{
		return posY;
	}
	private void setPosY(float posY) 
	{
		this.posY = posY;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public int getiD() 
	{
		return iD;
	}
	public void setiD(int iD) 
	{
		this.iD = iD;
	}

	
	

}

