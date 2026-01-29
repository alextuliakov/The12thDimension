package com.bdinc.t12d.objects;

import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;

import com.bdinc.t12d.level.LevelManager;
import com.bdinc.t12d.main.Game;
import com.bdinc.t12d.maths.Map;
import com.bdinc.t12d.maths.Vector2;
import com.bdinc.t12d.types.ISolidObject;
import com.bdinc.t12d.utils.IntVector2;

public class Block implements ISolidObject, Serializable {
	
	protected Image sprite;
	protected float x, y;
	protected int cellX, cellY;
	protected Map map = new Map();
	private Game game = new Game();
	public boolean isTrigger, isInteractive;
	
	public String id = "block";
	
	public Block(Image sprite)
	{
		this.sprite = sprite;
		try
		{
			map.init();
		}
		catch(Exception e)
		{
			System.err.println("Can't initialize the map...");
			System.err.println("Caused by block<"+this.toString()+">!");
			e.printStackTrace();
		}
	}
	
	public Image getSprite()
	{
		return this.sprite;
	}
	
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
	
	public IntVector2 getCell()
	{
		return new IntVector2(cellX, cellY);
	}
	
	public float posX()
	{
		return x;
	}
	
	@Override
	public String toString()
	{
		int id = 0;
		for(Object b : LevelManager.currentLevel.blocks.toArray()) {
			Block block = (Block)b;
			if(this.equals(block))
			{
				return "t12d:block#"+id;
			}
			id++;
		}
		return "t12d:block#???(null)";
	}
	
	public float posY()
	{
		return y;
	}
	
	public void draw(Graphics g)
	{
		if(sprite == null)
		{
			System.err.println("No sprite(null)! Caused by "+id+"<"+this.toString()+">!");
		}
		try
		{
			g.drawImage(sprite, (int)x, (int)y, null);
		}
		catch(Exception e)
		{
			System.err.println("Can't draw the "+id+"<"+this.toString()+">!");
			e.printStackTrace();
		}
		
	}

	@Override
	public void incY(float value) {
		y += value;
		
	}

	@Override
	public void decY(float value) {
		y -= value;
		
	}

	@Override
	public void setCell(int x, int y) {
		cellX = x;
		cellY = y;
		
	}
}
