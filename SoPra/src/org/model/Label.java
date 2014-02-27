package org.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Label {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private int LabelId;

	public Label() {
		// TODO Auto-generated constructor stub
	}

}
