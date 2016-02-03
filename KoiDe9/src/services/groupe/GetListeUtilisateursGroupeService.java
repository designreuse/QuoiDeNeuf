package services.groupe;

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

public class GetListeUtilisateursGroupeService extends AbstractService {

	public GetListeUtilisateursGroupeService() {
		this.requiredData = new String[]{TabAndCo.LISTE_USR_GROUP_UPARENT, TabAndCo.LISTE_USR_GROUP_IDGRP};
	}
	
	
	@Override
	public ResponseObject serviceLogic(RequestObject requestObject, ServletRequest servletRequest) {

		final int uparent = requestObject.getDataIntValue(TabAndCo.LISTE_USR_GROUP_UPARENT);
		final int idgrp = requestObject.getDataIntValue(TabAndCo.LISTE_USR_GROUP_IDGRP);
		
		final StringBuilder req = new StringBuilder("SELECT * FROM listusrgroup l, users u WHERE l.ufils = u.numu AND l.uparent = ? AND l.idgrp = ? ORDER BY u.nom ASC, u.login ASC;");
				
		final Connection connection = ServiceUtils.getConnection();
				
		try {
			final PreparedStatement pst = connection.prepareStatement(req.toString());
			pst.setInt(1, uparent);
			pst.setInt(2, idgrp);
			
			final ResultSet rs = pst.executeQuery();
			final List<Utilisateur> liste = MappingBddToBeans.resultsetToListUtilisateur(rs);
			if(liste.size() == 0){
				this.responseObject.setResponseData(ResponseObject.RETURN_CODE_WARNING, "Aucun utilisateur dans ce groupe.", liste);			
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
