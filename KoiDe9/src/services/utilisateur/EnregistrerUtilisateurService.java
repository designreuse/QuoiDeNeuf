package services.utilisateur;

import java.sql.*;
import java.util.List;

import javax.print.ServiceUI;

import app.utils.MappingBddToBeans;
import app.utils.ServiceUtils;
import services.core.AbstractService;
import services.objects.RequestObject;
import services.objects.ResponseObject;
import services.objects.Utilisateur;

public class EnregistrerUtilisateurService extends AbstractService {

	private String[] requiredData = { Utilisateur.LOGIN, Utilisateur.MDP };

	@Override
	public ResponseObject execute(final RequestObject requestObject) {
		if (validate(requestObject, requiredData)) {
			// ici appel a la bdd & traitements

			final Connection con = ServiceUtils.getConnection();
			final Statement st;
			try {
				st = con.createStatement();
				ResultSet rs = st.executeQuery("select * from user;");
				List<Utilisateur> liste = MappingBddToBeans.resultsetToListUtilisateur(rs);
				this.responseObject.setResponseData(ResponseObject.RETURN_CODE_OK, "", liste);
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			this.responseObject.setResponseData(ResponseObject.RETURN_CODE_ERROR, "Champ requis manquant", null);
		}
		return this.responseObject;
	}

}
