package org.control;

import java.io.IOException;
import java.util.Collection;

import org.model.Label;
import org.model.User;

public class LabelControl {
	
	
	
	public void createLabel(String name, Collection<User> managers, Collection<User> artists)
	{
		Label label = new Label();
		label.setName(name);
		
		for( User m : managers )
			label.addManager(m);
		
		for( User a : artists )
			label.addArtist(a);
		
		try {
			DatabaseControl.getInstance().save(label);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public boolean addArtistToLabel(User manager, Label label, User artist)
	{
		if( manager.getRights().equals("LabelManager")  &&  label.isManager(manager)  &&
				artist.getRights().equals("Artist")  &&  !label.isArtist(artist) )
		{
			label.addArtist(artist);
			DatabaseControl.getInstance().update(label);
			return true;
		}
		else
			return false;
	}
	
	public boolean remArtistToLabel(User manager, Label label, User artist)
	{
		if( manager.getRights().equals("LabelManager")  &&  label.isManager(manager)  &&
				artist.getRights().equals("Artist")  &&  label.isArtist(artist) )
		{
			label.removeArtist(artist);
			DatabaseControl.getInstance().update(label);
			return true;
		}
		
		return false;
	}
	
	public boolean addManagerToLabel(User managerAdding, Label label, User managerToAdd)
	{
		if( managerAdding.getRights().equals("LabelManager")  &&  label.isManager(managerAdding)  &&
				managerToAdd.getRights().equals("LabelManager")  &&  !label.isManager(managerToAdd))
		{
			label.addManager(managerToAdd);
			DatabaseControl.getInstance().update(label);
			return true;
		}
		else
			return false;
	}
	
	public boolean remManagertToLabel(User manager, Label label, User managerToRem)
	{
		if( manager.getRights().equals("LabelManager")  &&  label.isManager(manager)  &&
				managerToRem.getRights().equals("LabelManager")  &&  label.isManager(managerToRem) )
		{
			label.removeManager(managerToRem);
			DatabaseControl.getInstance().update(label);
			return true;
		}
		
		return false;
	}

}
