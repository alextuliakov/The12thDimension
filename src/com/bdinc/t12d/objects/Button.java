package com.bdinc.t12d.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;

import javax.swing.JOptionPane;

import com.bdinc.t12d.level.LevelManager;
import com.bdinc.t12d.main.Game;
import com.bdinc.t12d.settings.ResourcesManager;
import com.bdinc.t12d.utils.ColorManager;
import com.bdinc.t12d.utils.Debug;

public class Button extends Block implements Serializable {
	
	private Object target;
	private String args;
	
	public boolean onceClickMode, active;
	
	private boolean firstPlatformCoord;
	
	public Button(Image sprite, Object target, String args) {
		super(sprite);
		this.target = target;
		this.args = args;
		this.isInteractive = true;
		this.isTrigger = true;		
	}
	
	@Override
	public void draw(Graphics g)
	{
		if(sprite == null)
		{
			System.err.println("No sprite(null)! Caused by "+id+"<"+this.toString()+">!");
		}
		try
		{
			if(active) {
				sprite = ResourcesManager.buttonActive;
			}
			g.drawImage(sprite, (int)x, (int)y, null);
			if(Game.player.getCell().x == this.cellX-1 || Game.player.getCell().x == this.cellX+1 || Game.player.getCell().x == this.cellX) {
				if(Game.player.getCell().y == this.cellY || Game.player.getCell().y == this.cellY+1) {
					if(!active) {
						Game.player.isInteracting = true;
						g.setColor(Color.YELLOW);
						g.setFont(ResourcesManager.defaultFont14);
						g.drawString(ResourcesManager.interactTooltip, 5, Game.HEIGHT-30);
					}
				}
			} 
		}
		catch(Exception e)
		{
			System.err.println("Can't draw the "+id+"<"+this.toString()+">!");
			e.printStackTrace();
		}
		
	}
	
	public void setActionArguments(String a) {
		this.args = a;
	}
	
	private static boolean isCharExistIn(String s, char c)
	{
		char[] arr = s.toCharArray();
		for(int i = 0; i < arr.length; i++)
		{
			if(arr[i] == c)
			{
				return true;
			}
		}
		return false;
	}
	
	public void press() {
		if(!active) {
			if(target instanceof Platform) {
				for(Block b : LevelManager.currentLevel.blocks) {
					if(b instanceof Platform) {
						if(b.equals(target)) { //b.getCell().x == ((Platform)target).cellX && b.getCell().y == ((Platform)target).cellY
							if(args.startsWith("move")) {
								String[] argp1 = args.split("\\(");
								String[] argp2 = argp1[1].split("\\)");
								String a = argp2[0];
								if(isCharExistIn(a, ';')) {
									String[] property = a.split("\\;");
									for(int i = 0; i < property.length; i++) {
										String[] tmp = property[i].split(":");
										if(property[i].startsWith("direction")) {
											((Platform) b).setDirection(Integer.parseInt(tmp[1]));
										} else if(property[i].startsWith("count")) {
											((Platform) b).setPathCount(Integer.parseInt(tmp[1]));
										} else if(property[i].startsWith("speed")) {
											((Platform) b).setSpeed(Float.parseFloat(tmp[1]));
										} else if(property[i].startsWith("position")) {
											String[] tmp2 = tmp[1].split(",");
											((Platform) b).setLocation(Integer.parseInt(tmp2[0]), Integer.parseInt(tmp2[1]));
										} else {
											JOptionPane.showMessageDialog(null, ResourcesManager.logErrBtnAction, "Error!", 0);
										}
									}
								} else {
									String[] tmp = a.split("\\[");
									String[] ap2 = tmp[1].split("\\]");
									if(tmp[0].equals("direction")) {
										((Platform) b).setDirection(Integer.parseInt(ap2[0]));
									} else if(tmp[0].equals("count")) {
										((Platform) b).setPathCount(Integer.parseInt(ap2[0]));
									} else if(tmp[0].equals("speed")) {
										((Platform) b).setSpeed(Float.parseFloat(ap2[0]));
									} else if(tmp[0].equals("position")) {
										String[] tmp2 = ap2[0].split(",");
										((Platform) b).setLocation(Integer.parseInt(tmp2[0]), Integer.parseInt(tmp2[1]));
									} else {
										JOptionPane.showMessageDialog(null, ResourcesManager.logErrBtnAction, "Error!", 0);
									}
								}
								
							} else {
								JOptionPane.showMessageDialog(null, ResourcesManager.logErrBtnAction, "Error!", 0);
							}
						}
					}
				}
				
			} else if (target instanceof Door) {
				
			}
		}
		
		if(onceClickMode) {
			this.active = true;
		}
		Game.keyManager.interactKeyPressed = false;
	}
	
	public void setActionObject(Object obj) {
		this.target = obj;
	}
	
	public Object getActionObject() {
		return this.target;
	}
	
	@Override
	public String toString()
	{
		int id = 0;
		for(Block b : LevelManager.currentLevel.blocks) {
			if(this.equals(b))
			{
				return "t12d:button#"+id;
			}
			id++;
		}
		return "t12d:button#???(null)";
	}
	
	public float posX() {
		return x;
	}
	
	public float posY() {
		return y;
	}
	
}
