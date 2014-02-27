package org.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Class to save Autogenerated Ids
 * @author Philipp
 *
 */
@Entity
public class IDPool {
	@Id
	private static final int IDPOOL_ID=1;

}
