package com.temp;

import java.io.File;

public class FileListFromFolder {

	public static void main(String a[]){
		File file = new File("/home/ravi/Documents/scans");
		String[] fileList = file.list();

		File[] filesListInDirectory = file.listFiles();

		for(File name:filesListInDirectory){
			System.out.println("-----------------");
			System.out.println(name.getName().substring(0,name.getName().indexOf(".")));
			System.out.println(name.getAbsolutePath());
			System.out.println("-----------------");
	
		}
		
	}	
	
}
