package services.groupe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletRequest;

import app.utils.BddUtils;
import app.utils.ServiceUtils;
import app.utils.TabAndCo;
import services.core.AbstractService;
import services.objects.RequestObject;
import services.objects.ResponseObject;

public class SupprimerUtilisateurDunGroupeService extends AbstractService {

	public SupprimerUtilisateurDunGroupeService() {
		this.requiredData = new String[]{TabAndCo.LISTE_USR_GROUP_UPARENT, TabAndCo.LISTE_USR_GROUP_UFILS, TabAndCo.LISTE_USR_GROUP_IDGRP};
	}
	
	
	@Override
	public ResponseObject serviceLogic(RequestObject requestObject, ServletRequest servletRequest) {
		final int uparent = requestObject.getDataIntValue(TabAndCo.LISTE_USR_GROUP_UPARENT);
		final int ufils = requestObject.getDataIntValue(TabAndCo.LISTE_USR_GROUP_UFILS);
		final int idgrp = requestObject.getDataIntValue(TabAndCo.LISTE_USR_GROUP_IDGRP);
		
		if( BddUtils.existeDans(TabAndCo.TABLE_LISTE_USR_GROUP, " uparent = " +uparent+ " AND ufils = " +ufils+ " AND idgrp = " +idgrp + ";" )){
			
			final StringBuilder req = new StringBuilder("DELETE FROM listusrgroup WHERE uparent = ? AND ufils = ? AND idgrp = ?;");
			final Connection connection = ServiceUtils.getConnection();
			
			try {
				final PreparedStatement pst = connection.prepareStatement(req.toString());
			pst.setInt(1, uparent);
			pst.setInt(2, ufils);
			pst.setInt(3, idgrp);
			
			pst.executeUpdate();
			this.responseObject.setResponseData(ResponseObject.RETURN_CODE_OK, "Utilisateur supprimé avec succès.", null);
			} catch (SQLException e) {
				ServiceUtils.logger.error("Erreur exec SQL");
				e.printStackTrace();
			}finally {
				try { connection.close(); }
				catch (SQLException e) { ServiceUtils.logger.error("Erreur close connexion"); e.printStackTrace(); }
			}
		}else{
			this.responseObject.setResponseData(ResponseObject.RETURN_CODE_ERROR, "Cet utilisateur n'existe pas dans ce groupe.", null);
		
		}
		
		return this.responseObject;
		
	}

}
