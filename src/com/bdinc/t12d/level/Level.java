package com.bdinc.t12d.level;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

import com.bdinc.t12d.main.Game;
import com.bdinc.t12d.maths.Map;
import com.bdinc.t12d.objects.Block;
import com.bdinc.t12d.objects.Button;
import com.bdinc.t12d.objects.Entity;
import com.bdinc.t12d.objects.Flame;
import com.bdinc.t12d.objects.Item;
import com.bdinc.t12d.objects.Particle;
import com.bdinc.t12d.objects.SlotContainer;
import com.bdinc.t12d.settings.Options;
import com.bdinc.t12d.settings.ResourcesManager;
import com.bdinc.t12d.ui.UISlotOverlay;
import com.bdinc.t12d.utils.Container;
import com.bdinc.t12d.utils.Debug;
import com.bdinc.t12d.utils.ColorManager;

public class Level {
	
	public ArrayList<Entity> entities = new ArrayList<Entity>();
	public ArrayList<Block> blocks = new ArrayList<Block>();
	public ArrayList<Particle> particles = new ArrayList<Particle>();
	public ArrayList<Flame> flames = new ArrayList<Flame>();
	public ArrayList<Item> items = new ArrayList<Item>();
	public ArrayList<Particle> playerMagic = new ArrayList<Particle>();
	public ArrayList<Particle> enemyMagic = new ArrayList<Particle>();
	public ArrayList<SlotContainer> conts = new ArrayList<SlotContainer>();
	
	private LevelReader reader = new LevelReader();
	
	public UISlotOverlay overlay = new UISlotOverlay();
	
	private String name = "null", author = "null", version="1.0";
	private int ID = -10;
	
	private Map map = new Map();
	
	public boolean isExtra = false;
	
	public Level() {}
	
	public Level(String file, boolean extra) {
		create(file);
		isExtra = extra;
	}
	
	public void init()
	{
		map.init();
		try
		{
			entities.add(Game.player);
		}
		catch(Exception e) {
			System.err.println("Can't add player to the entities list!");
		}
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setID(int ID) {
		this.ID = ID;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}
	
	public int getID() {
		return this.ID;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getAuthor() {
		return this.author;
	}
	
	public String getVersion() {
		return this.version;
	}
	
	@SuppressWarnings("unchecked")
	public void create(String file)
	{
		Level source = null;
		try
		{
			if(isExtra) {
				source = reader.readLevel("assets/levels/extra/"+file);
				
			} else {
				source = reader.readLevel("assets/levels/"+file);
				//Debug.log("Level: assets/levels/"+file);
			}
		}
		catch(Exception e)
		{
			System.err.println("Can't read the level file: <"+file+">!");
			e.printStackTrace();
		}
		entities = source.entities;
		blocks = source.blocks;
		flames = source.flames;
		conts = source.conts;
		name = source.getName();
		ID = source.getID();
		author = source.getAuthor();
		version = source.getVersion();
		for(SlotContainer c : conts) {
			blocks.add(c);
		}
		this.init();
	}
	
	public void load(Graphics g)
	{
		try
		{
			if(LevelManager.currentLevel.blocks.size() > 0) {
				for(Block b : LevelManager.currentLevel.blocks) {
					if(b.getSprite() == null)
					{
						System.err.println("No sprite in block<"+b.toString()+">!");
					}
					else if (!(b instanceof SlotContainer)){
						b.draw(g);
					}
					
				}
			}
			if(LevelManager.currentLevel.flames.size() > 0) {
				for(Flame f : LevelManager.currentLevel.flames) {
					if(f.getSprite() == null)
					{
						System.err.println("No sprite in flame<"+f.toString()+">!");
					}
					f.draw(g);
				}
			}
			
//			if(entities.size() == 0) {
//				System.err.println("No entities on the current level!");
//			}
//			if(blocks.size() == 0) {
//				System.err.println("No blocks on the current level!");
//			}
			
			
//			if(LevelManager.currentLevel.particles.size() > 0) {
//				for(Particle p : LevelManager.currentLevel.particles) {
//					if(p.getSprite() == null) {
//						System.err.println("No sprite in particle<"+p.toString()+">!");
//					}
//					if(!p.active) {
//						LevelManager.currentLevel.particles.remove(p);
//					} else {
//						p.draw(g);
//					}
//					
//				}
//			}
			if(LevelManager.currentLevel.conts.size() > 0) {
				//Debug.log("ff");
				for(SlotContainer b : LevelManager.currentLevel.conts) {
					if(b.getSprite() == null)
					{
						System.err.println("No sprite in container<"+b.toString()+">!");
					}
					b.draw(g);
					
				}
			}
			if(LevelManager.currentLevel.entities.size() > 0) {
				for(Entity e : LevelManager.currentLevel.entities) {
					if(e.getSprite() == null)
					{
						System.err.println("No sprite in entity<"+e.toString()+">!");
					}
					e.draw(g);
				}
			}
			int lifeStrX = 10+ResourcesManager.life.getWidth(null)+5;
			int moneyX = lifeStrX+70;
			int moneyStrX = moneyX+21;
			int rubyX = moneyStrX+70;
			int rubyStrX = rubyX + 21;
			int ammoX = rubyStrX+70;
			int ammoStrX = ammoX + 31;
			int rectX = Game.WIDTH-240;
			g.setColor(ColorManager.getAlphaColor(ColorManager.VIOLET, 60));
			g.fillRect(0, 0, Game.WIDTH, 30);
			g.setColor(Color.white);
			g.drawRect(rectX, 2, 235, 24);
			g.drawImage(ResourcesManager.life, 10, 10, null);
			g.drawImage(ResourcesManager.coin10_X16, moneyX, 10, null);
			g.drawImage(ResourcesManager.ruby, rubyX, 10, null);
			g.drawImage(ResourcesManager.ammo, ammoX, 0, null);
			g.setColor(Color.WHITE);
			g.setFont(ResourcesManager.defaultFont);
			g.drawString(""+Game.player.getHealth(), lifeStrX, 25);
			g.drawString(""+Game.player.getMoney(), moneyStrX, 25);
			g.drawString(""+Game.player.getRubyCount(), rubyStrX, 25);
			g.drawString(""+Game.player.getAmmo()+"/"+Game.player.getMaxAmmo(), ammoStrX, 25);
			String profile = Options.profileName;
			
			if(profile.length() >= 21) {
				profile = profile.substring(0, 21);
			}
			g.drawString(profile, Game.WIDTH-235, 23);
			if(isExtra) {
				g.drawString(ResourcesManager.m_level+LevelManager.currentLevel.getName(), rectX-280, 23);
			} else {
				g.drawString(ResourcesManager.m_level+LevelManager.levelNumber+" ["+LevelManager.currentLevel.getName()+"]", rectX-280, 23);
			}
			g.drawLine(0, 30, Game.WIDTH, 30);
			if(overlay.isShow) {
				overlay.show(g);
			}
		}
		catch(Exception e)
		{
			System.err.println("Can't load the level (Level.load(Graphics g))");
			System.err.println("Source: Level.load(Graphics g)");
			e.printStackTrace();
		}
		
	}
	
	public Map getMap()
	{
		return map;
	}
	
}
