package services.core;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import services.objects.RequestObject;
import services.objects.ResponseObject;

@WebServlet("/coresvc")
public class CoreServiceServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {

		final RequestObject requestObject = (RequestObject) req.getAttribute("requestObject");
                
        final AbstractService service = ServiceFactory.getServiceInstance(requestObject.getNomService());
        final ResponseObject responseObject = service.execute(requestObject);
        
    	resp.setContentType("application/json");
    	resp.getWriter().write(responseObject.getJsonReponse());
	}


	
	
	
}
