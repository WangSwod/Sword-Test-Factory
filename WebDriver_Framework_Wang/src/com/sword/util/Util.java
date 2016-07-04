package com.sword.util;

public class Util {
	public static int convertToInt(String wait_Time_Content) {
		if(wait_Time_Content.equals("")){
			return  0;			
		}else {
			return  Integer.parseInt(wait_Time_Content);
		}
	}
}
