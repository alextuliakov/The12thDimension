package com.bdinc.t12d.maths;

public class Map {
	
	private static int horizontalCount = 35;
	private static int verticalCount = 25;
	private int size = horizontalCount * verticalCount;
	
	public static final int cellSize = 32;
	
	public Vector2[][] cells = new Vector2[horizontalCount][verticalCount];
	
	public void init()
	{
		int x = 0, y = 0;
		
		for(int i = 0; i < horizontalCount; i++)
		{
			for(int j = 0; j < verticalCount; j++)
			{
				cells[i][j] = new Vector2(x, y);
				y += cellSize;
				
			}
			y = 0;
			x += cellSize;
		}
	}
	
	public Vector2 checkCell(float x, float y) {
		init();
		for(int i = 0; i < horizontalCount; i++)
		{
			for(int j = 0; j < verticalCount; j++)
			{
				if (x >= cells[i][j].x && x <= cells[(i+1)%horizontalCount][j].x) {
					if (y >= cells[i][j].y && y <= cells[i][(j+1)%verticalCount].y) {
						return new Vector2(i, j);
					}
				}
				
			}
		}
		return null;
	}
	
	public Vector2 getCell(int x, int y)
	{
		return cells[x][y];
	}
	
	public void setHorizontalCount(int count)
	{
		horizontalCount = count;
	}
	
	public void setVerticalCount(int count)
	{
		verticalCount = count;
	}
	
	public int getHorizontalCount()
	{
		return horizontalCount;
	}
	
	public int getVerticalCount()
	{
		return verticalCount;
	}
	
}
