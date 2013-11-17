package terre.europe.fr.astazou.tank;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
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
	private Circle bullet;
	private Wind wind;
	private Boolean alreadyDestroyed;
	private Integer numberShot;
	
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
		System.out.println("force wind : " + this.wind.getForce());
		this.bombShell.fire(bombX, bombY);
		this.bullet = bombShell.getBound();
		this.alreadyDestroyed = new Boolean(false);
		this.numberShot = 0;
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
		
		else if ((input.isKeyDown(Keyboard.KEY_SPACE))&&(this.noRepeat==false)&&(this.parachute==false))
		{
			this.noRepeat=true;
			this.alreadyDestroyed = false;
			System.out.println("Fire at " + canon.getAngle() + " degrees");
			this.bombX = this.x+11;
			this.bombY = this.y-2;
			this.bombShell.fire(this.bombX,this.bombY);
			this.speedX=4;
			this.speedY=4;
			this.direction=canon.getAngle();
			this.fired=true;
			this.numberShot++;
			
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
			
			this.bullet = bombShell.getBound();
			
			if(this.bombShell.alive()==true || this.bombShell.play()==true)
			{
				bombShell.render(g);
			}
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
	
	public Circle getBullet()
	{
		return this.bullet;
	}

	public void setBombDestroyed() throws SlickException 
	{
		if(this.alreadyDestroyed==false)
		{
			this.bombShell.destroy();
			//this.fired = false;
			this.noRepeat = false;
			this.speedX = 0;
			this.speedY= 0;
			this.wind.init();
			System.out.println("force wind : " + this.wind.getForce());
			this.bombX = bombShell.getX();
			this.bombY = bombShell.getY();
			//this.bombShell.fire(this.bombX, this.bombY);
			this.bullet = bombShell.getBound();
		}
		this.alreadyDestroyed = true;
	}
	
	public float getWind()
	{
		return this.wind.getForce();
	}
	
	public float getAngle()
	{
		return this.canon.getAngle();
	}
	
	public Boolean getFired()
	{
		return this.noRepeat;
	}
	
	public Integer getNumberShot()
	{
		return this.numberShot;
	}
	
	public Boolean getPara()
	{
		return this.parachute;
	}

}
