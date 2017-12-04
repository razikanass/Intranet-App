import java.io.Serializable;
import java.util.ArrayList;


public class Departement implements Serializable {
	
	private String nomDep;
	private ArrayList<Enseignant> enseignList;
	
	public Departement(String _nomDep){
		nomDep = _nomDep;
	}
	
	public String getNomDep(){return nomDep;}
	public void setNomDep(String _nomDep){nomDep = _nomDep;}
	
	public ArrayList<Enseignant> getEnseignantList(){
		return enseignList;
	}

	public String toString() {
		return "Departement [nomDep=" + nomDep + "]";
	}

}
