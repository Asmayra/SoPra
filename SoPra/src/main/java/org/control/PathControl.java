package org.control;

import java.io.File;
/**
 * 
 * @author Mattias Schoenke
 *
 */
public class PathControl {

	private static PathControl instance = null;
	
	private final String rootPath;
	
	private PathControl()
	{
		
		File tmp = new File( getClass().getProtectionDomain().getCodeSource().getLocation().getPath() );
		if(!tmp.isDirectory())
			rootPath = tmp.getParent();
		else
			rootPath = tmp.getPath();
	}
	
	public static PathControl getInstance()
	{
		if(instance == null)
			instance = new PathControl();
		
		return instance;
	}
	
	public String getRoot()
	{
		return rootPath;
	}
	
	
	
}
