package terre.europe.fr.astazou.tank;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame
{
	private Menu menu;
	private GameState gameState;
	private Option option;
	
	public Game(String name)
	{
		super(name);
	}

	public static void main(String[] args) 
	{
		AppGameContainer container;
		try 
		{
			container = new AppGameContainer(new Game("Tank"));
			container.setDisplayMode(1024, 640, false);  
	        container.setTargetFrameRate(60);
	        container.setShowFPS(false);
	        container.setMultiSample(4);  
	        container.setVSync(true);  
	        container.start();  
		} 
		catch (SlickException e) 
		{
			e.printStackTrace();
		}  
		
		
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException 
	{
		menu = new Menu();
		gameState = new GameState();
		option = new Option();
		addState(menu);
		addState(gameState);
		addState(option);
	}

}
