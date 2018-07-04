/*******************************************************************************
 * @author Manonegra
 * Date de creation : 7 juin 2018
 * A : 12:03:09
 *
 * PE_LabJSP_12_Braderie
 ******************************************************************************/
/**
 *
 */
package controllers.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Manonegra
 * Classe de journalisation implémentant l'interface Filter
 *
 */
@WebFilter(urlPatterns="/FC?action=login" )
public class Journal implements Filter{

	// Logger
	private static final Log log = LogFactory.getLog(Journal.class);

	private FilterConfig filterConfig = null;
	private String appli = "" ;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		appli = filterConfig.getInitParameter("PE_LabJSP_12_Braderie") ;

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			if (filterConfig == null)
				return;

			log.info ("Poste Hôte client : " + request.getRemoteHost());
			log.info ("Adresse IP du client : " + request.getRemoteAddr());
			log.info ("Numéro du port du client : " + request.getRemotePort());
			log.info ("Protocole : " + request.getProtocol());

			chain.doFilter(request, response);
			log.info("Time to execute request:"+(System.currentTimeMillis() - System.currentTimeMillis())+ " milliseconds");
		}
		catch (Throwable t) {
			log.error(t);
		}
	}

	@Override
	public void destroy() {
		filterConfig = null;

	}

}
