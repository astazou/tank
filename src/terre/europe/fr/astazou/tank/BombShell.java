package terre.europe.fr.astazou.tank;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Circle;

public class BombShell 
{
	private Circle bomb;
	private float x,y;
	private SpriteSheet explosionSheet;
	private Animation explosion;
	private Boolean alive = false;
	private Boolean play = false;
	private int count = 0;
	
	public BombShell()
	{
		bomb = new Circle(x, y, 2);
	}
	
	public void fire(float x, float y) throws SlickException
	{
		this.x = x;
		this.y = y;
		this.alive = true;
		this.play = false;
		bomb.setRadius(2);
		bomb.setX(this.x);
		bomb.setY(this.y);
		this.count = 0;
	}
	
	public void render(Graphics g)
	{
		g.setColor(Color.black);
		g.fill(bomb);
		
		if(this.count>=20)
		{
			this.play = false;
		}
		
	    if(this.play==true)  
        {  
	    	this.count++;
            if(this.explosion.getFrame()>=7)
            {  
    	    	this.explosion.restart();
            }  
            if(this.explosion.getFrame()==0)
            {
            	play=true;
            }   
            if(this.play==true)
            {
            	explosion.draw(this.x-20,this.y-20);
            }  

        } 
		
	}
	
	public void update () throws SlickException
	{
		bomb.setLocation(getX(), getY());
	}
	
	public float getX()
	{
		return this.x;
	}
	
	public float getY()
	{
		return this.y;
	}
	
	public void setX(double d)
	{
		this.x += d;
	}
	
	public void setY(double y)
	{
		this.y += y;
	}
	
	public Circle getBound()
	{
		return bomb;
	}
	
	public void createAnimation() throws SlickException  
    {  
         explosionSheet = new SpriteSheet("pics/explosion.png", 40,40);   
         explosion = new Animation( explosionSheet, 0,0,1,1,false, 50, true);  
         for (int i=0;i < 2;i++)  
         {  
             for (int j=0;j < 4;j++)  
             {
            	 explosion.addFrame( explosionSheet.getSprite(j,i),100);
             }  
         }  
     	this.alive = false;
         explosion.stopAt(7); 
    } 
	
	
	public void destroy() throws SlickException
	{
		bomb.setRadius(0);
		this.play = true;
		createAnimation();
	}
	
	public Boolean play()
	{
		if(this.play == true)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public Boolean alive()
	{
		if(this.alive == true)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
