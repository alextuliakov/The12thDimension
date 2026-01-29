package com.bdinc.t12d.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import com.bdinc.t12d.objects.Item;
import com.bdinc.t12d.settings.ResourcesManager;
import com.bdinc.t12d.utils.Debug;

public class UISlot {
	private Color background, tmpBackground, hoverColor, borderColor, tooltipBg, 
	tooltipBorderColor, tooltipTextColor, countInfoColor;
	
	private boolean tooltipEnabled = false;
	
	private Item object = new Item(null);
	
	private int x, y;
	public int toolTipX, toolTipY, tooltipWidth = 300, tooltipHeight = 110;
	public int imgX, imgY, imgDragX, imgDragY;
	private int width, height;
	
	private String tooltipText, tooltipTitle, countStr;
	public boolean isHover, hasItem, isDragging, showCount = true;
	
	private Font tooltipTitleFont, tooltipFont;
	
	public UISlot() {}
	public UISlot(UISlot slot) {
		UISlot n = new UISlot();
		n = slot;
		this.setBackground(n.getBackground());
		this.tmpBackground = n.tmpBackground;
		this.setBorderColor(n.getBorderColor());
		this.setHoverColor(n.getHoverColor());
		this.setTooltipBackground(n.getTooltipBackground());
		this.setTooltipBorderColor(n.getTooltipBorderColor());
		this.setTooltipTextColor(n.getTooltipTextColor());
		this.setCountInfoColor(n.getCountInfoColor());
		this.tooltipEnabled = n.tooltipEnabled;
		this.setSize(n.getWidth(), n.getHeight());
		this.putItem(n.getItem());
		this.setLocation(n.getX(), n.getY());
		//Debug.log(n.getX()+";"+n.getY());
		this.toolTipX = n.toolTipX;
		this.toolTipY = n.toolTipY;
		this.tooltipWidth = n.tooltipWidth;
		this.tooltipHeight = n.tooltipHeight;
		this.tooltipText = n.tooltipText;
		this.tooltipTitle = n.tooltipTitle;
		this.countStr = n.countStr;
		this.tooltipTitleFont = n.tooltipTitleFont;
		this.tooltipFont = n.tooltipFont;
		this.hasItem = n.hasItem;
		this.isHover = n.isHover;
		this.imgX = n.imgX;
		this.imgY = n.imgY;
		this.imgDragX = n.imgDragX;
		this.imgDragY = n.imgDragY;
	}
	public UISlot(int x, int y, int width, int height, Color bg, Color hover, boolean tooltip) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.background = bg;
		this.tmpBackground = bg;
		this.hoverColor = hover;
	}
	
	public void putItem(Item itm) {
		this.object = itm;
		hasItem = true;
	}
	
	public void draw(Graphics g) {
		if(!isDragging) {
			imgX = x+3;
			imgY = y+3;
		}
		if(isHover) {
			setBackground(hoverColor);
		} else {
			resetBackground();
		}
		g.setColor(borderColor);
		g.drawRect(x, y, width, height);
		g.setColor(background);
		g.fillRect(x+1, y+1, width-1, height-1);
		g.drawImage(object.getSprite(), imgX, imgY, width-3, height-3, null);
		if(hasItem && showCount) {
			countStr = String.valueOf(object.getCount());
			g.setColor(countInfoColor);
			//Debug.log("RED:"+countInfoColor.getRed());
			g.setFont(ResourcesManager.defaultFont14);
			FontMetrics m = g.getFontMetrics(ResourcesManager.defaultFont14);
			g.drawString(countStr, x+width-m.stringWidth(countStr)-2, y+height-m.getDescent()-2);
			
		}
		if(tooltipEnabled) {
			g.setColor(tooltipBorderColor);
			g.drawRect(toolTipX, toolTipY, tooltipWidth, tooltipHeight);
			g.setColor(tooltipBg);
			g.fillRect(toolTipX+1, toolTipY+1, tooltipWidth-1, tooltipHeight-1);
			g.setColor(Color.YELLOW);
			g.setFont(tooltipTitleFont);
			FontMetrics m = g.getFontMetrics(tooltipTitleFont);
			g.drawString(object.getName(), toolTipX+3, toolTipY+m.getHeight()+2);
			g.setColor(tooltipTextColor);
			g.setFont(tooltipFont);
			FontMetrics m1 = g.getFontMetrics(tooltipFont);
//			if(tooltipWidth - m1.stringWidth(object.getDescription()) <= 0) {
//				
//			}
			g.drawString(object.getDescription(), toolTipX+3, toolTipY+m.getHeight()+4+m1.getHeight());
		}
	}
	
	public void setItemCount(int count) {
		this.object.setCount(count);
	}
	
	public void editItemCount(int value) {
		this.object.editCount(value);
	}
	
	public void setCountInfoColor(Color c) {
		this.countInfoColor = c;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setSize(int w, int h) {
		this.width = w;
		this.height = h;
	}
	
	public void setWidth(int w) {
		this.width = w;
	}
	
	public void setHeight(int h) {
		this.height = h;
	}
	
	public void setTooltipText(String text) {
		this.tooltipText = text;
	}
	
	public void setTooltipTitleFont(Font f) {
		this.tooltipTitleFont = f;
	}
	
	public void setTooltipFont(Font f) {
		this.tooltipFont = f;
	}
	
	public void setTooltipTitle(String text) {
		this.tooltipTitle = text;
	}
	
	public void setTooltipBorderColor(Color c) {
		this.tooltipBorderColor = c;
	}
	
	public void setTooltipTextColor(Color c) {
		this.tooltipTextColor = c;
	}
	
	public void setTooltipBackground(Color c) {
		this.tooltipBg = c;
	}
	
	public void setHoverColor(Color c) {
		this.hoverColor = c;
	}
	
	public void setBorderColor(Color c) {
		this.borderColor = c;
	}
	
	public void setBackground(Color c) {
		this.background = c;
	}
	
	public void resetBackground() {
		this.background = tmpBackground;
	}
	
	public Color getBackground() {
		return background;
	}
	
	public Color getHoverColor() {
		return hoverColor;
	}
	
	public Color getBorderColor() {
		return borderColor;
	}
	
	public Color getCountInfoColor() {
		return countInfoColor;
	}
	
	public Color getTooltipBackground() {
		return tooltipBg;
	}
	
	public Color getTooltipBorderColor() {
		return tooltipBorderColor;
	}
	
	public Color getTooltipTextColor() {
		return tooltipTextColor;
	}
	
	public Item getItem() {
		return object;
	}
	
	public Font getTooltipFont() {
		return tooltipFont;
	}
	
	public Font getTooltipTitleFont() {
		return tooltipTitleFont;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
}
