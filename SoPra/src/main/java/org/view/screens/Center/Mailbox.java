package org.view.screens.Center;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
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
	
	private JTextField subjText;
	private JTextField senderText;
	private JTextArea contentArea;
	
	private JPanel messagePanel;
	
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
	
	/**
	 * Zerstört den Singleton
	 */
	public static void destroy()
	{
		instance = null;
	}
	
	/**
	 * Initialisiert die Gui
	 */
	
	private void initGui(){
		this.setLayout(new BorderLayout());
		this.add(initTable(), BorderLayout.NORTH);
		this.add(initButtons(), BorderLayout.CENTER);
		this.add(initMessageView(), BorderLayout.SOUTH);
		this.setVisible(true);
		
		
	}
	
	/**
	 * Erzeugt die Tabelle in der die empfangenen Nachrichten angezeigt werden.
	 * @return die JScrollPane in der die Tabelle ist
	 */
	
	private JComponent initTable(){
		msgTable = new JTable();
		
		msgTable.addMouseListener(new MouseListener());
		
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
		msgTableModel.addColumn("");
		
		msgTable.getColumn("").setMaxWidth(10);
		msgTable.getColumn("").setMinWidth(10);
		
		msgTable.getColumn("").setCellRenderer( new ButtonRenderer());
		msgTable.getColumn("").setCellEditor(new ButtonEditor(new JCheckBox()));
		
		
		JScrollPane tableScrollPane = new JScrollPane(msgTable);
		tableScrollPane.setPreferredSize(new Dimension(TABLE_WIDTH, TABLE_HEIGHT));
		return tableScrollPane;
		
		
	}
	
	/**
	 * Erzeugt das ButtonPanel mit den Buttons Neu, Antworten und löschen.
	 * @return das buttonPanel
	 */
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
	
	
	/**
	 * Erzeugt das MessagePanel in der die ausgewählte Nachricht angezeigt wird.
	 * @return das messagePanel
	 */
	public JComponent initMessageView()
	{
		messagePanel = new JPanel();
		messagePanel.setLayout(new BorderLayout());
		
		JPanel textFields = new JPanel();
		textFields.setLayout(new GridLayout(0,1));
		
		senderText = new JTextField();
		senderText.setEditable(false);
		
		subjText = new JTextField();
		subjText.setEditable(false);
		
		textFields.add(senderText);
		textFields.add(subjText);
		
		
		JPanel labels = new JPanel();
		labels.setLayout(new GridLayout(0,1));
		
		JLabel senderLabel = new JLabel("Sender: ");
		
		JLabel subjLabel = new JLabel("Betreff: ");
		
		labels.add(senderLabel);
		labels.add(subjLabel);
		
		contentArea = new JTextArea();
		contentArea.setEditable(false);
		contentArea.setPreferredSize(new Dimension(200, 200));
		contentArea.setLineWrap(true);
		contentArea.setWrapStyleWord(true);
		
		messagePanel.add(labels, BorderLayout.WEST);
		messagePanel.add(textFields, BorderLayout.CENTER);
		messagePanel.add(contentArea, BorderLayout.SOUTH);
		
		messagePanel.setVisible(false);
		
		
		
		return messagePanel;
	}
	
	
	/**
	 * leert die Tabelle
	 * @post Tabelle wurde geleert
	 */
	public void clearTable(){
		msgTableModel.setRowCount(0);
	}
	
	/**
	 * Fügt eine neue Zeile zur Tabelle hinzu
	 * @param sender Sender der Nachricht
	 * @param subject Betreff der Nachricht
	 * @param date Datum der Nachricht
	 * @pre kein Parameter null
	 * @post neue Zeile wurde hinzugefügt
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

	
	/**
	 * Fügt der letzten Spalte in der Tabelle CheckBoxen hinzu
	 */
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
			if(isSelected)
			{
				setForeground(table.getSelectionForeground());
				setBackground(table.getSelectionBackground());
			}
			else
			{
				setForeground(table.getForeground());
				setBackground(UIManager.getColor("Button.background"));
			}
			
			setText((value == null) ? "" : value.toString());
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
			isPushed = true;
			return button;
		}
		
		public Object getCellEditorValue()
		{
			if(isPushed)
			{
				isPushed = false;
				if(MailboxControl.getInstance().markRow(msgTable.getSelectedRow()))
				{
					return new String(".");
				}
			}
			
			
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
	
	
	class MouseListener extends MouseAdapter
	{
		public void mouseClicked(MouseEvent e)
		{
			if(msgTable.getSelectedColumn() < 3)
			{
				MailboxControl.getInstance().setCurMessage(msgTable.getSelectedRow());
				messagePanel.setVisible(true);
				
				contentArea.setText( MailboxControl.getInstance().getCurMessage().getContent() );
				senderText.setText( MailboxControl.getInstance().getCurMessage().getSender().getUsername() );
				subjText.setText( MailboxControl.getInstance().getCurMessage().getSubject() );
			}
		}
	}
}

