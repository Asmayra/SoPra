package org.control;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.model.Album;
import org.model.Song;
import org.model.User;
import org.view.DiscoverElement;
import org.view.screens.Center.DiscoverExtendedScreen;
import org.view.screens.EastBar.DiscoverMiniScreen;

/**
 * provides the discover recomendations showed on the program start on the
 * eastbar
 * 
 * @author Tim Lange, Tim Michels, Sebastian Roth
 * 
 */
public class DiscoverControl {

	public DiscoverControl() {

		User currUser = LoginControl.getInstance().getCurrentUser();

		LinkedList<DiscoverElement> artists = new LinkedList<DiscoverElement>();
		LinkedList<DiscoverElement> albums = new LinkedList<DiscoverElement>();
		LinkedList<DiscoverElement> songs = new LinkedList<DiscoverElement>();

		DiscoverMiniScreen miniScreen = DiscoverMiniScreen.getInstance();
		DiscoverExtendedScreen extScreen = DiscoverExtendedScreen.getInstance();
		DiscoverElement discover1mini = null;
		DiscoverElement discover2mini = null;
		DiscoverElement discover3mini = null;

		try {
			Album recAlbum = RecommendationControl.getInstance().recommendAlbum();
			Image pic = recAlbum.getOwner().getPicture().getScaledInstance(100, 100, BufferedImage.SCALE_DEFAULT);
			ImageIcon pi = new ImageIcon(pic);
			discover1mini = new DiscoverElement(recAlbum.getName(), pi, recAlbum.getOwner().getUsername(), "Album");
			miniScreen.setDiscoverOne(discover1mini);
		} catch (Exception e) {
		}
		try {

			User recUser = RecommendationControl.getInstance().recommendArtist();
			Image pic = recUser.getPicture().getScaledInstance(100, 100, BufferedImage.SCALE_DEFAULT);
			ImageIcon pi = new ImageIcon(pic);
			discover2mini = new DiscoverElement(recUser.getUsername(), pi, recUser.getUsername(), "User");
			miniScreen.setDiscoverTwo(discover2mini);
		} catch (Exception e) {
		}
		try {
			Song recSong = RecommendationControl.getInstance().recommendSong();
			User usr = (User) DatabaseControl.getInstance().load(User.class, recSong.getInterpret());
			Image pic = usr.getPicture().getScaledInstance(100, 100, BufferedImage.SCALE_DEFAULT);
			ImageIcon pi = new ImageIcon(pic);
			discover3mini = new DiscoverElement(recSong.getTitle(), pi, usr.getUsername(), "Song");
			miniScreen.setDiscoverThree(discover3mini);

		} catch (Exception e) {
		}
		if (discover1mini == null && discover2mini == null && discover3mini == null) {
			JLabel noDiscover1 = new JLabel();
			JLabel noDiscover2 = new JLabel();
			JLabel noDiscover3 = new JLabel();
			noDiscover1.setText("Bitte folgen Sie anderen Nutzern");
			noDiscover2.setText("damit wir ihnen tolle Empfehlungen");
			noDiscover3.setText("ab dem nächsten Start geben können.");
			JPanel noDis = new JPanel();
			noDis.setLayout(new BoxLayout(noDis, BoxLayout.PAGE_AXIS));
			noDis.add(noDiscover1);
			noDis.add(noDiscover2);
			noDis.add(noDiscover3);
			miniScreen.setDiscoverOne(noDis);
		}

		List<User> arts = RecommendationControl.getInstance().allRecommendedArtists();
		for (int i = 0; i < arts.size(); i++) {
			User art = arts.get(i);
			Image pic = art.getPicture().getScaledInstance(150, 150, BufferedImage.SCALE_DEFAULT);
			ImageIcon pi = new ImageIcon(pic);
			DiscoverElement discover = new DiscoverElement(art.getUsername(), pi, art.getUsername(), "User");
			artists.add(discover);
		}
		List<Song> sons = RecommendationControl.getInstance().allRecomendedSongs();
		for (int i = 0; i < sons.size(); i++) {
			Song son = sons.get(i);
			User artist = (User) DatabaseControl.getInstance().load(User.class, son.getInterpret());
			Image pic = artist.getPicture().getScaledInstance(150, 150, BufferedImage.SCALE_DEFAULT);
			ImageIcon pi = new ImageIcon(pic);
			DiscoverElement discover = new DiscoverElement(son.getTitle(), pi, artist.getUsername(), "Song");
			songs.add(discover);
		}
		List<Album> albs = RecommendationControl.getInstance().allRecomendedAlben();
		for (int i = 0; i < albs.size(); i++) {
			Album alb = albs.get(i);
			Image pic = alb.getOwner().getPicture().getScaledInstance(150, 150, BufferedImage.SCALE_DEFAULT);
			ImageIcon pi = new ImageIcon(pic);
			DiscoverElement discover = new DiscoverElement(alb.getName(), pi, alb.getOwner().getUsername(), "Album");
			albums.add(discover);
		}

		extScreen.setDiscoverArtists(artists);
		extScreen.setDiscoverAlbums(albums);
		extScreen.setDiscoverSongs(songs);
		extScreen.showRecommendations();

	}

}
