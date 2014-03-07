package org.view;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;


public class ContextMenu extends JPopupMenu {
    private JMenuItem[] items;
    
    public ContextMenu(String[] options, ActionListener listener[]){
    	items = new JMenuItem[options.length];
    	for(int i=0;i<options.length;i++){
    		items[i]= new JMenuItem(options[i]);
    		items[i].addActionListener(listener[i]);
    		add(items[i]);
    	}
    }
    
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
