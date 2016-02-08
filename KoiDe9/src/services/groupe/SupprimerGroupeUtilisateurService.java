package services.groupe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletRequest;

import app.utils.ServiceUtils;
import app.utils.TabAndCo;
import services.core.AbstractService;
import services.objects.RequestObject;
import services.objects.ResponseObject;

public class SupprimerGroupeUtilisateurService extends AbstractService {
	public SupprimerGroupeUtilisateurService() {
		this.requiredData = new String[]{TabAndCo.LISTE_USR_GROUP_UPARENT, TabAndCo.LISTE_USR_GROUP_IDGRP}; 
	}
	@Override
	public ResponseObject serviceLogic(RequestObject requestObject, ServletRequest servletRequest) {
		final int uparent = requestObject.getDataIntValue(TabAndCo.LISTE_USR_GROUP_UPARENT);
		final int idgrp = requestObject.getDataIntValue(TabAndCo.LISTE_USR_GROUP_IDGRP);
		
		if(idgrp == 1 || idgrp == 2 || idgrp == 3 || idgrp == 4){
			this.responseObject.setResponseData(ResponseObject.RETURN_CODE_ERROR, "Les groupes par defaut (Amis, Autre, Famille, Pro) ne peuvent etre supprimés.", null);
			return this.responseObject;
		}
		
		
		final StringBuilder req1 = new StringBuilder("DELETE FROM listusrgroup WHERE uparent = ? AND idgrp = ?;");
		final StringBuilder req2 = new StringBuilder("DELETE FROM usergroups WHERE numu = ? AND idgrp = ?;");
		final Connection connection = ServiceUtils.getConnection();
			
		try {
			PreparedStatement pst = connection.prepareStatement(req1.toString());
			pst.setInt(1, uparent);
			pst.setInt(2, idgrp);
			
			pst.executeUpdate();
			
			pst = connection.prepareStatement(req2.toString());
			pst.setInt(1, uparent);
			pst.setInt(2, idgrp);
			pst.executeUpdate();
			
			this.responseObject.setResponseData(ResponseObject.RETURN_CODE_OK, "Groupe supprimé avec succès.", null);
		} catch (SQLException e) {
				ServiceUtils.logger.error("Erreur exec SQL");
				e.printStackTrace();
			}finally {
				try { connection.close(); }
				catch (SQLException e) { ServiceUtils.logger.error("Erreur close connexion"); e.printStackTrace(); }
		}
		
		return this.responseObject;
	}

}
