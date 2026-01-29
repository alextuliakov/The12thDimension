package com.bdinc.t12d.maths;

import java.util.ArrayList;

import com.bdinc.t12d.level.LevelManager;
import com.bdinc.t12d.main.Game;
import com.bdinc.t12d.objects.Block;
import com.bdinc.t12d.objects.Entity;
import com.bdinc.t12d.objects.Flame;
import com.bdinc.t12d.objects.Particle;
import com.bdinc.t12d.settings.ResourcesManager;
import com.bdinc.t12d.utils.Debug;

public class Physics {
	
	private static Map map = new Map();
	private static float checkedCellX, checkedCellY;
	
	private static ArrayList<Entity> entities;
	private static ArrayList<Block> blocks;
	private static ArrayList<Flame> flames;
	private static ArrayList<Particle> particles;
	
	public static final float gravity = 1f;
	
	public static boolean collidesRight(float px, float py)
	{
		checkedCellX = Game.player.getCell().x;
		checkedCellY = Game.player.getCell().y;
		blocks = LevelManager.currentLevel.blocks;
		for (Block b : blocks)
		{
			if(checkedCellX+1 == b.getCell().x && !b.isTrigger) {
				if(((py+32 > b.posY()+1 && py+32 < b.posY()+32) || (py < b.posY()+32 && py > b.posY()-1)) && !b.isTrigger) { //((py+32 >= b.posY() && py+32 <= b.posY()+32) || (py <= b.posY()+32 && py >= b.posY()))
					if(!collidesBottom(px, py) && checkedCellX+1 == b.getCell().x) {
						return true;
					} else if(collidesBottom(px, py) && checkedCellY == b.getCell().y && !b.isTrigger){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static boolean collidesRightForGravity(Block b, float px, float py)
	{
		if(px+32 >= b.posX()+3 && px+32 <= b.posX()+32 && !b.isTrigger) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean collidesLeft(float px, float py)
	{
		checkedCellX = Game.player.getCell().x;
		checkedCellY = Game.player.getCell().y;
		blocks = LevelManager.currentLevel.blocks;
		for (Block b : blocks)
		{
			if((px <= b.posX()+32 && px >= b.posX())&& !b.isTrigger) { //checkedCellX-1 == b.getCell().x OR (px <= b.posX()+32 && px >= b.posX())
				if(((py+32 > b.posY()+1 && py+32 < b.posY()+32) || (py < b.posY()+32 && py > b.posY()-1))&& !b.isTrigger) { //chekedCellY == b.getCell().y
					return true;
				}
			}
		}
		return false;
	}
	public static boolean collidesLeftForGravity(Block b, float px, float py)
	{
		if(px <= b.posX()+31 && px >= b.posX() && !b.isTrigger) {
			return true;
		}
		else {
			return false;
		}
	}
	public static boolean collidesBottom(float px, float py)
	{
		checkedCellY = Game.player.getCell().y;
		checkedCellX = Game.player.getCell().x;
		blocks = LevelManager.currentLevel.blocks;
		for (Block b : blocks)
		{
			if((py+32 > b.posY() && py+32 < b.posY()+32) && !b.isTrigger) {
				if(collidesRightForGravity(b, px, py) || collidesLeftForGravity(b, px, py)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean collidesBottomWith(Block b, float px, float py)
	{
		checkedCellY = Game.player.getCell().y;
		checkedCellX = Game.player.getCell().x;
		//blocks = LevelManager.currentLevel.blocks;
		if((py+32 > b.posY() && py+32 < b.posY()+32) && !b.isTrigger) {
			if(collidesRightForGravity(b, px, py) || collidesLeftForGravity(b, px, py)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean collidesEntity(float px, float py) {
		checkedCellX = Game.player.getCell().x;
		checkedCellY = Game.player.getCell().y;
		entities = LevelManager.currentLevel.entities;
		for(Entity e : entities) {
			if(e.getCell().x <= checkedCellX+1 && e.getCell().x >= checkedCellX) {
				if(e.getCell().y == checkedCellY) {
					return true;
				}
			}
		}
		return false;
	}
	public static boolean collidesTop(float px, float py)
	{
		checkedCellY = map.checkCell(px, py).y;
		blocks = LevelManager.currentLevel.blocks;
		for (Block b : blocks)
		{
			if((py < b.posY()+32 && py > b.posY()-1) && !b.isTrigger) {
				if(collidesRightForGravity(b, px, py) || collidesLeftForGravity(b, px, py)) {
					return true;
				}
			}
		}
		return false;
	}

}
