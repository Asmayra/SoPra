package org.model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.control.LoadImageControl;

/**
 * User Entity
 * 
 * @author Philipp, Michael Pfennings, Mattias Schoenke, Sebastian Roth
 * @inv username must be individual and not null
 */
@Entity
@Table(name = "USER_TABLE")
@org.hibernate.annotations.Entity(dynamicInsert = true)
public class User implements Comparable {
	@Id
	private String username;
	private String password = "";
	private String firstname = "";
	private String lastname = "";
	private Date dob;
	private String city = "";
	private String country = "";
	private String eMail = "";
	private String salt = "";
	private String rights = "StandardUser"; // Admin, Artist, LabelManager
	private String imagePath = "";
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "FOLLOW_TABLE")
	private Set<User> following = new HashSet<User>();
	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Set<Playlist> playlists = new HashSet<Playlist>();
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Album> alben = new HashSet<Album>();
	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinTable(name = "IGNORE_TABLE")
	private Set<User> ignoring = new HashSet<User>();
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	// Set Eager,because it's loaded at the Homescreen
	private Set<Post> posts = new HashSet<Post>();
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Set<Message> messages = new HashSet<Message>();
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Song> ownSongs = new HashSet<Song>();
	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Label Label;

	private boolean banned = false;

	public Label getLabel() {
		return Label;
	}

	public void setLabel(Label Label) {
		this.Label = Label;
	}

	public User() {
		System.out.println("Schweinebraten!");
	}

	/**
	 * creates the favorites-playlist of the user
	 */
	public void createFavorites() {
		Playlist favorites = new Playlist(this);
		favorites.setFavorite();
		playlists.add(favorites);
	}

	public List<User> getFollowing() {
		return new ArrayList<User>(this.following);
	}

	public void setFollowing(List<User> following) {
		this.following = new HashSet<User>(following);
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

		while (it.hasNext()) {
			if (it.next().getMessageId() == message.getMessageId()) {
				it.remove();
			}
		}
	}

	/**
	 * adds a song to the own songs of the user
	 * 
	 * @param s
	 *            added song
	 */
	public void addOwnSong(Song s) {
		ownSongs.add(s);
	}

	/**
	 * removes a song from the own songs of the user
	 * 
	 * @param s
	 *            removed song
	 */
	public void removeOwnSong(Song s) {
		ownSongs.remove(s);
	}

	public List<Song> getOwnSongs() {
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

	public BufferedImage getPicture() {
		System.out.println("LOADING IMAGE FROM " + imagePath);
		return LoadImageControl.loadBufferedImage(this.imagePath, this);
	}

	/**
	 * method evaluates the age from the users date of birth
	 * 
	 * @return string representation of users age
	 */
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
		return age.toString();
	}

	/**
	 * checks if the user is following another user
	 * 
	 * @param usr
	 *            user who would be followed
	 * @return true for following and false for not following
	 */
	public boolean isFollowing(User usr) {
		java.util.Iterator<User> it = following.iterator();
		while (it.hasNext()) {
			if (it.next().getUsername().equals(usr.getUsername())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * checks if the user is ignoring another user
	 * 
	 * @param usr
	 *            user who would be ignored
	 * @return true for ignoring and false for not ignoring
	 */
	public boolean isIgnoring(User usr) {
		java.util.Iterator<User> it = ignoring.iterator();
		while (it.hasNext()) {
			if (it.next().getUsername().equals(usr.getUsername())) {
				return true;
			}
		}
		return false;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	/**
	 * removes a user from the list of followed users
	 * 
	 * @param usr
	 *            removed user
	 */
	public void unfollow(User usr) {
		java.util.Iterator<User> it = following.iterator();
		while (it.hasNext()) {
			if (it.next().getUsername().equals(usr.getUsername())) {
				it.remove();
			}
		}
	}

	/**
	 * adds a user to the list of followed users
	 * 
	 * @param usr
	 *            added user
	 */
	public void follow(User usr) {
		following.add(usr);
	}

	/**
	 * removes a user from the list of ignored users
	 * 
	 * @param usr
	 *            removed user
	 */
	public void unignore(User usr) {
		java.util.Iterator<User> it = ignoring.iterator();
		while (it.hasNext()) {
			if (it.next().getUsername().equals(usr.getUsername())) {
				it.remove();
			}
		}
	}

	/**
	 * adds a user to the list of ignored users
	 * 
	 * @param usr
	 *            added user
	 */
	public void ignore(User user) {
		ignoring.add(user);

	}

	/**
	 * returns the favorites-playlist of a user
	 * 
	 * @return the favorites-playlist
	 */
	public Playlist getFavorites() {
		java.util.Iterator<Playlist> it = playlists.iterator();
		while (it.hasNext()) {
			Playlist next = it.next();
			if (next.getName().equals("Favorites")) {
				return next;
			}
		}
		Playlist playlist = new Playlist(this);
		playlist.setFavorite();
		this.addPlaylist(playlist);
		return playlist;

	}

	public List<Playlist> getPlaylists() {
		return new ArrayList<Playlist>(this.playlists);
	}

	public List<Album> getAlben() {
		return new ArrayList<Album>(this.alben);
	}

	public String getEMail() {
		return eMail;
	}

	public void setEMail(String eMail) {
		this.eMail = eMail;
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void addFavorite(Song s) {
		this.getFavorites().addSong(s);

	}

	public void removeFavorite(Song s) {
		this.getFavorites().deleteSong(s);

	}

	public void addPlaylist(Playlist p) {
		playlists.add(p);
	}

	public void setBanned(boolean b) {
		banned = b;
	}

	public boolean getBanned() {
		return banned;
	}

	public void removePlaylist(Playlist p) {
		playlists.remove(p);

	}

	public void addPlaylists(Playlist p) {
		this.playlists.add(p);
	}

	public void addAlben(Album a) {
		this.alben.add(a);
	}

	public void removeAlbum(Album a) {
		this.alben.remove(a);

	}

}
