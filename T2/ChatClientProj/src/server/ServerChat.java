package server;
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

import room.RoomChat;

@SuppressWarnings("serial")
public class ServerChat extends UnicastRemoteObject implements server.IServerChat{
	
	private Vector<Object> v = new Vector<Object>();
	ArrayList<RoomChat> roomList;
	
	public ServerChat() throws RemoteException{}
		
	public boolean login(client.IUserChat a) throws RemoteException{	
		System.out.println(a.getName() + " conectou-se.");	
		a.tell("Conexão bem-sucedida.");
		publish(a.getName()+ " conectou-se.");
		v.add(a);
		
		return true;
	}
	
	
	public void publish(String s) throws RemoteException{
	    System.out.println(s);
	    
		for(int i = 0; i < v.size(); i++){
		    try{
		    	IClientChat tmp = (IClientChat) v.get(i);
				tmp.tell(s);
		    } catch(Exception e){
		    	//problem with the client not connected.
		    	//Better to remove it
		    }
		}
	}

	public Vector<Object> getConnected() throws RemoteException{
		return v;
	}
	
	public ArrayList<RoomChat> getRooms() throws RemoteException{
		return roomList;
	}
	
	public void createRoom(String roomName) throws RemoteException, MalformedURLException{
		System.out.println("Sala " + roomName + " criada.");	
		//a.tell("Conexão bem-sucedida.");
		//publish(a.getName()+ " conectou-se.");
		//v.add(a);
		
		//return true;
  		IServerChat b = new ServerChat();	
		Naming.rebind("rmi://192.168.25.12/myabc", b);
	}
}
