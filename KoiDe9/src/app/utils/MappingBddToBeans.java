package app.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import services.beans.Groupe;
import services.beans.Utilisateur;

public class MappingBddToBeans {


	
	
	public static List<Utilisateur> resultsetToListUtilisateur(final ResultSet rs) throws SQLException {
		final List<Utilisateur> liste = new ArrayList<Utilisateur>();
				
		while(rs.next()){
			final Utilisateur utilisateur = new Utilisateur();
			utilisateur.setNumu(rs.getInt(TabAndCo.USERS_NUMU));
			utilisateur.setLogin(rs.getString(TabAndCo.USERS_LOGIN));
			utilisateur.setNom(rs.getString(TabAndCo.USERS_NOM));
			utilisateur.setEmail(rs.getString(TabAndCo.USERS_EMAIL));
			utilisateur.setPhoto(rs.getString(TabAndCo.USERS_PHOTO));
			utilisateur.setDescription(rs.getString(TabAndCo.USERS_DESCRIPTION));
			liste.add(utilisateur);
		}
		return liste;
	}

	public static List<Groupe> resultsetToListGroupe(final ResultSet rs) throws SQLException {
		final List<Groupe> liste = new ArrayList<Groupe>();
		
		while(rs.next()){
			final Groupe groupe = new Groupe();
			
			groupe.setIdgrp(rs.getInt(TabAndCo.GROUPS_IDGRP));
			groupe.setLibelle(rs.getString(TabAndCo.GROUPS_LIBELLE));
			liste.add(groupe);
		}
		return liste;
	}
	
	
	
	
	
}
