package services.core;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import app.utils.ServiceUtils;
import services.objects.RequestObject;
import services.objects.ResponseObject;

@WebServlet("/coresvc")
public class CoreServiceServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {

		final RequestObject requestObject = (RequestObject) req.getAttribute("requestObject");
		final ResponseObject responseObject;
		if(requestObject == null){
			responseObject = new ResponseObject(ResponseObject.RETURN_CODE_ERROR, "Erreur: Flux JSON vide ou invalide.");
		}else{
			final AbstractService service = ServiceFactory.getServiceInstance(requestObject.getNomService());
			if(service == null){
				responseObject = new ResponseObject(ResponseObject.RETURN_CODE_ERROR, "Erreur: Service " +requestObject.getNomService()+ " introuvable.");
				ServiceUtils.logger.error("Service " +requestObject.getNomService()+ " introuvable.");
			}else{
				responseObject = service.execute(requestObject, req);
			}
		}
        
    	resp.setContentType("application/json");
    	resp.getWriter().write(responseObject.getJsonReponse());
	}
	
}
