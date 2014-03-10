package org.testpackage;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.control.DatabaseControl;
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
		post.setMessage("jsjhsjsjsk");
		
		return post;
	}
	public void createLabel(){
		Label label = new Label();
		label.setName("testlabel");
		
		User usrk = new User();
		User usrl = new User();
		
		usrk.setUsername("k");
		usrl.setUsername("l");
		
		try {
			DatabaseControl.getInstance().save(usrk);
			DatabaseControl.getInstance().save(usrl);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		usrl = null;
		usrk = null;
		
		usrl = (User) DatabaseControl.getInstance().load(User.class, "l");
		usrk = (User) DatabaseControl.getInstance().load(User.class, "k");
		
		label.addArtist(usrk);
		label.addManager(usrl);
		
		usrk.getaLabels().add(label);
		usrl.setmLabel(label);
		
		try {
			DatabaseControl.getInstance().save(label);
			DatabaseControl.getInstance().update(usrk);
			DatabaseControl.getInstance().update(usrl);
		} catch (IOException e) {
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
		genre2.setParent(genre1);
		DatabaseControl.getInstance().save(genre2);
		
		Genre genre3 = new Genre();
		genre3.setName("Rock with guitar");
		genre3.setParent(genre1);
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
		Song song = new Song("interpret", "title", "");
		
		try {
			DatabaseControl.getInstance().save(song);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return song;
	}
	
	public Message createMessage(User usr, User usr2){
		Message message = new Message();
		message.setContent("balalalsjkdsklsjdslfdskl");		
		message.setDate("01.01.2000");
		message.setSender(usr);
		message.setSubject("testsubject");
		//message.addRecipient(usr2);
		
		try {
			DatabaseControl.getInstance().save(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return message;
	}
	
	public User createSingleUser(String username){
		User standard = new User();
		standard.setUsername(username);
		standard.setEMail("muster@gmail.com");
		standard.setCity("mustercity");
		standard.setCountry("mustercountry");
		Date date;
		try {
			date = new SimpleDateFormat("DD.MM.YYYY", Locale.GERMAN).parse("01.01.2000");
		} catch (ParseException e) {
			date = null;
			e.printStackTrace();
		}
		standard.setDob(date);
		standard.setFirstname("Max");
		standard.setLastname("mustermann");
		standard.setImagePath("avatar.jpg");
		standard.setPassword("127648922");
		standard.setSalt("B150M)gWN!");
		standard.setRights("StandardUser");
		
		try {
			DatabaseControl.getInstance().save(standard);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return standard;
	}
	
	public void createUser(){
		User standard = new User();
		standard.setUsername("q");
		standard.setEMail("muster@gmail.com");
		standard.setCity("mustercity");
		standard.setCountry("mustercountry");
		Date date;
		try {
			date = new SimpleDateFormat("DD.MM.YYYY", Locale.GERMAN).parse("01.01.2000");
		} catch (ParseException e) {
			date = null;
			e.printStackTrace();
		}
		standard.setDob(date);
		standard.setFirstname("Max");
		standard.setLastname("mustermann");
		standard.setImagePath("avatar.jpg");
		standard.setPassword("127648922");
		standard.setSalt("B150M)gWN!");
		standard.setRights("StandardUser");
				
		try {
			DatabaseControl.getInstance().save(standard);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		standard.addMessage(createMessage(standard, createSingleUser("q2")));
		DatabaseControl.getInstance().update(standard);
		
		standard.addPlaylist(createPlaylist(standard));
		standard.addPlaylist(createPlaylist(standard));
		standard.addAlben(createAlbum(standard));
		standard.addPosts(createPost(standard));
		
		DatabaseControl.getInstance().update(standard);

		
		/////////////////////////////////////////////////////
	
		
		User admin = new User();
		admin.setUsername("a");
		admin.setEMail("muster@gmail.com");
		admin.setCity("mustercity");
		admin.setCountry("mustercountry");

		try {
			date = new SimpleDateFormat("DD.MM.YYYY", Locale.GERMAN).parse("01.01.2000");
		} catch (ParseException e) {
			date = null;
			e.printStackTrace();
		}
		admin.setDob(date);
		admin.setFirstname("Max");
		admin.setLastname("mustermann");
		admin.setImagePath("avatar.jpg");
		admin.setPassword("127648922");
		admin.setSalt("B150M)gWN!");
		admin.setRights("Admin");
		
		try {
			DatabaseControl.getInstance().save(admin);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		////////////////////////////////////////////////////////
		
		User artist = new User();
		artist.setUsername("k");
		artist.setEMail("muster@gmail.com");
		artist.setCity("mustercity");
		artist.setCountry("mustercountry");

		try {
			date = new SimpleDateFormat("DD.MM.YYYY", Locale.GERMAN).parse("01.01.2000");
		} catch (ParseException e) {
			date = null;
			e.printStackTrace();
		}
		artist.setDob(date);
		artist.setFirstname("Max");
		artist.setLastname("mustermann");
		artist.setImagePath("avatar.jpg");
		artist.setPassword("127648922");
		artist.setSalt("B150M)gWN!");
		artist.setRights("Artist");

		try {
			DatabaseControl.getInstance().save(artist);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		artist.addOwnSong(this.createSong());
		artist.addOwnSong(this.createSong());
		artist.addFavorite(this.createSong());
		artist.addFavorite(this.createSong());
		artist.addPlaylists(this.createPlaylist(artist));
		artist.addAlben(this.createAlbum(artist));
		
		artist.addMessage(createMessage(artist, standard));
		DatabaseControl.getInstance().update(artist);
		
		artist.addMessage(createMessage(artist, admin));
		DatabaseControl.getInstance().update(artist);
		
		///////////////////////////////////////////////////////////
		
		User labelmanager = new User();
		labelmanager.setUsername("l");
		labelmanager.setEMail("muster@gmail.com");
		labelmanager.setCity("mustercity");
		labelmanager.setCountry("mustercountry");

		try {
			date = new SimpleDateFormat("DD.MM.YYYY", Locale.GERMAN).parse("01.01.2000");
		} catch (ParseException e) {
			date = null;
			e.printStackTrace();
		}
		labelmanager.setDob(date);
		labelmanager.setFirstname("Max");
		labelmanager.setLastname("mustermann");
		labelmanager.setImagePath("avatar.jpg");
		labelmanager.setPassword("127648922");
		labelmanager.setSalt("B150M)gWN!");
		labelmanager.setRights("LabelManager");
		
		try {
			

			DatabaseControl.getInstance().save(labelmanager);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		labelmanager.addMessage(createMessage(labelmanager, artist));
		
		DatabaseControl.getInstance().update(labelmanager);
		
		////////////////////////////////////////////////
		
		User artist1 = new User();
		artist1.setUsername("k2");
		artist1.setEMail("muster@gmail.com");
		artist1.setCity("mustercity");
		artist1.setCountry("mustercountry");

		try {
			date = new SimpleDateFormat("DD.MM.YYYY", Locale.GERMAN).parse("01.01.2000");
		} catch (ParseException e) {
			date = null;
			e.printStackTrace();
		}
		artist1.setDob(date);
		artist1.setFirstname("Max");
		artist1.setLastname("mustermann");
		artist1.setImagePath("avatar.jpg");
		artist1.setPassword("127648922");
		artist1.setSalt("B150M)gWN!");
		artist1.setRights("Artist");
		
		try {
			DatabaseControl.getInstance().save(artist1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		
		artist1.addOwnSong(this.createSong());
		artist1.addOwnSong(this.createSong());
		artist1.addFavorite(this.createSong());
		artist1.addFavorite(this.createSong());
		artist1.addPlaylists(this.createPlaylist(artist1));
		artist1.addAlben(this.createAlbum(artist1));
		artist1.addMessage(createMessage(artist1, artist));
		DatabaseControl.getInstance().update(artist1);
		
	}
	
}
