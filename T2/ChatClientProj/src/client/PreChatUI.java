package client;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class PreChatUI{
	private UserChat client;
	private server.IServerChat server;
	
	JLabel jlUser;
  	JTextField tx;
  	JTextField tf,ip, name;
  	JButton connect;
	JList<String> lst;
	JFrame frame;
	
	//User Interface code.
  	public PreChatUI(){
	    frame = new JFrame("UOL Bate Papo");
	    jlUser = new JLabel("Username:");
	    tx = new JTextField();
	    connect = new JButton("Conectar");
	    JScrollPane jsp = new JScrollPane();
	    jsp.setViewportView(lst);
	    
	    DefaultListModel<String> model = new DefaultListModel<>();
	    JPanel main = new JPanel();
	    
	    lst = new JList<String>(model);   
	    model.addElement("a");
	    model.addElement("b");
	    model.addElement("c");
	    model.addElement("d");
	    model.addElement("e");
	    model.addElement("e");
	    model.addElement("e");
	    model.addElement("e");
	    model.addElement("e");
	    model.addElement("e");
	    model.addElement("e");
	    model.addElement("e");
	    model.addElement("e");
	    model.addElement("e");
	    
	    jlUser.setBounds(5, 5, 100, 25);
	    tx.setBounds(75, 6, 153, 25);
	    lst.setBounds(5, 40, 223, 200);
	    //lst.setBounds(5, 40, 223, 200);
	    connect.setBounds(70, 250, 100, 30);
	    
	    main.add(jlUser);
	    main.add(tx);
	    main.add(lst);
	    //main.add(new JScrollPane(lst));
	    main.add(connect);
	    main.setLayout(null);         
	    
	    main.setBorder(new EmptyBorder(10, 10, 10, 10));
	    
	    //frame.setContentPane(main);
	    main.setBounds(0, 0, 400, 400);
	    frame.setLayout(null);
	    frame.add(main);
	    frame.setSize(250, 325);
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);  
  	}
	
  	public static void main(String [] args){
  		System.out.println("Yay");
  		@SuppressWarnings("unused")
		PreChatUI c = new PreChatUI();
  	}
}
