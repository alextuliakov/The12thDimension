package com.bdinc.t12d.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import com.bdinc.t12d.main.Game;
import com.bdinc.t12d.utils.ColorManager;
import com.bdinc.t12d.utils.Debug;

public class Inventory {
	
	public int x, y, width = 550, height = 500, slotSize = 50;
	
	public int helmetSlotX, helmetSlotY;
	public int bodySlotX, bodySlotY;
	public int legginsSlotX, legginsSlotY;
	public int bootsSlotX, bootsSlotY;
	
	public ArrayList<UISlot> cells = new ArrayList<UISlot>();
	public ArrayList<UISlot> armor = new ArrayList<UISlot>();
	
	public Inventory() {
		x = Game.WIDTH/2-(width/2);
		y = Game.HEIGHT/2-(height/2);
		helmetSlotX = x+5;
		helmetSlotY = y+5;
		bodySlotX = x+5;
		bodySlotY = helmetSlotY+slotSize+30;
		legginsSlotX = helmetSlotX+slotSize+30;
		legginsSlotY = y+5;
		bootsSlotX = legginsSlotX;
		bootsSlotY = bodySlotY;
		UISlot cell = new UISlot(0,0,slotSize, slotSize, ColorManager.getAlphaColor(ColorManager.GOLD, 75), ColorManager.getAlphaColor(ColorManager.GOLD, 255), false);
		cell.setBorderColor(Color.BLACK);
		cell.setLocation(helmetSlotX, helmetSlotY);
		cell.setCountInfoColor(Color.white);
		armor.add(cell);
		UISlot body = new UISlot(cell);
		body.setLocation(bodySlotX, bodySlotY);
		body.showCount = false;
		armor.add(body);
		UISlot leg = new UISlot(cell);
		leg.setLocation(legginsSlotX, legginsSlotY);
		leg.showCount = false;
		armor.add(leg);
		UISlot boots = new UISlot(cell);
		boots.setLocation(bootsSlotX, bootsSlotY);
		boots.showCount = false;
		armor.add(boots);
		UISlot fst = new UISlot(cell);
		fst.setLocation(x+5, y+200);
		cells.add(fst);
		for(int i = 1; i < 30; i++) {
			UISlot c = new UISlot(cells.get(i-1));
			if(x+width - (c.getX()+slotSize) < slotSize) {
				c.setLocation(cells.get(0).getX(),c.getY()+slotSize+5);
			} else {
				c.setX(c.getX()+slotSize+5);
			}
			cells.add(c);
		}
		height = cells.get(cells.size()-1).getY()-slotSize;
	}
	
	public void init() {
		x = Game.WIDTH/2-(width/2);
		y = Game.HEIGHT/2-(height/2);
		helmetSlotX = x+5;
		helmetSlotY = y+5;
		bodySlotX = x+5;
		bodySlotY = helmetSlotY+slotSize+30;
		legginsSlotX = helmetSlotX+slotSize+30;
		legginsSlotY = y+5;
		bootsSlotX = legginsSlotX;
		bootsSlotY = bodySlotY;
		UISlot cell = new UISlot(0,0,slotSize, slotSize, ColorManager.getAlphaColor(ColorManager.GOLD, 75), ColorManager.getAlphaColor(ColorManager.GOLD, 255), false);
		cell.setBorderColor(Color.BLACK);
		cell.setLocation(helmetSlotX, helmetSlotY);
		cell.setCountInfoColor(Color.white);
		cells.add(cell);
		UISlot body = new UISlot(cell);
		body.setLocation(bodySlotX, bodySlotY);
		cells.add(body);
		UISlot leg = new UISlot(cell);
		leg.setLocation(legginsSlotX, legginsSlotY);
		cells.add(leg);
		UISlot boots = new UISlot(cell);
		boots.setLocation(bootsSlotX, bootsSlotY);
		cells.add(boots);
		UISlot fst = new UISlot(cell);
		fst.setLocation(x+5, y+200);
		cells.add(fst);
		for(int i = 5; i < 34; i++) {
			UISlot c = new UISlot(cells.get(i-1));
			if(x+width - (c.getX()+slotSize) < slotSize) {
				c.setLocation(cells.get(4).getX(),c.getY()+slotSize+5);
			} else {
				c.setX(c.getX()+slotSize+5);
			}
			cells.add(c);
		}
		height = cells.get(cells.size()-1).getY()-slotSize;
	}
	
	public void show(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);
		g.setColor(ColorManager.getAlphaColor(ColorManager.GOLD, 75));
		g.fillRect(x+1, y+1, width-1, height-1);
		for(UISlot c : armor) {
			c.draw(g);
		}
		for(UISlot c : cells) {
			c.draw(g);
		}
	}
	
}
