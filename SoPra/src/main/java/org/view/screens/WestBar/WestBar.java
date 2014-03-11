package org.view.screens.WestBar;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
/**
 * A Structure Class for the WestBar
 * @author Philipp, Max, Tim
 *
 */
public class WestBar extends JPanel {
	private Menu menu = new Menu();
	private PlaylistMiniScreen miniscreen = new PlaylistMiniScreen();
	private CoverArt coverart = new CoverArt();
	private SongTicker songticker = SongTicker.getInstance();
	
	/**
	 * Erstellt die Westbar mit ihren Einzelelementen
	 */
	public WestBar(){
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx=0;
		c.gridy=0;
		//c.gridheight=2;
		c.anchor= GridBagConstraints.FIRST_LINE_START;
		add(menu,c);
		c.gridy=1;
		//c.gridheight=20;
		add(miniscreen,c);
		c.gridy=2;
		//c.gridheight=10;
		add(coverart,c);
		c.gridy=3;
		//c.gridheight=5;
		add(songticker,c);
		
		this.setMinimumSize(new Dimension(260,600));
		
	}
	
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	public PlaylistMiniScreen getMiniscreen() {
		return miniscreen;
	}
	public void setMiniscreen(PlaylistMiniScreen miniscreen) {
		this.miniscreen = miniscreen;
	}
	public CoverArt getCoverart() {
		return coverart;
	}
	public void setCoverart(CoverArt coverart) {
		this.coverart = coverart;
	}
	public SongTicker getSongticker() {
		return songticker;
	}
	public void setSongticker(SongTicker songticker) {
		this.songticker = songticker;
	}
	
	

}
