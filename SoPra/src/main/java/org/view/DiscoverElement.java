package org.view;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.control.listener.DiscoverElementListener;

/**
 * 
 * @author Tim Lange, Tim Michels, Max Küper
 */
public class DiscoverElement extends JPanel {
	
	private JLabel label;
	private JLabel pictureLabel;
	private String url;
	private String objectType; //Song, Album oder User
	
	public String getUrl(){
		return this.url;
	}
	/**
	 * creates an Discover Element
	 * @param text String to be shown as description
	 * @param pictureIcon JLabel with picture to be shown
	 * @param url to get to this discover
	 * @param type Used to get this DiscoverElement into the right Tab on the ExtendedDiscoverScreen types are: "Album", "Song" or "User"
	 */
	public DiscoverElement(String text, ImageIcon pictureIcon, String url, String type) {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.label = new JLabel("<html><body>"+text+"</body></html>");
		this.pictureLabel = new JLabel(pictureIcon);
		this.add(this.label);
		this.add(this.pictureLabel);
		this.url = url;
		this.objectType=type;
		this.addMouseListener(DiscoverElementListener.getInstance());
	}
	
	//getter & setter
	public String getObjectType() {
		return objectType;
	}
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
}
