package org.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
public class Album extends Playlist {
	
	private String coverart;

	public Album() {
		// TODO Auto-generated constructor stub
	}

	public String getCoverart() {
		return coverart;
	}

	public void setCoverart(String coverart) {
		this.coverart = coverart;
	}

}