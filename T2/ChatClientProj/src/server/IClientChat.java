package server;
import java.rmi.*;

public interface IClientChat extends Remote{	
	public void tell(String name)throws RemoteException ;
	public String getName()throws RemoteException ;
}