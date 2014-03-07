package org.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.model.Song;
import org.model.User;


/**
 * Stellt statische Funktionen zum hochladen(kopieren von A nach B) von Musik und Bildern bereit
 * @author Mattias Schoenke
 *
 */
public class UploadControl {
	
	
	/**
	 * Kopiert eine Musik Datei von A nach B
	 * @param srcPath Pfad der Quell Datei
	 * @param dstPath Pfad der Ziel Datei
	 * @param dstName Name der Ziel Datei
	 * @param interpret Interpret des Liedes
	 * @param title Titel des Liedes
	 * @throws IOException Falls quell Datei nicht existiert, kein mp3 ist oder Ziel Datei bereits existiert
	 */
	public static void updaloadMusic(String srcPath, String dstPath, String dstName, String title, User user) throws IOException
	{
		File src = new File(srcPath);
		
		if(!src.exists())
			throw new IOException("Quell Datei existiert nicht!");
		
		if(!src.getName().endsWith(".mp3"))
			throw new IOException("Quell Datei ist kein mp3!");
		
		File dst = new File(dstPath);
		
		dstName = dstName + ".mp3";
		
		if(dst.isDirectory())
			dst = new File(dst, dstName);
		
		if(dst.exists())
			throw new IOException("Ziel Datei existiert bereits!");
		
		copy(srcPath, dstPath, dstName);
		
		Song newSong = new Song(user.getUsername(), title, dst.getAbsolutePath());
		DatabaseControl.getInstance().save(newSong);
		
		user.addOwnSong(newSong);
		DatabaseControl.getInstance().update(user);
	}
	
	
	/**
	 * Kopiert eine Bild Datei von A nach B
	 * @param srcPath Pfad der Quell Datei
	 * @param dstPath Pfad der Ziel Datei
	 * @param dstName Name der Ziel Datei
	 * 
	 * @throws IOException Falls quell Datei nicht existiert, kein jpg ist oder Ziel Datei bereits existiert
	 */
	public static void updaloadImage(String srcPath, String dstPath, String dstName) throws IOException
	{
		File src = new File(srcPath);
		
		if(!src.exists())
			throw new IOException("Quell Datei existiert nicht!");
		
		if(!src.getName().endsWith(".jpg"))
			throw new IOException("Quell Datei ist kein jpg!");
		
		File dst = new File(dstPath);
		
		dstName = dstName + ".jpg";
		
		if(dst.isDirectory())
			dst = new File(dst, dstName);
		
		if(dst.exists())
			throw new IOException("Ziel Datei existiert bereits!");
		
		copy(srcPath, dstPath, dstName);
	}
	
	
	/**
	 * Kopiert eine Datei von from zu to
	 * @param from Pfad von wo die Datei kopiert werden soll
	 * @param to Pfad zu dem die Dater kopiert werden soll
	 * @param toName Name der Ziel Datei
	 * 
	 * @throws IOException Exceptions beim Kopieren
	 * @pre Pfade sind gültig, from Datei existiert, to Datei existiert nicht
	 * @post Datei kopiert
	 */
	private static void copy(String from, String to, String toName) throws IOException
	{
		File src = new File(from);
		
		File dst = new File(to);
		dst.mkdirs();
		
		if(dst.isDirectory())
			dst = new File(dst, toName);
		
		dst.setWritable(true);
		dst.createNewFile();
		
		FileInputStream in = new FileInputStream(src); 
		FileOutputStream out = new FileOutputStream(dst);
		
		int bytesRead = 0;
		byte[] buffer = new byte[4096];
		
		while( (bytesRead = in.read(buffer) ) >= 0 )
		{
			out.write(buffer, 0, bytesRead);
		}
		
		in.close();
		out.flush();
		out.close();
	}

}
