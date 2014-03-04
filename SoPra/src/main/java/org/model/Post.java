package org.model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
	@Lob
	private String message;
	@Transient
	private Image prflPicture;
	@Transient
	private Image prflPic;
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
	
	public void setMessage(String s){
		this.message=s;
	}
	public String getMessage(){
		return this.message;
	}
	
	public JLabel create(){
		JLabel pst = new JLabel();
		pst.setLayout(new FlowLayout());
		//Scale the Autor's Prifeile Picture
		try {
			prflPicture = this.getAutor().getPicture();
			prflPic= prflPicture.getScaledInstance(50, -1, BufferedImage.SCALE_DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageIcon pic =new ImageIcon(prflPic);
		JLabel autorPic = new JLabel(pic);
		autorPic.setSize(50,50);		
		pst.add(autorPic);
		
		JPanel content = new JPanel();
		content.setLayout(new BoxLayout(content,BoxLayout.Y_AXIS));
				
		JLabel info = new JLabel(" "+autor.getFirstname()+" has great news!");
		info.setFont(new Font("Arial",Font.BOLD,14));	
		JLabel text = new JLabel("<html><body>"+message+"</body></html>");
		content.add(info);
		content.add(text);
		content.setPreferredSize(new Dimension(330,100));
		pst.add(content);

		return pst;
	}
	

}
