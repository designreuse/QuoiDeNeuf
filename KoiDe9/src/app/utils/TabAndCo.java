package app.utils;

public class TabAndCo {
	
	// Table users ============================================================
	final public static String TABLE_USERS = "users";
	final public static String USERS_NUMU 	= "numu";
	final public static String USERS_LOGIN 	= "login";
	final public static String USERS_MDP 		= "mdp";
	final public static String USERS_NOM 		= "nom"; 
	final public static String USERS_PHOTO 	= "photo"; 
	final public static String USERS_DESCRIPTION  = "description";
	final public static String USERS_EMAIL 	= "email";
	
	// Table groups ===========================================================
	public static final String TABLE_GROUPS   = "groups";
	public static final String GROUPS_IDGRP   = "idgrp";
	public static final String GROUPS_LIBELLE = "libelle";
	
	
	// Table usergroups =======================================================
	public static final String TABLE_USERGROUPS = "usergroups";
	public static final String USERGROUPS_NUMU  = "numu";
	public static final String USERGROUPS_IDGRP = "idgrp";
	
	
	
	// Table listeusrgroup ====================================================
	public static final String TABLE_LISTE_USR_GROUP   = "listeusrgroup";
	final public static String LISTE_USR_GROUP_UPARENT = "uparent";
	final public static String LISTE_USR_GROUP_UFILS   = "ufils";
	public static final String LISTE_USR_GROUP_IDGRP   = "idgrp";
	
	
	
	public static String[] getRequiredFields(final String table){
		switch (table) {
		case TABLE_USERS 	: return new String[]{USERS_LOGIN, USERS_MDP};
		case TABLE_GROUPS 	: return new String[]{GROUPS_LIBELLE}; 	

		default: return null;
		}		
	}
	
	
	
	
	
	
}
