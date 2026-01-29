package com.bdinc.t12d.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.bdinc.t12d.level.LevelManager;
import com.bdinc.t12d.main.Game;
import com.bdinc.t12d.settings.ResourcesManager;
import com.bdinc.t12d.utils.Debug;

import javafx.scene.input.KeyCode;

public class Chest extends SlotContainer {

	public Chest(Image sprite) {
		super(sprite);
		this.isInteractive = true;
	}
	
	@Override
	public void draw(Graphics g)
	{
		if(show) {
			sprite = ResourcesManager.chestOpen;
		} else {
			sprite = ResourcesManager.chest;
		}
		if(sprite == null)
		{
			System.err.println("No sprite(null)! Caused by <"+this.toString()+">!");
		}
		try
		{
			g.drawImage(sprite, (int)x, (int)y, null);
			if(Game.player.getCell().x == this.cellX-1 || Game.player.getCell().x == this.cellX+1 || Game.player.getCell().x == this.cellX) {
				if(Game.player.getCell().y == this.cellY || Game.player.getCell().y == this.cellY+1) {
					float posX = Game.player.posX();
					float posY = Game.player.posY();
					g.setColor(Color.YELLOW);
					g.setFont(ResourcesManager.defaultFont14);
					g.drawString(ResourcesManager.interactTooltip, 5, Game.HEIGHT-30);
					Game.player.isInteracting = true;
					if(show) {
						overlay.isShow = true;
						LevelManager.currentLevel.overlay = this.overlay;
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
	
}
