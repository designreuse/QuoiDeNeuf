package services.objects;

public class Utilisateur {
	 
	private int 	numu;
	private String 	login;
	private String 	mdp;
	private String 	nom; 
	private String 	photo; 
	private String 	description;
	private boolean infosPrivees;
	
	
	final public static String NUMU 	= "numu";
	final public static String LOGIN 	= "login";
	final public static String MDP 		= "mdp";
	final public static String NOM 		= "nom"; 
	final public static String PHOTO 	= "photo"; 
	final public static String DESCRIPTION  = "description";
	final public static String INFOSPRIVEES = "infosPrivees";
	
	
	public int getNumu() { return numu; } 
	public String getLogin() { return login; } 
	public String getMdp() { return mdp; } 
	public String getNom() { return nom; } 
	public String getDescription() { return description; } 
	public boolean isInfosPrivees() { return infosPrivees; } 
	public String getPhoto() { return photo; } 
	
	public void setNumu(int numu) { this.numu = numu; } 
	public void setLogin(String login) { this.login = login; } 
	public void setMdp(String mdp) { this.mdp = mdp; } 
	public void setNom(String nom) { this.nom = nom; } 
	public void setDescription(String description) { this.description = description; } 
	public void setInfosPrivees(boolean infosPrivees) { this.infosPrivees = infosPrivees; }
	public void setPhoto(String photo) { this.photo = photo; }
	


	
	 
	 
	 
	 
	
	
	
	
}
