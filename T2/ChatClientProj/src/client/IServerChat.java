package client;
import java.rmi.*;
import java.util.*;

public interface IServerChat extends Remote{	
	public boolean login(IUserChat a)throws RemoteException;
	public void publish(String s)throws RemoteException;
	public Vector<Object> getConnected() throws RemoteException;
}