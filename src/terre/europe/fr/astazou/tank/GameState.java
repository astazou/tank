package terre.europe.fr.astazou.tank;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameState extends BasicGameState
{
	public static final int ID = 3;
	
	private World world;
	private Tank tank;
	private Input input;
	private Target target1,target2,target3;

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
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
	{
		world.render(g);
		tank.render(g);
		target1.render(g);
		target2.render(g);
		target3.render(g);
		
		
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
		
		/*
		if(tank.getBomb().intersects(world.getGround()))
		{
			tank.setBombDestroyed(true);
		}
		*/
	}

	@Override
	public int getID() 
	{
		return ID;
	}
	

}
