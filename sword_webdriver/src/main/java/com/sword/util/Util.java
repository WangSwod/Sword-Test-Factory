package com.sword.util;

public final class Util {
	
	public static void pause(int millisecond){
		
		try{
			
			Thread.sleep(millisecond);
		}catch(Exception e){
			
			e.printStackTrace();
		}
	}
}
