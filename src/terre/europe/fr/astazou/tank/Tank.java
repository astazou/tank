package terre.europe.fr.astazou.tank;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class Tank 
{
	private Input input;
	private Image textureTank;
	private Image textureCanon;
	private Image texturePara;
	private float x = 34f, y = 350f;
	private Rectangle bound;
	private Canon canon;
	private Boolean parachute;
	private Boolean noRepeat;
	private BombShell bombShell;
	private Boolean fired;
	private float speedX, speedY, bombX, bombY, direction;
	private Wind wind;
	
	public Tank(Input input)
	{
		this.input = input;
		this.bombShell = new BombShell();
	}
	
	public void init() throws SlickException
	{
		this.textureTank = new Image("pics/tank.png");
		this.textureCanon = new Image("pics/canon.png");
		this.textureCanon.rotate((float) (-45));
		this.texturePara = new Image("pics/para.png");
		this.bound = new Rectangle(x,y,textureTank.getWidth(),textureTank.getHeight());
		this.canon = new Canon();
		this.parachute = new Boolean(true);
		this.noRepeat = new Boolean(false);
		this.bombShell = new BombShell();
		this.fired = new Boolean(false);
		this.speedX = 0;
		this.speedY = 0;
		this.bombX = this.x+10;
		this.bombY = this.y+1;
		this.direction = canon.getAngle();
		this.wind = new Wind();
		this.wind.init();
	}
	
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException
	{
		input = gc.getInput();
		
		if(input.isKeyDown(Input.KEY_RIGHT))
		{
			if(x<1004)
			{
				x+=delta*0.05f;
			}
		}
		
		else if(input.isKeyDown(Input.KEY_LEFT))
		{
			if(x>0)
			{
				x-=delta*0.05f;
			}
		}
		
		else if (input.isKeyDown(Keyboard.KEY_Q))
		{
			game.enterState(Menu.ID);
		}
		
		else if (input.isKeyDown(Keyboard.KEY_UP))
		{		
			if((canon.getAngle()+0.5)>= 80)
			{
				System.out.println("Max");
			}
			else
			{
				canon.setAngle(0.5);
				textureCanon.rotate((float) (-0.5));
			}
		}
		
		else if (input.isKeyDown(Keyboard.KEY_DOWN))
		{
			if((canon.getAngle()-0.5)<= 10)
			{
				System.out.println("Min");
			}
			else
			{
				canon.setAngle(-0.5);
				textureCanon.rotate((float) (0.5));
			}
		}
		
		else if (input.isKeyDown(Keyboard.KEY_R))
		{
			this.fired = false;
			this.noRepeat = false;
			this.wind.init();
			System.out.println("force wind : " + this.wind.getForce());
			
		}
		
		else if ((input.isKeyDown(Keyboard.KEY_SPACE))&&(this.noRepeat==false))
		{
			this.noRepeat=true;
			System.out.println("Fire at " + canon.getAngle() + " degrees");
			this.bombX = this.x+11;
			this.bombY = this.y-2;
			bombShell.fire(this.bombX,this.bombY);
			this.speedX=4;
			this.speedY=4;
			this.direction=canon.getAngle();
			this.fired=true;
			
		}
		bound.setLocation(x, y);
	}
	
	public void render(Graphics g) throws SlickException
	{
		textureTank.draw(x,y);
		textureCanon.draw(x,y+1);
		if(this.parachute==true)
		{
			texturePara.draw(x,y-20);
		}
		if(this.fired==true)
		{
			this.speedY-=0.02f;
			this.speedX+=this.wind.getForce()/500;
			bombShell.setY(-speedY*Math.sin(this.direction*0.0174532925));
			bombShell.setX(speedX*Math.cos(this.direction*0.0174532925));
			bombShell.update();
			bombShell.render(g);
		}
		
	}
	
	public Rectangle getBound()
	{
		return bound;
	}
	
	public void setY(float y)
	{
		this.y+=y;
	}

	public void parachute() 
	{
		this.parachute = false;
	}
	

}
