package org.control;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.model.Label;
import org.model.Song;
import org.model.User;
import org.view.DiscoverElement;
import org.view.MainScreen;
import org.view.screens.Center.SearchResultScreen;
import org.view.screens.EastBar.EastBar;
import org.view.screens.EastBar.SearchMask;

public class SearchControl {

	
	/**
	 * generate the results to be shown
	 * @param searchKeyword String to search for
	 * @return List with results
	 */
	public void generateResults(String searchKeyword){	
		ArrayList<User> userResults =(ArrayList<User>) DatabaseControl.getInstance().queryForKeyword(User.class, "username", searchKeyword);
		ArrayList<Song> songResults =(ArrayList<Song>) DatabaseControl.getInstance().queryForKeyword(Song.class, "title", searchKeyword);
		ArrayList<Label> labelResults =(ArrayList<Label>) DatabaseControl.getInstance().queryForKeyword(Label.class, "name", searchKeyword);
		int userNum=userResults.size();
		int songNum=songResults.size();
		int labelNum=labelResults.size();
		LinkedList<DiscoverElement> user = new LinkedList<DiscoverElement>();
		LinkedList<DiscoverElement> labels = new LinkedList<DiscoverElement>();
		LinkedList<DiscoverElement> songs = new LinkedList<DiscoverElement>();
		for(int i=0;i<userNum;i++){
			User current = userResults.get(i);
			DiscoverElement curUser = new DiscoverElement(current.getUsername(),LoadImageControl.loadImageIcon(
					current.getImagePath(), current),current.getUsername(),"User");
			user.add(curUser);
		}
		for(int i=0;i<labelNum;i++){
			Label current = labelResults.get(i);
			DiscoverElement curLabel = new DiscoverElement(current.getName(),LoadImageControl.loadImageIcon(
					current.getManager().getImagePath(), current.getManager()),""+current.getId(),"Label");
			user.add(curLabel);
		}
		for(int i=0;i<songNum;i++){
			Song recSong = songResults.get(i);
			User usr = (User) DatabaseControl.getInstance().load(User.class, recSong.getInterpret());
			DiscoverElement discover3mini = new DiscoverElement(recSong.getTitle(), LoadImageControl.loadImageIcon(
					usr.getImagePath(), usr), usr.getUsername(), "Song");
			songs.add(discover3mini);
		}
		
		SearchResultScreen screen = SearchResultScreen.getInstance();
		screen.setDiscoverArtists(user);
		screen.setDiscoverSongs(songs);
		screen.setDiscoverLabels(labels);
		screen.showRecommendations();
		
		MainScreen.getInstance().updateCenter(screen);
		
	}
}
