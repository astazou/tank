package terre.europe.fr.astazou.tank;

public class Canon 
{
	private float angle;
	private float angleMax;
	private float angleMin;
	
	public Canon()
	{
		this.angle = 45;
		this.angleMax = 80;
		this.angleMin = 10;
	}
	
	public float getAngle()
	{
		return this.angle;
	}
	
	public void setAngle(double d)
	{
		if((this.angle+d>=this.angleMax) || (this.angle+d<=this.angleMin))
		{
			d=0;
		}
			
		else
		{
			this.angle+=d;
		}
		
	}
}
