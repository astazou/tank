package terre.europe.fr.astazou.tank;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class Target 
{
	private Image targetTexture;
	private Image texturePara;
	private Rectangle target;
	private float x,y;
	private Boolean alive;
	private Boolean parachute;
	
	public Target(float x, float y)
	{
		this.x=x;
		this.y=y;
	}

	public void init() throws SlickException 
	{
		targetTexture = new Image("pics/target.png");
		texturePara = new Image("pics/para.png");
		target = new Rectangle(x,y,targetTexture.getWidth(),targetTexture.getHeight());
		alive = new Boolean(true);
		parachute = new Boolean(true);
	}

	public void render(Graphics g) 
	{
		if(alive == true)
		{
			targetTexture.draw(x,y);
		}
		
		if(parachute == true)
		{
			texturePara.draw(x,y-20);
		}
		
	}

	public void update(GameContainer container, StateBasedGame game, int delta) 
	{
		target.setLocation(this.x, this.y);
		
	}

	public Rectangle getBound() 
	{
		return target;
	}
	
	public void setY(float y)
	{
		this.y+=y;
	}
	
	public void parachute() 
	{
		this.parachute = false;
	}
	
	public void destroyed()
	{
		this.alive = false;
	}


}
