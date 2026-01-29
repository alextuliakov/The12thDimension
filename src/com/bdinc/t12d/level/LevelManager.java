package com.bdinc.t12d.level;

import com.bdinc.t12d.main.Game;
import com.bdinc.t12d.objects.Block;
import com.bdinc.t12d.objects.Button;
import com.bdinc.t12d.objects.Chest;
import com.bdinc.t12d.objects.Entity;
import com.bdinc.t12d.objects.Flame;
import com.bdinc.t12d.objects.HorizontalPlatform;
import com.bdinc.t12d.objects.Platform;
import com.bdinc.t12d.objects.SlotContainer;
import com.bdinc.t12d.objects.Vase;
import com.bdinc.t12d.objects.VerticalPlatform;
import com.bdinc.t12d.scenes.ProfilesListDialog;
import com.bdinc.t12d.settings.ResourcesManager;

public class LevelManager {
	
	public static final String BRICK_1 = "BRICK_DANGEON";
	public static final String BRICK_2 = "BRICK_ICE";
	public static final String BRICK_3 = "BRICK_SEWER";
	public static final String BRICK_4 = "BRICK_GOLD";
	public static final String BRICK_5 = "BRICK_LIGHT";
	public static final String BRICK_6 = "BRICK_FIRE";
	public static final String BRICK_7 = "BRICK_DARK";
	public static final String VPLATFORM_DANGEON = "PLT_VERTICAL_DANGEON";
	public static final String HPLATFORM_DANGEON = "PLT_HORIZONTAL_DANGEON";
	public static final String FLOOR_1 = "Floor1";
	public static final String FLOOR_7 = "Floor7";
	public static final String WALL_1 = "WALL_DANGEON";
	public static final String CHEST = "CHEST";
	public static final String VASE = "VASE";
	public static final String KEY = "KEY";
	public static final String BUTTON = "BUTTON";
	public static final String BULLET_PACK_DEF = "BulletPack_Default";
	public static final String FINISH = "FINISH";
	public static final String COIN10 = "Coin10";
	public static final String THIEF = "ENT_THIEF";
	public static final String FIRE_MAN = "ENT_MONST_FIRE";
	public static final String LIGHT_OFFICER = "ENT_OFFICER_LIGHT";
	public static final String FLAME_OFF = "FLAME";
	public static final String FLAME = "FLAME_ACTIVE";
	public static final String BUTTON_DEFAULT = "BUTTON";
	public static final String CONT_CHEST = "CONT_CHEST";
	public static final String CONT_VASE = "CONT_VASE";
	
	public static Level currentLevel;
	public static int levelNumber;
	
	private static ResourcesManager resources = new ResourcesManager();
	private static Level lvl;
	
	public static void setLevelByID(int ID) {
		switch(ID) {
			case -3:
				levelNumber = -3;
				break;
			case -2:
				levelNumber = -2;
				break;
			case -1:
				levelNumber = -1;
				break;
			case 0:
				levelNumber = 0;
				break;
			case 1:
				lvl = new Level();
				lvl.isExtra = false;
				try {
					lvl.create("level1.map");
				} catch(Exception e) {
					e.printStackTrace();
				}
				
				levelNumber = 1;
				setLevel(lvl);
				break;
			case 2:
				lvl = new Level();
				lvl.isExtra = false;
				lvl.create("level2.map");
				levelNumber = 2;
				setLevel(lvl);
				break;
			case 3:
				lvl = new Level();
				lvl.isExtra = false;
				lvl.create("level3.map");
				levelNumber = 3;
				setLevel(lvl);
				break;
		}
	}
	
	public static void setLevelByName(String name) {
		lvl = new Level();
		lvl.create("assets/levels/extra/"+name+".map");
		levelNumber = lvl.getID();
		setLevel(lvl);
	}
	
	public static void setLevel(Level lvl)
	{
		currentLevel = lvl;
		levelNumber = lvl.getID();
	}
	
	public static Level getCurrentLevel()
	{
		return currentLevel;
	}
	
	public static Platform getPlatformByName(String name) {
		Platform p = null;
		switch(name) {
			case VPLATFORM_DANGEON:
				p = new VerticalPlatform(ResourcesManager.vplatformDangeon);
				return p;
			case HPLATFORM_DANGEON:
				p = new HorizontalPlatform(ResourcesManager.hplatformDangeon);
				return p;
			default:
				return null;
		}
	}
	
	public static Entity getEntityByName(String name)
	{
		switch(name) {
			case FIRE_MAN:
				Entity ent = new Entity(resources.monstFire);
				ent.setHealth(100);
				ent.setMagicCount("unlimited");
				return ent;
			default:
				return null;
		}
	}
	
	public static SlotContainer getContainer(String name) {
		SlotContainer c = null;
		switch(name) {
			case CONT_CHEST:
				c = new Chest(ResourcesManager.chest);
				return c;
			case CONT_VASE:
				c = new Vase(ResourcesManager.vase);
				return c;
			default:
				return null;
		}
	}
	
	public static Block getObjectByName(String name)
	{
		Block b = null;
		switch(name) {
			case BRICK_1:
				b = new Block(ResourcesManager.brick1);
				return b;
			case BRICK_2:
				b = new Block(ResourcesManager.brick2);
				return b;
			case BRICK_3:
				b = new Block(ResourcesManager.brick3);
				return b;
			case BRICK_4:
				b = new Block(ResourcesManager.brick4);
				return b;
			case BRICK_5:
				b = new Block(ResourcesManager.brick5);
				return b;
			case BRICK_6:
				b = new Block(ResourcesManager.brick6);
				return b;
			case BRICK_7:
				b = new Block(ResourcesManager.brick7);
				return b;
			case WALL_1:
				b = new Block(ResourcesManager.wall1);
				b.isTrigger = true;
				return b;
			case CHEST:
				b = new Chest(ResourcesManager.chest);
				b.isInteractive = true;
				return b;
//			case BUTTON:
//				b = new Button(ResourcesManager.button, null, "null");
//				b.isInteractive = true;
//				return b;
			default:
				return null;
		}
	}
	
	public static Button getButton(String name) {
		Button b = null;
		switch(name) {
			case BUTTON_DEFAULT:
				b = new Button(ResourcesManager.button, null, "null");
				return b;
			default:
				return null;
		}
	}
	
	public static Flame getFlame(String name) {
		Flame f = null;
		switch(name) {
			case FLAME_OFF:
				f = new Flame(ResourcesManager.flameOff);
				return f;
			case FLAME:
				f = new Flame(ResourcesManager.flame);
				f.setActive(true);
				return f;
			default:
				return null;
		}
	}
	
}
