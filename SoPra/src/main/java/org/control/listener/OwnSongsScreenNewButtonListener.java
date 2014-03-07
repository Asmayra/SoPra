package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

import org.control.LoginControl;
import org.control.PathControl;
import org.control.UploadControl;
import org.view.LoginScreen;

public class OwnSongsScreenNewButtonListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser openFile = new JFileChooser();
		openFile.showOpenDialog(null);
		File selected_file = openFile.getSelectedFile();
		
		try {
			UploadControl.uploadMusic(selected_file.getAbsolutePath(), PathControl.getInstance().getRoot() + File.separator + "Musik", selected_file.getName(), LoginControl.getInstance().getCurrentUser());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}