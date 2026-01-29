package com.bdinc.t12d.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.bdinc.t12d.level.LevelManager;
import com.bdinc.t12d.main.Game;
import com.bdinc.t12d.maths.Physics;
import com.bdinc.t12d.objects.Block;
import com.bdinc.t12d.objects.Button;
import com.bdinc.t12d.objects.Entity;
import com.bdinc.t12d.objects.SlotContainer;
import com.bdinc.t12d.objects.Vase;
import com.bdinc.t12d.ui.Inventory;
import com.bdinc.t12d.utils.Debug;

public class InputManager extends KeyAdapter implements KeyListener {
	
	private Entity player;
	private boolean colBot, colTop;
	public static boolean interactKeyPressed, interactContKeyPressed;
	
	@Override
	public void keyPressed(KeyEvent e) {
		player = Game.player;
		if(e.getKeyCode() == KeyEvent.VK_A) {
			player.left = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			player.right = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
			player.isRunning = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_W) {
			colBot = Physics.collidesBottom(player.posX(), player.posY());
			colTop = Physics.collidesTop(player.posX(), player.posY());
			if(colBot && !colTop)
			{
				player.jump = true;
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		player = Game.player;
		if(e.getKeyCode() == KeyEvent.VK_A) {
			player.left = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			player.right = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
			player.isRunning = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_I) {
			player.inventoryShow = !player.inventoryShow;
		}
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE && !(LevelManager.levelNumber <= 0 && LevelManager.levelNumber > -10)) {
			Game.paused = !Game.paused;
		}
		if(e.getKeyCode() == KeyEvent.VK_E && !(LevelManager.levelNumber <= 0 && LevelManager.levelNumber > -10)) {
			//Debug.log(Game.player.interactiveTarget);
			Object tg = Game.player.interactiveTarget;
			
			if(tg instanceof SlotContainer && !(tg instanceof Vase)) {
				((SlotContainer)tg).show = !((SlotContainer)tg).show;
			} 
			else if(tg instanceof Button) {
				if(!((Button)tg).onceClickMode) {
					((Button)tg).press();
				} else {
					((Button)tg).press();
					((Button)tg).active = true;
				}
			} else if(tg instanceof Vase) {
				if(!((Vase)tg).broken) {
					((Vase)tg).broken = true;
				} else {
					((Vase)tg).show = !((Vase)tg).show;
				}
			}
//			if(Game.player.isInteracting && !interactKeyPressed && !interactContKeyPressed) {
//				interactKeyPressed = true;
//			} else if(Game.player.isInteracting && interactKeyPressed) {
//				interactKeyPressed = false;
//			} else {
//				interactKeyPressed = false;
//			}
			interactContKeyPressed = !interactContKeyPressed;
			
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
