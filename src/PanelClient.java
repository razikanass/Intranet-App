import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class PanelClient extends JPanel {
	
	public JLabel ipAdress;
	public JLabel port;
	public JLabel nom;
	public JLabel prenom;
	public JLabel tel;
	public JLabel dep;
	public JTextField ipAdressVal;
	public JTextField portVal;
	public JTextField nomVal;
	public JTextField prenomVal;
	public JTextField telVal;
	public JComboBox<String> depVal;
	public JButton connect;
	public JButton send;
	public JPanel connectionPanel;
	public JPanel formPanel;
	public ClientActionHandler handler;
	public Socket socket;
	
	private void initComponents(){
		port = new JLabel("PORT");
		portVal = new JTextField();
		portVal.setColumns(5);
		ipAdress = new JLabel("IP ADRESS");
		ipAdressVal = new JTextField();
		ipAdressVal.setColumns(10);
		nom = new JLabel("NOM");
		nomVal = new JTextField();
		nomVal.setColumns(10);
		prenom = new JLabel("PRENOM");
		prenomVal = new JTextField();
		prenomVal.setColumns(10);
		tel = new JLabel("TEL");
		telVal = new JTextField();
		telVal.setColumns(10);
		dep = new JLabel("DEP");
		depVal = new JComboBox<String>(new String[]{"informatique","economique","mathématique"});
		connect = new JButton("CONNECT");
		send = new JButton("SEND");
		connectionPanel = new JPanel(new VerticalLayout());
		formPanel = new JPanel(new VerticalLayout());
	}
	
	public PanelClient(){
		
		initComponents();
		
		ClientActionHandler handler = new ClientActionHandler(){
			public void actionPerformed(ActionEvent eve){
				if(eve.getSource().equals(connect)){
					try {
						int port = Integer.parseInt(portVal.getText().toString());
						String ip = ipAdressVal.getText();
						new Thread(()->{
							try{
								makeConnection(ip, port);
								while(true){
									try {
										ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
										Enseignant enseignant = (Enseignant)ois.readObject();
										JOptionPane.showMessageDialog(new Client(), "message", enseignant.toString(), JOptionPane.INFORMATION_MESSAGE);
									} catch (Exception e) {
										e.printStackTrace();
										break;
									}
								}
							}
							catch (UnknownHostException e) {
								JOptionPane.showMessageDialog(new Client(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
							}catch (IOException e) {
								JOptionPane.showMessageDialog(new Client(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
							}
						}).start();
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(new Client(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				if(eve.getSource().equals(send)){
					try {
						ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
						Enseignant enseignant = new Enseignant(nomVal.getText(),
															   prenomVal.getText(),
															   telVal.getText(),
															   new Departement(depVal.getSelectedItem().toString()));
						System.out.println(enseignant.toString());
						oos.writeObject(enseignant);
					}
					catch (NumberFormatException|IOException e) {e.printStackTrace();}
				}
			}
		};
		
		JPanel panePort = new JPanel(new FlowLayout());
		JPanel paneIp = new JPanel(new FlowLayout());
		JPanel paneId = new JPanel(new FlowLayout());
		JPanel paneNom = new JPanel(new FlowLayout());
		JPanel panePrenom = new JPanel(new FlowLayout());
		JPanel paneTel = new JPanel(new FlowLayout());
		JPanel paneDep = new JPanel(new FlowLayout());
		
		panePort.add(port);
		panePort.add(portVal);
		paneIp.add(ipAdress);
		paneIp.add(ipAdressVal);
		paneNom.add(nom);
		paneNom.add(nomVal);
		panePrenom.add(prenom);
		panePrenom.add(prenomVal);
		paneTel.add(tel);
		paneTel.add(telVal);
		paneDep.add(dep);
		paneDep.add(depVal);
		
		connectionPanel.add(paneIp);
		connectionPanel.add(panePort);
		connectionPanel.add(connect);
		
		formPanel.add(paneId);
		formPanel.add(paneNom);
		formPanel.add(panePrenom);
		formPanel.add(paneTel);
		formPanel.add(paneDep);
		formPanel.add(send);
		
		setLayout(new BorderLayout());
		add(connectionPanel,BorderLayout.WEST);
		add(formPanel,BorderLayout.EAST);
		
		connect.addActionListener(handler);
		send.addActionListener(handler);
	}
	
	public void makeConnection(String ip, int port) throws UnknownHostException,IOException{
		socket = new Socket(ip, port);
	}

}
