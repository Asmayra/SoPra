package org.model;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Entity for News like "New Playlist created"
 * @author Philipp
 *
 */
@Entity
@Table(name="POST_TABLE")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private long postId;
	@ManyToOne
	private User autor;
	@Transient
	private BufferedImage prflPicture;
	public long getPostId() {
		return postId;
	}
	public void setPostId(long postId) {
		this.postId = postId;
	}
	public User getAutor() {
		return autor;
	}
	public void setAutor(User autor) {
		this.autor = autor;
	}
	
	public JLabel create(){
		JLabel pst = new JLabel();
		try {
			prflPicture = this.getAutor().getPicture();
			prflPicture.getScaledInstance(50,50,BufferedImage.SCALE_DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageIcon pic =new ImageIcon(prflPicture);
		JLabel autorPic = new JLabel(pic);
		autorPic.setSize(50,50);		
		pst.add(autorPic);
		return pst;
	}
	

}
