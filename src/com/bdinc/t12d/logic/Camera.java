package com.bdinc.t12d.logic;

import java.awt.Graphics;

import com.bdinc.t12d.level.LevelManager;
import com.bdinc.t12d.main.Game;
import com.bdinc.t12d.objects.Block;

public class Camera {
	
	public float x, y;
	
	public void watch(Graphics g) {
		
	}
	
	public void followPlayer() {
		if(Game.player.posX() >= Game.WIDTH/2) {
			this.x = Game.player.posX();
		}
		
	}
	
}
