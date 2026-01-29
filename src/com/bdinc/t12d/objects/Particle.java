package com.bdinc.t12d.objects;

import java.awt.Graphics;
import java.awt.Image;

import com.bdinc.t12d.level.LevelManager;
import com.bdinc.t12d.main.Game;
import com.bdinc.t12d.maths.Map;
import com.bdinc.t12d.maths.Vector2;
import com.bdinc.t12d.utils.IntVector2;

public class Particle {
	
	private Image sprite;
	
	private float x, y;
	private int cellX, cellY, tmpX, tmpY;
	
	private Entity source, target;
	
	public static final int DIRECTION_RIGHT = 2001;
	public static final int DIRECTION_LEFT = 2002;
	public static final int DIRECTION_TOP = 2003;
	public static final int DIRECTION_BOTTOM = 2004;
	public static final int DIRECTION_TOP_RIGHT = 2005;
	public static final int DIRECTION_TOP_LEFT = 2006;
	public static final int DIRECTION_BOTTOM_RIGHT = 2007;
	public static final int DIRECTION_BOTTOM_LEFT = 2008;
	
	private Map map = new Map();
	
	private float speed = 0.5f;
	
	public boolean active = false;
	public boolean hit = false;
	
	public Particle(Image sprite) {
		this.sprite = sprite;
		try
		{
			map.init();
		}
		catch(Exception e)
		{
			System.err.println("Can't initialize the map...");
			System.err.println("Caused by particle<"+this.toString()+">!");
			e.printStackTrace();
		}
	}
	
	public void start() {
		active = true;
		tmpX = this.cellX;
		tmpY = this.cellY;
	}
	
	public void setSpeed(float value) {
		this.speed = value;
	}
	
	public void incSpeed() {
		this.speed += 1;
	}
	
	public void incSpeed(float value) {
		this.speed += value;
	}
	
	public void decSpeed() {
		this.speed -= 1;
	}
	
	public void decSpeed(float value) {
		this.speed -= value;
	}
	
	public void move() {
		if(this.cellX > target.getCell().x) {
			if(this.x <= 0) {
				this.active = false;
				this.hit = true;
			}
			if(this.cellX != target.getCell().x) {
				this.x -= speed;
				this.setCell(map.checkCell(x, y));
			}
		} else if (this.cellX < target.getCell().x) {
			if(this.x >= Game.WIDTH) {
				this.active = false;
				this.hit = true;
			}
			if(this.cellX != target.getCell().x) {
				this.x += speed;
				this.setCell(map.checkCell(x, y));
			}
		} else {
			this.hit = true;
			target.decreaseHealth(10);
		}
	}
	
	public void moveTo(int direction) {
		switch(direction) {
			case DIRECTION_RIGHT:
				if(this.cellX >= target.getCell().x) {
					if(this.cellY == target.getCell().y) {
						target.decreaseHealth(10);
						active = false;
					}
				}
				if(this.cellX - tmpX >= 5) {
					active = false;
					
				}
				this.x += 1*speed;
				setCell(map.checkCell(x, y));
				break;
			case DIRECTION_LEFT:
				if(this.cellX <= target.getCell().x) {
					if(this.cellY == target.getCell().y) {
						target.decreaseHealth(10);
						active = false;
					}
				}
				if(tmpX - this.cellX >= 5) {
					
					active = false;
				}
				this.x -= 1*speed;
				setCell(map.checkCell(x, y));
				break;
			case DIRECTION_TOP:
				this.y -= 1*speed;
				setCell(map.checkCell(x, y));
				break;
			case DIRECTION_BOTTOM:
				this.y += 1*speed;
				setCell(map.checkCell(x, y));
				break;
			case DIRECTION_TOP_RIGHT:
				this.y -= 1*speed;
				this.x += 1*speed;
				setCell(map.checkCell(x, y));
				break;
			case DIRECTION_TOP_LEFT:
				this.y -= 1*speed;
				this.x -= 1*speed;
				break;
			case DIRECTION_BOTTOM_RIGHT:
				this.y += 1*speed;
				this.x += 1*speed;
				break;
			case DIRECTION_BOTTOM_LEFT:
				this.y += 1*speed;
				this.x -= 1*speed;
				break;
			default:
				break;
		}
	}
	
	public void setSource(Entity src) {
		this.source = src;
	}
	
	public void setTarget(Entity target) {
		this.target = target;
	}
	
	public Entity getTarget() {
		return target;
	}
	
	public Entity getSource() {
		return source;
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
			System.err.println("Can't set the location to particle<"+this.toString()+">!");
		}
		
		this.cellX = x;
		this.cellY = y;
		this.x = pos.x;
		this.y = pos.y;
	}
	
	@Override
	public String toString()
	{
		int id = 0;
		for(Object e : LevelManager.currentLevel.particles.toArray()) {
			Entity ent = (Entity)e;
			if(this.equals(ent))
			{
				return "t12d:particle#"+id;
			}
			id++;
		}
		return "t12d:particle#???(null)";
	}
	
	public void draw(Graphics g)
	{
		if(sprite == null)
		{
			System.err.println("No sprite(null)! Caused by particle<"+this.toString()+">!");
		}
		try
		{
			g.drawImage(sprite, (int)x, (int)y, null);
		}
		catch(Exception e)
		{
			System.err.println("Can't draw the particle<"+this.toString()+">!");
			e.printStackTrace();
		}
	}
	
	public void setCell(Vector2 cell) {
		cellX = (int)cell.x;
		cellY = (int)cell.y;
	}
	
	public IntVector2 getCell() {
		return new IntVector2(cellX, cellY);
	}
	
	public Image getSprite() {
		return sprite;
	}
	
	public Vector2 position() {
		return new Vector2(x, y);
	}
	
}
