import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnect {

	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	final String DB_URL = "jdbc:mysql://localhost/gestionemploye";
	 
	final String USER = "root";
	final String PASS = "";
	
	public DBConnect(){
		try {
			Class.forName(JDBC_DRIVER);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() throws SQLException{
		return DriverManager.getConnection(DB_URL, USER, PASS);
	}

}
