package com.bdinc.t12d.settings;

import java.awt.Font;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

import com.bdinc.t12d.graphics.FontManager;
import com.bdinc.t12d.level.Level;
import com.bdinc.t12d.main.Game;
import com.bdinc.t12d.utils.Debug;
import com.bdinc.t12d.utils.LangManager;

public class ResourcesManager {
	
	public static String assetsDir = "assets";
	public static String spritesDir = "assets/sprites";
	public static String entitiesDir = "assets/sprites/entities";
	public static String blocksDir = "assets/sprites/blocks";
	public static String envDir = "assets/sprites/environment";
	public static String itemsDir = "assets/sprites/items";
	public static String particlesDir = "assets/sprites/particles";
	public static String guiDir = "assets/gui";
	public static String fontsDir = guiDir+"/fonts";
	public static String musicDir = "assets/music";
	public static String weaponDir = itemsDir+"/weapon";
	
	/*
	 * #Strings for localization
	 */
	public static String m_moreContent = "More content!";
	public static String m_moreContentSub = "Want more content? Try this:";
	public static String m_profileMoney = "Money: ";
	public static String m_selectProfile = ">>   Select Profile";
	public static String m_storyMode = "Story Mode";
	public static String m_storyModeDis1 = "Presenting you a story mode. It is a cool";
	public static String m_storyModeDis2 = "story about the main hero - Adam Robbins!";
	public static String m_storyModeDis3 = "The 1st episode will be available in: v10.0";
	public static String m_back = "Back";
	public static String m_pause = "Pause";
	public static String m_pauseCnt = ">> Continue";
	public static String m_pauseOpt = ">> Options";
	public static String m_pauseEx = ">> Main Menu";
	public static String m_level = "Level: ";
	public static String m_levelID = "Level ID: ";
	public static String m_levelAuthor = "Level Author: ";
	public static String m_levelVer = "Level Version: ";
	public static String m_levelExtra = "isExtra-Level?: ";
	public static String m_playerHealth = "Player Health: ";
	public static String m_playerAmmo = "Player Ammo: ";
	public static String m_playerMagic = "Player Mana: ";
	public static String m_profileRuby = "Ruby count: ";
	public static String interactTooltip = "Press [E] to interact";
	public static String makarovAmmoDescStr = "It's a cool pistol!", 
			makarovAmmoName  = "Makarov Gun";
	public static String logErrNullProfile = "Choose a profile or create it, before you can play!";
	public static String logErrLoadingGame = "Error with loading the game!";
	public static String logErrDamagedData = "Data files are damaged!\nCan't load the game!";
	public static String logMsgDlcFirstPlayOrError = "Loading save-data failed!\n"
			+ "May be you play first time?\n"
			+ "If not, it means that your data-files are damaged!";
	public static String logErrBtnAction = "Incorrect arguments for action called by this BUTTON.";
	
	/*
	 * #Fonts
	 */
	public static Font defaultFont32 = FontManager.getFont(fontsDir+"/Bulgaria_Glorious_Cyr.ttf", 32);
	public static Font defaultFont30 = FontManager.getFont(fontsDir+"/Bulgaria_Glorious_Cyr.ttf", 30);
	public static Font defaultFont28 = FontManager.getFont(fontsDir+"/Bulgaria_Glorious_Cyr.ttf", 28);
	public static Font defaultFont26 = FontManager.getFont(fontsDir+"/Bulgaria_Glorious_Cyr.ttf", 26);
	public static Font defaultFont24 = FontManager.getFont(fontsDir+"/Bulgaria_Glorious_Cyr.ttf", 24);
	public static Font defaultFont22 = FontManager.getFont(fontsDir+"/Bulgaria_Glorious_Cyr.ttf", 22);
	public static Font defaultFont20 = FontManager.getFont(fontsDir+"/Bulgaria_Glorious_Cyr.ttf", 20);
	public static Font defaultFont = FontManager.getFont(fontsDir+"/Bulgaria_Glorious_Cyr.ttf", 18);
	public static Font defaultFont16 = FontManager.getFont(fontsDir+"/Bulgaria_Glorious_Cyr.ttf", 16);
	public static Font defaultFont14 = FontManager.getFont(fontsDir+"/Bulgaria_Glorious_Cyr.ttf", 14);
	public static Font defaultFont12 = FontManager.getFont(fontsDir+"/Bulgaria_Glorious_Cyr.ttf", 12);
	public static Font defaultFont11 = FontManager.getFont(fontsDir+"/Bulgaria_Glorious_Cyr.ttf", 11);
	public static Font defaultFont10 = FontManager.getFont(fontsDir+"/Bulgaria_Glorious_Cyr.ttf", 10);
	public static Font defaultFont8 = FontManager.getFont(fontsDir+"/Bulgaria_Glorious_Cyr.ttf", 8);
	public static Font defaultFont6 = FontManager.getFont(fontsDir+"/Bulgaria_Glorious_Cyr.ttf", 6);
	
	/*
	 * #GUI
	 */
	public static Image gameIcon = new ImageIcon("assets/gameIcon.png").getImage();
	public static Image logo = new ImageIcon(guiDir+"/logo.png").getImage();
	public static Image playBtn = new ImageIcon(guiDir+"/buttons/btnPlay.png").getImage();
	public static Image loadBtn = new ImageIcon(guiDir+"/buttons/btnLoad.png").getImage();
	public static Image shopBtn = new ImageIcon(guiDir+"/buttons/btnShop.png").getImage();
	public static Image storyBtn = new ImageIcon(guiDir+"/buttons/btnStory.png").getImage();
	public static Image extraBtn = new ImageIcon(guiDir+"/buttons/btnExtra.png").getImage();
	public static Image optBtn = new ImageIcon(guiDir+"/buttons/btnOptions.png").getImage();
	public static Image exitBtn = new ImageIcon(guiDir+"/buttons/btnExit.png").getImage();
	public static Image playBtnHover = new ImageIcon(guiDir+"/buttons/btnPlay_hover_style2.png").getImage();
	public static Image loadBtnHover = new ImageIcon(guiDir+"/buttons/btnLoad_hover_style2.png").getImage();
	public static Image shopBtnHover = new ImageIcon(guiDir+"/buttons/btnShop_hover_style2.png").getImage();
	public static Image storyBtnHover = new ImageIcon(guiDir+"/buttons/btnStory_hover_style2.png").getImage();
	public static Image storyBtnDisabled = new ImageIcon(guiDir+"/buttons/btnStory_disabled.png").getImage();
	public static Image langBtn = new ImageIcon(guiDir+"/buttons/langBtn.png").getImage();
	public static Image langBtnHover = new ImageIcon(guiDir+"/buttons/langBtn_hover.png").getImage();
	public static Image optionsBtn = new ImageIcon(guiDir+"/buttons/optionsBtn.png").getImage();
	public static Image optionsBtnHover = new ImageIcon(guiDir+"/buttons/optionsBtn_hover.png").getImage();
	public static Image extraBtnHover = new ImageIcon(guiDir+"/buttons/btnExtra_hover_style2.png").getImage();
	public static Image optBtnHover = new ImageIcon(guiDir+"/buttons/btnOptions_hover_style2.png").getImage();
	public static Image exitBtnHover = new ImageIcon(guiDir+"/buttons/btnExit_hover_style2.png").getImage();
	public static Image profile = new ImageIcon(guiDir+"/profile_image.png").getImage();
	public static Image dlcIcon = new ImageIcon(guiDir+"/dlc_image.png").getImage();
	public static Image langIcon = new ImageIcon(guiDir+"/lang.png").getImage();
	
	/*
	 * #Blocks
	 */
	public static Image vplatformDangeon = new ImageIcon(blocksDir+"/vplatform_dangeon.gif").getImage();
	public static Image hplatformDangeon = new ImageIcon(blocksDir+"/hplatform_dangeon.gif").getImage();
	public static Image brick1 = new ImageIcon(blocksDir+"/brick1.png").getImage();
	public static Image brick2 = new ImageIcon(blocksDir+"/brick2.png").getImage();
	public static Image brick3 = new ImageIcon(blocksDir+"/brick3.png").getImage();
	public static Image brick4 = new ImageIcon(blocksDir+"/brick4.png").getImage();
	public static Image brick5 = new ImageIcon(blocksDir+"/brick5.png").getImage();
	public static Image brick6 = new ImageIcon(blocksDir+"/brick6.png").getImage();
	public static Image brick7 = new ImageIcon(blocksDir+"/brick7.png").getImage();
	public static Image wall1 = new ImageIcon(blocksDir+"/dangeon_wall1.png").getImage();
	public static Image chest = new ImageIcon(blocksDir+"/interactive/chest.png").getImage();
	public static Image chestOpen = new ImageIcon(blocksDir+"/interactive/chest_open.png").getImage();
	public static Image key = new ImageIcon(itemsDir+"/key.png").getImage();
	public static Image button = new ImageIcon(blocksDir+"/interactive/button.png").getImage();
	public static Image buttonActive = new ImageIcon(blocksDir+"/interactive/button_active.png").getImage();
	public static Image vase = new ImageIcon(blocksDir+"/interactive/vase.png").getImage();
	public static Image vaseBroken = new ImageIcon(blocksDir+"/interactive/vase_broken.png").getImage();
	
	/*
	 * #Music
	 */
	public static String musicMain = musicDir+"/main-theme.mp3";
	
	/*
	 * #Entities
	 */
	public static Image player = new ImageIcon(entitiesDir+"/AdamRobbins.png").getImage();
	public static Image darkOfficer = new ImageIcon(entitiesDir+"/dark_officer.png").getImage();
	public static Image lightOfficer = new ImageIcon(entitiesDir+"/light_officer.png").getImage();
	public static Image fireOfficer = new ImageIcon(entitiesDir+"/fire_officer.png").getImage();
	public static Image iceOfficer = new ImageIcon(entitiesDir+"/ice_officer.png").getImage();
	
	public static Image monstDarkness = new ImageIcon(entitiesDir+"/monst_darkness.png").getImage();
	public static Image monstLight = new ImageIcon(entitiesDir+"/monst_light.png").getImage();
	public static Image monstFire = new ImageIcon(entitiesDir+"/monst_fire.png").getImage();
	public static Image monstIce = new ImageIcon(entitiesDir+"/monst_ice.png").getImage();
	
	public static Image thief = new ImageIcon(entitiesDir+"/thief.png").getImage();
	
	/*
	 * #Environment
	 */
	public static Image finish = new ImageIcon(envDir+"/finish.png").getImage();
	public static Image flame = new ImageIcon(envDir+"/flame.png").getImage();
	public static Image flameOff = new ImageIcon(envDir+"/flame_off.png").getImage();
	
	/*
	 * #Items
	 */
	public static Image coin10_X16 = new ImageIcon(itemsDir+"/coin10_16x16.png").getImage();
	public static Image coin10 = new ImageIcon(itemsDir+"/coin10.png").getImage();
	public static Image life = new ImageIcon(itemsDir+"/life.png").getImage();
	public static Image ruby = new ImageIcon(itemsDir+"/ruby.png").getImage();
	public static Image ammo = new ImageIcon(itemsDir+"/ammo.png").getImage();
	public static Image ammo16 = new ImageIcon(itemsDir+"/ammo_16.png").getImage();
	
	/*
	 * #GUNS & WEAPON
	 */
	public static Image makarovGun = new ImageIcon(weaponDir+"/ПМ.png").getImage();
	
	/*
	 * #Particles
	 */
	public static Image bullet1_01 = new ImageIcon(particlesDir+"/bullet1_01.png").getImage();
	public static Image bullet1_02 = new ImageIcon(particlesDir+"/bullet1_02.png").getImage();
	public static Image bullet1_03 = new ImageIcon(particlesDir+"/bullet1_03.png").getImage();
	public static Image fire1 = new ImageIcon(particlesDir+"/fire1.png").getImage();
	public static Image fire2 = new ImageIcon(particlesDir+"/fire2.png").getImage();
	public static Image fire3 = new ImageIcon(particlesDir+"/fire3.png").getImage();
	public static Image fire = new ImageIcon(particlesDir+"/fire.gif").getImage();
	
	/*
	 * Initialization Processes
	 */
	public static void loadConfiguration() {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("assets/saves/config/config.dat"));
			ArrayList<String> lines = new ArrayList<String>();
			String line = "";
			String profile = "";
			int bufferCount = 0;
			float musicVol, ambVol;
			while((line = reader.readLine()) != null) {
				lines.add(line);
			}
			for(String ln : lines) {
				String[] filter = ln.split("=");
				switch(filter[0]) {
					case "MusicVolume":
						Options.musicVolume = Float.parseFloat(filter[1]);
						break;
					case "AmbientVolume":
						Options.ambientVolume = Float.parseFloat(filter[1]);
						break;
					case "CanvasBufferStrategy.BufferCount":
						if(filter[1].equals("@autoCountMode")) {
							if(Game.screenSize.height <= 768 || Game.screenSize.width <= 1366) {
								Options.bufferCount = 4;
								Game.player.setSpeed(0.5f);
								Game.player.setRunSpeed(1f);
							}
							else if (Game.screenSize.height >= 768 || Game.screenSize.width >= 1366){
								Options.bufferCount = 2;
								Game.player.setSpeed(1f);
								Game.player.setRunSpeed(1.5f);
							}
						} else {
							Options.bufferCount = Integer.parseInt(filter[1]);
						}
						break;
					case "CurrentProfile":
						Options.profileName = filter[1];
						break;
					case "CurrentLanguage":
						LangManager.setLanguage(filter[1]);
						break;
				}
			}
		}catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(reader != null) {
				try {
					reader.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void saveConfiguration() {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter("assets/saves/config/config.dat"));
			writer.write("MusicVolume="+Options.musicVolume+"\n");
			writer.write("AmbientVolume="+Options.ambientVolume+"\n");
			writer.write("CanvasBufferStrategy.BufferCount=@autoCountMode\n");
			writer.write("CurrentProfile="+Options.profileName+"\n");
			writer.write("CurrentLanguage="+LangManager.currentLang+"\n");
			
		}catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(writer != null) {
				try {
					writer.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static ArrayList<Level> getDLCList() {
		ArrayList<Level> dlc = new ArrayList<Level>();
		File f = new File("assets/levels/extra/");
		String[] files = f.list();
		for(String file : files) {
			if(file.endsWith(".map")) {
				Level lvl = new Level();
				lvl.isExtra = true;
				lvl.create(file);
				//Debug.log(lvl.getName());
				dlc.add(lvl);
			}
		}
		return dlc;
	}
	
	public static ArrayList<String> getLangList() {
		ArrayList<String> lang = new ArrayList<String>();
		File f = new File("assets/lang/");
		String[] files = f.list();
		for(String file : files) {
			if(file.endsWith(".lang")) {
				lang.add(file);
			}
		}
		return lang;
	}
	
	public static ArrayList<String> getProfilesList() {
		BufferedReader reader = null;
		ArrayList<String> lines = new ArrayList<String>();
		try {
			reader = new BufferedReader(new FileReader("assets/saves/config/profileList.dat"));
			String line = "";
			while((line = reader.readLine()) != null) {
				lines.add(line);
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(reader != null) {
				try {
					reader.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		for(String line : lines) {
			if(line.equals("") || line.equals(null)) {
				lines.remove(line);
			}
		}
		return lines;
	}
	
//	public static void initAudio() {
//		main.setVolume(Options.musicVolume);
//		main.setCycleCount(main.INDEFINITE);
//	}
	
//	public static MediaPlayer getMusic(String file) {
////		String p1 = new File(file).getAbsolutePath();
////		String path = p1.replace('\\', '/');
////		String gg = "file:///"+path;
////		String done = gg.replace(' ', '_');
////		Debug.log(new File(done).exists());
////		Media hit = new Media(new File(file).toURI().toString());
////		MediaPlayer mediaPlayer = new MediaPlayer(hit);
////		return mediaPlayer;
//	}
	
	public static Clip getClip(String file) {
		Clip clip = null;
		try {
			File soundFile = new File(file);
			
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
			
			clip = AudioSystem.getClip();
			clip.open(ais);
			
			clip.setFramePosition(0);
//			Thread.sleep(clip.getMicrosecondLength()/1000);
//			clip.stop(); //Останавливаем
//			clip.close(); //Закрываем
		} catch (IOException | UnsupportedAudioFileException | LineUnavailableException exc) {
			exc.printStackTrace();
		}
		return clip;
	}
	
}
