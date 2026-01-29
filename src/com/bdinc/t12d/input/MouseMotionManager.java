package com.bdinc.t12d.input;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import com.bdinc.t12d.level.LevelManager;
import com.bdinc.t12d.main.Game;
import com.bdinc.t12d.objects.SlotContainer;
import com.bdinc.t12d.scenes.DLCListDialog;
import com.bdinc.t12d.scenes.LangListDialog;
import com.bdinc.t12d.scenes.ProfilesListDialog;
import com.bdinc.t12d.settings.ResourcesManager;
import com.bdinc.t12d.ui.UICell;
import com.bdinc.t12d.ui.UISlot;
import com.bdinc.t12d.utils.ColorManager;
import com.bdinc.t12d.utils.Debug;

public class MouseMotionManager extends MouseAdapter implements MouseMotionListener {

	@Override
	public void mouseDragged(MouseEvent e) {
		

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(Game.paused) {
			if(e.getX() >= Game.m_continueBtnX && e.getX() <= Game.m_continueBtnX+300) {
				if(e.getY() >= Game.m_continueBtnY && e.getY() <= Game.m_continueBtnY+45) {
					Game.m_continueBtnColor = Game.m_pauseBtnHoverColor;
				}
				else {
					Game.m_continueBtnColor = Game.m_pauseBtnTmp;
				}
			}
			else {
				Game.m_continueBtnColor = Game.m_pauseBtnTmp;
			}
			if(e.getX() >= Game.m_optionsBtnX && e.getX() <= Game.m_optionsBtnX+300) {
				if(e.getY() >= Game.m_optionsBtnY && e.getY() <= Game.m_optionsBtnY+45) {
					Game.m_optionsBtnColor = Game.m_pauseBtnHoverColor;
				}
				else {
					Game.m_optionsBtnColor = Game.m_pauseBtnTmp;
				}
			}
			else {
				Game.m_optionsBtnColor = Game.m_pauseBtnTmp;
			}
			if(e.getX() >= Game.m_exmBtnX && e.getX() <= Game.m_exmBtnX+300) {
				if(e.getY() >= Game.m_exmBtnY && e.getY() <= Game.m_exmBtnY+45) {
					Game.m_exmBtnColor = Game.m_pauseBtnHoverColor;
				}
				else {
					Game.m_exmBtnColor = Game.m_pauseBtnTmp;
				}
			}
			else {
				Game.m_exmBtnColor = Game.m_pauseBtnTmp;
			}
		}
		
		if(LevelManager.levelNumber == 0) {
			if(e.getX() >= Game.m_playBtnX && e.getX() <= Game.m_playBtnX+Game.m_playBtn.getWidth(null)) {
				if(e.getY() >= Game.m_playBtnY && e.getY() <= Game.m_playBtnY+Game.m_playBtn.getHeight(null)) {
					Game.m_playBtn = ResourcesManager.playBtnHover;
				}
				else {
					Game.m_playBtn = ResourcesManager.playBtn;
				}
			}
			else {
				Game.m_playBtn = ResourcesManager.playBtn;
			}
			//ShopButton
			if(e.getX() >= Game.m_shopBtnX && e.getX() <= Game.m_shopBtnX+Game.m_shopBtn.getWidth(null)) {
				if(e.getY() >= Game.m_shopBtnY && e.getY() <= Game.m_shopBtnY+Game.m_shopBtn.getHeight(null)) {
					Game.m_shopBtn = ResourcesManager.shopBtnHover;
				}
				else {
					Game.m_shopBtn = ResourcesManager.shopBtn;
				}
			}
			else {
				Game.m_shopBtn = ResourcesManager.shopBtn;
			}
			//OptionsButton
			if(e.getX() >= Game.m_optBtnX && e.getX() <= Game.m_optBtnX+60) {
				if(e.getY() >= Game.m_optBtnY && e.getY() <= Game.m_optBtnY+60) {
					Game.m_optBtn = ResourcesManager.optionsBtnHover;
				}
				else {
					Game.m_optBtn = ResourcesManager.optionsBtn;
				}
			}
			else {
				Game.m_optBtn = ResourcesManager.optionsBtn;
			}
			//ExitButton
			if(e.getX() >= Game.m_exitBtnX && e.getX() <= Game.m_exitBtnX+Game.m_exitBtn.getWidth(null)) {
				if(e.getY() >= Game.m_exitBtnY && e.getY() <= Game.m_exitBtnY+Game.m_exitBtn.getHeight(null)) {
					Game.m_exitBtn = ResourcesManager.exitBtnHover;
				}
				else {
					Game.m_exitBtn = ResourcesManager.exitBtn;
				}
			}
			else {
				Game.m_exitBtn = ResourcesManager.exitBtn;
			}
			//Profile select button
			if(e.getX() >= Game.m_profileBtnX && e.getX() <= Game.m_profileBtnX+Game.m_profileBtnWidth) {
				if(e.getY() >= Game.m_profileBtnY && e.getY() <= Game.m_profileBtnY+Game.m_profileBtnHeight) {
					Game.m_profileBtnColor = Color.ORANGE;
				}
				else {
					Game.m_profileBtnColor = Color.CYAN;
				}
			}
			else {
				Game.m_profileBtnColor = Color.CYAN;
			}
			//ExtraBtn
			if(e.getX() >= Game.m_extraBtnX && e.getX() <= Game.m_extraBtnX+Game.m_extraBtn.getWidth(null)) {
				if(e.getY() >= Game.m_extraBtnY && e.getY() <= Game.m_extraBtnY+Game.m_extraBtn.getHeight(null)) {
					Game.m_extraBtn = ResourcesManager.extraBtnHover;
				}
				else {
					Game.m_extraBtn = ResourcesManager.extraBtn;
				}
			}
			else {
				Game.m_extraBtn = ResourcesManager.extraBtn;
			}
			//StoryBtn
			if(e.getX() >= Game.m_contBtnX && e.getX() <= Game.m_contBtnX+Game.m_storyBtn.getWidth(null)) {
				if(e.getY() >= Game.m_storyBtnY && e.getY() <= Game.m_storyBtnY+Game.m_storyBtn.getHeight(null)) {
					Game.m_storyBtn = ResourcesManager.storyBtnDisabled;
					Game.tooltip = true;
					Game.tooltipX = e.getX()+10;
					Game.tooltipY = e.getY();
				}
				else {
					Game.m_storyBtn = ResourcesManager.storyBtnDisabled;
					Game.tooltip = false;
					Game.tooltipX = 0;
					Game.tooltipY = 0;
				}
			}
			else {
				Game.m_storyBtn = ResourcesManager.storyBtnDisabled;
				Game.tooltip = false;
				Game.tooltipX = 0;
				Game.tooltipY = 0;
			}
			//LangBtn
			if(e.getX() >= Game.m_langBtnX && e.getX() <= Game.m_langBtnX+Game.m_langBtnWidth) {
				if(e.getY() >= Game.m_langBtnY && e.getY() <= Game.m_langBtnY+Game.m_langBtnHeight) {
					Game.m_langBtn = ResourcesManager.langBtnHover;
				}
				else {
					Game.m_langBtn = ResourcesManager.langBtn;
				}
			}
			else {
				Game.m_langBtn = ResourcesManager.langBtn;
			}
		}
		else if(LevelManager.levelNumber == -3)
		{
			if(e.getX() >= LangListDialog.btnBackX && e.getX() <= LangListDialog.btnBackX+LangListDialog.btnBackWidth) {
				if(e.getY() >= LangListDialog.btnBackY && e.getY() <= LangListDialog.btnBackY+LangListDialog.btnBackHeight) {
					LangListDialog.btnBackColor = Color.BLUE;
				} else {
					LangListDialog.btnBackColor = Color.RED;
				}
			} else {
				LangListDialog.btnBackColor = Color.RED;
			}
			
		}
		else if(LevelManager.levelNumber == -2)
		{
			if(e.getX() >= DLCListDialog.btnBackX && e.getX() <= DLCListDialog.btnBackX+DLCListDialog.btnBackWidth) {
				if(e.getY() >= DLCListDialog.btnBackY && e.getY() <= DLCListDialog.btnBackY+DLCListDialog.btnBackHeight) {
					DLCListDialog.btnBackColor = Color.BLUE;
				} else {
					DLCListDialog.btnBackColor = Color.RED;
				}
			} else {
				DLCListDialog.btnBackColor = Color.RED;
			}
			if(e.getX() >= DLCListDialog.btnPlayX && e.getX() <= DLCListDialog.btnPlayX+DLCListDialog.btnPlayWidth) {
				if(e.getY() >= DLCListDialog.btnPlayY && e.getY() <= DLCListDialog.btnPlayY+DLCListDialog.btnPlayHeight) {
					DLCListDialog.btnPlayColor = Color.BLUE;
				} else {
					DLCListDialog.btnPlayColor = ColorManager.VIOLET;
				}
			} else {
				DLCListDialog.btnPlayColor = ColorManager.VIOLET;
			}
		}
		else if(LevelManager.levelNumber == -1)
		{
			if(e.getX() >= ProfilesListDialog.btnBackX && e.getX() <= ProfilesListDialog.btnBackX+ProfilesListDialog.btnBackWidth) {
				if(e.getY() >= ProfilesListDialog.btnBackY && e.getY() <= ProfilesListDialog.btnBackY+ProfilesListDialog.btnBackHeight) {
					ProfilesListDialog.btnBackColor = Color.BLUE;
				}
				else {
					ProfilesListDialog.btnBackColor = Color.RED;
				}
			} else {
				ProfilesListDialog.btnBackColor = Color.RED;
			}
			if(e.getX() >= ProfilesListDialog.btnNewX && e.getX() <= ProfilesListDialog.btnNewX+ProfilesListDialog.btnNewWidth) {
				if(e.getY() >= ProfilesListDialog.btnNewY && e.getY() <= ProfilesListDialog.btnNewY+ProfilesListDialog.btnNewHeight) {
					ProfilesListDialog.btnNewColor = Color.ORANGE;
				} else {
					ProfilesListDialog.btnNewColor = Color.YELLOW;
				}
			} else {
				ProfilesListDialog.btnNewColor = Color.YELLOW;
			}
			for(UICell c : ProfilesListDialog.profiles) {
				if(!c.isSelected) {
					if(e.getX() >= c.getX() && e.getX() <= c.getX()+c.getWidth()) {
						if(e.getY() >= c.getY() && e.getY() <= c.getY()+c.getHeight()) {
							c.setBackground(c.getHoverColor());
						}
						else {
							try {
								c.resetBackground();
							} catch (Exception ex) {
								ex.printStackTrace();
							}
							
						}
					} else {
						try {
							c.resetBackground();
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				} else {
					c.setBackground(c.getActiveColor());
				}
			}
		} else if(LevelManager.levelNumber > 0 || LevelManager.levelNumber <= -10) {
			if (Game.player.inventoryShow) {
				/*
				 * Armor cells must be described too.
				 * Don't forget!
				 */
				for(UISlot c : Game.player.inventory.cells) {
					//Debug.log("X:"+e.getX());
					if(c.isDragging) {
						//Debug.log("X:"+e.getX());
						c.imgX = e.getX();
						c.imgY = e.getY();
					} 
					if(e.getX() >= c.getX() && e.getX() <= c.getX()+c.getWidth()) {
						if(e.getY() >= c.getY() && e.getY() <= c.getY()+c.getHeight()) {
							c.isHover = true;
						}
						else {
							c.isHover = false;
						}
					}
					else {
						c.isHover = false;
					}
				}
			}
			for(SlotContainer c : LevelManager.currentLevel.conts) {
				for(UISlot cell : c.cells) {
					if(e.getX() >= cell.getX() && e.getX() <= cell.getX()+cell.getWidth()) {
						if(e.getY() >= cell.getY() && e.getY() <= cell.getY()+cell.getHeight()) {
							cell.isHover = true;
							cell.toolTipX = e.getX();
							cell.toolTipY = e.getY();
						} else {
							cell.isHover = false;
						}
					} else {
						cell.isHover = false;
					}
				}
			}
		}

	}

}
