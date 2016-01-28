package services.utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import app.utils.MappingBddToBeans;
import app.utils.ServiceUtils;
import app.utils.TabAndCo;
import services.core.AbstractService;
import services.objects.RequestObject;
import services.objects.ResponseObject;
import services.objects.Utilisateur;

public class LireUtilisateurService extends AbstractService {

	public LireUtilisateurService() {
		this.requiredData = new String[]{TabAndCo.USERS_LOGIN};
	}
	
	
	
	@Override
	public ResponseObject serviceLogic(RequestObject requestObject) {
		final String login = requestObject.getDataStringValue(TabAndCo.USERS_LOGIN);
		
		final StringBuilder req = new StringBuilder("SELECT * FROM users WHERE login = ? ;");
		final Connection connection = ServiceUtils.getConnection();
		try {
			final PreparedStatement pst = connection.prepareStatement(req.toString());
			pst.setString(1, login);
			
			final ResultSet rs = pst.executeQuery();
			final List<Utilisateur> liste = MappingBddToBeans.resultsetToListUtilisateur(rs);
			
			this.responseObject.setResponseData(ResponseObject.RETURN_CODE_OK, "", liste);			
			
		}catch (SQLException e) {
			ServiceUtils.logger.error("Erreur SQLException");
			e.printStackTrace();
		}finally {
			try { connection.close(); } 
			catch (SQLException e) { ServiceUtils.logger.error("Erreur close connexion"); e.printStackTrace(); }
		}
		
		
		return this.responseObject;
	}

	
	
	
	
	
	
	
	
	
}
