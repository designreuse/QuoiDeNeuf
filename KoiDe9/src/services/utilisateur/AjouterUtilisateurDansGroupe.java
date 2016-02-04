package services.utilisateur;

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

public class AjouterUtilisateurDansGroupe extends AbstractService {
	
	public AjouterUtilisateurDansGroupe() {
		super();
		this.requiredData = new String[]{TabAndCo.LISTE_USR_GROUP_UPARENT, TabAndCo.LISTE_USR_GROUP_UFILS, TabAndCo.LISTE_USR_GROUP_IDGRP};
	}
	
	
	
	@Override
	public ResponseObject serviceLogic(RequestObject requestObject, ServletRequest servletRequest) {
		final int uparent = requestObject.getDataIntValue(TabAndCo.LISTE_USR_GROUP_UPARENT);
		final int ufils = requestObject.getDataIntValue(TabAndCo.LISTE_USR_GROUP_UFILS);
		final int idgrp = requestObject.getDataIntValue(TabAndCo.LISTE_USR_GROUP_IDGRP);
		
		if(uparent == ufils){
			this.responseObject.setResponseData(ResponseObject.RETURN_CODE_ERROR, "Un utilisateur ne peut s'ajouter lui meme.", null);
		}else if( BddUtils.existeDans(TabAndCo.TABLE_LISTE_USR_GROUP, " uparent = " +uparent+ " AND ufils = " +ufils+ " AND idgrp = " +idgrp + ";" )){
			this.responseObject.setResponseData(ResponseObject.RETURN_CODE_ERROR, "Cet utilisateur existe deja dans le groupe sélectionné.", null);
		}else{
			
			final StringBuilder req = new StringBuilder("INSERT INTO listusrgroup VALUES(?,?,?);");
			
			final Connection connection = ServiceUtils.getConnection();
					
			try {
				final PreparedStatement pst = connection.prepareStatement(req.toString());
				pst.setInt(1, uparent);
				pst.setInt(2, ufils);
				pst.setInt(3, idgrp);
			
				pst.executeUpdate();
				this.responseObject.setResponseData(ResponseObject.RETURN_CODE_OK, "Utilisateur ajouté avec succès.", null);
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
