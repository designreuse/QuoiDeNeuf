package app.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import services.objects.Utilisateur;

public class MappingBddToBeans {


	
	
	public static List<Utilisateur> resultsetToListUtilisateur(final ResultSet rs) throws SQLException {
		final List<Utilisateur> liste = new ArrayList<Utilisateur>();
				
		while(rs.next()){
			final Utilisateur utilisateur = new Utilisateur();
			utilisateur.setNumu(rs.getInt(Utilisateur.NUMU));
			utilisateur.setLogin(rs.getString(Utilisateur.LOGIN));
			utilisateur.setNom(rs.getString(Utilisateur.NOM));
			utilisateur.setMdp(rs.getString(Utilisateur.MDP));
			utilisateur.setPhoto(rs.getString(Utilisateur.PHOTO));
			utilisateur.setDescription(rs.getString(Utilisateur.DESCRIPTION));
			utilisateur.setInfosPrivees(rs.getBoolean(Utilisateur.INFOSPRIVEES));
			liste.add(utilisateur);
		}
		return liste;
	}
	
	
	
	
	
}
