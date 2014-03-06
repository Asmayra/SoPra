package org.control;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class FileIOControl {

	private static FileIOControl instance = null;
	
	private final String rootPath;
	
	private FileIOControl()
	{
		
		File tmp = new File( getClass().getProtectionDomain().getCodeSource().getLocation().getPath() );
		if(!tmp.isDirectory())
			rootPath = tmp.getParent();
		else
			rootPath = tmp.getPath();
	}
	
	public static FileIOControl getInstance()
	{
		if(instance == null)
			instance = new FileIOControl();
		
		System.out.println(instance.rootPath);
		return instance;
	}
	
	public boolean makeDir(String newDir)
	{
		return new File(rootPath + File.separator + newDir).mkdir();
	}
	
	public String getRoot()
	{
		return rootPath;
	}
	
	
	
}
