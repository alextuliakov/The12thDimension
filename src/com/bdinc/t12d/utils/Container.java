package com.bdinc.t12d.utils;

import java.util.ArrayList;

import com.bdinc.t12d.objects.Block;
import com.bdinc.t12d.objects.Entity;

public class Container {
	
	private ArrayList<Block> map1;
	private ArrayList<Entity> map2;
	
	public Container(ArrayList<Block> map1, ArrayList<Entity> map2)
	{
		this.map1 = map1;
		this.map2 = map2;
	}
	
	public ArrayList<Block> getKey()
	{
		return this.map1;
	}
	
	public ArrayList<Entity> getValue()
	{
		return this.map2;
	}
}
