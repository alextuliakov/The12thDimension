package com.bdinc.t12d.scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.bdinc.t12d.level.LevelManager;
import com.bdinc.t12d.main.Game;
import com.bdinc.t12d.objects.Flame;
import com.bdinc.t12d.settings.Options;
import com.bdinc.t12d.settings.ResourcesManager;
import com.bdinc.t12d.ui.UICell;
import com.bdinc.t12d.utils.Debug;

public class ProfilesListDialog {
	
	static int count = 0;
	
	public static ArrayList<UICell> profiles = new ArrayList<UICell>();
	
	private static int cellWidth = 398;
	private static int cellHeight = 100;
	
	private static int listX = 120;

	private static int listY = 120;
	private static int listWidth = 410, listHeight = 410;
	
	public static int btnBackWidth = 30;
	public static int btnBackHeight = 30;
	public static int btnNewWidth = 70;
	public static int btnNewHeight = 40;
	public static int btnDelWidth = 100;
	public static int btnDelHeight = 40;
//	public static int btnSelWidth = 100;
//	public static int btnSelHeight = 40;
	
	public static int btnBackX = 30;
	public static int btnBackY = 30;
	public static int btnNewX = 120;
	public static int btnNewY = 70;
	public static int btnDelX = btnNewX+btnNewWidth+5;
	public static int btnDelY = 70;
//	public static int btnSelX = btnDelX+btnDelWidth+5;
//	public static int btnSelY = 70;
	
	public static Color btnBackColor, btnNewColor;
	
	public static int levelID = 0, pcX = 0, pcY = 0, health = 0, magic = 0, ammo = 0, money = 0, ruby = 0;
	
	public static ArrayList<UICell> activeCells = new ArrayList<UICell>();
	
	private static Polygon exitArrow;
	
	public static String lvlName, lvlAuthor, lvlVer, lvlExtra;
	
	private static boolean repaintRequest;
	
	static int index = 0;
	public static void init() {
		int firstY = 121;
		exitArrow = new Polygon();
		exitArrow.addPoint(35, 45);
		exitArrow.addPoint(55, 35);
		exitArrow.addPoint(55, 55);
		for(String s : ResourcesManager.getProfilesList()) {
			UICell c = new UICell(s, ResourcesManager.profile, Color.GRAY, Color.CYAN, Color.BLUE, false);
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
			
			profiles.add(c);
			firstY = firstY+index;
			index = (1+cellHeight);
			count++;
		}
	}
	
	public static void repaintProfiles(Graphics g) {
		for(UICell c : profiles) {
			c.draw(g);
		}
	}
	
	public static void load(Graphics g) {
		g.setColor(Color.white);
		g.drawRect(listX, listY, listWidth, listHeight);
		
		for(UICell c : profiles) {
			c.draw(g);
		}
		
		for(UICell c : profiles) {
			if(c.isSelected) {
				viewinfo();
			}
		}
		
		g.setColor(Color.white);
		g.drawRect(btnBackX, btnBackY, btnBackWidth, btnBackHeight);
		g.drawRect(btnNewX, btnNewY, btnNewWidth, btnNewHeight);
		g.drawRect(btnDelX, btnDelY, btnDelWidth, btnDelHeight);
//		g.drawRect(btnSelX, btnSelY, btnSelWidth, btnSelHeight);
		g.setFont(ResourcesManager.defaultFont);
		g.drawString(ResourcesManager.m_back, 65, 55);
		g.drawString("Info: "+Options.profileName, 540, 120);
		g.setFont(ResourcesManager.defaultFont16);
		g.drawString(ResourcesManager.m_level+lvlName, 540, 145);
		g.drawString(ResourcesManager.m_levelID+levelID, 540, 170);
		g.drawString(ResourcesManager.m_levelAuthor+lvlAuthor, 540, 195);
		g.drawString(ResourcesManager.m_levelVer+lvlVer, 540, 220);
		g.drawString(ResourcesManager.m_levelExtra+lvlExtra, 540, 245);
		g.drawString("Player X: "+pcX, 540, 270);
		g.drawString("Player Y: "+pcY, 540, 295);
		g.drawString(ResourcesManager.m_playerHealth+health, 540, 320);
		g.drawString(ResourcesManager.m_playerAmmo+ammo, 540, 345);
		g.drawString(ResourcesManager.m_playerMagic+magic, 540, 370);
		g.drawString(ResourcesManager.m_profileMoney+money, 540, 395);
		g.drawString(ResourcesManager.m_profileRuby+ruby, 540, 420);
		
		g.setColor(btnBackColor);
		g.fillRect(btnBackX+1, btnBackY+1, btnBackWidth-1, btnBackHeight-1);
		
		g.setColor(Color.white);
		g.drawPolygon(exitArrow);
		g.fillPolygon(exitArrow);
		
		g.setColor(btnNewColor);
		g.fillRect(btnNewX+1, btnNewY+1, btnNewWidth-1, btnNewHeight-1);
		g.setColor(Color.GRAY);
		g.fillRect(btnDelX+1, btnDelY+1, btnDelWidth-1, btnDelHeight-1);
//		g.fillRect(btnSelX+1, btnSelY+1, btnSelWidth-1, btnSelHeight-1);
		
		g.setColor(Color.BLACK);
		g.setFont(ResourcesManager.defaultFont14);
		g.drawString("New", btnNewX+btnNewWidth/2-15, btnNewY+btnNewY/2-10);
		g.drawString("Delete", btnDelX+btnDelWidth/2-20, btnDelY+btnDelY/2-10);
//		g.drawString("Select", btnSelX+btnSelWidth/2-20, btnSelY+btnSelY/2-10);
	}
	
	public static void requestRepaint() {
		repaintRequest = true;
	}
	
	public static void responseRepaint() {
		repaintRequest = false;
	}
	
	public static void resetInfo() {
		levelID = 0;
		lvlName = "???";
		lvlAuthor = "???";
		lvlVer = "???";
		lvlExtra = "???";
		pcX = 0;
		pcY = 0;
		ammo = 0;
		health = 0;
		magic = 0;
		money = 0;
		ruby = 0;
		Options.profileName = "Unknow Player (???)";
	}
	
	public static void viewinfo() {
		BufferedReader reader1 = null;
		String lvlID, pscx, pscy, psH, psM, psmoney, psruby, psA;
		String line;
		ArrayList<String> lines = new ArrayList<String>();
		try {
			if(Options.profileName.equals("Unknown Player (???)")) {
				reader1 = new BufferedReader(new FileReader("assets/saves/default_info.dat"));
			} else {
				reader1 = new BufferedReader(new FileReader("assets/saves/"+Options.profileName+"_info.dat"));
			}
			while((line = reader1.readLine()) != null) {
				lines.add(line);
			}
			lvlName = lines.get(0).split(":")[1];
			lvlID = lines.get(1);
			lvlAuthor = lines.get(2).split(":")[1];
			lvlVer = lines.get(3).split(":")[1];
			lvlExtra = lines.get(4).split(":")[1];
			pscx = lines.get(7);
			pscy = lines.get(8);
			psH = lines.get(9);
			psA = lines.get(11);
			psM = lines.get(13);
			psmoney = lines.get(15);
			psruby = lines.get(16);
			levelID = Integer.parseInt(lvlID.split(":")[1]);
			pcX = Integer.parseInt(pscx.split(":")[1]);
			pcY = Integer.parseInt(pscy.split(":")[1]);
			health = Integer.parseInt(psH.split(":")[1]);
			magic = Integer.parseInt(psM.split(":")[1]);
			money = Integer.parseInt(psmoney.split(":")[1]);
			ruby = Integer.parseInt(psruby.split(":")[1]);
			ammo = Integer.parseInt(psA.split(":")[1]);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			if(reader1 != null) {
				try {
					reader1.close();
				}
				catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void resetAll() {
		profiles.clear();
	}
	
	public static boolean repaintWaiting() {
		return repaintRequest;
	}
	
}
