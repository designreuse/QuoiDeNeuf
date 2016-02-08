package services.beans;

import java.sql.Timestamp;

public class Discussion {

	private int idmsg;
	private Timestamp dateenv;
	private int uexp;
	private int udest;
	private String contenu;
	private Utilisateur utilisateur;
	
	
	public int getIdmsg() {
		return idmsg;
	}
	public Timestamp getDateenv() {
		return dateenv;
	}
	public int getUexp() {
		return uexp;
	}
	public int getUdest() {
		return udest;
	}
	public String getContenu() {
		return contenu;
	}
	public void setIdmsg(int idmsg) {
		this.idmsg = idmsg;
	}
	public void setDateenv(Timestamp dateenv) {
		this.dateenv = dateenv;
	}
	public void setUexp(int uexp) {
		this.uexp = uexp;
	}
	public void setUdest(int udest) {
		this.udest = udest;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	
	
	
	
	
	
}
