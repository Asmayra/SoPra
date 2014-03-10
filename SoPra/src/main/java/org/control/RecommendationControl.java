package org.control;

import java.util.List;

import org.model.Album;
import org.model.Playlist;
import org.model.Song;
import org.model.User;

public class RecommendationControl {

	private User user = LoginControl.getInstance().getCurrentUser();
	private static RecommendationControl recControl;

	public static RecommendationControl getInstance() {
		if (recControl == null) {
			recControl = new RecommendationControl();
		}
		return recControl;
	};

	public RecommendationControl() {
	}

	public Album recommendAlbum() throws Exception {
		List<User> following = user.getFollowing();
		int random2 = 0;
		while (random2 == 0) {
			if (following.size() == 0) {
				break;
			}
			int random1 = (int) ((Math.random()) * following.size() + 1);

			List<Album> alben = following.get(random1).getAlben();
			if (alben.size() == 0) {
				continue;
			}
			random2 = (int) ((Math.random()) * alben.size() + 1);
			return alben.get(random2);
		}
		throw new Exception();
	}

	public Playlist recommendPlaylist() throws Exception {
		List<User> following = user.getFollowing();
		int random2 = 0;
		while (random2 == 0) {
			if (following.size() == 0) {
				break;
			}
			int random1 = (int) ((Math.random()) * following.size() + 1);

			List<Playlist> playlists = following.get(random1).getPlaylists();
			if (playlists.size() == 0) {
				continue;
			}
			random2 = (int) ((Math.random()) * playlists.size() + 1);
			return playlists.get(random2);
		}
		throw new Exception();
	}

	public Song recommendSong() throws Exception {
		List<User> following = user.getFollowing();
		int random2 = 0;
		while (random2 == 0) {
			if (following.size() == 0) {
				break;
			}
			int random1 = (int) ((Math.random()) * following.size() + 1);
			List<Song> songs = following.get(random1).getOwnSongs();
			if (songs.size() == 0) {
				continue;
			}
			random2 = (int) ((Math.random()) * songs.size() + 1);
			return songs.get(random2);
		}
		throw new Exception();
	}

	public User recommendArtist() throws Exception {
		List<User> following = user.getFollowing();
		int random2 = 0;
		while (random2 == 0) {
			if (following.size() == 0) {
				break;
			}
			int random1 = (int) ((Math.random()) * following.size() + 1);
			List<User> followedFollower = following.get(random1).getFollowing();
			if (followedFollower.size() == 0) {
				continue;
			}
			random2 = (int) ((Math.random()) * followedFollower.size() + 1);
			if (user.getFollowing().contains(followedFollower.get(random2))) {
				random2 = 0;
				continue;
			}
			return followedFollower.get(random2);
		}
		throw new Exception();

	}

}