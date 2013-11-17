package terre.europe.fr.astazou.tank;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameState extends BasicGameState
{
	public static final int ID = 3;
	
	private World world;
	private Tank tank;
	private Input input;
	private Target target1,target2,target3;
	private TrueTypeFont forceWind, angle, reloading, fireCount;
	private Font font;

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException 
	{
		world = new World();
		world.init();
		tank = new Tank(input);
		tank.init();
		target1 = new Target(850,350);
		target1.init();
		target2 = new Target(900,325);
		target2.init();
		target3 = new Target(950,300);
		target3.init();
		font = new Font("Verdana", 0, 30);
		forceWind = new TrueTypeFont(font,true);
		angle = new TrueTypeFont(font,true);
		reloading = new TrueTypeFont(font, true);
		fireCount = new TrueTypeFont(font, true);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
	{
		world.render(g);
		tank.render(g);
		target1.render(g);
		target2.render(g);
		target3.render(g);
		forceWind.drawString(20, 550, "Wind : "+tank.getWind(), Color.white);
		angle.drawString(20, 590, "Angle : "+tank.getAngle(), Color.white);
		if((tank.getFired() == false) && (tank.getPara()==false))
		{
			reloading.drawString(20, 510, "Loaded, you can fire.", Color.green);
		}
		else
		{
			reloading.drawString(20, 510, "Reloading...", Color.red);
		}
		
		fireCount.drawString(20, 20, "Number of shot : "+ tank.getNumberShot().toString(), Color.black);
		
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException 
	{
		world.update(container, delta);
		tank.update(container, game, delta);
		target1.update(container, game, delta);
		target2.update(container, game, delta);
		target3.update(container, game, delta);
		
		if(!(tank.getBound().intersects(world.getGround())))
		{
			tank.setY(1);
		}
		else
		{
			this.tank.parachute();
		}
		
		if(!(target1.getBound().intersects(world.getGround())))
		{
			target1.setY(1);
		}
		else
		{
			this.target1.parachute();
		}
		
		if(!(target2.getBound().intersects(world.getGround())))
		{
			target2.setY(1);
		}
		else
		{
			this.target2.parachute();
		}
		
		if(!(target3.getBound().intersects(world.getGround())))
		{
			target3.setY(1);
		}
		else
		{
			this.target3.parachute();
		}
		
		
		if(tank.getBullet().intersects(world.getGround()) || tank.getBullet().intersects(world.getWall()))
		{
			tank.setBombDestroyed();
		}
		
	}

	@Override
	public int getID() 
	{
		return ID;
	}
	

}
