package org.model;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;









import java.util.Set;
import java.util.TreeSet;

import javax.imageio.ImageIO;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.control.DatabaseControl;
import org.control.LoadImageControl;
import org.hibernate.Session;

@Entity
@Table(name = "USER_TABLE")
public class User {
	@Id
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private Date dob;
	private String city;
	private String country;
	private String eMail;
	private String salt;
	private String rights = "StandardUser"; // Admin, Artist, LabelManager
	private String imagePath;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<User> following;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Playlist> playlists;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<User> ignoring;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	// Set Eager,because it's loaded at the Homescreen
	private Set<Post> posts = new TreeSet<Post>();
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Message> messages = new TreeSet<Message>();
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Song> ownSongs;
	
	public User(){	
	}
	
	public void createFavorites(){
		try{
			Playlist favorites = new Playlist(this);
			favorites.setName("Favorites");
			playlists.add(favorites);
		} catch (IllegalArgumentException e){
			e.printStackTrace();
		}
	}
	
	public List<User> getFollowing() {
		return new ArrayList<User>(this.following);
	}


	public void setFollowing(List<User> following) {
		this.following = new TreeSet<User>(following);
	}


	public Collection<Post> getPosts() {
		return posts;
	}
	
	public Collection<Message> getMessages() {
		return messages;
	}
	
	
	public void addMessage(Message message) {
		this.messages.add(message);
	}
	
	public void removeMessage(Message message) {
		java.util.Iterator<Message> it = messages.iterator();
		
		while (it.hasNext() ){
			if (it.next().getMessageId() == message.getMessageId()){
				it.remove();
			}
		}
	}
	
	public void addOwnSong(Song s){
		ownSongs.add(s);
	}
	
	public List<Song> getOwnSongs(){
		return new ArrayList<Song>(this.ownSongs);
	}
	
	/**
	 * Adds a Post to the User and sets him as the Autor
	 * 
	 * @param pst
	 */
	public void addPosts(Post pst) {
		pst.setAutor(this);
		this.posts.add(pst);
		
	}


	public String getRights() {
		return rights;
	}

	public void setRights(String rights) {
		this.rights = rights;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public BufferedImage getPicture() throws IOException {
		if(this.imagePath == null || this.imagePath == ""){
			return LoadImageControl.loadBufferedImage("placeholder.jpg");
		} else{
			return LoadImageControl.loadBufferedImage(this.imagePath);
		}
	}

	public String getAge() {
		Calendar birth = Calendar.getInstance();
		Calendar now = Calendar.getInstance();
		birth.setTime(dob);
		Integer age = (now.get(Calendar.YEAR) - birth.get(Calendar.YEAR));
		if (birth.get(Calendar.MONTH) < now.get(Calendar.MONTH)) {
			age++;
		} else if (birth.get(Calendar.MONTH) == now.get(Calendar.MONTH)
				&& birth.get(Calendar.DAY_OF_MONTH) < now.get(Calendar.DAY_OF_MONTH)) {
			age++;
		}
		System.out.println(birth.get(Calendar.YEAR));
		System.out.println(now.get(Calendar.YEAR));
		return age.toString();
	}

	public boolean isFollowing(User usr) {
		if (following.contains(usr)) {
			return true;
		}
		return false;
	}

	public boolean isIgnoring(User user) {
		
		if (ignoring.contains(user)) {
			return true;
		}
		return false;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public void unfollow(User usr) {
		following.remove(usr);	
	}

	public void follow(User usr) {
		following.add(usr);	
	}
	
	public void unignore(User user) {
		ignoring.remove(user);
		
	}

	public void ignore(User user) {
		ignoring.add(user);
		
	}

	public Playlist getFavorites() {
		List<Playlist> list = new ArrayList<Playlist>(this.playlists);
		for (int i=0;i<list.size();i++){
			if (list.get(i).getName()=="Favorites"){
				return list.get(i);
			}
		}
		//null
		return list.get(0);
	}


	public List<Playlist> getPlaylists() {
		return new ArrayList<Playlist>(this.playlists);
	}


	public String getEMail() {
		return eMail;
	}


	public void setEMail(String eMail) {
		this.eMail = eMail;
	}

}
