package com.bdinc.t12d.scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.bdinc.t12d.level.Level;
import com.bdinc.t12d.settings.ResourcesManager;
import com.bdinc.t12d.ui.UICell;
import com.bdinc.t12d.utils.LangManager;

public class LangListDialog {
	
private static Polygon exitArrow;
	
	private static int cellWidth = 398, cellHeight = 100;
	
	public static ArrayList<UICell> lang = new ArrayList<UICell>();
	
	private static int listX = 120;
	private static int listY = 120;
	private static int listWidth = 410, listHeight = 410;
	
	public static UICell selected = new UICell();
	//public static Level selLevel = new Level();
	
	public static int btnBackX = 30;
	public static int btnBackY = 30;
	public static int btnBackWidth = 30;
	public static int btnBackHeight = 30;
	
	public static Color btnBackColor;
	
	private static String langName, tmpName;
	
	static int count = 0;
	
	static int index = 0;
	public static void init() {
		int firstY = 121;
		exitArrow = new Polygon();
		exitArrow.addPoint(35, 45);
		exitArrow.addPoint(55, 35);
		exitArrow.addPoint(55, 55);
		for(String s : ResourcesManager.getLangList()) {
			//levelDlcs.add(l);
			//Debug.log(l.getName());
			viewinfo(s);
			UICell c = new UICell(tmpName, ResourcesManager.langIcon, Color.GRAY, Color.CYAN, Color.BLUE, false);
			c.setPosition(121, firstY+index);
			c.setSize(cellWidth, cellHeight);
			c.setBorderColor(Color.WHITE);
			//wtf....
			c.setBackground(Color.GRAY);
			
			c.setTitleColor(Color.RED);
			c.setDescriptionColor(Color.BLACK);
			c.setTitleFont(ResourcesManager.defaultFont16);
			c.setDescriptionFont(ResourcesManager.defaultFont14);
			c.setDescription(s);
			
			lang.add(c);
			firstY = firstY+index;
			index = (1+cellHeight);
			count++;
		}
	}
	
	public static void load(Graphics g) {
		g.setColor(Color.white);
		g.drawRect(listX, listY, listWidth, listHeight);
		for(UICell l : lang) {
			if(l.isSelected && !l.equals(selected)) {
				for(UICell l2 : lang) {
					if(l2.equals(selected)) {
						l2.isSelected = false;
						l2.resetBackground();
						selected = null;
					}
				}
				selected = l;
				selected.setBackground(selected.activeColor);
			}
			l.draw(g);
		}
		viewinfo();
		g.setColor(btnBackColor);
		g.fillRect(btnBackX+1, btnBackY+1, btnBackWidth-1, btnBackHeight-1);
		
		g.setColor(Color.white);
		g.drawPolygon(exitArrow);
		g.fillPolygon(exitArrow);
		
		g.setColor(Color.white);
		g.drawRect(btnBackX, btnBackY, btnBackWidth, btnBackHeight);
		
		g.setFont(ResourcesManager.defaultFont);
		g.drawString(ResourcesManager.m_back, 65, 55);
		
		g.setColor(Color.WHITE);
		g.drawString("Info: "+langName, 540, 120);
	}
	
	public static void viewinfo(String file) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("assets/lang/"+file));
			String line = reader.readLine();
			String[] part = line.split("#");
			tmpName = part[1];
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(reader != null) {
				try {
					reader.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void viewinfo() {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("assets/lang/"+selected.getDescription()));
			String line = reader.readLine();
			String[] part = line.split("#");
			langName = part[1];
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(reader != null) {
				try {
					reader.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
