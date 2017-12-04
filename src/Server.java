import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Server extends JFrame{
	
	public ServerSocket serverSocket;
	public Socket socket;
	public JButton listen;
	
	public Server(){}
	public Server(int width, int height, String title) throws IOException{
		
		serverSocket = new ServerSocket(1994);
		
		listen = new JButton("Listen");
		
		ServerActionHandler handler = new ServerActionHandler(){
			public void actionPerformed(ActionEvent eve){
				if(eve.getSource().equals(listen)){
					new Thread(()->{
						try {
							while(true){
								socket = serverSocket.accept();
								JOptionPane.showMessageDialog(new Server(), "client connected !");
								new ServerService(socket);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						finally{
							try {
								socket.close();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}).start();
				}
			}
		};
		setTitle(title);
		setSize(width, height);
		
		setLayout(new FlowLayout());
		getContentPane().add(listen);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		listen.addActionListener(handler);
	}

	public static void main(String[] args) {
		
		try{
			Server server = new Server(500,500,"ServerApp");		
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}

	}

}
