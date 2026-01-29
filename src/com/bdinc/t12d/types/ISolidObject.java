package com.bdinc.t12d.types;

import java.awt.Graphics;
import java.awt.Image;

import com.bdinc.t12d.utils.IntVector2;

public interface ISolidObject {
	public boolean isSolid = true;
	
	public void draw(Graphics g);
	public String toString();
	public IntVector2 getCell();
	public void setLocation(int x, int y);
	public Image getSprite();
	public void incY(float value);
	public void decY(float value);
	public void setCell(int x, int y);
}
