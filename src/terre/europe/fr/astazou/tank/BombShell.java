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
	private Boolean alive;
	
	public BombShell()
	{
	}
	
	public void fire(float x, float y) throws SlickException
	{
		this.x = x;
		this.y = y;
		this.alive = true;
		bomb = new Circle(x, y, 2);
	}
	
	public void render(Graphics g)
	{
		g.setColor(Color.black);
		g.fill(bomb);
		if(this.alive==true)
		{
			g.draw(bomb);
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
         explosionSheet = new SpriteSheet("pics/explosion2.png", 40,40);   
         explosion = new Animation( explosionSheet, 0,0,1,1,false, 50, true);  
         for (int i=0;i < 2;i++)  
             {  
                 for (int j=0;j < 4;j++)  
                 {explosion.addFrame( explosionSheet.getSprite(j,i),100);}  
     }  
         explosion.stopAt(7);  
    } 
}
