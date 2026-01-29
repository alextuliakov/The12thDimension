package com.bdinc.t12d.main;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.bdinc.t12d.graphics.DisplayManager;
import com.bdinc.t12d.input.InputManager;
import com.bdinc.t12d.input.MouseInputManager;
import com.bdinc.t12d.input.MouseMotionManager;
import com.bdinc.t12d.level.Level;
import com.bdinc.t12d.level.LevelManager;
import com.bdinc.t12d.maths.Map;
import com.bdinc.t12d.objects.Entity;
import com.bdinc.t12d.objects.MakarovGun;
import com.bdinc.t12d.scenes.DLCListDialog;
import com.bdinc.t12d.scenes.LangListDialog;
import com.bdinc.t12d.scenes.ProfilesListDialog;
import com.bdinc.t12d.settings.Options;
import com.bdinc.t12d.settings.ResourcesManager;
import com.bdinc.t12d.ui.Inventory;
import com.bdinc.t12d.ui.UIButton;
import com.bdinc.t12d.ui.UISlot;
import com.bdinc.t12d.ui.UISlotOverlay;
import com.bdinc.t12d.utils.ColorManager;
import com.bdinc.t12d.utils.Debug;
import com.bdinc.t12d.utils.LangManager;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	
	public static boolean isRunning;
	public static long delta;
	public static Graphics g;
	
	public static Game canvas;
	
	public Level currentLevel;
	
	public static final int WIDTH = 1120; //32 x35
	public static final int HEIGHT = 704; //32 x22
	
	public static final String VERSION = "v1.0-build4";
	
	public static final boolean isDevelopmentBuild = true;
	public static boolean paused;
	
	public static boolean stopped;
	
	private DisplayManager display = new DisplayManager();
	private static ResourcesManager resources;
	private static MouseMotionManager mouseManager;
	private static MouseInputManager mouseInputManager;
	public static InputManager keyManager;
	
	public static UISlotOverlay QAPanel;
	public static ArrayList<UISlot> QAPS = new ArrayList<UISlot>();
	public static int qapWidth = 300, qapHeight = 60;
	public static int qapX = WIDTH/2-(qapWidth/2), qapY = HEIGHT-(qapHeight+2);
	
	public static LevelManager manager;
	public Level lvl1;
	public Map map;
	
	public static Color m_profileBtnColor;
	
	private static byte bufferCount;
	private ArrayList<UIButton> pauseBtns = new ArrayList<UIButton>();
	
	public static Color m_pauseBtnTmp, m_pauseBtnHoverColor;
	public static Color m_continueBtnColor, m_optionsBtnColor;
	public static Color m_exmBtnColor;
	
	public static int m_playBtnX, m_playBtnY;
	public static int m_shopBtnX, m_shopBtnY;
	public static int m_optBtnX, m_optBtnY;
	public static int m_exitBtnX, m_exitBtnY;
	public static int m_profileBtnX, m_profileBtnY;
	public static int m_profileBtnWidth, m_profileBtnHeight;
	public static int m_labelX, m_labelY;
	public static int m_contRectX, m_contRectY, m_contBtnX, m_extraBtnX, m_extraBtnY, m_storyBtnY;
	public static int m_langBtnWidth, m_langBtnHeight, m_langBtnX, m_langBtnY;
	
	public static JFrame gameWindow;
	
	public static Entity player;
	
	public static Image m_playBtn, m_shopBtn, m_optBtn, m_exitBtn, m_extraBtn, m_storyBtn, m_langBtn,
	m_continueBtn;
	
	private static Thread gameStream;
	public static Dimension screenSize;
	
	public static boolean tooltip;
	public static int tooltipX, tooltipY;
	
	public static int m_pauseRectX, m_pauseRectY, m_continueBtnX, m_continueBtnY, 
	m_optionsBtnX, m_optionsBtnY, m_exmBtnX, m_exmBtnY, m_pauseX, m_pauseY;
	
	public void init()
	{
		QAPanel = new UISlotOverlay(qapX, qapY, qapWidth, qapHeight, 
				ColorManager.getAlphaColor(ColorManager.GOLD, 70), Color.BLACK);
		UISlot slotE = new UISlot(qapX+2, qapY+2, 60, 60, ColorManager.getAlphaColor(ColorManager.GOLD, 0), ColorManager.getAlphaColor(ColorManager.GOLD, 30), false);
		slotE.setBorderColor(Color.BLACK);
		slotE.setCountInfoColor(Color.BLUE);
		QAPS.add(slotE);
		for(int i = 1; i < 5; i++) {
			UISlot slot = new UISlot(QAPS.get(i-1));
				
			if((qapX+qapWidth) - (slot.getX()+slot.getWidth()) < slot.getWidth()) {
				slot.setLocation(QAPS.get(0).getX(),slot.getY()+slot.getHeight()+5);
					
			} else {
				slot.setX(slot.getX()+slot.getWidth()+5);
			}
			QAPS.add(slot);
		}
		QAPanel.cells = QAPS;
		QAPanel.isShow = true;
		map = new Map();
		mouseManager = new MouseMotionManager();
		mouseInputManager = new MouseInputManager();
		keyManager = new InputManager();
		map.init();
		LevelManager.setLevelByID(0);
		m_playBtn = resources.playBtn;
		m_shopBtn = resources.shopBtn;
		m_optBtn = resources.optionsBtn;
		m_exitBtn = resources.exitBtn;
		m_extraBtn = resources.extraBtn;
		m_storyBtn = resources.storyBtnDisabled;
		m_langBtn = resources.langBtn;
		m_continueBtn = resources.playBtn;
		m_continueBtnColor = ColorManager.FOREST_GREEN;
		m_optionsBtnColor = ColorManager.VIOLET;
		m_exmBtnColor = ColorManager.FOREST_GREEN;
		m_pauseBtnTmp = ColorManager.FOREST_GREEN;
		m_pauseBtnHoverColor = ColorManager.VIOLET;
		player = new Entity(resources.player);
		player.setMaxHealth(100);
		player.setHealth(100);
		player.setMagicCount(0);
		player.setMaxMagic(30);
		player.setName("Adam Robbins");
		player.setPosition(5, 1);
		//player.inventory.init();
		ResourcesManager.loadConfiguration();
		//ResourcesManager.initAudio();
		display.init();
		m_profileBtnColor = Color.CYAN;
		ProfilesListDialog.init();
		DLCListDialog.init();
		LangListDialog.init();
		System.out.println("BufferStrategy created with buffer count: " + Options.bufferCount);
		System.out.println("Music volume is: " + Options.musicVolume);
		System.out.println("Ambient volume is: " + Options.ambientVolume);
		System.out.println("Current profile is: <" + Options.profileName + ">");
		
		System.out.println("===========================");
		System.out.println("Initialization complete successfully!");
		System.out.println("===========================");
		//System.out.println("="+bin(16793));
		UIButton btn = new UIButton();
		pauseBtns.add(btn);
		pauseBtns.add(btn);
		pauseBtns.add(btn);
	}
	
	public String hex(int n) {
		String[] nums = new String[16];
		nums[0] = "0000";
		for(int i = 1; i < nums.length; i++) {
			nums[i] = bin(i);
		}
		String binStr = bin(n);
		char[] sym = binStr.toCharArray();
		String p1 = "";
		String[] nums2 = new String[4];
		int index = 0;
		for(int i = 0; i < sym.length; i++) {
			p1 += sym[i];
			if(i % 4 == 0) {
				nums2[index] = p1;
				p1 = "";
				index++;
			}
		}
		return null;
	}
	
	public int dec(String bin) {
		char[] cs = bin.toCharArray();
		byte[] bytes = new byte[cs.length];
		int ind = 0;
		for(char c : cs) {
			bytes[ind] = (byte)Integer.parseInt(""+c);
			ind++;
		}
		return 0;
	}
	
	public String bin(int n) {
		ArrayList<Integer> mods = new ArrayList<Integer>();
		while(n != 0) {
			mods.add(n % 2);
			n /= 2;
		}
		String num = "";
		for(int i = mods.size()-1; i >= 0; i--) {
			num += mods.get(i);
		}
		return num;
	}
	
	public static void pause(long millis) {
		try {
			gameStream.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void start()
	{
		isRunning = true;
		gameStream = new Thread(this);
		gameStream.start();
		
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println("===========================");
		System.out.println("The 12th Dimension");
		System.out.println("Version: 1.0");
		System.out.print("Build: v4 ");
		System.out.println("(Development Build)");
		System.out.println("Developed by <The Black Dragon Inc.> (C) 2017");
		System.out.println("Author: Alexandr Tulyakov");
		System.out.println("===========================");
		System.out.println("Screen size is: " + screenSize.width + "x" + screenSize.height);
		//Timer.waitSeconds(5);
	}
	
	public static void main(String[] args) {
		
		canvas = new Game();
		canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		canvas.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				keyManager.keyTyped(e);
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				keyManager.keyReleased(e);
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				keyManager.keyPressed(e);
				
			}
		});
		canvas.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				mouseInputManager.mouseReleased(e);
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				mouseInputManager.mousePressed(e);
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				mouseInputManager.mouseExited(e);
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				if(LevelManager.levelNumber == 0) {
					mouseInputManager.mouseEntered(e);
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseInputManager.mouseClicked(e);
				
			}
		});
		canvas.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
					mouseManager.mouseMoved(e);
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
					mouseManager.mouseDragged(e);
				
			}
		});
		gameWindow = new JFrame("The 12th Dimension");
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.setIconImage(resources.gameIcon);
		gameWindow.setLayout(new BorderLayout());
		gameWindow.setResizable(false);
		gameWindow.setVisible(true);
		gameWindow.add(canvas, BorderLayout.CENTER);
		
		gameWindow.pack();
		
		canvas.start();
		
		
    
	}
	
	public int getWidth()
	{
		return WIDTH;
	}
	
	public int getHeight()
	{
		return HEIGHT;
	}
	
	public static void stop() {
		ResourcesManager.saveConfiguration();
		gameStream.stop();
		gameWindow.dispose();
	}
	
	public long deltaTime()
	{
		return delta;
	}
	
	public void render()
	{
		BufferStrategy bs = getBufferStrategy();
		if(bs == null)
		{
			createBufferStrategy(Options.bufferCount);
			requestFocus();
			return;
		}
		g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		if(g == null) {
			System.err.println("Graphics lost!");
		}
		try
		{
			if(LevelManager.levelNumber > 0) {
				if(LevelManager.levelNumber == 1) {
					g.setColor(Color.BLACK);
					g.fillRect(0, 0, WIDTH, HEIGHT);
				}
				try {
					LevelManager.currentLevel.load(g);
					QAPanel.show(g);
					if(player.inventoryShow) {
						player.inventory.show(g);
					}
				}
				catch(Exception e) {
					System.err.println("Can't load the level (Level.load(Graphics g))");
					System.err.println("Source: Game.render()");
					bs.dispose();
					e.printStackTrace();
				}
				
			}
			else if(LevelManager.levelNumber <= -10) {
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, WIDTH, HEIGHT);
				try {
					LevelManager.currentLevel.load(g);
					QAPanel.show(g);
				}
				catch(Exception e) {
					System.err.println("Can't load the level (Level.load(Graphics g))");
					System.err.println("Source: Game.render()");
					bs.dispose();
					e.printStackTrace();
				}
			}
			else if(LevelManager.levelNumber == -1) {
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, WIDTH, HEIGHT);
				if(ProfilesListDialog.repaintWaiting()) {
					ProfilesListDialog.repaintProfiles(g);
					ProfilesListDialog.responseRepaint();
				}
				ProfilesListDialog.load(g);
			}
			else if(LevelManager.levelNumber == -2) {
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, WIDTH, HEIGHT);
				DLCListDialog.load(g);
			}
			else if(LevelManager.levelNumber == -3) {
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, WIDTH, HEIGHT);
				LangListDialog.load(g);
			}
			else {
				/*
				 * #MainMenu level:
				 */
				g.drawImage(resources.logo, 10, 50, null);
				
//				m_playBtnX = this.getWidth()/2-m_playBtn.getWidth(null)/2;
				m_playBtnX = 10;
				m_playBtnY = (this.getHeight()>>1) - 10;
				
				m_contRectX = 10; //10
				
				m_contRectY = m_playBtnY+5;
				m_contBtnX = m_contRectX;
				m_storyBtnY = (this.getHeight()>>1) - 100;
//				m_shopBtnX = this.getWidth()/2-m_shopBtn.getWidth(null)/2;
				m_shopBtnX = 10;
				m_shopBtnY = m_playBtnY+m_playBtn.getHeight(null)+5;
//				m_optBtnX = this.getWidth()/2-m_optBtn.getWidth(null)/2;
				
//				m_extraBtnX = this.getWidth()/2-m_extraBtn.getWidth(null)/2;
				m_extraBtnX = 10;
				m_extraBtnY = m_shopBtnY+m_shopBtn.getHeight(null)+5;
//				m_exitBtnX = this.getWidth()/2-m_exitBtn.getWidth(null)/2;
				m_exitBtnX = 10;
				m_exitBtnY = m_extraBtnY+m_extraBtn.getHeight(null)+5;
				m_profileBtnX = Game.WIDTH-297;
				m_profileBtnY = Game.HEIGHT-94;
				m_profileBtnWidth = 290;
				m_profileBtnHeight = 39;
				m_langBtnX = m_exitBtnX+m_exitBtn.getWidth(null)+10;
				m_langBtnY = m_exitBtnY;
				m_langBtnWidth = 60;
				m_langBtnHeight = 60;
				m_optBtnX = m_langBtnX;
				m_optBtnY = m_extraBtnY;
				m_labelX = 10;
				m_labelY = m_playBtnY-30;
				g.drawImage(m_playBtn, m_playBtnX, m_playBtnY, null);
				g.drawImage(m_shopBtn, m_shopBtnX, m_shopBtnY, null);
				g.drawImage(m_optBtn, m_optBtnX, m_optBtnY, 60, 60, null);
				g.drawImage(m_exitBtn, m_exitBtnX, m_exitBtnY, null);
				g.drawImage(m_extraBtn, m_extraBtnX, m_extraBtnY, null);
				g.drawImage(m_storyBtn, m_contBtnX, m_storyBtnY, null);
				g.drawImage(m_langBtn, m_langBtnX, m_langBtnY, m_langBtnWidth, m_langBtnHeight, null);
				
				//�������:
				g.setColor(Color.WHITE);
				g.drawRect(Game.WIDTH-300, Game.HEIGHT-150, 295, 100); //������� ������� (���)
				g.drawRect(Game.WIDTH-298, Game.HEIGHT-148, 51, 51); //������� ������� �������
				g.drawImage(ResourcesManager.profile, Game.WIDTH-297, Game.HEIGHT-147, null);
				g.drawRect(Game.WIDTH-246, Game.HEIGHT-148, 240, 26);
				
				//g.drawRect(m_contRectX, m_contRectY, 304, 230);
				
				g.setFont(ResourcesManager.defaultFont);
				//g.drawString(ResourcesManager.m_moreContent, m_labelX, 500);
				g.drawString(Options.profileName, Game.WIDTH-244, Game.HEIGHT-126);
				g.setFont(ResourcesManager.defaultFont14);
				//g.drawString(ResourcesManager.m_moreContentSub, m_labelX, 500);
				g.drawString(ResourcesManager.m_profileMoney+player.getMoney(), Game.WIDTH-244, Game.HEIGHT-104);
				g.drawRect(Game.WIDTH-298, Game.HEIGHT-95, 291, 40);
				g.setColor(m_profileBtnColor);
				g.fillRect(m_profileBtnX, m_profileBtnY, m_profileBtnWidth, m_profileBtnHeight);
				g.setColor(Color.black);
				String strSelectProfile = ResourcesManager.m_selectProfile;
				g.drawString(strSelectProfile, m_profileBtnX+50, m_profileBtnY+25);
				//����������
				g.setColor(Color.white);
				g.setFont(ResourcesManager.defaultFont);
				g.drawString("The 12th Dimension", 5, getHeight()-40);
				g.drawString(VERSION, 5, getHeight()-10);
				g.setColor(Color.YELLOW);
				g.drawString("The Fantasy is real!", 450, 70+resources.logo.getHeight(null));
				if(isDevelopmentBuild) {
					g.drawString("[Development Build]", 110, getHeight()-10);
				}
				g.setColor(Color.white);
				g.drawString("Copyright (C) BDINC 2017", getWidth()-235, getHeight()-10);
				
				if(tooltip) {
					g.setColor(Color.WHITE);
					if(LangManager.currentLang.equals("ru_RU.lang")) {
						g.drawRect(tooltipX, tooltipY, 440, 100);
					} else {
						g.drawRect(tooltipX, tooltipY, 340, 100);
					}
					g.setColor(ColorManager.getAlphaColor(Color.BLACK, 90));
					if(LangManager.currentLang.equals("ru_RU.lang")) {
						g.fillRect(tooltipX+1, tooltipY+1, 439, 99);
					} else {
						g.fillRect(tooltipX+1, tooltipY+1, 339, 99);
					}
					g.setColor(Color.YELLOW);
					g.setFont(ResourcesManager.defaultFont16);
					g.drawString(ResourcesManager.m_storyMode, tooltipX+3, tooltipY+20);
					g.setColor(Color.WHITE);
					g.setFont(ResourcesManager.defaultFont14);
					g.drawString(ResourcesManager.m_storyModeDis1, tooltipX+3, tooltipY+35);
					g.drawString(ResourcesManager.m_storyModeDis2, tooltipX+3, tooltipY+52);
					g.setColor(ColorManager.VIOLET);
					g.drawString(ResourcesManager.m_storyModeDis3, tooltipX+3, tooltipY+85);
				}
				
			}
			
			if(paused) {
				m_pauseRectX = this.getWidth()/2-200;
				m_pauseRectY = this.getHeight()/2-210;
				m_pauseX = m_pauseRectX+155;
				m_pauseY = m_pauseRectY+50;
				m_continueBtnX = m_pauseRectX+45;
				m_continueBtnY = m_pauseRectY+100;
				m_optionsBtnX = m_pauseRectX+45;
				m_optionsBtnY = m_continueBtnY+50;
				m_exmBtnX = m_pauseRectX+45;
				m_exmBtnY = m_optionsBtnY+50;
				g.setColor(Color.WHITE);
				g.drawRect(m_pauseRectX, m_pauseRectY, 400, 290);
				g.setColor(ColorManager.getAlphaColor(ColorManager.GOLD, 90));
				g.fillRect(m_pauseRectX+1, m_pauseRectY+1, 399, 289);
				g.setColor(Color.WHITE);
				g.drawRect(m_continueBtnX, m_continueBtnY, 300, 45);
				g.drawRect(m_optionsBtnX, m_optionsBtnY, 300, 45);
				g.drawRect(m_exmBtnX, m_exmBtnY, 300, 45);
				g.setFont(ResourcesManager.defaultFont30);
				g.drawString(ResourcesManager.m_pause, m_pauseX, m_pauseY);
				
				g.setColor(m_continueBtnColor);
				g.fillRect(m_continueBtnX+1, m_continueBtnY+1, 299, 44);
				g.setColor(m_optionsBtnColor);
				g.fillRect(m_optionsBtnX+1, m_optionsBtnY+1, 299, 44);
				g.setColor(m_exmBtnColor);
				g.fillRect(m_exmBtnX+1, m_exmBtnY+1, 299, 44);
				
				g.setColor(Color.WHITE);
				g.setFont(ResourcesManager.defaultFont16);
				g.drawString(ResourcesManager.m_pauseCnt, m_continueBtnX+5, m_continueBtnY+30);
				g.drawString(ResourcesManager.m_pauseOpt, m_optionsBtnX+5, m_optionsBtnY+30);
				g.drawString(ResourcesManager.m_pauseEx, m_exmBtnX+5, m_exmBtnY+30);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		g.dispose();
		bs.show();
	}
	
	@Override
	public void run() {
		long last = System.currentTimeMillis();
		
		init();
		display.init();
		while(isRunning)
		{
			//System.out.println(""+paused);
			delta = System.currentTimeMillis() - last;
			last = System.currentTimeMillis();
			display.update(delta);

			render();
			//System.out.println(""+player.getSpeed());
		}
		
		
	}

}
