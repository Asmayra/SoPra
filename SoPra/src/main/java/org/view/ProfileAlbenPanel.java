package org.view;

import java.awt.Component;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import org.control.LoginControl;
import org.control.listener.AlbenTableListener;
import org.control.listener.PlaylistTableListener;
import org.control.listener.SongTableListener;
import org.model.Album;
import org.model.Playlist;
import org.model.Song;
import org.model.User;

public class ProfileAlbenPanel extends JScrollPane {

	private static String[] columns = new String[] { "Name", "Interpret", "#Lieder", "Favor", "ID" };
	private static Object[][] context;
	private static DefaultTableModel model;
	private static JTable table;

	public ProfileAlbenPanel(User u) {
		super(create(u));
		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	}

	private static JTable create(User u) {
		model = new DefaultTableModel(context, columns) {
			@Override
			public Class getColumnClass(int col) {
				if (col == 3)
					return Boolean.class;
				else
					return String.class;
			}
			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 3;
			}
		};
		table = new JTable();
		table.setModel(model);
		List<Album> alben = u.getAlben();
		for (int i = 0; i < alben.size(); i++) {
			String name = alben.get(i).getName();
			String interpret = alben.get(i).getInterpret();
			String songCount = ((Integer) alben.get(i).getSongs().size()).toString();
			boolean favor;
			if (LoginControl.getInstance().getCurrentUser().getAlben().contains(alben.get(i))) {
				System.out.println("true");
				favor = true;
			} else {
				System.out.println("false");
				favor = false;
			}
			int id = alben.get(i).getPlaylistId();
			Object[] entry = { name, interpret, songCount, favor, id };
			model.addRow(entry);
		}
		model.addTableModelListener(new AlbenTableListener());
		return table;
	}
}
