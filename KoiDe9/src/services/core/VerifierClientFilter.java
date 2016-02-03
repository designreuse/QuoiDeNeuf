package services.core;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebFilter("/app/*")
public class VerifierClientFilter implements Filter {
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse resp = (HttpServletResponse) response;

        final HttpSession session = req.getSession();

        if ( session.getAttribute("dejaConnecte") == null ) {
            resp.sendRedirect( req.getContextPath() + "/index.jsp" );
        } else {
            chain.doFilter( req, resp );
        }
	}
	

	public void init(FilterConfig fConfig) throws ServletException {}

	public void destroy() {}
	
	
}
