package org.control;

import java.util.LinkedList;
import java.util.List;

import org.model.Album;
import org.model.Song;
import org.model.User;
import org.view.DiscoverElement;
import org.view.screens.Center.DiscoverExtendedScreen;
import org.view.screens.EastBar.DiscoverMiniScreen;

/**
 * 
 * @author Tim Lange, Tim Michels
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

		try {
			Album recAlbum = RecommendationControl.getInstance().recommendAlbum();
			DiscoverElement discover1mini = new DiscoverElement(recAlbum.getName(), LoadImageControl.loadImageIcon(
					recAlbum.getOwner().getImagePath(), recAlbum.getOwner()), recAlbum.getOwner().getUsername(), "Album");
			miniScreen.setDiscoverOne(discover1mini);
		} catch (Exception e) {
		}
		try {
			User recUser = RecommendationControl.getInstance().recommendArtist();
			DiscoverElement discover2mini = new DiscoverElement(recUser.getUsername(), LoadImageControl.loadImageIcon(
					recUser.getImagePath(), recUser), recUser.getUsername(), "User");
			miniScreen.setDiscoverTwo(discover2mini);
		} catch (Exception e) {
		}
		try {
			Song recSong = RecommendationControl.getInstance().recommendSong();
			if (recSong.getAlbum() == null) {
				User usr = (User) DatabaseControl.getInstance().load(User.class, recSong.getInterpret());
				DiscoverElement discover3mini = new DiscoverElement(recSong.getTitle(), LoadImageControl.loadImageIcon(
						usr.getImagePath(), usr), usr.getUsername(), "Song");
				miniScreen.setDiscoverThree(discover3mini);
			} else {
				User usr = (User) DatabaseControl.getInstance().load(User.class, recSong.getInterpret());
				DiscoverElement discover3mini = new DiscoverElement(recSong.getTitle(), LoadImageControl.loadImageIcon(
						recSong.getAlbum().getOwner().getImagePath(), usr), usr.getUsername(), "Song");
				miniScreen.setDiscoverThree(discover3mini);
			}
			
		} catch (Exception e) {
		}

		List<User> arts = RecommendationControl.getInstance().allRecommendedArtists();
		for (int i = 0; i < arts.size(); i++) {
			User art = arts.get(i);
			DiscoverElement discover = new DiscoverElement(art.getUsername(), LoadImageControl.loadImageIcon(art.getImagePath(),
					art), art.getUsername(), "User");
			artists.add(discover);
		}
		List<Song> sons = RecommendationControl.getInstance().allRecomendedSongs();
		for (int i = 0; i < sons.size(); i++) {
			Song son = sons.get(i);
			User artist = (User) DatabaseControl.getInstance().load(User.class, son.getInterpret());
			DiscoverElement discover = new DiscoverElement(son.getTitle(), LoadImageControl.loadImageIcon(artist.getImagePath(),
					artist), artist.getUsername(), "Song");
			songs.add(discover);
		}
		List<Album> albs = RecommendationControl.getInstance().allRecomendedAlben();
		for (int i = 0; i < albs.size(); i++) {
			Album alb = albs.get(i);
			DiscoverElement discover = new DiscoverElement(alb.getName(), LoadImageControl.loadImageIcon(alb.getOwner().getImagePath(),
					alb.getOwner()),alb.getOwner().getUsername(), "Album");
			albums.add(discover);
		}

		extScreen.setDiscoverArtists(artists);
		extScreen.setDiscoverAlbums(albums);
		extScreen.setDiscoverSongs(songs);
		extScreen.showRecommendations();

	}

}
