package terre.europe.fr.astazou.tank;

public class Wind 
{
	public float force,min,max;
	
	
	public Wind()
	{
		this.min = -10;
		this.max = 10;
	}
	
	public void init()
	{
		this.force = (float) (Math.random()*20-10);
	}
	
	public float getForce()
	{
		return this.force;
	}
}
