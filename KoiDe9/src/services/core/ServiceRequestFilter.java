package services.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.google.gson.JsonParseException;

import app.utils.ServiceUtils;
import services.objects.RequestObject;
import services.objects.ResponseObject;

/**
 * Servlet Filter implementation class RequestFilter
 */
@WebFilter("/coresvc")
public class ServiceRequestFilter implements Filter {


	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

		ServiceUtils.logger.info("Reception requete depuis : " +req.getRemoteAddr() + ":" + req.getRemotePort());
		final BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
		
		// On recupere le flux json
		final StringBuilder json = new StringBuilder();
        if(br != null){ json.append(br.readLine());}
        
        // On verifie que le flux json est valide
        try {
        	final RequestObject requestObject = RequestObject.jsonToRequestObject(json.toString());
            req.setAttribute("requestObject", requestObject);
        	chain.doFilter(req, resp);
        
        } catch (JsonParseException e) {
        	// On renvoie une reponse au client si le flux json est invalide
        	final ResponseObject responseObject = new ResponseObject(ResponseObject.RETURN_CODE_ERROR, "Flux json invalide");
        	
        	resp.setContentType("application/json");
        	resp.getWriter().write(responseObject.getJsonReponse());
        }
	}

	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {}

	@Override
	public void destroy() {}

}
