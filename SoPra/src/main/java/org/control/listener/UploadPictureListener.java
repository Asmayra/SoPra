package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.control.DatabaseControl;
import org.control.LoginControl;
import org.control.SaveImageControl;
/**
 * Listener, um Bidler hochzuladen
 * 
 * @author Tim
 *
 */
public class UploadPictureListener implements ActionListener {
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JFileChooser filechooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "gif","png");
		filechooser.setFileFilter(filter);
		int state = filechooser.showOpenDialog(null);
		if(state== JFileChooser.APPROVE_OPTION){
			File input = filechooser.getSelectedFile();
			BufferedImage image;
			try {
				image = (BufferedImage)ImageIO.read(input);
				SaveImageControl.saveBufferedImage(image, LoginControl.getInstance().getCurrentUser().getUsername());
				LoginControl.getInstance().getCurrentUser().setImagePath("avatar.jpg");
				
				DatabaseControl.getInstance().saveWithQuery("UPDATE User SET imagepath = 'avatar.jpg' WHERE username = "+"'"+LoginControl.getInstance().getCurrentUser().getUsername()+"'");
				//DatabaseControl.getInstance().update(LoginControl.getInstance().getCurrentUser());
				//System.out.println("SAVE!");
			} catch (IOException e) {
				System.out.println("Loading failed");
				e.printStackTrace();
			}
			
		}
		
	}

}
