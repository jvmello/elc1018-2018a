package room;

import java.rmi.Remote;

public interface IRoomChat extends Remote{

	public void sendMsg(String usrName, String msg);

	public void joinRoom(String usrName);

	public void leaveRoom(String usrName);

	public void closeRoom();
}
