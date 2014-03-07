package org.testpackage;

public class CurrentDir {
	String Data_Location = CurrentDir()+"Names.abf";
	public static String CurrentDir(){
		String path=System.getProperty("java.class.path");
		String FileSeparator=(String)System.getProperty("file.separator");
		return path.substring(0, path.lastIndexOf(FileSeparator)+1);
	}
}
