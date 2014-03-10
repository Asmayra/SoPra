package org.model;

import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * Entity for Albums
 * @author Philipp
 *
 */
@Entity

public class Album extends Playlist {
	
	

	private String coverart = "";
	private String name = "";
	private String interpret = "";

	public Album(){
		super();
	}
	public Album(User u) {
		super(u);
	}

	public String getInterpret(){
		return interpret;
	}
	
	public void setInterpret(String i){
		interpret = i;
	}
	public String getCoverart() {
		return coverart;
	}

	public void setCoverart(String coverart) {
		this.coverart = coverart;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String n) {
		name = n;
	}
	
	
}
