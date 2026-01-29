package com.bdinc.t12d.scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.bdinc.t12d.level.Level;
import com.bdinc.t12d.level.LevelManager;
import com.bdinc.t12d.settings.Options;
import com.bdinc.t12d.settings.ResourcesManager;
import com.bdinc.t12d.ui.UICell;
import com.bdinc.t12d.utils.Debug;

public class DLCListDialog {
	
	private static Polygon exitArrow;
	
	private static int cellWidth = 398, cellHeight = 100;
	
	public static ArrayList<UICell> dlc = new ArrayList<UICell>();
	public static ArrayList<UICell> activeCells = new ArrayList<UICell>();
	public static ArrayList<Level> levelDlcs = new ArrayList<Level>();
	
	private static int listX = 120;
	private static int listY = 120;
	private static int listWidth = 410, listHeight = 410;
	
	public static UICell selected = new UICell(), nullObj = null;
	public static Level selLevel = new Level();
	
	public static int btnBackX = 30;
	public static int btnBackY = 30;
	public static int btnPlayX = 540; //620
	public static int btnPlayY = 250; //550
	public static int btnBackWidth = 30;
	public static int btnBackHeight = 30;
	public static int btnPlayWidth = 130;
	public static int btnPlayHeight = 60;
	
	public static Color btnBackColor, btnPlayColor;
	
	private static String lvlName, lvlID, lvlAuthor, lvlVer;
	private static int levelID;
	
	static int count = 0;
	
	static int index = 0;
	public static void init() {
		int firstY = 121;
		exitArrow = new Polygon();
		exitArrow.addPoint(35, 45);
		exitArrow.addPoint(55, 35);
		exitArrow.addPoint(55, 55);
		for(Level l : ResourcesManager.getDLCList()) {
			levelDlcs.add(l);
			//Debug.log(l.getName());
			UICell c = new UICell(l.getName(), ResourcesManager.dlcIcon, Color.GRAY, Color.CYAN, Color.BLUE, false);
			c.setPosition(121, firstY+index);
			c.setSize(cellWidth, cellHeight);
			c.setBorderColor(Color.WHITE);
			//wtf....
			c.setBackground(Color.GRAY);
			
			c.setTitleColor(Color.RED);
			c.setDescriptionColor(Color.BLACK);
			c.setTitleFont(ResourcesManager.defaultFont16);
			c.setDescriptionFont(ResourcesManager.defaultFont14);
			c.setDescription("[Click to select]");
			
			dlc.add(c);
			firstY = firstY+index;
			index = (1+cellHeight);
			count++;
		}
	}
	
	public static void load(Graphics g) {
		g.setColor(Color.white);
		g.drawRect(listX, listY, listWidth, listHeight);
		for(UICell d : dlc) {
			if(d.isSelected && !d.equals(selected)) {
				for(UICell d2 : dlc) {
					if(d2.equals(selected)) {
						d2.isSelected = false;
						d2.resetBackground();
						selected = null;
					}
				}
				selected = d;
				selected.setBackground(selected.activeColor);
			}
			d.draw(g);
		}
		viewinfo();
		g.setColor(btnBackColor);
		g.fillRect(btnBackX+1, btnBackY+1, btnBackWidth-1, btnBackHeight-1);
		g.setColor(btnPlayColor);
		g.fillRect(btnPlayX+1, btnPlayY+1, btnPlayWidth-1, btnPlayHeight-1);
		
		g.setColor(Color.white);
		g.drawPolygon(exitArrow);
		g.fillPolygon(exitArrow);
		
		g.setColor(Color.white);
		g.drawRect(btnBackX, btnBackY, btnBackWidth, btnBackHeight);
		g.drawRect(btnPlayX, btnPlayY, btnPlayWidth, btnPlayHeight);
		
		g.setFont(ResourcesManager.defaultFont);
		g.drawString(ResourcesManager.m_back, 65, 55);
		g.setColor(Color.yellow);
		g.setFont(ResourcesManager.defaultFont24);
		g.drawString("PLAY", btnPlayX+btnPlayWidth/2-30, btnPlayY+btnPlayHeight/2+10);
		
		g.setColor(Color.WHITE);
		g.drawString("Info: "+selected.getTitle(), 540, 120);
		g.setFont(ResourcesManager.defaultFont16);
		g.drawString(ResourcesManager.m_level+lvlName, 540, 145);
		g.drawString(ResourcesManager.m_levelID+levelID, 540, 170);
		g.drawString(ResourcesManager.m_levelAuthor+lvlAuthor, 540, 195);
		g.drawString(ResourcesManager.m_levelVer+lvlVer, 540, 220);
	}
	
	public static void viewinfo() {
		for(Level lv : levelDlcs) {
			if(lv.getName().equals(selected.getTitle())) {
				selLevel = lv;
			}
		}
		lvlName = selLevel.getName();
		levelID = selLevel.getID();
		lvlAuthor = selLevel.getAuthor();
		lvlVer = selLevel.getVersion();
	}
	
}
