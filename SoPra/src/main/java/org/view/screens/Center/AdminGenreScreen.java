package org.view.screens.Center;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import org.control.listener.AdminGenreListener;
import org.model.Genre;

public class AdminGenreScreen extends JPanel {
	private Genre root;
	private JTree genretree;
	
	private static AdminGenreScreen instance = null;
	
	public static AdminGenreScreen getInstance()
	{
		if(instance == null){
			Genre emptyroot = new Genre();
			instance = new AdminGenreScreen(emptyroot);
		}
		return instance;
	}
	
	public AdminGenreScreen(Genre root){
		this.setLayout(new GridBagLayout());
		this.setRoot(root);
		DefaultMutableTreeNode top = new DefaultMutableTreeNode(root);
		createNodes(top, root);
		genretree=new JTree(top);
		AdminGenreListener listener = new AdminGenreListener();
		genretree.addMouseListener(listener);
		genretree.setEditable(true);
		genretree.setPreferredSize(new Dimension(300,400));
		GridBagConstraints c = new GridBagConstraints();
		c.fill=GridBagConstraints.BOTH;
		this.add(genretree,c);
	}
	
	private void createNodes(DefaultMutableTreeNode top, Genre topGenre){
		int numberOfTopSubs = topGenre.getSubGenres().size();
		DefaultMutableTreeNode[] current = new DefaultMutableTreeNode[numberOfTopSubs];
		for(int i=0; i<numberOfTopSubs;i++){
			Genre currentGenre = topGenre.getSubGenres().get(i);
			current[i] = new DefaultMutableTreeNode(currentGenre);
			top.add(current[i]);
			createNodes(current[i], currentGenre);
		}
	}
	
	
	public int getNumofNodes(){
		return getNumberOfNodes(root);
	}
	
	private int getNumberOfNodes(Genre node){  
		int count = 1;
		for(int i=0; i<node.getSubGenres().size();i++){
			count+=getNumberOfNodes(node.getSubGenres().get(i));
	    }
	    return count;
	}
	
	public void deleteGenreFromTree(int index){
		genretree.remove(index);
	}
	
	
	public Genre getRoot() {
		return root;
	}

	public void setRoot(Genre root) {
		this.root = root;
	}
	
	public JTree getGenretree(){
		return genretree;
	}
}


