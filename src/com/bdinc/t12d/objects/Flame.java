package com.bdinc.t12d.objects;

import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

import com.bdinc.t12d.level.LevelManager;
import com.bdinc.t12d.main.Game;
import com.bdinc.t12d.maths.Map;
import com.bdinc.t12d.maths.Vector2;
import com.bdinc.t12d.settings.Options;
import com.bdinc.t12d.settings.ResourcesManager;
import com.bdinc.t12d.utils.IntVector2;

public class Flame extends Block implements Serializable{
	
	public boolean activated;
	
	public Flame(Image sprite) {
		super(sprite);
		this.id = "flame";
		this.isTrigger = true;
		if(activated) {
			sprite = ResourcesManager.flame;
		}else {
			sprite = ResourcesManager.flameOff;
		}
		try {
			map.init();
		} catch(Exception e) {
			System.err.println("Can't initialize the map...");
			System.err.println("Caused by flame<"+this.toString()+">!");
			e.printStackTrace();
		}
	}
	
	public void setActive(boolean state) {
		if(state) {
			sprite = ResourcesManager.flame;
			activated = true;
		}
		else {
			sprite = ResourcesManager.flameOff;
			activated = false;
		}
	}
	
	public void activate() {
		if(LevelManager.currentLevel.isExtra) {
			if(!activated) {
				sprite = ResourcesManager.flame;
				BufferedWriter writer1 = null;
				BufferedWriter writer2 = null;
				try {
					writer1 = new BufferedWriter(new FileWriter("assets/saves/"+Options.profileName+"_"+LevelManager.currentLevel.getName()+"_info.dat"));
					writer1.write("Level:"+LevelManager.currentLevel.getName()+"\n");
					writer1.write("LevelID:"+LevelManager.currentLevel.getID()+"\n");
					writer1.write("LevelAuthor:"+LevelManager.currentLevel.getAuthor()+"\n");
					writer1.write("LevelVersion:"+LevelManager.currentLevel.getVersion()+"\n");
					writer1.write("Player.position.x:"+Game.player.posX()+"\n");
					writer1.write("Player.position.y:"+Game.player.posY()+"\n");
					writer1.write("Player.position.cellX:"+Game.player.getCell().x+"\n");
					writer1.write("Player.position.cellY:"+Game.player.getCell().y+"\n");
					writer1.write("Player.health:"+Game.player.getHealth()+"\n");
					writer1.write("Player.maxHealth:"+Game.player.getMaxHealth()+"\n");
					writer1.write("Player.ammo:"+Game.player.getAmmo()+"\n");
					writer1.write("Player.maxAmmo:"+Game.player.getMaxAmmo()+"\n");
					writer1.write("Player.magic:"+Game.player.getMagicCount()+"\n");
					writer1.write("Player.maxMagic:"+Game.player.getMaxMagicCount()+"\n");
					writer1.write("Player.money:"+Game.player.getMoney()+"\n");
					writer1.write("Player.rubies:"+Game.player.getRubyCount()+"\n");
					writer1.write("Flame.cellX:"+this.cellX+"\n");
					writer1.write("Flame.cellY:"+this.cellY+"\n");
					writer2 = new BufferedWriter(new FileWriter("assets/saves/"+Options.profileName+"_"+LevelManager.currentLevel.getName()+"_blocks.dat"));
					writer2.write(""+LevelManager.currentLevel.blocks);
					if(writer2 != null) {
						writer2.close();
					}
					writer2 = new BufferedWriter(new FileWriter("assets/saves/"+Options.profileName+"_"+LevelManager.currentLevel.getName()+"_entities.dat"));
					writer2.write(""+LevelManager.currentLevel.entities);
					if(writer2 != null) {
						writer2.close();
					}
				}
				catch(IOException e) {
					e.printStackTrace();
				}
				finally {
					if(writer1 != null) {
						try {
							writer1.close();
						}
						catch(IOException e) {
							e.printStackTrace();
						}
					}
					if(writer2 != null) {
						try {
							writer2.close();
						}
						catch(IOException e) {
							e.printStackTrace();
						}
					}
				}
				activated = true;
			}
		} else {
			if(!activated) {
				sprite = ResourcesManager.flame;
				BufferedWriter writer1 = null;
				BufferedWriter writer2 = null;
				FileOutputStream out1 = null;
				try {
					out1 = new FileOutputStream("assets/saves/"+Options.profileName+"_blocks.dat");
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				FileOutputStream out2 = null;
				try {
					out2 = new FileOutputStream("assets/saves/"+Options.profileName+"_entities.dat");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ObjectOutputStream obj = null;
				try {
					writer1 = new BufferedWriter(new FileWriter("assets/saves/"+Options.profileName+"_info.dat"));
					writer1.write("Level:"+LevelManager.currentLevel.getName()+"\n");
					writer1.write("LevelID:"+LevelManager.currentLevel.getID()+"\n");
					writer1.write("LevelAuthor:"+LevelManager.currentLevel.getAuthor()+"\n");
					writer1.write("LevelVersion:"+LevelManager.currentLevel.getVersion()+"\n");
					writer1.write("Level.isExtra:"+LevelManager.currentLevel.isExtra+"\n");
					writer1.write("Player.position.x:"+Game.player.posX()+"\n");
					writer1.write("Player.position.y:"+Game.player.posY()+"\n");
					writer1.write("Player.position.cellX:"+Game.player.getCell().x+"\n");
					writer1.write("Player.position.cellY:"+Game.player.getCell().y+"\n");
					writer1.write("Player.health:"+Game.player.getHealth()+"\n");
					writer1.write("Player.maxHealth:"+Game.player.getMaxHealth()+"\n");
					writer1.write("Player.ammo:"+Game.player.getAmmo()+"\n");
					writer1.write("Player.maxAmmo:"+Game.player.getMaxAmmo()+"\n");
					writer1.write("Player.magic:"+Game.player.getMagicCount()+"\n");
					writer1.write("Player.maxMagic:"+Game.player.getMaxMagicCount()+"\n");
					writer1.write("Player.money:"+Game.player.getMoney()+"\n");
					writer1.write("Player.rubies:"+Game.player.getRubyCount()+"\n");
					writer1.write("Flame.cellX:"+this.cellX+"\n");
					writer1.write("Flame.cellY:"+this.cellY+"\n");
					writer2 = new BufferedWriter(new FileWriter("assets/saves/"+Options.profileName+"_blocks.dat"));
					writer2.write(""+LevelManager.currentLevel.blocks);
					if(writer2 != null) {
						writer2.close();
					}
//					writer2 = new BufferedWriter(new ObjectOutputStream("assets/saves/"+Options.profileName+"_entities.dat"));
//					writer2.write(""+LevelManager.currentLevel.entities);
//					if(writer2 != null) {
//						writer2.close();
//					}
					//FileOutputStream out = new FileOutputStream("assets/saves/"+Options.profileName+"_blocks.dat");
					obj = new ObjectOutputStream(out1);
					for(Block b : LevelManager.currentLevel.blocks) {
						obj.writeObject(b);
					}
					obj = new ObjectOutputStream(out2);
					for(Entity ent : LevelManager.currentLevel.entities) {
						obj.writeObject(ent);
					}
				}
				catch(IOException e) {
					e.printStackTrace();
				}
				finally {
					if(writer1 != null) {
						try {
							writer1.close();
						}
						catch(IOException e) {
							e.printStackTrace();
						}
					}
					if(obj != null) {
						try {
							obj.close();
						}
						catch(IOException e) {
							e.printStackTrace();
						}
					}
				}
				activated = true;
			}
		}
		
	}
	
	@Override
	public String toString()
	{
		int id = 0;
		for(Flame f : LevelManager.currentLevel.flames) {
			if(this.equals(f))
			{
				return "t12d:flame#"+id;
			}
			id++;
		}
		return "t12d:flame#???(null)";
	}
	
	public float posX() {
		return x;
	}
	
	public float posY() {
		return y;
	}
	
}
