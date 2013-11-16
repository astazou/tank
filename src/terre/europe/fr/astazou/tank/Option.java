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

public class Option extends BasicGameState
{
	public static final int ID = 2;
	
	private Image background;
	private Font font;
	private TrueTypeFont ttf;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException 
	{
		background = new Image("pics/menu.png");
		font = new Font("font", Font.BOLD, 60);
		ttf = new TrueTypeFont(font, true);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
	{
		background.draw(0,0);
		ttf.drawString(420, 150, "Options", Color.red);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException 
	{
		Input input = container.getInput();
		
		if (input.isKeyDown(Keyboard.KEY_Q))
		{
			game.enterState(GameState.ID);
		}
		
	}

	@Override
	public int getID() 
	{
		return ID;
	}

}
