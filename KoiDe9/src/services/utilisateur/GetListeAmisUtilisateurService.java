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

public class GetListeAmisUtilisateurService extends AbstractService {

	public GetListeAmisUtilisateurService() {
		this.requiredData = new String[]{TabAndCo.USERS_NUMU};
	}
	
	
	@Override
	public ResponseObject serviceLogic(RequestObject requestObject, ServletRequest servletRequest) {
		final int uparent = requestObject.getDataIntValue(TabAndCo.USERS_NUMU);
		
		final StringBuilder req = new StringBuilder("SELECT distinct u.* FROM listusrgroup l, users u WHERE l.ufils = u.numu AND l.uparent = ? ORDER BY u.nom ASC;");
		final Connection connection = ServiceUtils.getConnection();
		try {
			final PreparedStatement pst = connection.prepareStatement(req.toString());
			pst.setInt(1, uparent);
			
			final ResultSet rs = pst.executeQuery();
			final List<Utilisateur> liste = MappingBddToBeans.resultsetToListUtilisateur(rs);
			if(liste.size() == 0){
				this.responseObject.setResponseData(ResponseObject.RETURN_CODE_WARNING, "Cet utilisateur n'a pas d'amis.", liste);			
			}else{
				this.responseObject.setResponseData(ResponseObject.RETURN_CODE_OK, "", liste);
			}
			
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
