package com.sword.util;

import java.io.File;
import java.io.IOException;

public class FileUtil {
	public static boolean createFile(String path) {
		File file = new File(path);
		
		//check if the file already exists
		if(file.exists()){	
			LogUtil.info("fail to create, file already exists");
			return false;
		}	
		
		//check if the directory of file already exists
		if(!file.getParentFile().exists()){
			LogUtil.	info("directory of the file does not exist,try to create it");
			if(!file.getParentFile().mkdirs()){
				LogUtil.info("fail to create directory of the file ");
				return false;
			}
		}
		
		//try to create the file
		try {
			
			if(file.createNewFile()){
				LogUtil.info("create file successfully");
				return true;
			}else{
				LogUtil.info("fail to create file");
				return false;
			}
			
			
		} catch (IOException e) {
			
			LogUtil.info("fail to create file");
			e.printStackTrace();
			return false;
			
		}
	}
	
	public static boolean  createDir(String path) {
		File dir = new File(path);
		
		if(dir.exists()){
			LogUtil.info("fail to create, directory already exist");
			return false;
			
		}
		
		if(dir.mkdirs()){
			LogUtil.info("create directory successfully");
			return true;
		}else{
			LogUtil.info("fail to create directory");
			return false;
		}
	}
}
