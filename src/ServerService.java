import java.awt.HeadlessException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.SQLException;

import javax.swing.JOptionPane;


public class ServerService extends Thread {
	
	private Socket socket;
	private DAOEnseignant actions;
	
	public ServerService(Socket _socket) throws ClassNotFoundException{
		socket = _socket;
		actions = new DAOEnseignant();
		start();
	}
	
	public Socket getSocket(){return socket;}
	
	public void run() {
		while(true){
			try {
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				Enseignant e = (Enseignant) ois.readObject();
				JOptionPane.showMessageDialog(new Server(), e.toString(),"message",1);
				actions.insertRecord(e);
			} 
			catch(HeadlessException|IOException|ClassNotFoundException e) {
				break;
			}
			catch(SQLException e){
				e.printStackTrace();
			}
			finally{
			}
		}
	}

}
