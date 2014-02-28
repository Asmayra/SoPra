package org.model;

import java.awt.Dimension;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
		pst.setText(autor.getUsername()+" created this fancy post.");
		pst.setPreferredSize(new Dimension(150,30));
		return pst;
	}
	

}
