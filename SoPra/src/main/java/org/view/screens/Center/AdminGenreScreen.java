package org.view.screens.Center;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.control.DatabaseControl;
import org.control.listener.AdminGenreListener;
import org.model.Genre;
/**
 * @author Max Küper, Tim Michels
 */
public class AdminGenreScreen extends JPanel {
	private Genre root;
	private JTree genretree;
	private static List<Genre> genres = null;
	
	private static AdminGenreScreen instance = null;
	
	/**
	 * @return Instanz des AdminGenreScreens
	 */
	public static AdminGenreScreen getInstance()
	{
		if(instance == null){
			Genre emptyroot = null;
			genres = (List<Genre>) DatabaseControl.getInstance().getTableContent("Genre");
			if(genres==null || genres.size()==0){
				emptyroot = new Genre();
				emptyroot.setName("root");
				try {
					DatabaseControl.getInstance().save(emptyroot);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				for(int i=0;i<genres.size();i++){
					if(genres.get(i).getName().equals("root")){
						emptyroot=genres.get(i);
					}
				}
			}
			instance = new AdminGenreScreen(emptyroot);
		}
		return instance;
	}
	
	/**
	 * Konstruktor
	 * @param root Für Testzwecke noch im Konstruktor übergeben, Wurzel des Genretrees
	 */
	private AdminGenreScreen(Genre root){
		this.setLayout(new GridBagLayout());
		this.setRoot(root);
		DefaultMutableTreeNode top = new DefaultMutableTreeNode(root);
		createNodes(top, root);
		
		DefaultTreeModel dtm = new DefaultTreeModel(top){
			@Override
			public void valueForPathChanged(TreePath path, Object newValue){
				Object obj = ((DefaultMutableTreeNode)path.getLastPathComponent()).getUserObject();
				((Genre)obj).setName(newValue.toString());
				super.valueForPathChanged(path, obj);
			}
		};
		
		genretree = new JTree(dtm);
		AdminGenreListener listener = new AdminGenreListener();
		genretree.addMouseListener(listener);
		genretree.setEditable(true);
		genretree.setPreferredSize(new Dimension(300,400));
		GridBagConstraints c = new GridBagConstraints();
		c.fill=GridBagConstraints.BOTH;
		this.add(genretree,c);
	}
	
	/**
	 * Traversion über den Genrebaum, um den JTree zu erzeugen
	 * @param top
	 * @param topGenre
	 */
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
	
	/**
	 * Entfernt Element mit Index index aus JTree
	 * @param index Index des zu entfernenden Elements
	 */
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


