package server;
import java.net.MalformedURLException;
import java.rmi.*;
import java.util.*;

import room.RoomChat;
import client.IUserChat;

public interface IServerChat extends Remote{
	public boolean login (IUserChat a)throws RemoteException ;
	public void publish (String s)throws RemoteException ;
	public Vector<Object> getConnected() throws RemoteException ;
	public ArrayList<RoomChat> getRooms() throws RemoteException;
	public void createRoom(String roomName) throws RemoteException, MalformedURLException;
}