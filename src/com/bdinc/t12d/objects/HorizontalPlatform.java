package com.bdinc.t12d.objects;

import java.awt.Image;
import java.io.Serializable;

import com.bdinc.t12d.main.Game;
import com.bdinc.t12d.maths.Physics;
import com.bdinc.t12d.maths.Vector2;
import com.bdinc.t12d.utils.Debug;

public class HorizontalPlatform extends Platform implements Serializable{
	
	private int tmpX;
	
	public HorizontalPlatform(Image sprite) {
		super(sprite);
	}
	public HorizontalPlatform(Image sprite, float speed, int count, int direction) {
		super(sprite);
		this.speed = speed;
		this.count = count;
		this.direction = direction;
		this.x = map.getCell(cellX, cellY).x;
		this.y = map.getCell(cellX, cellY).y;
	}

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
			this.tmpX = this.cellX;
			this.moving = true;
		} else {
			if(this.cellX == this.tmpX+this.count) {
				this.direction = -1;
			} else if(this.cellX == this.tmpX-this.count-1) {
				this.direction = 1;
			}
			if(Physics.collidesBottomWith(this, Game.player.posX(), Game.player.posY())) {
				Game.player.move(this.speed * this.direction);
				Debug.log(Physics.collidesTop(Game.player.posX(), Game.player.posY()));
			} else {
			}
			this.x += this.direction * this.speed;
			this.setCell(map.checkCell(this.x, this.y));
		}
	}
	
}
