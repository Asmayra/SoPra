package org.view;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * Anzeige eines Kontextmenüs
 * @author Tim
 *
 */
public class ContextMenu extends JPopupMenu {
    private JMenuItem[] items;
    
    /**
     * Konstruktor für ein Kontextmenü ohne Submenüs
     * @param options Unterpunkte
     * @param listener Listener für diese Unterpunkte
     */
    public ContextMenu(String[] options, ActionListener listener[]){
    	items = new JMenuItem[options.length];
    	for(int i=0;i<options.length;i++){
    		items[i]= new JMenuItem(options[i]);
    		items[i].addActionListener(listener[i]);
    		add(items[i]);
    	}
    }
    
    /**
     * Konstruktor für ein Kontextmenü mit einem Submenü
     * @param options Unterpunkte
     * @param listener Listener der Unterpunkte
     * @param submenuName Submenüname
     * @param submenuOptions Unterpunte des Submenüs
     * @param submenuListener Listener für diese Unterpunkte
     */
    public ContextMenu(String[] options, ActionListener listener[], String submenuName, String[] submenuOptions, ActionListener submenuListener[]){
    	items = new JMenuItem[options.length];
    	for(int i=0;i<options.length;i++){
    		items[i]= new JMenuItem(options[i]);
    		items[i].addActionListener(listener[i]);
    		add(items[i]);
    	}
    	JMenu sub = new JMenu(submenuName);
    	items = new JMenuItem[submenuOptions.length];
    	for(int i=0;i<submenuOptions.length;i++){
    		items[i]= new JMenuItem(submenuOptions[i]);
    		items[i].addActionListener(submenuListener[i]);
    		sub.add(items[i]);
    	}
    	add(sub);
    }
}
