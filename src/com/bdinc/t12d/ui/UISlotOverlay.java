package com.bdinc.t12d.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import com.bdinc.t12d.utils.Debug;

public class UISlotOverlay {
	
	private int x, y;
	private int width, height;
	
	private Color background, border;
	public boolean isShow;
	public ArrayList<UISlot> cells = new ArrayList<UISlot>();
	
	public UISlotOverlay() {}
	
	public UISlotOverlay(int x, int y, int width, int height, Color background, Color border) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.background = background;
		this.border = border;
	}
	
	public void show(Graphics g) {
		if(isShow) {
			g.setColor(border);
			g.drawRect(x, y, width, height);
			g.setColor(background);
			g.fillRect(x+1, y+1, width-1, height-1);
			//Debug.log("R:"+cells.get(0).getCountInfoColor().getRed());
			for(UISlot c : cells) {
				c.draw(g);
			}
		}
		
	}
	
}
