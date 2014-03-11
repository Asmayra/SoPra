package org.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.model.Album;
import org.model.Playlist;
import org.model.Song;
import org.model.User;

/**
 * class provides methods to create recommendations
 * 
 * @author Sebastian Roth
 * 
 */
public class RecommendationControl {

	private User user;
	private static RecommendationControl recControl;

	/**
	 * @return
	 * 
	 */
	public static RecommendationControl getInstance() {
		if (recControl == null) {
			recControl = new RecommendationControl();
		}
		return recControl;
	}
	
	/**
	 * zerstört den SingleTon
	 */
	public static void destroy()
	{
		recControl = null;
	}

	private RecommendationControl() {
		user = LoginControl.getInstance().getCurrentUser();
	}

	public Album recommendAlbum() throws Exception {
		List<User> following = user.getFollowing();
		int random1 = 0;
		int random2 = 0;
		int saveCount = 0;
		Random rand = new Random();
		while (random2 == 0 && saveCount < 11) {
			if (following.size() == 0) {
				break;
			}
			random1 = rand.nextInt(following.size());
			List<Album> alben = following.get(random1).getAlben();
			if (alben.size() == 0) {
				saveCount++;
				continue;
			}
			random2 = rand.nextInt(alben.size());
			return alben.get(random2);
		}
		throw new Exception();
	}

	public Song recommendSong() throws Exception {
		List<User> following = user.getFollowing();
		Random rand = new Random();
		int saveCount = 0;
		int random1 = 0;
		int random2 = 0;
		while (random2 == 0 && saveCount < 11) {
			if (following.size() == 0) {
				break;
			}
			random1 = rand.nextInt(following.size());
			List<Song> songs = following.get(random1).getOwnSongs();
			if (songs.size() == 0) {
				saveCount++;
				continue;
			}
			random2 = rand.nextInt(songs.size());
			return songs.get(random2);
		}
		throw new Exception();
	}

	public User recommendArtist() throws Exception {
		List<User> following = user.getFollowing();
		int saveCount = 0;
		int random1 = 0;
		int random2 = 0;
		Random rand = new Random();
		while (random2 == 0 && saveCount < 20) {
			if (following.size() == 0) {
				break;
			}
			random1 = rand.nextInt(following.size());
			List<User> followedFollower = following.get(random1).getFollowing();
			if (followedFollower.size() == 0) {
				saveCount++;
				continue;
			}
			random2 = rand.nextInt(followedFollower.size());
			if (user.getFollowing().contains(followedFollower.get(random2))) {
				random2 = 0;
				saveCount++;
				continue;
			}
			return followedFollower.get(random2);
		}
		throw new Exception();

	}

	public List<User> allRecommendedArtists() {
		List<User> recUser = new ArrayList<User>();
		List<User> following = user.getFollowing();
		for (int i = 0; i < following.size(); i++) {
			List<User> followedFollower = following.get(i).getFollowing();
			recUser.addAll(followedFollower);
		}
		return recUser;
	}

	public List<Album> allRecomendedAlben() {
		List<Album> recAlbum = new ArrayList<Album>();
		List<User> following = user.getFollowing();
		for (int i = 0; i < following.size(); i++) {
			List<Album> followedAlben = following.get(i).getAlben();
			recAlbum.addAll(followedAlben);
		}
		return recAlbum;
	}

	public List<Song> allRecomendedSongs() {
		List<Song> recSongs = new ArrayList<Song>();
		List<User> following = user.getFollowing();
		for (int i = 0; i < following.size(); i++) {
			List<Song> followedSongs = following.get(i).getOwnSongs();
			recSongs.addAll(followedSongs);
		}
		return recSongs;
	}

}
