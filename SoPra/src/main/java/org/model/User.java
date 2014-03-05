package org.model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;







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

import org.control.DatabaseController;
import org.control.LoadImageController;
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
	private String salt;
	private String rights = "StandardUser"; // Admin, Artist, LabelManager
	private String imagePath;
	@ManyToMany
	private List<User> following;
	@ManyToMany
	private List<User> ignoring;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	// Set Eager,because it's loaded at the Homescreen
	private Collection<Post> posts = new LinkedList<Post>();
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Collection<Message> messages = new LinkedList<Message>();
	
	public User(){
	}
	
	
	public Collection<Post> getPosts() {
		return posts;
	}
	
	public Collection<Message> getMessages() {
		return messages;
	}
	
	
	public void addMessage(Message message) {
		message.setSender(this);
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
			return LoadImageController.loadBufferedImage("placeholder.jpg");
		} else{
			return LoadImageController.loadBufferedImage(this.imagePath);
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
		Session session = DatabaseController.getInstance().getSessionFactory().openSession();
		session.beginTransaction();
		User obj = (User)session.get(User.class, this.getUsername());
		if (obj.following.contains(usr)) {
			DatabaseController.getInstance().update(obj);
			session.getTransaction().commit();
			session.close();
			return true;
		}
		session.getTransaction().commit();
		session.close();
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
		Session session = DatabaseController.getInstance().getSessionFactory().openSession();
		session.beginTransaction();
		User obj = (User)session.get(User.class, this.getUsername());
		obj.following.remove(usr);
		DatabaseController.getInstance().update(obj);
		session.getTransaction().commit();
		session.close();
		
	}

	public void follow(User usr) {
		Session session = DatabaseController.getInstance().getSessionFactory().openSession();
		session.beginTransaction();
		User obj = (User)session.get(User.class, this.getUsername());
		obj.following.add(usr);
		DatabaseController.getInstance().update(obj);
		session.getTransaction().commit();
		session.close();
		
	}
	
	public void unignore(User user) {
		ignoring.remove(user);
		
	}

	public void ignore(User user) {
		ignoring.add(user);
		
	}

}
