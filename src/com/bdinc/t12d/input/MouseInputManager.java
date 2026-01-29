package com.bdinc.t12d.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.bdinc.t12d.level.Level;
import com.bdinc.t12d.level.LevelManager;
import com.bdinc.t12d.main.Game;
import com.bdinc.t12d.objects.Flame;
import com.bdinc.t12d.objects.Item;
import com.bdinc.t12d.objects.MakarovGun;
import com.bdinc.t12d.scenes.DLCListDialog;
import com.bdinc.t12d.scenes.LangListDialog;
import com.bdinc.t12d.scenes.ProfilesListDialog;
import com.bdinc.t12d.settings.Options;
import com.bdinc.t12d.settings.ResourcesManager;
import com.bdinc.t12d.ui.UICell;
import com.bdinc.t12d.ui.UISlot;
import com.bdinc.t12d.utils.Debug;
import com.bdinc.t12d.utils.LangManager;

public class MouseInputManager implements MouseListener {
	
	File sav1 = null;
	File sav2 = null;
	File sav3 = null;
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(Game.paused) {
			if(e.getX() >= Game.m_continueBtnX && e.getX() <= Game.m_continueBtnX+300) {
				if(e.getY() >= Game.m_continueBtnY && e.getY() <= Game.m_continueBtnY+45) {
					Game.paused = false;
				}
			}
			if(e.getX() >= Game.m_optionsBtnX && e.getX() <= Game.m_optionsBtnX+300) {
				if(e.getY() >= Game.m_optionsBtnY && e.getY() <= Game.m_optionsBtnY+45) {
					
				}
			}
			if(e.getX() >= Game.m_exmBtnX && e.getX() <= Game.m_exmBtnX+300) {
				if(e.getY() >= Game.m_exmBtnY && e.getY() <= Game.m_exmBtnY+45) {
					Game.paused = false;
					LevelManager.setLevelByID(0);
				}
			}
		}
		if(LevelManager.levelNumber == 0) {
			if(e.getX() >= Game.m_playBtnX && e.getX() <= Game.m_playBtnX+Game.m_playBtn.getWidth(null)) {
				if(e.getY() >= Game.m_playBtnY && e.getY() <= Game.m_playBtnY+Game.m_playBtn.getHeight(null)) {
					if(ResourcesManager.getProfilesList().size() > 0 && !Options.profileName.equals(Options.DEFAULT_PROFILE)) {
						sav1 = new File("assets/saves/"+Options.profileName+"_info.dat");
						sav2 = new File("assets/saves/"+Options.profileName+"_blocks.dat");
						sav3 = new File("assets/saves/"+Options.profileName+"_entities.dat");
						if((!sav1.exists() || !sav2.exists() || !sav3.exists()) && !Options.profileName.equals(Options.DEFAULT_PROFILE)) {
							JOptionPane.showMessageDialog(null, "Data files are damaged!\nCan't load the game!", "Error!"+sav1.getAbsolutePath(), 0);
						} else {
							try {
								if(loadGame() != -1) {
									Game.player.invList.add(new MakarovGun(ResourcesManager.makarovGun));
									Game.player.invList.add(new MakarovGun(ResourcesManager.key));
									Game.player.invList.add(new MakarovGun(ResourcesManager.makarovGun));
									Game.player.invList.add(new MakarovGun(ResourcesManager.key));
									MakarovGun mkg = new MakarovGun(ResourcesManager.makarovGun);
									mkg.setCount(10);
									Game.player.invList.add(mkg);
									Game.player.invList.add(new MakarovGun(ResourcesManager.key));
									
								} else {
									JOptionPane.showMessageDialog(null, ResourcesManager.logErrLoadingGame, "Error", 0);
								}
							} catch(Exception ex) {
								ex.printStackTrace();
							}
							
						}
					}
					else {
						JOptionPane.showMessageDialog(null, ResourcesManager.logErrNullProfile, "Error", 0);
					}                                                                       
				}
			}
			//ShopButton
			if(e.getX() >= Game.m_shopBtnX && e.getX() <= Game.m_shopBtnX+Game.m_shopBtn.getWidth(null)) {
				if(e.getY() >= Game.m_shopBtnY && e.getY() <= Game.m_shopBtnY+Game.m_shopBtn.getHeight(null)) {
					//soon
				}
			}
			//OptionsButton
			if(e.getX() >= Game.m_optBtnX && e.getX() <= Game.m_optBtnX+Game.m_optBtn.getWidth(null)) {
				if(e.getY() >= Game.m_optBtnY && e.getY() <= Game.m_optBtnY+Game.m_optBtn.getHeight(null)) {
					//soon
				}
			}
			//ExitButton
			if(e.getX() >= Game.m_exitBtnX && e.getX() <= Game.m_exitBtnX+Game.m_exitBtn.getWidth(null)) {
				if(e.getY() >= Game.m_exitBtnY && e.getY() <= Game.m_exitBtnY+Game.m_exitBtn.getHeight(null)) {
					Game.stop();
				}
			}
			//Profile select button
			if(e.getX() >= Game.m_profileBtnX && e.getX() <= Game.m_profileBtnX+Game.m_profileBtnWidth) {
				if(e.getY() >= Game.m_profileBtnY && e.getY() <= Game.m_profileBtnY+Game.m_profileBtnHeight) {
					LevelManager.setLevelByID(-1);
				}
			}
			//ExtraBtn
			if(e.getX() >= Game.m_extraBtnX && e.getX() <= Game.m_extraBtnX+Game.m_extraBtn.getWidth(null)) {
				if(e.getY() >= Game.m_extraBtnY && e.getY() <= Game.m_extraBtnY+Game.m_extraBtn.getHeight(null)) {
					LevelManager.setLevelByID(-2);
				}
			}
			//LangBtn
			if(e.getX() >= Game.m_langBtnX && e.getX() <= Game.m_langBtnX+Game.m_langBtnWidth) {
				if(e.getY() >= Game.m_langBtnY && e.getY() <= Game.m_langBtnY+Game.m_langBtnHeight) {
					LevelManager.setLevelByID(-3);
				}
			}
		}
		else if(LevelManager.levelNumber == -3)
		{
			if(e.getX() >= LangListDialog.btnBackX && e.getX() <= LangListDialog.btnBackX+LangListDialog.btnBackWidth) {
				if(e.getY() >= LangListDialog.btnBackY && e.getY() <= LangListDialog.btnBackY+LangListDialog.btnBackHeight) {
					LevelManager.setLevelByID(0);
				}
			}
			for(UICell c : LangListDialog.lang) {
				if(!c.isSelected) {
					if(e.getX() >= c.getX() && e.getX() <= c.getX()+c.getWidth()) {
						if(e.getY() >= c.getY() && e.getY() <= c.getY()+c.getHeight()) {
							c.isSelected = true;
							LangManager.setLanguage(c.getDescription());
						}
					}
				}
			}
		}
		else if(LevelManager.levelNumber == -2)
		{
			if(e.getX() >= DLCListDialog.btnBackX && e.getX() <= DLCListDialog.btnBackX+DLCListDialog.btnBackWidth) {
				if(e.getY() >= DLCListDialog.btnBackY && e.getY() <= DLCListDialog.btnBackY+DLCListDialog.btnBackHeight) {
					LevelManager.setLevelByID(0);
				}
			}
			if(e.getX() >= DLCListDialog.btnPlayX && e.getX() <= DLCListDialog.btnPlayX+DLCListDialog.btnPlayWidth) {
				if(e.getY() >= DLCListDialog.btnPlayY && e.getY() <= DLCListDialog.btnPlayY+DLCListDialog.btnPlayHeight) {
					if(Options.profileName.equals(Options.DEFAULT_PROFILE)) {
						JOptionPane.showMessageDialog(null, ResourcesManager.logErrNullProfile, "Error!", 0);
					} else {
						
						if(loadExtraGame(DLCListDialog.selected.getTitle()) != -1) {
							Debug.log("Extra level: <"+DLCListDialog.selected.getTitle()+">");
							Debug.log("Has been loaded successfully!");
						} else {
							saveExtraGame(Options.profileName, DLCListDialog.selected.getTitle());
							JOptionPane.showMessageDialog(null, ResourcesManager.logMsgDlcFirstPlayOrError, "Warning!", 1);
						}
						Level lvl = new Level();
						lvl.isExtra = true;
						lvl.create(DLCListDialog.selected.getTitle()+".map");
						//LevelManager.levelNumber = lvl.getID();
						LevelManager.setLevel(lvl);
						LevelManager.levelNumber = lvl.getID();
					}
					
				}
			}
			for(UICell c : DLCListDialog.dlc) {
				if(!c.isSelected) {
					if(e.getX() >= c.getX() && e.getX() <= c.getX()+c.getWidth()) {
						if(e.getY() >= c.getY() && e.getY() <= c.getY()+c.getHeight()) {
							c.isSelected = true;
						}
					}
				}
			}
		}
		else if (LevelManager.levelNumber == -1) 
		{
			if(e.getX() >= ProfilesListDialog.btnBackX && e.getX() <= ProfilesListDialog.btnBackX+ProfilesListDialog.btnBackWidth) {
				if(e.getY() >= ProfilesListDialog.btnBackY && e.getY() <= ProfilesListDialog.btnBackY+ProfilesListDialog.btnBackHeight) {
					LevelManager.setLevelByID(0);
				}
			}
			if(e.getX() >= ProfilesListDialog.btnDelX && e.getX() <= ProfilesListDialog.btnDelX+ProfilesListDialog.btnDelWidth) {
				if(e.getY() >= ProfilesListDialog.btnDelY && e.getY() <= ProfilesListDialog.btnDelY+ProfilesListDialog.btnDelHeight) {
				}
			}
			if(e.getX() >= ProfilesListDialog.btnNewX && e.getX() <= ProfilesListDialog.btnNewX+ProfilesListDialog.btnNewWidth) {
				if(e.getY() >= ProfilesListDialog.btnNewY && e.getY() <= ProfilesListDialog.btnNewY+ProfilesListDialog.btnNewHeight) {
					if(ResourcesManager.getProfilesList().size() < 4) {
						String profile = JOptionPane.showInputDialog("Enter profile's name:");
						BufferedWriter writer = null;
						try {
							writer = new BufferedWriter(new FileWriter("assets/saves/config/profileList.dat", true));
							writer.append(profile+"\n");
						} catch (IOException ex) {
							ex.printStackTrace();
						} finally {
							if(writer != null) {
								try {
									writer.close();
								} catch (IOException ex) {
									ex.printStackTrace();
								}
							}
						}
						File sav1 = new File("assets/saves/"+profile+"_info.dat");
						File sav2 = new File("assets/saves/"+profile+"_blocks.dat");
						File sav3 = new File("assets/saves/"+profile+"_entities.dat");
						try {
							sav1.createNewFile();
							sav2.createNewFile();
							sav3.createNewFile();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						saveGame(profile);
						ProfilesListDialog.resetAll();
						ProfilesListDialog.init();
						if(ProfilesListDialog.profiles.size() > 0) {
							for(UICell c : ProfilesListDialog.profiles) {
								c.setY(c.getY()-101);
							}
						}
						
					}
					
				}
			}
			for(UICell c : ProfilesListDialog.profiles) {
				if(!c.isSelected && !existActive(c)) {
					if(e.getX() >= c.getX() && e.getX() <= c.getX()+c.getWidth()) {
						if(e.getY() >= c.getY() && e.getY() <= c.getY()+c.getHeight()) {
							c.isSelected = true;
							Options.profileName = c.getTitle();
							ProfilesListDialog.viewinfo();
							for(UICell cell : ProfilesListDialog.activeCells) {
								cell.isSelected = false;
							}
							ProfilesListDialog.activeCells.removeAll(ProfilesListDialog.activeCells);
							ProfilesListDialog.activeCells.add(c);
							ProfilesListDialog.requestRepaint();
						}
					}
				}
			}
		}

	}
	
	public boolean existActive(UICell c) {
		boolean res = false;
		for(UICell cell : ProfilesListDialog.activeCells) {
			if(c.equals(cell)) {
				res = true;
			}
		}
		return res;
	}
	
	public void saveExtraGame(String profile, String lvlName) {
		BufferedWriter writer1 = null;
		BufferedWriter writer2 = null;
		try {
			writer1 = new BufferedWriter(new FileWriter("assets/saves/"+profile+"_"+lvlName+"_info.dat"));
			writer1.write("Level:DANGEON"+"\n");
			writer1.write("LevelID:1"+"\n");
			writer1.write("LevelAuthor:???"+"\n");
			writer1.write("LevelVersion:???"+"\n");
			writer1.write("Player.position.x:32"+"\n");
			writer1.write("Player.position.y:32"+"\n");
			writer1.write("Player.position.cellX:3"+"\n");
			writer1.write("Player.position.cellY:1"+"\n");
			writer1.write("Player.health:"+Game.player.getHealth()+"\n");
			writer1.write("Player.maxHealth:"+Game.player.getMaxHealth()+"\n");
			writer1.write("Player.ammo:"+Game.player.getAmmo()+"\n");
			writer1.write("Player.maxAmmo:"+Game.player.getMaxAmmo()+"\n");
			writer1.write("Player.magic:"+Game.player.getMagicCount()+"\n");
			writer1.write("Player.maxMagic:"+Game.player.getMaxMagicCount()+"\n");
			writer1.write("Player.money:"+Game.player.getMoney()+"\n");
			writer1.write("Player.rubies:"+Game.player.getRubyCount()+"\n");
			writer1.write("Flame.cellX:0"+"\n");
			writer1.write("Flame.cellY:0"+"\n");
			File s1 = new File("assets/saves/"+profile+"_"+lvlName+"_blocks.dat");
			File s2 = new File("assets/saves/"+profile+"_"+lvlName+"_entities.dat");
			if(!s1.exists()) {
				s1.createNewFile();
			}
			if(!s2.exists()) {
				s2.createNewFile();
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
		}
	}
	
	public void saveGame(String profile) {
		BufferedWriter writer1 = null;
		BufferedWriter writer2 = null;
		try {
			writer1 = new BufferedWriter(new FileWriter("assets/saves/"+profile+"_info.dat"));
			writer1.write("Level:DANGEON"+"\n");
			writer1.write("LevelID:1"+"\n");
			writer1.write("LevelAuthor:???"+"\n");
			writer1.write("LevelVersion:???"+"\n");
			writer1.write("Level.isExtra:false"+"\n");
			writer1.write("Player.position.x:32"+"\n");
			writer1.write("Player.position.y:32"+"\n");
			writer1.write("Player.position.cellX:3"+"\n");
			writer1.write("Player.position.cellY:1"+"\n");
			writer1.write("Player.health:"+Game.player.getHealth()+"\n");
			writer1.write("Player.maxHealth:"+Game.player.getMaxHealth()+"\n");
			writer1.write("Player.ammo:"+Game.player.getAmmo()+"\n");
			writer1.write("Player.maxAmmo:"+Game.player.getMaxAmmo()+"\n");
			writer1.write("Player.magic:"+Game.player.getMagicCount()+"\n");
			writer1.write("Player.maxMagic:"+Game.player.getMaxMagicCount()+"\n");
			writer1.write("Player.money:"+Game.player.getMoney()+"\n");
			writer1.write("Player.rubies:"+Game.player.getRubyCount()+"\n");
			writer1.write("Flame.cellX:0"+"\n");
			writer1.write("Flame.cellY:0"+"\n");
			writer2 = new BufferedWriter(new FileWriter("assets/saves/"+Options.profileName+"_blocks.dat"));
			writer2.write(""+LevelManager.currentLevel.blocks);
			if(writer2 != null) {
				writer2.close();
			}
			writer2 = new BufferedWriter(new FileWriter("assets/saves/"+Options.profileName+"_entities.dat"));
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
	}
	
	public int loadGame() {
		BufferedReader reader1 = null;
		BufferedReader reader2 = null;
		int levelID, pcX, pcY, health, magic, maxH, maxM, flameX, flameY, money, ruby, ammo, maxA;
		float px, py;
		boolean extra;
		String lvlID, psA, psMA, psx, psy, pscx, pscy, psH, psM, psMH, psMM, fX, fY, psmoney, psruby;
		String pName, line, lvlName, lvlAuthor, lvlVer, lvlExtra;
		ArrayList<String> lines = new ArrayList<String>();
		int count = 0, i = 0;
		try {
			reader1 = new BufferedReader(new FileReader("assets/saves/"+Options.profileName+"_info.dat"));
			while((line = reader1.readLine()) != null) {
				lines.add(line);
			}
			lvlName = lines.get(0).split(":")[1];
			lvlID = lines.get(1);
			lvlAuthor = lines.get(2).split(":")[1];
			lvlVer = lines.get(3).split(":")[1];
			lvlExtra = lines.get(4);
			//System.err.println(""+lvlID);
			psx = lines.get(5);
			psy = lines.get(6);
			pscx = lines.get(7);
			pscy = lines.get(8);
			psH = lines.get(9);
			psMH = lines.get(10);
			psA = lines.get(11);
			psMA = lines.get(12);
			psM = lines.get(13);
			psMM = lines.get(14);
			psmoney = lines.get(15);
			psruby = lines.get(16);
			fX = lines.get(17);
			fY = lines.get(18);
			levelID = Integer.parseInt(lvlID.split(":")[1]);
			px = Float.parseFloat(psx.split(":")[1]);
			py = Float.parseFloat(psy.split(":")[1]);
			pcX = Integer.parseInt(pscx.split(":")[1]);
			pcY = Integer.parseInt(pscy.split(":")[1]);
			health = Integer.parseInt(psH.split(":")[1]);
			maxH = Integer.parseInt(psMH.split(":")[1]);
			magic = Integer.parseInt(psM.split(":")[1]);
			maxM = Integer.parseInt(psMM.split(":")[1]);
			money = Integer.parseInt(psmoney.split(":")[1]);
			ruby = Integer.parseInt(psruby.split(":")[1]);
			flameX = Integer.parseInt(fX.split(":")[1]);
			flameY = Integer.parseInt(fY.split(":")[1]);
			ammo = Integer.parseInt(psA.split(":")[1]);
			maxA = Integer.parseInt(psMA.split(":")[1]);
			extra = Boolean.parseBoolean(lvlExtra.split(":")[1]);
			
			Game.player.setHealth(health);
			Game.player.setMaxHealth(maxH);
			Game.player.setAmmo(ammo);
			Game.player.setMaxAmmo(maxA);
			Game.player.setMagicCount(magic);
			Game.player.setMaxMagic(maxM);
			Game.player.setMoney(money);
			Game.player.setRubyCount(ruby);
			
			if(extra) {
				Level lvl = new Level();
				lvl.isExtra = extra;
				lvl.create(lvlName+".map");
				LevelManager.setLevel(lvl);
			} else {
				Level lvl = new Level();
				lvl.isExtra = extra;
				try {
					lvl.create("level"+levelID+".map");
				} catch(Exception e) {
					e.printStackTrace();
				}
				
				LevelManager.setLevel(lvl);
			}
			
			//LevelManager.setLevelByID(levelID);
			for(Flame f: LevelManager.currentLevel.flames) {
				if(f.getCell().x == flameX && f.getCell().y == flameY) {
					f.setActive(true);
				}
			}
			Game.player.setPosition(pcX, pcY);
//			reader2 = new BufferedReader(new FileReader("assets/saves/"+Game.profileName+"_blocks.dat"));
//			for(Block b : LevelManager.currentLevel.blocks) {
//				b = reader2.readLine();
//			}
//			if(reader2 != null) {
//				reader2.close();
//			}
//			reader2 = new BufferedWriter(new FileWriter("assets/saves/"+Game.profileName+"_entities.dat"));
//			for(Entity e: LevelManager.currentLevel.entities) {
//				reader2.write(""+e);
//				reader2.newLine();
//			}
//			if(reader2 != null) {
//				reader2.close();
//			}
			return 0;
		}
		catch(IOException e) {
			e.printStackTrace();
			return -1;
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
//			if(reader2 != null) {
//				try {
//					reader2.close();
//				}
//				catch(IOException e) {
//					e.printStackTrace();
//				}
//			}
		}
	}
	
	public int loadExtraGame(String levelName) {
		BufferedReader reader1 = null;
		BufferedReader reader2 = null;
		int levelID, pcX, pcY, health, magic, maxH, maxM, flameX, flameY, money, ruby, ammo, maxA;
		float px, py;
		boolean extra;
		String lvlID, psx, psy, pscx, pscy, psH, psM, psMH, psMM, fX, fY, psmoney, psruby, psA, psMA;
		String pName, line, lvlName, lvlAuthor, lvlVer, lvlExtra;
		ArrayList<String> lines = new ArrayList<String>();
		int count = 0, i = 0;
		try {
			reader1 = new BufferedReader(new FileReader("assets/saves/"+Options.profileName+"_"+levelName+"_info.dat"));
			while((line = reader1.readLine()) != null) {
				lines.add(line);
			}
			lvlName = lines.get(0).split(":")[1];
			lvlID = lines.get(1);
			lvlAuthor = lines.get(2).split(":")[1];
			lvlVer = lines.get(3).split(":")[1];
			//System.err.println(""+lvlID);
			psx = lines.get(4);
			psy = lines.get(5);
			pscx = lines.get(6);
			pscy = lines.get(7);
			psH = lines.get(8);
			psMH = lines.get(9);
			psA = lines.get(10);
			psMA = lines.get(11);
			psM = lines.get(12);
			psMM = lines.get(13);
			psmoney = lines.get(14);
			psruby = lines.get(15);
			fX = lines.get(16);
			fY = lines.get(17);
			levelID = Integer.parseInt(lvlID.split(":")[1]);
			px = Float.parseFloat(psx.split(":")[1]);
			py = Float.parseFloat(psy.split(":")[1]);
			pcX = Integer.parseInt(pscx.split(":")[1]);
			pcY = Integer.parseInt(pscy.split(":")[1]);
			health = Integer.parseInt(psH.split(":")[1]);
			maxH = Integer.parseInt(psMH.split(":")[1]);
			magic = Integer.parseInt(psM.split(":")[1]);
			maxM = Integer.parseInt(psMM.split(":")[1]);
			money = Integer.parseInt(psmoney.split(":")[1]);
			ruby = Integer.parseInt(psruby.split(":")[1]);
			flameX = Integer.parseInt(fX.split(":")[1]);
			flameY = Integer.parseInt(fY.split(":")[1]);
			ammo = Integer.parseInt(psA.split(":")[1]);
			maxA = Integer.parseInt(psMA.split(":")[1]);
			
			Game.player.setHealth(health);
			Game.player.setMaxHealth(maxH);
			Game.player.setAmmo(ammo);
			Game.player.setMaxAmmo(maxA);
			Game.player.setMagicCount(magic);
			Game.player.setMaxMagic(maxM);
			Game.player.setMoney(money);
			Game.player.setRubyCount(ruby);
			
			//LevelManager.setLevelByID(levelID);
//			for(Flame f: LevelManager.currentLevel.flames) {
//				if(f.getCell().x == flameX && f.getCell().y == flameY) {
//					f.setActive(true);
//				}
//			}
			Game.player.setPosition(pcX, pcY);
//			reader2 = new BufferedReader(new FileReader("assets/saves/"+Game.profileName+"_blocks.dat"));
//			for(Block b : LevelManager.currentLevel.blocks) {
//				b = reader2.readLine();
//			}
//			if(reader2 != null) {
//				reader2.close();
//			}
//			reader2 = new BufferedWriter(new FileWriter("assets/saves/"+Game.profileName+"_entities.dat"));
//			for(Entity e: LevelManager.currentLevel.entities) {
//				reader2.write(""+e);
//				reader2.newLine();
//			}
//			if(reader2 != null) {
//				reader2.close();
//			}
			return 0;
		}
		catch(IOException e) {
			e.printStackTrace();
			return -1;
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
//			if(reader2 != null) {
//				try {
//					reader2.close();
//				}
//				catch(IOException e) {
//					e.printStackTrace();
//				}
//			}
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (LevelManager.levelNumber > 0 || LevelManager.levelNumber <= -10) {
			if (Game.player.inventoryShow) {
				/*
				 * Armor cells must be described too.
				 * Don't forget!
				 */
				for(UISlot c : Game.player.inventory.cells) {
					if(e.getX() >= c.getX() && e.getX() <= c.getX()+c.getWidth()) {
						if(e.getY() >= c.getY() && e.getY() <= c.getY()+c.getHeight()) {
							c.isDragging = true;
							
							
						}
					}
				}
			}
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (LevelManager.levelNumber > 0 || LevelManager.levelNumber <= -10) {
			if (Game.player.inventoryShow) {
				/*
				 * Armor cells must be described too.
				 * Don't forget!
				 */
				for(UISlot c : Game.player.inventory.cells) {
					if(c.isDragging) {
						//Debug.log("!!!");
//						for(UISlot c2 : Game.player.inventory.cells) {
//							if(e.getX() >= c2.getX() && e.getX() <= c2.getX()+c2.getWidth()) {
//								if(e.getY() >= c2.getY() && e.getY() <= c2.getY()+c2.getHeight()) {
//									Item it = c2.getItem();
//									c.putItem(null);
//									c.isDragging = false;
//									c2.isDragging = true;
//									c2.putItem(c.getItem());
//								}
//							}
//						}
						c.isDragging = false;
					}
				}
			}
		}

	}

}
