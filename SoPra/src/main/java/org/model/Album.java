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
	
	

	private String coverart;
	private String Name;

	public Album(){
		super();
	}
	public Album(User u) {
		super(u);
	}

	public String getCoverart() {
		return coverart;
	}

	public void setCoverart(String coverart) {
		this.coverart = coverart;
	}
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
	
}
