package com.bdinc.t12d.graphics;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FontManager {
	
	public static Font getFont(String file, float size) {
		Font font;
		Font result = null;
		try {	
			font = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(file));
		    result = font.deriveFont(size);
		}
		catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		catch (FontFormatException e2) {
			e2.printStackTrace();
		}
		catch (IOException e2) {
			e2.printStackTrace();
		}
		return result;
	}
	
}
