import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DAOEnseignant {
	
	private DBConnect connection;
	
	public DAOEnseignant() throws ClassNotFoundException{
		connection = new DBConnect();
	}
	
	public void insertRecord(Enseignant e) throws SQLException{
		String sql = "INSERT INTO `enseignant`(`nom`, `prenom`, `tel`, `nomdep`) values(?,?,?,?)";
		PreparedStatement pstmt = connection.getConnection().prepareStatement(sql);
		pstmt.setString(1, e.getNom());
		pstmt.setString(2, e.getPrenom());
		pstmt.setString(3, e.getTel());
		pstmt.setString(4, e.getDep().getNomDep());
		pstmt.executeUpdate();
	}

}
