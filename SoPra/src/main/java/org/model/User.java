package org.model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="USER_TABLE")
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
	private String rights ="StandardUser";	
	@OneToMany
	private Collection<Post> posts;



	public Collection<Post> getPosts() {
		return posts;
	}
	/**
	 * Adds a Post to the User and sets him as the Autor
	 * @param pst
	 */
	public void addPosts(Post pst) {
		this.posts.add(pst);
		pst.setAutor(this);
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
	public BufferedImage getPicture()  throws IOException {
		//Test-Rumpf
		return ImageIO.read(new File("C:/Dropbox/SoPra/SoPraMusic/Dummy-Resources/dummy-avatar.png"));
	}

	

}
