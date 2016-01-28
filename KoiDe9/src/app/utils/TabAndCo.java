package app.utils;

public class TabAndCo {

	final public static String TABLE_USERS = "users";
	final public static String USERS_NUMU 	= "numu";
	final public static String USERS_LOGIN 	= "login";
	final public static String USERS_MDP 		= "mdp";
	final public static String USERS_NOM 		= "nom"; 
	final public static String USERS_PHOTO 	= "photo"; 
	final public static String USERS_DESCRIPTION  = "description";
	final public static String USERS_EMAIL 	= "email";
		
	public static String[] getRequiredFields(final String table){
		switch (table) {
		case TABLE_USERS : return new String[]{USERS_LOGIN, USERS_MDP};
			

		default: return null;
		}		
	}
	
	
	
	
	
	
}
