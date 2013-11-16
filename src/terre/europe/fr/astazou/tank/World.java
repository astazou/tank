package terre.europe.fr.astazou.tank;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;



public class World 
{
	private Image background;
	private Rectangle ground;
	
	public World(){}
	
	public void init() throws SlickException
	{
		background = new Image("pics/background.png");
		ground = new Rectangle(0,500,1024,140);
	}
	
	public void render(Graphics g)
	{
		background.draw(0,0);
		g.setColor(Color.gray);
		g.fill(ground);
		g.draw(ground);
	}

	public void update(GameContainer container, int delta) throws SlickException
	{
		
	}
	
	public Rectangle getGround()
	{
		return ground;
	}
}
