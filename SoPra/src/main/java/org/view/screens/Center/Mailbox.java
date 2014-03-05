package org.view.screens.Center;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.control.MailboxControl;
import org.control.listener.MailboxDeleteMessageButtonListener;
import org.control.listener.MailboxNewMessageButtonListener;
import org.control.listener.MailboxReplyMessageButtonListener;
import org.view.LoginScreen;

/**
 * 
 * @author Michael Pfennings, Mattias Schoenke
 *
 */
public class Mailbox extends JPanel{
	
	private final int TABLE_WIDTH = 400;
	private final int TABLE_HEIGHT = 300;
	private JTable msgTable;
	private DefaultTableModel msgTableModel;
	
	public static Mailbox instance = null;
	
	private Mailbox(){
		initGui();
	}
	
	/**
	 * Singleton
	 * @return Instanz des Singletons
	 */
	public static Mailbox getInstance(){
		if(instance == null)
		{
			instance = new Mailbox();
		}
		
		return instance;
	}
	
	
	private void initGui(){
		this.setLayout(new BorderLayout());
		this.add(initTable(), BorderLayout.NORTH);
		this.add(initButtons(), BorderLayout.CENTER);
		this.setVisible(true);
		
		
	}
	
	
	private JComponent initTable(){
		msgTable = new JTable();
		msgTableModel = new DefaultTableModel(){
			public boolean isCellEditable(int rowIndex, int columnIndex){
				if(columnIndex == 3)
					return true;
				else
					return false;
			}
		};
		
		msgTable.setPreferredSize(new Dimension(TABLE_WIDTH, TABLE_HEIGHT));
		
		msgTable.setModel(msgTableModel);
		msgTableModel.addColumn("Absender");
		msgTableModel.addColumn("Betreff");
		msgTableModel.addColumn("Datum");
		msgTableModel.addColumn("Makiert");
		
		msgTable.getColumn("Makiert").setCellRenderer( new ButtonRenderer());
		msgTable.getColumn("Makiert").setCellEditor(new ButtonEditor(new JCheckBox()));
		
		
		JScrollPane tableScrollPane = new JScrollPane(msgTable);
		tableScrollPane.setPreferredSize(new Dimension(TABLE_WIDTH, TABLE_HEIGHT));
		return tableScrollPane;
		
		
	}
	
	private JComponent initButtons(){
		
		JPanel  buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton newButton = new JButton("Neu");
		newButton.addActionListener(new MailboxNewMessageButtonListener());
		
		JButton replyButton = new JButton("Antworten");
		replyButton.addActionListener(new MailboxReplyMessageButtonListener());
		
		JButton delButton = new JButton("löschen");
		delButton.addActionListener(new MailboxDeleteMessageButtonListener());
		
		
		buttonPanel.add(newButton);
		buttonPanel.add(replyButton);
		buttonPanel.add(delButton);
		return buttonPanel;
	}
	
	public void clearTable(){
		msgTableModel.setRowCount(0);
	}
	
	/**
	 * Fügt eine neue Zeile zur Tabelle hinzu
	 * @param sender Sender der Nachricht
	 * @param subject Betreff der Nachricht
	 * @param date Datum der Nachricht
	 */
	public void addRow(String sender, String subject, String date){
		String[] newRow = new String[3];
		newRow[0] = sender;
		newRow[1] = subject;
		newRow[2] = date;
		msgTableModel.addRow(newRow);
		
	}
	
	/**
	 * Löscht eine Zeile in der Tabelle
	 * @param id Index der Zeile die gelöscht werden soll
	 * @pre id ist gültiger index der Tabelle
	 * @post Zeile wurde von der Tabelle entfernt
	 */
	public void deleteRow(int id){
		msgTableModel.removeRow(id);
	}

	
	
	@SuppressWarnings("serial")
	class ButtonRenderer extends JButton implements TableCellRenderer {
		
		public ButtonRenderer()
		{
//			setOpaque(true);
		}
		
		public Component getTableCellRendererComponent( JTable table,
														Object value,
														boolean isSelected,
														boolean hasFocus,
														int row,
														int column	)
		{
//			if(isSelected)
//			{
//				setForeground(table.getSelectionForeground());
//				setBackground(table.getSelectionBackground());
//			}
//			else
//			{
//				setForeground(table.getForeground());
//				setBackground(UIManager.getColor("Button.background"));
//			}
			
//			setText((value == null) ? "" : value.toString());
			return this;
		}
	}
	
	@SuppressWarnings("serial")
	class ButtonEditor extends DefaultCellEditor
	{
		
		protected JButton button;
		
		private String label;
		private boolean isPushed;
		
		public ButtonEditor(JCheckBox checkBox)
		{
			super(checkBox);
			button = new JButton();
			button.setOpaque(true);
			button.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					fireEditingStopped();
					
				}
			});
		}
		
		public Component getTableCellEditorComponent( 	JTable table,
														Object value,
														boolean isSelected,
														int row,
														int column		)
		{
//			if(isSelected)
//			{
//				button.setForeground(table.getSelectionForeground());
//				button.setBackground(table.getSelectionBackground());
//			}
//			else
//			{
//				button.setForeground(table.getForeground());
//				button.setBackground(table.getBackground());
//			}
			
//			label = (value == null) ? "" : value.toString();
//			button.setText(label);
			isPushed = true;
			return button;
		}
		
		public Object getCellEditorValue()
		{
			if(isPushed)
			{
				if(MailboxControl.getInstance().markRow(msgTable.getSelectedRow()))
				{
					button.setForeground(Color.RED);
					button.setBackground(Color.RED);
					
					button.setText("test");
				}
				else
				{
					button.setForeground(Color.GREEN);
					button.setBackground(Color.GREEN);
				}
				button.repaint();
			}
			
			isPushed = false;
			return new String("");
		}
		
		public boolean stopCellEditing()
		{
			isPushed = false;
			return super.stopCellEditing();
		}
		
		protected void fireEditingStopped()
		{
			super.fireEditingStopped();
		}
	}
	
}

