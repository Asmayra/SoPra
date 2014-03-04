package org.testpackage;


import java.awt.Dimension;
import java.util.LinkedList;

import javax.swing.JFrame;

import org.model.Genre;
import org.view.screens.Center.AdminGenreScreen;

public class GenreListTest{

	private static AdminGenreScreen genrescreen;

	public static void main(String[] args) {
		Genre root = new Genre();
		root.setName("root");
		Genre Rock = new Genre();
		Rock.setName("Rock");
		Genre Pop = new Genre();
		Pop.setName("Pop");
		Genre Metal = new Genre();
		Metal.setName("Metal");
		Genre Indierock = new Genre();
		Indierock.setName("Indierock");
		Genre DeathMetal = new Genre();
		DeathMetal.setName("Death Metal");
		
		DeathMetal.setParent(Metal);
		Indierock.setParent(Rock);
		Rock.addSubGenre(Indierock);
		Metal.addSubGenre(DeathMetal);
		root.addSubGenre(Rock);
		root.addSubGenre(Pop);
		root.addSubGenre(Metal);
		
		
		
		JFrame f = new JFrame("MarqueeTest");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		genrescreen = new AdminGenreScreen(root);
		f.add(genrescreen);
		f.setPreferredSize(new Dimension(500,500));
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
		
 	}
 	
 }