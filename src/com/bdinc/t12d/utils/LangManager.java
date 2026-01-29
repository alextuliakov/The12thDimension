package com.bdinc.t12d.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.bdinc.t12d.settings.ResourcesManager;

public class LangManager {
	
	public static final String MORE_CONTENT_TITLE = "MoreContentTitle";
	public static final String MORE_CONTENT = "MoreContent";
	public static final String PROFILE_MONEYINFO = "ProfileMoneyInfo";
	public static final String PROFILE_SELECT_BTN = "ProfileSelectBtn";
	public static final String STORYMODE_TOOLTIP_TITLE = "StoryMode.toolTipTitle";
	public static final String STORYMODE_TOOLTIP_DIS1 = "StoryMode.toolTipDiscription1";
	public static final String STORYMODE_TOOLTIP_DIS2 = "StoryMode.toolTipDiscription2";
	public static final String STORYMODE_TOOLTIP_DIS3 = "StoryMode.toolTipDiscription3";
	public static final String BACK = "Back";
	public static final String PAUSE = "Pause";
	public static final String PAUSE_CONTBTN = "PauseScreen.continue";
	public static final String PAUSE_OPTBTN = "PauseScreen.options";
	public static final String PAUSE_EXTBTN = "PauseScreen.exit";
	public static final String INFO_LEVEL = "LevelInfo";
	public static final String INFO_LEVELID = "LevelInfo.ID";
	public static final String INFO_LEVELAUTHOR = "LevelInfo.Author";
	public static final String INFO_LEVELVERSION = "LevelInfo.Version";
	public static final String INFO_LEVELEXTRA = "LevelInfo.isExtra";
	public static final String INFO_PLAYERHEALTH = "PlayerInfo.health";
	public static final String INFO_PLAYERAMMO = "PlayerInfo.ammo";
	public static final String INFO_PLAYERMAGIC = "PlayerInfo.magicCount";
	public static final String INFO_PLAYERRUBY = "PlayerInfo.ruby";
	public static final String MSG_NULLPROFILE = "Message.nullProfile";
	public static final String MSG_LOADINGERR = "Message.loadingGame";
	public static final String MSG_DATADAMAGED = "Message.dataDamaged";
	public static final String MSG_DLCFIRSTPLAY = "Message.dlcFirstPlay";
	public static final String MSG_MECH_BUTTONARGSERR = "Message.mech.button.incorrectArgs";
	
	public static String currentLang = "";
	
	public static void setLanguage(String file) {
		currentLang = file;
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("assets/lang/"+file));
			ArrayList<String> lines = new ArrayList<String>();
			String line = "";
			while((line = reader.readLine()) != null) {
				lines.add(line);
			}
			for(int i = 1; i < lines.size(); i++) {
				String[] propertyParts = lines.get(i).split("=");
				switch(propertyParts[0]) {
					case MORE_CONTENT_TITLE:
						ResourcesManager.m_moreContent = propertyParts[1];
						break;
					case MORE_CONTENT:
						ResourcesManager.m_moreContentSub = propertyParts[1];
						break;
					case PROFILE_MONEYINFO:
						ResourcesManager.m_profileMoney = propertyParts[1];
						break;
					case PROFILE_SELECT_BTN:
						ResourcesManager.m_selectProfile = propertyParts[1];
						break;
					case STORYMODE_TOOLTIP_TITLE:
						ResourcesManager.m_storyMode = propertyParts[1];
						break;
					case STORYMODE_TOOLTIP_DIS1:
						ResourcesManager.m_storyModeDis1 = propertyParts[1];
						break;
					case STORYMODE_TOOLTIP_DIS2:
						ResourcesManager.m_storyModeDis2 = propertyParts[1];
						break;
					case STORYMODE_TOOLTIP_DIS3:
						ResourcesManager.m_storyModeDis3 = propertyParts[1];
						break;
					case BACK:
						ResourcesManager.m_back = propertyParts[1];
						break;
					case INFO_LEVEL:
						ResourcesManager.m_level = propertyParts[1];
						break;
					case INFO_LEVELID:
						ResourcesManager.m_levelID = propertyParts[1];
						break;
					case INFO_LEVELAUTHOR:
						ResourcesManager.m_levelAuthor = propertyParts[1];
						break;
					case INFO_LEVELVERSION:
						ResourcesManager.m_levelVer = propertyParts[1];
						break;
					case INFO_LEVELEXTRA:
						ResourcesManager.m_levelExtra = propertyParts[1];
						break;
					case INFO_PLAYERHEALTH:
						ResourcesManager.m_playerHealth = propertyParts[1];
						break;
					case INFO_PLAYERAMMO:
						ResourcesManager.m_playerAmmo = propertyParts[1];
						break;
					case INFO_PLAYERMAGIC:
						ResourcesManager.m_playerMagic = propertyParts[1];
						break;
					case INFO_PLAYERRUBY:
						ResourcesManager.m_profileRuby = propertyParts[1];
						break;
					case MSG_NULLPROFILE:
						ResourcesManager.logErrNullProfile = propertyParts[1];
						break;
					case MSG_LOADINGERR:
						ResourcesManager.logErrLoadingGame = propertyParts[1];
						break;
					case MSG_DATADAMAGED:
						ResourcesManager.logErrDamagedData = propertyParts[1];
						break;
					case MSG_DLCFIRSTPLAY:
						ResourcesManager.logMsgDlcFirstPlayOrError = propertyParts[1];
						break;
					case PAUSE:
						ResourcesManager.m_pause = propertyParts[1];
						break;
					case PAUSE_CONTBTN:
						ResourcesManager.m_pauseCnt = propertyParts[1];
						break;
					case PAUSE_OPTBTN:
						ResourcesManager.m_pauseOpt = propertyParts[1];
						break;
					case PAUSE_EXTBTN:
						ResourcesManager.m_pauseEx = propertyParts[1];
						break;
					case MSG_MECH_BUTTONARGSERR:
						ResourcesManager.logErrBtnAction = propertyParts[1];
						break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(reader != null) {
				try {
					reader.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
