package org.testpackage;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class Testframe extends JFrame {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Testframe frame = new Testframe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Testframe() {
		String curDir = System.getProperty("user.dir");
		String delimeter_symbol = System.getProperty("file.separator");
		String new_dir = curDir +delimeter_symbol+"Names.abf";
		String path= System.getProperty("java.class.path");
		String Data_Location = CurrentDir()+"Names.abf";
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 40, 400, 100);
		contentPane.add(scrollPane);
		
		JTextArea TextArea_output = new JTextArea();
		scrollPane.setViewportView(TextArea_output);
		TextArea_output.setText("Text Area with ways" +"\n");
		TextArea_output.append(path+"\n");
		TextArea_output.append(new_dir +"\n");
		TextArea_output.append(Data_Location);
		
	}
	/**
	 * 
	 * @return
	 */
	public static String CurrentDir(){
		String path=System.getProperty("java.class.path");
		String FileSeparator=(String)System.getProperty("file.separator");
		return path.substring(0, path.lastIndexOf(FileSeparator)+1);
	}
	
}
