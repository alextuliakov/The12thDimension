package com.bdinc.t12d.objects;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

import com.bdinc.t12d.level.LevelManager;
import com.bdinc.t12d.main.Game;
import com.bdinc.t12d.maths.Map;
import com.bdinc.t12d.maths.Physics;
import com.bdinc.t12d.maths.Vector2;
import com.bdinc.t12d.settings.ResourcesManager;
import com.bdinc.t12d.ui.Inventory;
import com.bdinc.t12d.utils.Debug;
import com.bdinc.t12d.utils.IntVector2;

public class Entity implements Serializable {
	
	private float x, y, tmp, enemyTmpX, enemyTmpY;
	private int cellX, cellY;
	private Map map = new Map();
	
	public String id;
	
	public ArrayList<Item> invList = new ArrayList<Item>();
	
	private float direction = -0.5f;
	
	private int health = 100;
	private int maxHealth = 100;
	
	private int ammo = 30;
	private int maxAmmo = 30;
	private int money = 100;
	private int rubies = 20;
	
	private int magicCount = 50;
	private int maxMagic = 50;
	
	public boolean inventoryShow;
	public Inventory inventory;
	
	public static Object interactiveTarget;
	private float speed = 1f, runSpeed = 1.5f;
	
	public boolean isRunning, right, left, jump, isFalling, isLifting, isFlying, isInteracting;
	private boolean magicUnlimited, eMoving = false;
	
	private Platform liftingObject;
	
	private String name = "#Entity:???";
	
	Game game = new Game();
	
	private Image texture;
	private Vector2 position;
	
	public Entity(Image texture)
	{
		inventory = new Inventory();
		this.texture = texture;
		try
		{
			map.init();
		}
		catch(Exception e)
		{
			System.err.println("Can't initialize the map...");
			System.err.println("Caused by entity<"+this.toString()+">!");
			e.printStackTrace();
		}
		position = map.checkCell(x, y);
	}
	
	public void enemyMove() {
		if(!Game.paused) {
			if(!eMoving) {
				enemyTmpX = this.cellX;
				enemyTmpY = this.cellY;
				eMoving = true;
			}
			if(eMoving) {
				if(cellX == enemyTmpX-2) {
					direction = 0.5f;
				}
				else if(cellX == enemyTmpX+2) {
					direction = -0.5f;
				}
				this.x = this.x + direction;
				setCell(map.checkCell(x, y));
				
			}
		}
		
	}
	
	public void move(float speed) {
		position = map.checkCell(x, y);
		this.x += speed;
		setCell(position);
	}
	
	public void moveUp(float speed) {
		position = map.checkCell(x, y);
		this.y += speed;
		setCell(position);
		
	}
	
	boolean colRight = false;
	public void moveRight() {
		colRight = Physics.collidesRight(x, y);
		position = map.checkCell(x, y);
		if(!Game.paused) {
			if(!colRight) {
				if(!isRunning) {
					if(this.x+map.cellSize < game.getWidth()) {
						if(isFalling && speed > 0.5f) {
							this.x += 1 * (speed - 0.5f);
						}
						else if(isFalling && speed <= 0.5f) {
							this.x += 1 * speed+0.2;
						}else {
							this.x += 1 * speed;
						}
						
						setCell(position);
					}
				}
				else {
					if(this.x+map.cellSize < game.getWidth()) {
						if(isFalling && runSpeed > 1f) {
							this.x += 1 * (runSpeed - 1f);
						}else if(isFalling && runSpeed <= 0.5f) {
							this.x += 1 * runSpeed+0.2;
						} else {
							this.x += 1 * runSpeed;
						}
						setCell(position);
					}
				}
			}
		}
	}
	
	public void setCell(Vector2 cell) {
		cellX = (int)cell.x;
		cellY = (int)cell.y;
	}
	
	public float getSpeed() {
		return speed;
	}
	
	public float getRunSpeed() {
		return runSpeed;
	}
	
	public Platform getLiftingObject() {
		return this.liftingObject;
	}
	
	public void setLiftingObject(Platform p) {
		this.liftingObject = p;
	}
	
	public void setSpeed(float v) {
		this.speed = v;
	}
	
	public void setRunSpeed(float v) {
		this.runSpeed = v;
	}
	
	boolean colBot = false;
	boolean colTop = false;
	public void jump() {
		colBot = Physics.collidesBottom(x, y);
		colTop = Physics.collidesTop(x, y);
		position = map.checkCell(x, y);
		if(!Game.paused) {
			if(colBot) {
				tmp = y;
			}
			y -= 1.5f;
			setCell(position);
			if(y <= tmp-60 || colTop) {
				jump = false;
			}
		}
	}
	
	boolean colLeft = false;
	public void moveLeft() {
		colLeft = Physics.collidesLeft(x, y);
		position = map.checkCell(x, y);
		if(!Game.paused) {
			if(!colLeft) {
				if(!isRunning) {
					if(this.x > 0) {
						if(isFalling && speed > 0.5f) {
							this.x -= 1 * (speed - 0.5f);
						} else if(isFalling && speed <= 0.5f) {
							this.x -= 1 * speed+0.2;
						}else {
							this.x -= 1 * speed;
						}
						setCell(position);
					}
				}
				else {
					if(this.x > 0) {
						if(isFalling && runSpeed > 1f) {
							this.x -= 1 * (runSpeed - 1f);
						} else if(isFalling && runSpeed <= 0.5f) {
							this.x -= 1 * runSpeed+0.2;
						}else {
							this.x -= 1 * runSpeed;
						}
						setCell(position);
					}
				}
			}
		}
		
	}
	
	public void attack(Entity target) {
		Particle magic = new Particle(ResourcesManager.bullet1_01);
		magic.setSource(this);
		magic.setPosition(cellX, cellY+1);
		magic.setSpeed(1);
		magic.setTarget(target);
		for(Particle p : LevelManager.currentLevel.particles) {
			if(p.getTarget().equals(magic.getTarget())) {
				if(p.hit) {
					LevelManager.currentLevel.particles.add(magic);
					p.active = false;
				}
			}
		}
		if(LevelManager.currentLevel.particles.size() == 0) {
			LevelManager.currentLevel.particles.add(magic);
		}
	}
	
	public void setAmmo(int value) {
		this.ammo = value;
	}
	
	public void setMaxAmmo(int value) {
		this.maxAmmo = value;
	}
	
	public void increaseAmmo(int value) {
		this.ammo += value;
	}
	
	public void decreaseAmmo(int value) {
		this.ammo -= value;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public void setMaxHealth(int health) {
		this.maxHealth = health;
	}
	
	public void increaseHealth() {
		this.health += 1;
	}
	
	public void decreaseHealth() {
		this.health -= 1;
	}
	
	public void increaseHealth(int value) {
		this.health += value;
	}
	
	public void decreaseHealth(int value) {
		this.health -= value;
	}
	
	public void setMagicCount(int magic) {
		this.magicCount = magic;
	}
	
	public void setMagicCount(String tag) {
		if(tag.equals("unlimited")) {
			magicUnlimited = true;
		}
	}
	
	public void setMaxMagic(int magic) {
		this.maxMagic = magic;
	}
	
	public void increaseMagic() {
		this.magicCount += 1;
	}
	
	public void decreaseMagic() {
		this.magicCount -= 1;
	}
	
	public void increaseMagic(int value) {
		this.magicCount += value;
	}
	
	public void decreaseMagic(int value) {
		this.magicCount -= value;
	}
	
	public void setMoney(int value) {
		this.money = value;
	}
	
	public void increaseMoney(int count) {
		this.money += count;
	}
	
	public void decreaseMoney(int count) {
		this.money -= count;
	}
	
	public void increaseMoney() {
		this.money += 1;
	}
	
	public void decreaseMoney() {
		this.money -= 1;
	}
	
	public void setRubyCount(int value) {
		this.rubies = value;
	}
	
	public void increaseRubyCount(int count) {
		this.rubies += count;
	}
	
	public void decreaseRubyCount(int count) {
		this.rubies -= count;
	}
	
	public void increaseRubyCount() {
		this.rubies += 1;
	}
	
	public void decreaseRubyCount() {
		this.rubies -= 1;
	}
	
	public void incY(float value) {
		this.y += value;
	}
	
	public int getAmmo() {
		return this.ammo;
	}
	
	public int getMaxAmmo() {
		return this.maxAmmo;
	}
	
	public int getMagicCount() {
		return this.magicCount;
	}
	
	public int getMaxMagicCount() {
		return this.maxMagic;
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public int getMaxHealth() {
		return this.maxHealth;
	}
	
	public int getMoney() {
		return this.money;
	}
	
	public int getRubyCount() {
		return this.rubies;
	}
	
	public Image getSprite()
	{
		return this.texture;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setPosition(int x, int y)
	{
		Vector2 pos = null;
		try
		{
			pos = map.getCell(x, y);
		}
		catch(Exception e)
		{
			System.err.println("Can't set the location to entity<"+this.toString()+">!");
		}
		
		this.cellX = x;
		this.cellY = y;
		this.x = pos.x;
		this.y = pos.y;
	}
	
	public int getX() {
		return (int)x;
	}
	
	public IntVector2 getCell()
	{
		return new IntVector2(cellX, cellY);
	}
	
	@Override
	public String toString()
	{
		int id = 0;
		for(Object e : LevelManager.currentLevel.entities.toArray()) {
			Entity ent = (Entity)e;
			if(this.equals(ent))
			{
				return "t12d:entity#"+id;
			}
			id++;
		}
		return "t12d:entity#???(null)";
	}
	
	public void draw(Graphics g)
	{
		if(texture == null)
		{
			System.err.println("No sprite(null)! Caused by entity<"+this.toString()+">!");
		}
		try
		{
			g.drawImage(texture, (int)x, (int)y, null);
		}
		catch(Exception e)
		{
			System.err.println("Can't draw the entity<"+this.toString()+">!");
			e.printStackTrace();
		}
	}
	
	public float posX()
	{
		return x;
	}
	
	public float posY()
	{
		return y;
	}
	
}
