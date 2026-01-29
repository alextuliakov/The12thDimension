package com.bdinc.t12d.utils;

import com.bdinc.t12d.main.Game;

public class Timer {
	
	public static void waitSeconds(long seconds) {
		Game.pause(seconds*1000);
	}
	
}
