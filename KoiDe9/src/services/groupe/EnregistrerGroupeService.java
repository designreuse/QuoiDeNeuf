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

public class EnregistrerGroupeService extends AbstractService {

	public EnregistrerGroupeService() {
		this.requiredData = TabAndCo.getRequiredFields(TabAndCo.TABLE_GROUPS);
	}
	
	@Override
	public ResponseObject serviceLogic(RequestObject requestObject, ServletRequest servletRequest) {
		
		final String libelle = requestObject.getDataStringValue(TabAndCo.GROUPS_LIBELLE);
		
		boolean existeDeja = BddUtils.existeDans(TabAndCo.TABLE_GROUPS, TabAndCo.GROUPS_LIBELLE, libelle);

		if(existeDeja){
			this.responseObject.setResponseData(ResponseObject.RETURN_CODE_ERROR, "Le groupe " +libelle+ " existe deja.", null);
		}else{
			final Connection connection = ServiceUtils.getConnection();
			final StringBuilder req = new StringBuilder("INSERT INTO groups (libelle) VALUES (?); ");

			try {
				final PreparedStatement pst = connection.prepareStatement(req.toString());
				pst.setString(1, libelle);
				pst.executeUpdate();
				this.responseObject.setResponseData(ResponseObject.RETURN_CODE_OK, "Groupe "+ libelle + " créé.", null);
			} catch (SQLException e) {
				ServiceUtils.logger.error("Erreur SQLException");
				e.printStackTrace();
			}finally {
				try { connection.close();
				} catch (SQLException e) { ServiceUtils.logger.error("Erreur close connexion"); e.printStackTrace(); }
			}
		
		}
		
		return this.responseObject;
	}



}
