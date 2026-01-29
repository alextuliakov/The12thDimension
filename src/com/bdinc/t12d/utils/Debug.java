package com.bdinc.t12d.utils;

public class Debug {
	
	public static void log(Object msg, boolean err) {
		if(err) {
			System.err.println(msg);
		} else {
			System.out.println(msg);
		}
		
	}
	
	public static void log(Object msg) {
		System.out.println(msg);
	}
	
}
