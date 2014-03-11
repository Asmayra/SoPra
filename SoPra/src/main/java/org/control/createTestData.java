package org.control;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.model.Album;
import org.model.Genre;
import org.model.Label;
import org.model.Message;
import org.model.Playlist;
import org.model.Post;
import org.model.Song;
import org.model.User;

public class createTestData {

	public static void main(String[] args) throws IOException{
		createTestData test = new createTestData();
		test.createGenre();
		test.createUser();
		test.createLabel();
		
		System.out.println("done");
	}
	
	public Post createPost(User usr){
		Post post = new Post();
		
		post.setAutor(usr);
		post.setMessage("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. ");
		
		return post;
	}
	public void createLabel(){
		Label label = new Label();
		label.setName("testlabel");
		
		
		
		User usrl = (User) DatabaseControl.getInstance().load(User.class, "l");
		User usrk = (User) DatabaseControl.getInstance().load(User.class, "k");
		
		label.setManager(usrl);
		label.addArtist(usrk);
		
		usrk.setLabel(label);
		usrl.setLabel(label);
		
		try {
			DatabaseControl.getInstance().save(label);
			DatabaseControl.getInstance().update(usrk);
			DatabaseControl.getInstance().update(usrl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	
	public void createGenre() throws IOException{
		Genre genre1 = new Genre();
		genre1.setName("Rock");
		DatabaseControl.getInstance().save(genre1);
		
		Genre genre2 = new Genre();
		genre2.setName("Rock with lasers");
		DatabaseControl.getInstance().save(genre2);
		
		Genre genre3 = new Genre();
		genre3.setName("Rock with guitar");
		DatabaseControl.getInstance().save(genre3);
		
		genre1.addSubGenre(genre2);
		genre1.addSubGenre(genre3);
		DatabaseControl.getInstance().update(genre1);
	}
	
	public Playlist createPlaylist(User user){
		Playlist playlist = new Playlist(user);
		playlist.addSong(this.createSong());
		playlist.addSong(this.createSong());
		playlist.setName("Playlist");
		
		try {
			DatabaseControl.getInstance().save(playlist);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		return playlist;
	}
	
	public Album createAlbum(User user){
		Album album = new Album(user);
		album.setCoverart("cover.jpg");
		album.setName("album");
		album.addSong(this.createSong());
		album.addSong(this.createSong());
		
		try {
			DatabaseControl.getInstance().save(album);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return album;
	}
	
	
	public Song createSong(){
		Song song = new Song("k", "title", "");
		
		try {
			DatabaseControl.getInstance().save(song);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return song;
	}
	
	public Message createMessage(User usr){
		Message message = new Message();
		message.setContent("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. ");		
		message.setDate("01.01.2000");
		message.setSender(usr);
		message.setSubject("Lorem ipsum");
		
		try {
			DatabaseControl.getInstance().save(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}
	
	public User createSingleUser(String username, String email, String city, String country, String dateString, String firstname, String lastname, String imagePath, String password, String rights){
		User usr = new User();
		usr.setUsername(username);
		usr.setEMail(email);
		usr.setCity(city);
		usr.setCountry(country);
		Date date;
		try {
			date = new SimpleDateFormat("DD.MM.YYYY", Locale.GERMAN).parse(dateString);
		} catch (ParseException e) {
			date = null;
			e.printStackTrace();
		}
		usr.setDob(date);
		usr.setFirstname(firstname);
		usr.setLastname(lastname);
		usr.setImagePath(imagePath);
		usr.setRights(rights);
		
		usr.setSalt(PasswordControl.generateSalt());
		usr.setPassword(PasswordControl.encodePassword(password, usr.getSalt()));
		
		
		try {
			DatabaseControl.getInstance().save(usr);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return usr;
	}
	
	public void createUser(){
		User standard = createSingleUser("q", "MaxStandard@gmail.com", "Münster", "Deutschland", "10.01.1989", "Max", "Standard", "placeholder.jpg", "q", "StandardUser");
			
		standard.addPlaylist(createPlaylist(standard));
		standard.addPlaylist(createPlaylist(standard));
		standard.addAlben(createAlbum(standard));
		standard.addPosts(createPost(standard));
		
		DatabaseControl.getInstance().update(standard);

		
		/////////////////////////////////////////////////////
	
		
		User admin = createSingleUser("a", "BillAdmin@gmail.com", "Berlin", "Deutschland", "20.04.1971", "Bill", "Admin", "placeholder.jpg", "a", "Admin");
		
		
		////////////////////////////////////////////////////////
		
		User artist = createSingleUser("k", "JackArtist@gmail.com", "Moskau", "Russland", "14.10.1993", "Jack", "Artist", "placeholder.jpg", "k", "Artist");
		
		
		artist.addOwnSong(this.createSong());
		artist.addOwnSong(this.createSong());
		artist.addFavorite(this.createSong());
		artist.addFavorite(this.createSong());
		artist.addPlaylists(this.createPlaylist(artist));
		artist.addAlben(this.createAlbum(artist));
		DatabaseControl.getInstance().update(artist);
		
		artist.addMessage(createMessage(standard));
		DatabaseControl.getInstance().update(artist);
		
		artist.addMessage(createMessage(standard));
		DatabaseControl.getInstance().update(artist);
		///////////////////////////////////////////////////////////
		
		User labelmanager = createSingleUser("l", "JustinLabel@gmail.com", "New York", "USA", "23.06.1956", "Justin", "Label", "placeholder.jpg", "l", "LabelManager");

		labelmanager.addMessage(createMessage(artist));
		
		DatabaseControl.getInstance().update(labelmanager);
		
		////////////////////////////////////////////////
		
		User artist1 = createSingleUser("k2", "SamanthaArtist@gmail.com", "Tokio", "Japan", "27.12.2008", "Samantha", "Artist", "placeholder.jpg", "k2", "Artist");
	
		
		artist1.addOwnSong(this.createSong());
		artist1.addOwnSong(this.createSong());
		artist1.addFavorite(this.createSong());
		artist1.addFavorite(this.createSong());
		artist1.addPlaylists(this.createPlaylist(artist1));
		artist1.addAlben(this.createAlbum(artist1));
		artist1.addMessage(createMessage(artist));
		DatabaseControl.getInstance().update(artist1);
		
	}
	
}
