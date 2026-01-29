package com.bdinc.t12d.graphics;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class ImageWorker {
	
	private BufferedImage image;
	
	public ImageWorker(BufferedImage img, String path) {
		this.image = img;
		setImage(path);
	}
	
	public void setImage(String path) {
		Graphics g = image.createGraphics();
		Image img = new ImageIcon(path).getImage();
		g.drawImage(img, 0, 0, img.getWidth(null), img.getHeight(null), null);
	}
	
	public Image getImage() {
		return this.image;
	}
	
}
