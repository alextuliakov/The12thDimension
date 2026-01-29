package com.bdinc.t12d.utils;

import java.awt.Color;

public class ColorManager {
	
	public static Color VIOLET = new Color(138, 43, 226);
	public static Color DARK_BLUE = new Color(0, 0, 139);
	public static Color CORNFLOWER_BLUE = new Color(100, 149, 237);
	public static Color DARK_CYAN = new Color(0, 139, 139);
	public static Color DEEP_SKYBLUE = new Color(0, 191, 255);
	public static Color LIGHT_SLATEBLUE = new Color(132, 112, 255);
	public static Color LIGHT_SKYBLUE = new Color(135, 206, 250);
	public static Color MEDIUM_AQUAMARINE = new Color(102, 205, 170);
	public static Color MEDIUM_BLUE = new Color(0, 0, 205);
	public static Color MIDNIGHT_BLUE = new Color(25, 25, 112);
	public static Color NAVY_BLUE = new Color(0, 0, 128);
	public static Color BROWN = new Color(139, 69, 19);
	public static Color SANDY_BROWN = new Color(224, 164, 96);
	public static Color BURLEY_WOOD = new Color(222, 184, 135);
	public static Color CHOCOLATE = new Color(210, 105, 30);
	public static Color CHOCOLATE_LIGHTER = new Color(138, 127, 36);
	public static Color DIM_GRAY = new Color(105, 105, 105);
	public static Color GRAY = new Color(190, 190, 190);
	public static Color LIGHT_GRAY = new Color(211, 211, 211);
	public static Color DARK_GREEN = new Color(0, 100, 0);
	public static Color FOREST_GREEN = new Color(34, 139, 34);
	public static Color LAWN_GREEN = new Color(124, 252, 0);
	public static Color LIME_GREEN = new Color(50, 205, 50);
	public static Color GREEN = new Color(0, 255, 0);
	public static Color DARK_ORANGE = new Color(255, 140, 0);
	public static Color ORANGE = new Color(255, 165, 0);
	public static Color DARK_RED = new Color(139, 0, 0);
	public static Color DEEP_PINK = new Color(255, 20, 147);
	public static Color PINK = new Color(255, 192, 203);
	public static Color RED = new Color(255, 0, 0);
	public static Color DARK_MAGENTA = new Color(139, 0, 139);
	public static Color MAGENTA = new Color(255, 0, 255);
	public static Color DARK_GOLDENROD = new Color(138, 185, 15);
	public static Color GOLD = new Color(255, 215, 0);
	public static Color YELLOW = new Color(255, 255, 0);
	
	public static Color getAlphaColor(int r, int g, int b, int alpha) {
		return new Color(r, g, b, alpha);
	}
	
	public static Color getAlphaColor(Color c, int alpha) {
		return new Color(c.getRed(), c.getGreen(), c.getBlue(), alpha);
	}
	
	public static Color sumColors(Color a, Color b) {
		return new Color(a.getRed()+b.getRed(), a.getGreen()+b.getGreen(), a.getBlue()+b.getBlue());
	}
	
	
}
