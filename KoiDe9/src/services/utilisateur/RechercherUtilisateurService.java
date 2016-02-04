package services.utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletRequest;

import app.utils.MappingBddToBeans;
import app.utils.ServiceUtils;
import app.utils.TabAndCo;
import services.beans.Utilisateur;
import services.core.AbstractService;
import services.objects.RequestObject;
import services.objects.ResponseObject;

public class RechercherUtilisateurService extends AbstractService{
	
	@Override
	public ResponseObject serviceLogic(RequestObject requestObject, ServletRequest servletRequest) {
		
		final Connection connection = ServiceUtils.getConnection();
		
		// Si l'utilisateur n'a rien mis dans le champ de recherche fait affiche toute la table users
		final String recherche = requestObject.getDataStringValue(TabAndCo.USERS_NOM);

		final StringBuilder req = new StringBuilder("SELECT * FROM users WHERE login LIKE ? OR nom LIKE ?;");
		
		try {
			final PreparedStatement pst = connection.prepareStatement(req.toString());
			pst.setString(1, "%"+recherche+"%");
			pst.setString(2, "%"+recherche+"%");

			final ResultSet rs = pst.executeQuery();
			
			final List<Utilisateur> liste = MappingBddToBeans.resultsetToListUtilisateur(rs);
			if(liste.isEmpty()){
				this.responseObject.setResponseData(ResponseObject.RETURN_CODE_WARNING, "Aucun utilisateur ne correspond a la recherche.", liste);
			}else{
				this.responseObject.setResponseData(ResponseObject.RETURN_CODE_OK, "", liste);	
			}

		} catch (SQLException e) {
			 ServiceUtils.logger.error("Erreur SQLException");
			e.printStackTrace();
		}finally {
			try { connection.close(); } 
			catch (SQLException e) { ServiceUtils.logger.error("Erreur close connexion"); e.printStackTrace(); }
		}
		
		return this.responseObject;
	}

	
}