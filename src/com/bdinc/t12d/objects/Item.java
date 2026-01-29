package com.bdinc.t12d.objects;

import java.awt.Graphics;
import java.awt.Image;

import com.bdinc.t12d.level.LevelManager;
import com.bdinc.t12d.maths.Map;
import com.bdinc.t12d.maths.Vector2;
import com.bdinc.t12d.utils.IntVector2;

public class Item {
	
	protected float x, y;
	protected int cellX, cellY;
	protected int uiX, uiY;
	protected Image sprite;
	protected Map map = new Map();
	protected int count = 0;
	protected String name, description;;
	
	public Item(Image sprite) {
		this.sprite = sprite;
		
		try
		{
			map.init();
		}
		catch(Exception e)
		{
			System.err.println("Can't initialize the map...");
			System.err.println("Caused by item<"+this.toString()+">!");
			e.printStackTrace();
		}
	}
	
	public void setDescription(String d) {
		this.description = d;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setName(String n) {
		this.name = n;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public void editCount(int value) {
		this.count += value;
	}
	
	public int getCount() {
		return this.count;
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
			System.err.println("Can't set the location to <"+this.toString()+">!");
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
	
	public float posY()
	{
		return x;
	}
	
	public int getUIX()
	{
		return uiX;
	}
	
	public int getUIY()
	{
		return uiY;
	}
	
	@Override
	public String toString()
	{
		int id = 0;
		for(Item i : LevelManager.currentLevel.items) {
			if(this.equals(i))
			{
				return "t12d:item#"+id;
			}
			id++;
		}
		return "t12d:item#???(null)";
	}
	
	public void draw(Graphics g)
	{
		if(sprite == null)
		{
			System.err.println("No sprite(null)! Caused by <"+this.toString()+">!");
		}
		try
		{
			g.drawImage(sprite, (int)x, (int)y, null);
		}
		catch(Exception e)
		{
			System.err.println("Can't draw the <"+this.toString()+">!");
			e.printStackTrace();
		}
		
	}
	
	public void setCell(int x, int y) {
		cellX = x;
		cellY = y;
	}
}
