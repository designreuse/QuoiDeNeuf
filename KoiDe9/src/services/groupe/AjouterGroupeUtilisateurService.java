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

/**
 * 
 * @author ZZE
 * Permet a l'utilisateur de creer un groupe
 * Le groupe doit prealablement existé, sinon utiliser 
 * @see EnregistrerGroupeService
 */
public class AjouterGroupeUtilisateurService extends AbstractService {

	public AjouterGroupeUtilisateurService() {
		this.requiredData = new String[]{TabAndCo.USERGROUPS_NUMU, TabAndCo.USERGROUPS_IDGRP};
	}
	
	
	@Override
	public ResponseObject serviceLogic(RequestObject requestObject, ServletRequest servletRequest) {
		final int numu = requestObject.getDataIntValue(TabAndCo.USERGROUPS_NUMU);
		final int idgrp = requestObject.getDataIntValue(TabAndCo.USERGROUPS_IDGRP);
		
		
		if(BddUtils.existeDans(TabAndCo.TABLE_USERGROUPS, " numu = " +numu+ " AND idgrp = " +idgrp+ ";")){
			this.responseObject.setResponseData(ResponseObject.RETURN_CODE_ERROR, "Ce groupe existe deja.", null);
		}else{
			
			final StringBuilder req = new StringBuilder("INSERT INTO usergroups VALUES(?,?);");
			final Connection connection = ServiceUtils.getConnection();
			try {
				final PreparedStatement pst = connection.prepareStatement(req.toString());
				pst.setInt(1, numu);
				pst.setInt(2, idgrp);
			
				pst.executeUpdate();
				this.responseObject.setResponseData(ResponseObject.RETURN_CODE_OK, "Groupe ajouté avec succès.", null);
			} catch (SQLException e) {
				ServiceUtils.logger.error("Erreur exec SQL");
				e.printStackTrace();
			}finally {
				try { connection.close(); }
				catch (SQLException e) { ServiceUtils.logger.error("Erreur close connexion"); e.printStackTrace(); }
			}
		
		
		
		}
		
		return this.responseObject;
		
			
			
		}
		
		
		
		
		


}
