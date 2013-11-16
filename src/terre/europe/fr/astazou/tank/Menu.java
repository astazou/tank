package terre.europe.fr.astazou.tank;



import java.awt.Font;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Menu extends BasicGameState
{

	public static final int ID = 1;
	private Image background;
	private Font font, font2;
	private TrueTypeFont ttf, ttf2;
	
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException 
	{
		background = new Image("pics/menu.png");
		font = new Font("Verdana", 0, 30);
		font2 = new Font("font", Font.BOLD, 60);
		ttf = new TrueTypeFont(font, true);
		ttf2 = new TrueTypeFont(font2, true);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
	{
		background.draw(0,0);
		ttf.drawString(440, 340, "1) Play", Color.red);
		ttf.drawString(440, 400, "2) Option", Color.red);
		ttf2.drawString(440, 150,"Tank", Color.red);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int up) throws SlickException 
	{
		Input input = container.getInput();
		
		if (input.isKeyDown(Keyboard.KEY_1))
		{
			game.enterState(GameState.ID);
		}
		
		if (input.isKeyDown(Keyboard.KEY_2))
		{
			game.enterState(2);
		}
		
	    if (input.isKeyDown(Keyboard.KEY_ESCAPE))  
	    {         
	    	container.exit();        
	    }  
		
	}

	@Override
	public int getID() 
	{
		return ID;
	}

}
