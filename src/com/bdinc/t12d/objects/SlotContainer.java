package com.bdinc.t12d.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;
import java.util.ArrayList;

import com.bdinc.t12d.level.LevelManager;
import com.bdinc.t12d.main.Game;
import com.bdinc.t12d.settings.ResourcesManager;
import com.bdinc.t12d.ui.UISlot;
import com.bdinc.t12d.ui.UISlotOverlay;
import com.bdinc.t12d.utils.Debug;

public class SlotContainer extends Block implements Serializable {
	
	public ArrayList<UISlot> cells = new ArrayList<UISlot>();
	
	public UISlotOverlay overlay;
	
	protected int overlayX, overlayY, overlayWidth, overlayHeight;
	
	protected Color overlayBg, overlayHover, overlayTmpBg, overlayBorderColor;
	
	public boolean show;
	
	public SlotContainer(Image sprite) {
		super(sprite);
		this.isInteractive = true;
		this.isTrigger = true;
	}
	
	public SlotContainer(Image sprite, UISlot example, int slotCount) {
		super(sprite);
		
		this.isInteractive = true;
		this.isTrigger = true;
		
		overlayX = Game.WIDTH/2-(overlayWidth/2);
		overlayY = Game.HEIGHT/2-(overlayHeight/2);
		
		example.setLocation(overlayX+5, overlayY+5);
		
		cells.add(example);
		if(slotCount > 1) {
			for(int i = 1; i < slotCount; i++) {
				UISlot slot = cells.get(i-1);
				if((overlayX+overlayWidth) - (slot.getX()+slot.getWidth()) < slot.getWidth()) {
					slot.setLocation(cells.get(0).getX(), slot.getY()+slot.getHeight()+5);
				} else {
					slot.setLocation(slot.getX()+slot.getWidth()+5, slot.getY());
				}
				cells.add(slot);
			}
		}
	}
	
	public void init(int slotCount, Color bg, Color border, UISlot example, int ovW, int ovH) {
		this.isInteractive = true;
		
		overlayWidth = ovW;
		overlayHeight = ovH;
		overlayX = Game.WIDTH/2-(overlayWidth/2);
		overlayY = Game.HEIGHT/2-(overlayHeight/2);
		
		example.setLocation(overlayX+5, overlayY+5);
		
		cells.add(example);
		
		overlay = new UISlotOverlay(overlayX, overlayY, overlayWidth, overlayHeight, bg, border);
		overlay.cells = this.cells;
		
		if(slotCount > 1) {
			for(int i = 1; i < slotCount; i++) {
				UISlot slot = new UISlot(cells.get(i-1));
				
				if((overlayX+overlayWidth) - (slot.getX()+slot.getWidth()) < slot.getWidth()) {
					//Debug.log(slot.getX()+slot.getWidth()+"/"+(overlayX+overlayWidth));
					slot.setLocation(cells.get(0).getX(),slot.getY()+slot.getHeight()+5);
					
				} else {
					slot.setX(slot.getX()+slot.getWidth()+5);
				}
				cells.add(slot);
			}
		}
		overlayBg = bg;
		overlayBorderColor = border;
		MakarovGun gun = new MakarovGun(ResourcesManager.makarovGun);
		cells.get(0).putItem(gun);
		cells.get(0).editItemCount(45);
		
		//Debug.log("SlotCountInfo: "+cells.get(1).getCountInfoColor().getRed());
	}
	
	public void setOverlaySize(int w, int h) {
		this.overlayWidth = w;
		this.overlayHeight = h;
	}
	
	public void setBackground(Color c) {
		this.overlayBg = c;
	}
	
	public void resetBackground(Color c) {
		this.overlayBg = overlayTmpBg;
	}
	
	public void setHoverColor(Color c) {
		this.overlayHover = c;
	}
	
	public void setBorderColor(Color c) {
		this.overlayBorderColor = c;
	}
	
	public void showOverlay(Graphics g) {
		g.setColor(overlayBorderColor);
		g.drawRect(overlayX, overlayY, overlayWidth, overlayHeight);
		g.setColor(overlayBg);
		g.fillRect(overlayX+1, overlayY+1, overlayWidth-1, overlayHeight-1);
		for(UISlot c : cells) {
			c.draw(g);
		}
	}
	
	@Override
	public void draw(Graphics g)
	{
		if(sprite == null)
		{
			System.err.println("No sprite(null)! Caused by <"+this.toString()+">!");
		}
		try
		{
			g.drawImage(sprite, (int)x, (int)y, null);
			if(Game.player.getCell().x == this.cellX-1 || Game.player.getCell().x == this.cellX+1 || Game.player.getCell().x == this.cellX) {
				if(Game.player.getCell().y == this.cellY || Game.player.getCell().y == this.cellY+1) {
					g.setColor(Color.YELLOW);
					g.setFont(ResourcesManager.defaultFont14);
					g.drawString(ResourcesManager.interactTooltip, 5, Game.HEIGHT-30);
					Game.player.isInteracting = true;
					if(show) {
						overlay.isShow = true;
						LevelManager.currentLevel.overlay = this.overlay;
						//overlay.isShow = true;
					} else {
						overlay.isShow = false;
					}
				} 
				
			}
			
		}
		catch(Exception e)
		{
			System.err.println("Can't draw the <"+this.toString()+">!");
			e.printStackTrace();
		}
		
	}
	
	public void setOverlayLocation(int x, int y) {
		this.overlayX = x;
		this.overlayY = y;
	}
	
	@Override
	public String toString()
	{
		int id = 0;
		for(SlotContainer b : LevelManager.currentLevel.conts) {
			if(this.equals(b))
			{
				return "t12d:container#"+id;
			}
			id++;
		}
		//return ""+LevelManager.currentLevel.conts.size();
		return "t12d:container#???(null)";
	}
	
}
