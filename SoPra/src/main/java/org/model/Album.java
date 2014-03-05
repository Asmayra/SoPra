package org.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
public class Album extends Playlist {
	
	private String coverart;

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

}
