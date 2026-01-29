package com.bdinc.t12d.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class UIButton {
	
	private Color background, captionColor = Color.black, tmpBackground, hover, borderColor = Color.WHITE;
	private String caption;
	private Font captionFont;
	
	private int x, y;
	private int width, height;
	
	public boolean isHover;
	
	public UIButton() {}
	
	public UIButton(String caption, int x, int y, Color bg, Color hover, int width, int height) {
		this.caption = caption;
		this.x = x;
		this.y = y;
		this.background = bg;
		this.tmpBackground = bg;
		this.hover = hover;
		this.width = width;
		this.height = height;
	}
	
	public void draw(Graphics g) {
		g.setColor(borderColor);
		g.drawRect(x, y, width, height);
		g.setColor(background);
		g.fillRect(x+1, y+1, width-1, height-1);
		g.setFont(captionFont);
		g.setColor(captionColor);
		g.drawString(caption, x+5, y+height/2-10);
		if(isHover) {
			setBackground(hover);
		} else {
			resetBackground();
		}
	}
	
	public void setSize(int w, int h) {
		this.width = w;
		this.height = h;
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setFont(Font f) {
		this.captionFont = f;
	}
	
	public void setBackground(Color bg) {
		this.background = bg;
	}
	
	public void setCaptionColor(Color c) {
		this.captionColor = c;
	}
	
	public void resetBackground() {
		this.background = tmpBackground;
	}
	
	public void setCaption(String s) {
		this.caption = s;
	}
	
	public void setBorderColor(Color c) {
		this.borderColor = c;
	}
	
	public void setHoverColor(Color c) {
		this.hover = c;
	}
	
	public Color getBackground() {
		return this.background;
	}
	
	public Color getCaptionColor() {
		return this.captionColor;
	}
	
	public Color getBorderColor() {
		return this.borderColor;
	}
	
	public Color getHoverColor() {
		return this.hover;
	}
	
	public String getCaption() {
		return this.caption;
	}
	
	public Font getCaptionFont() {
		return this.captionFont;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
}
