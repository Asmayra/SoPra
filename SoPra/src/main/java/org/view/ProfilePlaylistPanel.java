package org.view;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ProfilePlaylistPanel extends JPanel {
	
	private String[] columns = new String[] {"Interpret","Title","Album","Länge","Community Rating","Favor"};
	private Object[][] songs;
	private DefaultTableModel model;
	private JTable table;

}
