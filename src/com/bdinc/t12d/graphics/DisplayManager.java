package com.bdinc.t12d.graphics;

import java.awt.Canvas;
import java.util.ArrayList;

import com.bdinc.t12d.level.Level;
import com.bdinc.t12d.level.LevelManager;
import com.bdinc.t12d.main.Game;
import com.bdinc.t12d.maths.Map;
import com.bdinc.t12d.maths.Physics;
import com.bdinc.t12d.maths.Vector2;
import com.bdinc.t12d.objects.Block;
import com.bdinc.t12d.objects.Chest;
import com.bdinc.t12d.objects.Entity;
import com.bdinc.t12d.objects.Flame;
import com.bdinc.t12d.objects.Item;
import com.bdinc.t12d.objects.Particle;
import com.bdinc.t12d.objects.Platform;
import com.bdinc.t12d.objects.SlotContainer;
import com.bdinc.t12d.settings.ResourcesManager;
import com.bdinc.t12d.ui.UISlot;
import com.bdinc.t12d.utils.Debug;
import com.bdinc.t12d.utils.IntVector2;

public class DisplayManager {
	
	private Map map = new Map();
	
	private ArrayList<Entity> entities;
	private ArrayList<Block> blocks;
	private ArrayList<Flame> flames;
	private ArrayList<Particle> particles;
	
	public void init()
	{
		map.init();
		//new Thread(this).start();
	}
	
	boolean collisionBottom;
	Entity player;
	IntVector2 plCell, flameCell;
	
	public void update(long delta)
	{
		player = Game.player;
		if(LevelManager.levelNumber > 0 || LevelManager.levelNumber <= -10) {
			Vector2 checkedCell = map.checkCell(player.posX(), player.posY());
			
			entities = LevelManager.currentLevel.entities;
			blocks = LevelManager.currentLevel.blocks;
			flames = LevelManager.currentLevel.flames;
			particles = LevelManager.currentLevel.particles;
			collisionBottom = Physics.collidesBottom(player.posX(), player.posY());
			plCell = Game.player.getCell();
			
			for(int i = 0; i < player.invList.size(); i++) {
				if(!(i >= player.inventory.cells.size())) {
					//Debug.log(player.invList.get(i).getSprite().equals(ResourcesManager.makarovGun));
					player.inventory.cells.get(i).putItem(player.invList.get(i));
					//Debug.log(player.invList.get(i).equals(player.inventory.cells.get(i).getItem()));
				}
				
			}
			
//			if(!Game.paused) {
//				//blocks.forEach();
//				for(Block b : blocks) {
//					if(!b.isSolid) {
//						b.incY(Physics.gravity);
//					}
//				}
//			}
			
			if(!Game.paused) {
				for(Block b : blocks) {
					if(b instanceof Platform) {
						((Platform)b).move();
					}
					
				}
			}

			for (Block b : LevelManager.currentLevel.blocks) {
				if (b.isInteractive) {
					if (player.getCell().x == b.getCell().x-1 || player.getCell().x == b.getCell().x+1 || player.getCell().x == b.getCell().x) {
						if (player.getCell().y == b.getCell().y || player.getCell().y == b.getCell().y+1) {
							player.isInteracting = true;
							player.interactiveTarget = b;
							break;
						} else {
							player.isInteracting = false;
							player.interactiveTarget = null;
							continue;
						}
					} else {
						player.isInteracting = false;
						player.interactiveTarget = null;
						continue;
					}
				} else {
					player.isInteracting = false;
					player.interactiveTarget = null;
					continue;
				}
			}
			
			//Debug.log("Interact:"+Game.player.isInteracting+", Target:"+Game.player.interactiveTarget);
			
			player.setCell(checkedCell);
			if(!Game.paused) {
				if(!collisionBottom && !player.jump && !player.isLifting) {
					player.incY(Physics.gravity);
					//player.setCell(checkedCell);
					player.isFalling = true;
				}
				else {
					player.isFalling = false;
				}
				
				if(player.jump) {
					player.jump();
				}
				
				if(player.left) {
					player.moveLeft();
				}
				if(player.right) {
					player.moveRight();
				}
				
				for(Flame f : flames) {
					flameCell = f.getCell();
					if(plCell.x == flameCell.x) {
						if(plCell.y == flameCell.y) {
							f.activate();
						}
					}
				}
			
			}
			
			if(particles.size() > 0 && !Game.paused) {
				for(Particle p : particles) {
					if(p.active) {
						p.move();
					}
				}
			}
			if(!Game.paused) {
				for(Entity e : entities) {
					if(!e.equals(player)) {
						e.enemyMove();
						if(player.getCell().x == e.getCell().x-3) {
							e.attack(player);
						}
					}
				}
			}
			
		}
	}

	
	public void riseUp(float value) {
		Game.player.incY(value);
		Game.player.setCell(map.checkCell(Game.player.posX(), Game.player.posY()));
	}
	
}
