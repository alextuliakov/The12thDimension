package com.bdinc.t12d.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

public class UICell {
	
	private int x, y;
	
	private int width, height;
	
	public Color backgroundColor, headerColor, descriptionColor;
	private Color arrowActiveColor, arrowPassiveColor;
	public Color borderColor, activeColor, tmpColor, hoverColor;
	private Color tmpBackground;
	
	private Image icon;
	
	public boolean isSelected, useArrow;
	
	private String title, description;
	
	private Font titleFont, descriptionFont;
	
	public UICell() {}
	public UICell(String title, Image icon, Color bgColor, Color activeColor, Color hoverColor, boolean useArrow) {
		this.title = title;
		this.icon = icon;
		this.backgroundColor = bgColor;
		this.tmpBackground = bgColor;
		this.activeColor = activeColor;
		this.useArrow = useArrow;
		this.hoverColor = hoverColor;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setActive(boolean state) {
		isSelected = state;
		if(state) {
			tmpColor = backgroundColor;
			backgroundColor = activeColor;
		} else {
			backgroundColor = tmpColor;
			tmpColor = null;
		}
		
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public void setTitle(String text) {
		this.title = text;
	}
	
	public void setDescription(String text) {
		this.description = text;
	}
	
	public void setTitleFont(Font f) {
		this.titleFont = f;
	}
	
	public void setDescriptionFont(Font f) {
		this.descriptionFont = f;
	}
	
	public void setIcon(Image icon) {
		this.icon = icon;
	}
	
	public void setBorderColor(Color c) {
		this.borderColor = c;
	}
	
	public void setTitleColor(Color c) {
		this.headerColor = c;
	}
	
	public void setDescriptionColor(Color c) {
		this.descriptionColor = c;
	}
	
	public void setBackground(Color c) {
		this.backgroundColor = c;
	}
	
	public void resetBackground() {
		this.backgroundColor = tmpBackground;
	}
	
	public void draw(Graphics g) {
		int iconX = x+4, iconY = y+4;
		int icnW = height-4, icnH = height-4;
		int icnBX = x+3, icnBY = y+3, icnBW = height-3, icnBH = height - 3;
		g.setColor(borderColor);
		g.drawRect(x, y, width, height);
		g.drawRect(icnBX, icnBY, icnBW, icnBH);
		g.setColor(backgroundColor);
		g.fillRect(x+1, y+1, width-1, height-1);
		g.drawImage(icon, iconX, iconY, icnW, icnH, null);
		g.setColor(headerColor);
		g.setFont(titleFont);
		g.drawString(title, icnBX+icnBW+5, icnBY+20);
		g.setColor(descriptionColor);
		g.setFont(descriptionFont);
		g.drawString(description, icnBX+icnBW+5, icnBY+40);
	}
	
	public Color getHoverColor() {
		return this.hoverColor;
	}
	
	public Color getBackground() {
		return this.backgroundColor;
	}
	
	public Color getActiveColor() {
		return this.activeColor;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
}
