import javax.swing.JFrame;


public class Client extends JFrame {
	
	public PanelClient panel;
	
	public Client(){}
	
	public Client(int width, int height, String title){
		panel = new PanelClient();
		
		setTitle(title);
		setSize(width, height);
		getContentPane().add(panel);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		try{
			Client client = new Client(500,500,"ClientApp");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

}
