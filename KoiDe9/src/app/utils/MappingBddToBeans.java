package app.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import services.beans.Discussion;
import services.beans.Groupe;
import services.beans.Message;
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

	public static List<Message> resultsetToListMessage(ResultSet rs) throws SQLException {
		final List<Message> liste = new ArrayList<Message>();
		
		while(rs.next()){
			final Message message = new Message();
			
			message.setIdmsg(rs.getInt(TabAndCo.MESSAGES_IDMSG));
			message.setDateenv(rs.getTimestamp(TabAndCo.MESSAGES_DATEENV));
			message.setMessage(rs.getString(TabAndCo.MESSAGES_CONTENU));
			liste.add(message);
		}
		return liste;
	}

	public static List<Discussion> resultsetToListDiscussion(ResultSet rs) throws SQLException {
		final List<Discussion> liste = new ArrayList<Discussion>();

		while(rs.next()){
			final Discussion discussion = new Discussion();
			final Utilisateur utilisateur = new Utilisateur();
			discussion.setIdmsg(rs.getInt(TabAndCo.MESSAGES_IDMSG));
			discussion.setDateenv(rs.getTimestamp(TabAndCo.MESSAGES_DATEENV));
			discussion.setUexp(rs.getInt(TabAndCo.USER_TO_USER_UEXP));
			discussion.setUdest(rs.getInt(TabAndCo.USER_TO_USER_UDEST));
			discussion.setContenu(rs.getString(TabAndCo.MESSAGES_CONTENU));
			
			utilisateur.setNumu(rs.getInt(TabAndCo.USERS_NUMU));
			utilisateur.setLogin(rs.getString(TabAndCo.USERS_LOGIN));
			utilisateur.setNom(rs.getString(TabAndCo.USERS_NOM));
			utilisateur.setPhoto(rs.getString(TabAndCo.USERS_PHOTO));
			utilisateur.setDescription(rs.getString(TabAndCo.USERS_DESCRIPTION));
			
			discussion.setUtilisateur(utilisateur);
			liste.add(discussion);
		}
		return liste;
	}
	
	
	
	
	
}
