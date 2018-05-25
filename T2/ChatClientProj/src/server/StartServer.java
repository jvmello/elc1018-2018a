package server;
import java.rmi.*;
 
public class StartServer{
	public static void main(String[] args){
		try{
			java.rmi.registry.LocateRegistry.createRegistry(2020);
			 	
			IServerChat b = new ServerChat();
			IServerChat b2 = new ServerChat();
			IServerChat b3 = new ServerChat();
			//Naming.rebind("rmi://192.168.25.12/myabc", b);
	  		//IServerChat b = new ServerChat();	
			Naming.rebind("rmi://192.168.0.1/myabc", b);
			Naming.rebind("rmi://192.168.0.2/myabc", b2);
			Naming.rebind("rmi://192.168.0.3/myabc", b3);
			System.out.println("Servidor pronto!");
		} catch(Exception e){
			System.out.println("A ativação do servidor falhou: " + e);
		}
	}
}