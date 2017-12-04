import java.io.Serializable;


public class Enseignant implements Serializable{
	
	private int id;
	private String nom;
	private String prenom;
	private String tel;
	private Departement dep;
	
	public Enseignant(String nom, String prenom, String tel, Departement dep) {
		this.nom = nom;
		this.prenom = prenom;
		this.tel = tel;
		this.dep = dep;
	}
	
	public int getId(){return id;}
	
	public String getNom(){return nom;}
	public void setNom(String nom){this.nom = nom;}
	
	public String getPrenom(){return prenom;}
	public void setPrenom(String prenom){this.prenom = prenom;}
	
	public String getTel(){return tel;}
	public void setTel(String tel){this.tel = tel;}

	public Departement getDep(){return dep;}
	public void setDep(Departement dep){this.dep = dep;}

	public String toString() {
		return "Enseignant [id=" + id + ", nom=" + nom + ", prenom=" + prenom
				+ ", tel=" + tel + ", nomDep=" + dep.toString() + "]";
	}
	
	

}
