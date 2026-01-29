package com.bdinc.t12d.objects;

import java.awt.Image;
import java.io.Serializable;

import com.bdinc.t12d.main.Game;
import com.bdinc.t12d.maths.Physics;
import com.bdinc.t12d.maths.Vector2;
import com.bdinc.t12d.utils.Debug;

public class VerticalPlatform extends Platform implements Serializable {
	
	private int tmpY;
	
	public VerticalPlatform(Image sprite) {
		super(sprite);
	}
	public VerticalPlatform(Image sprite, float speed, int count, int direction) {
		super(sprite);
		this.speed = speed;
		this.count = count;
		this.direction = direction;
		this.x = map.getCell(cellX, cellY).x;
		this.y = map.getCell(cellX, cellY).y;
	}
	
	/*
	 * Direction:
	 * 1 - Down
	 * -1 - Up
	 */
//	public void move(int count, int direction) {
//		if(!this.moving) {
//			this.tmpY = this.cellY;
//			//System.out.println("TMP:"+tmpY+";CELLY:"+cellY);
//			this.moving = true;
//		} else {
//			System.out.println("TMP:"+this.tmpY+";CELLY:"+this.cellY);
//			if(this.cellY == this.tmpY+this.count) {
//				this.direction = -1;
//			} else if(this.cellY == this.tmpY) {
//				this.direction = 1;
//			}
//			this.y += this.direction * this.speed; 
//			this.setCell(map.checkCell(this.x, this.y));
//		}
//	}
	
	@Override
	public void setLocation(int x, int y)
	{
		Vector2 pos = null;
		try
		{
			pos = map.getCell(x, y);
		}
		catch(Exception e)
		{
			System.err.println("Can't set the location to "+id+"<"+this.toString()+">!");
		}
		
		this.cellX = x;
		this.cellY = y;
		this.x = pos.x;
		this.y = pos.y;
	}
	
	@Override
	public void move() {
		if(!this.moving) {
			this.tmpY = this.cellY;
			this.moving = true;
		} else {
			if(this.cellY == this.tmpY+this.count) {
				this.direction = -1;
			} else if(this.cellY == this.tmpY-1) {
				this.direction = 1;
			}
			if(Physics.collidesBottomWith(this, Game.player.posX(), Game.player.posY())) {
				Game.player.moveUp(this.direction * this.speed);
			} else {
				//Game.player.isLifting = false;
			}
			//this.setCell(map.checkCell(this.x, this.y));
			this.y += this.direction * this.speed;
			this.setCell(map.checkCell(this.x, this.y));
		}
	}
	
}
