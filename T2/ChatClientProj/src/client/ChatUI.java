package client;
import javax.swing.*;
import javax.swing.border.*;

import server.IServerChat;
import server.ServerChat;

import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.*;

public class ChatUI{
	private UserChat client;
	private server.IServerChat server;
	
  	JTextArea tx;
  	JTextField tf,ip, name;
  	JButton connect;
	JList<Object> lst;
	JFrame frame;
	
	//User Interface code.
  	public ChatUI(){
	    frame = new JFrame("UOL Bate Papo");
	    
	    JPanel main = new JPanel();
	    JPanel top = new JPanel();
	    JPanel cn = new JPanel();
	    JPanel bottom = new JPanel();
	    
	    ip = new JTextField();
	    tf = new JTextField();
	    name = new JTextField();
	    tx = new JTextArea();
	    connect = new JButton("Conectar");
	    
	    JButton bt = new JButton("Enviar");
	    
	    lst = new JList<Object>();        
	    
	    main.setLayout(new BorderLayout(5,5));         
	    top.setLayout(new GridLayout(1,0,5,5));   
	    cn.setLayout(new BorderLayout(5,5));
	    bottom.setLayout(new BorderLayout(5,5));
	    
	    top.add(new JLabel("Nome de usuário: "));top.add(name);    
	    top.add(new JLabel("Endereço IP: "));top.add(ip);
	    top.add(connect);
	    
	    cn.add(new JScrollPane(tx), BorderLayout.CENTER);        
	    cn.add(lst, BorderLayout.EAST);    
	    
	    bottom.add(tf, BorderLayout.CENTER);    
	    bottom.add(bt, BorderLayout.EAST);
	    
	    main.add(top, BorderLayout.NORTH);
	    main.add(cn, BorderLayout.CENTER);
	    main.add(bottom, BorderLayout.SOUTH);
	    
	    main.setBorder(new EmptyBorder(10, 10, 10, 10));

	    //Events
	    connect.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		doConnect();
	    	} 
	    });
	    bt.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		sendText();
	    	}
	    });
	    tf.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){ 
	    		sendText();
	    	} 
	    });
	    
	    frame.setContentPane(main);
	    frame.setSize(600,600);
	    frame.setVisible(true);  
  	}
	
	public void doConnect(){
	    if(connect.getText().equals("Conectar")){
	    	if(name.getText().length() < 2){
	    		JOptionPane.showMessageDialog(frame, "Digite um nome de usuário válido.");
	    		return;
	    	}
	    	if(ip.getText().length() < 2){
	    		JOptionPane.showMessageDialog(frame, "Digite um IP válido.");
	    		return;
	    	}	    	
	    	try{
				client=new UserChat(name.getText());
	    		client.setGUI(this);
				server=(server.IServerChat)Naming.lookup("rmi://"+ip.getText()+"/myabc");
				server.login(client);
				updateUsers(server.getConnected());
			    connect.setText("Desconectar");			    
	    	} catch(Exception e){
	    		e.printStackTrace();
	    		JOptionPane.showMessageDialog(frame, "ERRO: Conexão mal-sucedida.");
	    		}		  
	     } else{
	    	 updateUsers(null);
	    	 connect.setText("Conectar");
	    	 //Better to implement Logout ....
	     }
  	}  
  
	public void sendText(){
		if(connect.getText().equals("Conectar")){
			JOptionPane.showMessageDialog(frame, "Você precisa conectar-se primeiro.");
			return;	
		}
		String usrName = name.getText();
		String st = tf.getText();
		st="["+usrName+"] "+st;
		tf.setText("");
		//Remove if you are going to implement for remote invocation
		try{
			server.publish(st);
  	  	}catch(Exception e){
  	  		e.printStackTrace();
  	  	}
	}

	public void writeMsg(String st){
		tx.setText(tx.getText()+"\n"+st);
	}

	public void updateUsers(Vector<Object> v){
		DefaultListModel<Object> listModel = new DefaultListModel<Object>();
		if(v != null)
			for (int i = 0; i < v.size(); i++){
				try{
					String tmp = ((IUserChat)v.get(i)).getName();
					listModel.addElement(tmp);
				} catch(Exception e){
					e.printStackTrace();
				}
			}
		lst.setModel(listModel);
	}

  	public static void main(String [] args) throws RemoteException, MalformedURLException{
  		System.out.println("Yay");
  		@SuppressWarnings("unused")
		ChatUI c = new ChatUI();
  	}
}