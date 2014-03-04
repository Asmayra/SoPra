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
		//C:\Users\MI6\git\SoPra\SoPra\target\classes;C:\Users\MI6\.m2\repository\org\hibernate\hibernate-entitymanager\4.3.1.Final\hibernate-entitymanager-4.3.1.Final.jar;C:\Users\MI6\.m2\repository\org\jboss\logging\jboss-logging-annotations\1.2.0.Beta1\jboss-logging-annotations-1.2.0.Beta1.jar;C:\Users\MI6\.m2\repository\dom4j\dom4j\1.6.1\dom4j-1.6.1.jar;C:\Users\MI6\.m2\repository\xml-apis\xml-apis\1.0.b2\xml-apis-1.0.b2.jar;C:\Users\MI6\.m2\repository\org\hibernate\common\hibernate-commons-annotations\4.0.4.Final\hibernate-commons-annotations-4.0.4.Final.jar;C:\Users\MI6\.m2\repository\org\hibernate\javax\persistence\hibernate-jpa-2.1-api\1.0.0.Final\hibernate-jpa-2.1-api-1.0.0.Final.jar;C:\Users\MI6\.m2\repository\org\jboss\spec\javax\transaction\jboss-transaction-api_1.2_spec\1.0.0.Final\jboss-transaction-api_1.2_spec-1.0.0.Final.jar;C:\Users\MI6\.m2\repository\org\javassist\javassist\3.18.1-GA\javassist-3.18.1-GA.jar;C:\Users\MI6\.m2\repository\org\hibernate\hibernate-validator\5.1.0.CR1\hibernate-validator-5.1.0.CR1.jar;C:\Users\MI6\.m2\repository\javax\validation\validation-api\1.1.0.Final\validation-api-1.1.0.Final.jar;C:\Users\MI6\.m2\repository\com\fasterxml\classmate\1.0.0\classmate-1.0.0.jar;C:\Users\MI6\.m2\repository\net\sf\ehcache\ehcache-core\2.2.0\ehcache-core-2.2.0.jar;C:\Users\MI6\.m2\repository\org\slf4j\slf4j-api\1.5.11\slf4j-api-1.5.11.jar;C:\Users\MI6\.m2\repository\antlr\antlr\2.7.7\antlr-2.7.7.jar;C:\Users\MI6\.m2\repository\org\jboss\logging\jboss-logging\3.2.0.Beta1\jboss-logging-3.2.0.Beta1.jar;C:\Users\MI6\.m2\repository\org\hsqldb\hsqldb\2.3.2\hsqldb-2.3.2.jar;C:\Users\MI6\.m2\repository\org\hibernate\hibernate-core\4.3.1.Final\hibernate-core-4.3.1.Final.jar;C:\Users\MI6\.m2\repository\org\jboss\jandex\1.1.0.Final\jandex-1.1.0.Final.jar;C:\Users\MI6\.m2\repository\javax\media\jmf\2.1.1e\jmf-2.1.1e.jar;C:\Users\MI6\.m2\repository\org\postgresql\postgresql\9.3-1101-jdbc41\Names.abf
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
 	public static String CurrentDir(){
 		String path=System.getProperty("java.class.path");
 		String FileSeparator=(String)System.getProperty("file.separator");
 		return path.substring(0, path.lastIndexOf(FileSeparator)+1);
 	}
 	
 }